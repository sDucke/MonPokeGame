package szIndustry.MonPoke.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.utils.ui.ButtonEffects;
import szIndustry.MonPoke.utils.ui.ScaleFunction;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.ui.menu.MenuScreen;

public class MapScreen extends Screens {

    // --- MAPA Y RENDER ---
    private TiledMap map;
    private AssetManager manager;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    // --- UI ---
    private Stage stage;
    private Texture texBack;
    private Image btnBack;

    public MapScreen(Main game) {
        super(game);

        // 1. CARGA DEL MAPA CON ASSET MANAGER (Como en el mapa 2)
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        // Asegúrate de que el archivo esté en assets/TiledMaps/
        manager.load("Maps/tilemap.tmx", TiledMap.class);
        manager.finishLoading();
        map = manager.get("Maps/tilemap.tmx", TiledMap.class);

        // 2. CONFIGURAR RENDERIZADOR
        // LibGDX detectará automáticamente si el mapa usa CSV o Base64/Zlib
        renderer = new OrthogonalTiledMapRenderer(map);

        // 3. CONFIGURAR CÁMARA DEL MUNDO
        // Usamos una vista de 400x240 para ver los tiles de 32px con buen tamaño
        camera = new OrthographicCamera(400, 240);
        // Posicionamos en 200,120 para ver la esquina inferior izquierda (0,0)
        camera.position.set(200f, 120f, 0);
        camera.update();

        // 4. CONFIGURAR UI (Stage independiente con su propio Viewport)
        stage = new Stage(new FitViewport(V_WIDTH, V_HEIGHT));

        // 5. BOTÓN REGRESAR
        texBack = new Texture("ui/back_button.png");
        btnBack = new ScaleFunction().scaleImage(texBack, 60f);
        // Posicionamiento basado en coordenadas de píxeles (arriba a la izquierda)
        btnBack.setPosition(10, V_HEIGHT - btnBack.getHeight() - 10);
        btnBack.addListener(new ButtonEffects(actor -> game.setScreen(new MenuScreen(game))));
        stage.addActor(btnBack);

        // 6. GESTIÓN DE INPUT
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Color de limpieza (Azul cielo para depuración)
        Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // --- DIBUJAR MAPA ---
        camera.update();
        renderer.setView(camera);
        renderer.render();

        // --- DIBUJAR UI ---
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Actualiza el viewport de la UI para que los botones no se muevan de lugar
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        // Liberar recursos al cambiar de pantalla
        dispose();
    }

    @Override
    public void dispose() {
        if (manager != null) manager.dispose();
        if (renderer != null) renderer.dispose();
        if (stage != null) stage.dispose();
        if (texBack != null) texBack.dispose();
    }
}
