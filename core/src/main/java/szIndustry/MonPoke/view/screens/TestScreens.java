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
import szIndustry.MonPoke.controller.player.KeyboardInput;
import szIndustry.MonPoke.controller.player.VirtualController;
import szIndustry.MonPoke.model.player.Player;
import szIndustry.MonPoke.utils.ui.ButtonEffects;
import szIndustry.MonPoke.utils.ui.ScaleFunction;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.ui.menu.MenuScreen;

public class TestScreens extends Screens {

    private TiledMap map;
    private AssetManager manager;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    // ESCALA VITAL: Convierte 32px en 1 unidad.
    // Esto hace que el mapa mida 90x90 unidades, igual que el Player(1x1)
    private final float UNIT_SCALE = 1 / 32f;

    private Player player;
    private VirtualController ctrl;
    private Stage stage;
    private Texture texBack;
    private Image btnBack;

    public TestScreens(Main game) {
        super(game);

        // 1. CARGAR MAPA
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load("Maps/test.tmx", TiledMap.class);
        manager.finishLoading();
        map = manager.get("Maps/test.tmx", TiledMap.class);
        // Esto imprimirá en la consola cuántas capas encontró
        System.out.println("Capas detectadas: " + map.getLayers().getCount());

        // Esto verificará si la imagen del tileset está cargada
        if (map.getTileSets().getTileSet(0) != null) {
            System.out.println("Tileset cargado correctamente");
        }

        // 2. RENDERIZADOR ESCALADO
        renderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);

        // 3. CÁMARA (Ver 20 tiles de ancho)
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 20 * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));

        // 4. JUGADOR (Posicionado en coordenadas de TILE)
        ctrl = new VirtualController();
        // Ponemos al jugador en el tile 10, 10 para asegurar que esté dentro del mapa
        player = new Player(10, 10);

        // Sincronizamos la cámara inmediatamente con el jugador
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        // 5. UI STAGE (Píxeles 800x480)
        stage = new Stage(new FitViewport(V_WIDTH, V_HEIGHT));

        texBack = new Texture("ui/back_button.png");
        btnBack = new ScaleFunction().scaleImage(texBack, 60f);
        btnBack.setPosition(10, V_HEIGHT - btnBack.getHeight() - 10);
        btnBack.addListener(new ButtonEffects(actor -> game.setScreen(new MenuScreen(game))));
        stage.addActor(btnBack);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new KeyboardInput(ctrl)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // ACTUALIZACIÓN
        player.update(delta, ctrl);
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        // RENDER MUNDO (MAPA + PERSONAJE)
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch); // Dibujará 1x1 unidades sobre los tiles escalados
        batch.end();

        // RENDER UI
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        manager.dispose();
        renderer.dispose();
        player.dispose();
        stage.dispose();
        texBack.dispose();
    }
}
