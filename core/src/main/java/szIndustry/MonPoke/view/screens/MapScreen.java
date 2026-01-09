package szIndustry.MonPoke.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.controller.player.KeyboardInput;
import szIndustry.MonPoke.controller.player.VirtualController;
import szIndustry.MonPoke.controller.map.MapManager;
import szIndustry.MonPoke.model.player.Player;
import szIndustry.MonPoke.utils.ui.ButtonEffects;
import szIndustry.MonPoke.utils.ui.ScaleFunction;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.ui.menu.MenuScreen;

public class MapScreen extends Screens {

    // --- LÓGICA Y MUNDO ---
    private MapManager mapManager;
    private Player player;
    private VirtualController ctrl;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    // ESCALA: 1 unidad = 32 píxeles (Importante para que coincida con MapManager)
    private final float UNIT_SCALE = 1 / 16f;

    // --- UI ---
    private Stage stage;
    private Texture texBack;
    private Image btnBack;

    public MapScreen(Main game) {
        super(game);

        // 1. CARGAR MAPA A TRAVÉS DEL MANAGER
        // El Manager ahora se encarga de cargar el .tmx y procesar colisiones
        mapManager = new MapManager("Maps/tilemap.tmx", UNIT_SCALE);

        // 2. RENDERIZADOR ESCALADO
        renderer = new OrthogonalTiledMapRenderer(mapManager.getMap(), UNIT_SCALE);

        // 3. CÁMARA DEL MUNDO (Ver 20 tiles de ancho)
        camera = new OrthographicCamera();
        float aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        camera.setToOrtho(false, 20, 20 * aspectRatio);

        // 4. JUGADOR Y CONTROLES
        ctrl = new VirtualController();
        player = new Player(10, 10); // Inicia en el tile 10,10

        // 5. CONFIGURAR UI (Mantiene píxeles para botones nítidos)
        stage = new Stage(new FitViewport(V_WIDTH, V_HEIGHT));
        texBack = new Texture("ui/back_button.png");
        btnBack = new ScaleFunction().scaleImage(texBack, 60f);
        btnBack.setPosition(10, V_HEIGHT - btnBack.getHeight() - 10);
        btnBack.addListener(new ButtonEffects(actor -> game.setScreen(new MenuScreen(game))));
        stage.addActor(btnBack);

        // 6. INPUT MULTIPLEXER (Para que funcionen el botón y las teclas a la vez)
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new KeyboardInput(ctrl));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // --- ACTUALIZACIÓN ---
        // Pasamos el mapManager al player para checar colisiones
        player.update(delta, ctrl, mapManager);

        // La cámara sigue al jugador
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        // --- RENDER MUNDO ---
        renderer.setView(camera);
        renderer.render();

        // Dibujar Player usando la escala del mundo
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();

        // --- RENDER UI ---
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        // Actualizar cámara del mundo si es necesario
        camera.viewportWidth = 20;
        camera.viewportHeight = 20 * ((float) height / width);
        camera.update();
    }

    @Override
    public void dispose() {
        mapManager.dispose();
        renderer.dispose();
        player.dispose();
        stage.dispose();
        texBack.dispose();
    }
}
