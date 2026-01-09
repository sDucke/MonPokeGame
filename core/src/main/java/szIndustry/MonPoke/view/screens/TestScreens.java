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
import szIndustry.MonPoke.model.NPC.NPC;
import szIndustry.MonPoke.model.player.Player;
import szIndustry.MonPoke.utils.ui.ButtonEffects;
import szIndustry.MonPoke.utils.ui.ScaleFunction;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.ui.menu.MenuScreen;

public class TestScreens extends Screens {

    private MapManager mapManager;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private final float UNIT_SCALE = 1 / 16f; // Tiles de 16px = 1 unidad

    private Player player;
    private VirtualController ctrl;
    private Stage stage;
    private Texture texBack;
    private Image btnBack;

    public TestScreens(Main game) {
        super(game);

        // 1. CARGA DEL MAPA Y COLISIONES
        mapManager = new MapManager("Maps/tilemap.tmx", UNIT_SCALE);
        renderer = new OrthogonalTiledMapRenderer(mapManager.getMap(), UNIT_SCALE);

        // 2. CÁMARA DEL MUNDO (Ver 20 tiles de ancho)
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 20 * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));

        // 3. JUGADOR
        ctrl = new VirtualController();
        player = new Player(10, 10);

        // 4. UI STAGE (Píxeles fijos)
        stage = new Stage(new FitViewport(V_WIDTH, V_HEIGHT));
        texBack = new Texture("ui/back_button.png");
        btnBack = new ScaleFunction().scaleImage(texBack, 60f);
        btnBack.setPosition(10, V_HEIGHT - btnBack.getHeight() - 10);
        btnBack.addListener(new ButtonEffects(actor -> game.setScreen(new MenuScreen(game))));
        stage.addActor(btnBack);

        // 5. GESTIÓN DE INPUT
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new KeyboardInput(ctrl)));
    }

    @Override
    public void render(float delta) {
        // Limpieza de pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // --- 1. ACTUALIZACIÓN DE LÓGICA ---
        player.update(delta, ctrl, mapManager);
        for (NPC npc : mapManager.getNpcs()) {
            npc.update(delta, player);
        }

        // Seguimiento de cámara
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        // --- 2. RENDER MUNDO (Coordenadas de Tiles/Unidades) ---
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();

        // --- 3. RENDER UI Y ETIQUETAS (Coordenadas de Pantalla/Píxeles) ---
        // IMPORTANTE: Reiniciamos la matriz para que el texto sea nítido y de tamaño correcto
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.begin();
        for (NPC npc : mapManager.getNpcs()) {
            // Este método debe usar camera.project() internamente
            npc.drawInteractionLabel(batch, camera);
        }
        batch.end();

        // Dibujado del Stage de Scene2D
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        // Ajustar la cámara del mundo para mantener la proporción
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
