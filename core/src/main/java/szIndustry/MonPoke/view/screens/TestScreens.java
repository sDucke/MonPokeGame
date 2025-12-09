package szIndustry.MonPoke.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

    private Player player;
    private VirtualController ctrl;

    private Stage stage;
    private Texture texBack;
    private Image btnBack;

    public TestScreens(Main game) {
        super(game);

        // ----------------------------------------------------
        // CONTROLADOR VIRTUAL DEL JUGADOR
        // ----------------------------------------------------
        ctrl = new VirtualController();

        // ----------------------------------------------------
        // PLAYER
        // ----------------------------------------------------
        player = new Player(200, 200);

        // ----------------------------------------------------
        // STAGE (para botones, UI)
        // ----------------------------------------------------
        stage = new Stage(new FitViewport(V_WIDTH, V_HEIGHT), batch);

        // ----------------------------------------------------
        // BOTÓN VOLVER
        // ----------------------------------------------------
        texBack = new Texture("ui/back_button.png");
        btnBack = new ScaleFunction().scaleImage(texBack, 60f);
        btnBack.setPosition(
            10,
            stage.getViewport().getWorldHeight() - btnBack.getHeight() - 10
        );

        btnBack.addListener(new ButtonEffects(actor -> game.setScreen(new MenuScreen(game))));
        stage.addActor(btnBack);

        // ----------------------------------------------------
        // INPUT SYSTEM
        // ----------------------------------------------------
        InputMultiplexer mux = new InputMultiplexer();
        mux.addProcessor(stage);                    // UI
        mux.addProcessor(new KeyboardInput(ctrl));  // Movimiento jugador

        Gdx.input.setInputProcessor(mux);
    }

    @Override
    public void render(float delta) {

        // ----------------------------------------------------
        // LIMPIAR PANTALLA
        // ----------------------------------------------------
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // ----------------------------------------------------
        // ACTUALIZAR JUGADOR
        // ----------------------------------------------------
        player.update(delta, ctrl);

        // ----------------------------------------------------
        // DIBUJAR MUNDO
        // ----------------------------------------------------
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();

        // ----------------------------------------------------
        // UI
        // ----------------------------------------------------
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        texBack.dispose();
    }
}
