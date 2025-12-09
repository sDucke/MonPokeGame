package szIndustry.MonPoke.view.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.utils.ui.ScaleFunction;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.utils.ui.ButtonEffects;

public class SelectPlayer extends Screens {

    private Stage stage;
    private Texture bgTexture;

    public SelectPlayer(Main game) {
        super(game);

        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        // ===== BACKGROUND =====
        bgTexture = new Texture("backgrounds/bgPlayerSelect.png");
        Image bg = new Image(bgTexture);
        bg.setSize(V_WIDTH, V_HEIGHT);
        stage.addActor(bg);

        // ===== CARGAR PERSONAJES =====
        Texture p1Tex = new Texture("characters/player1.png");
        Texture p2Tex = new Texture("characters/playerG1.png");

        Image player1 = new ScaleFunction().scaleImage(p1Tex, 380f);
        Image player2 = new ScaleFunction().scaleImage(p2Tex, 380f);

        float spacing = 80f;
        float centerX = V_WIDTH / 2f;

        player1.setPosition(centerX - player1.getWidth() - spacing,
            (V_HEIGHT - player1.getHeight()) / 2f);

        player2.setPosition(centerX + spacing,
            (V_HEIGHT - player2.getHeight()) / 2f);

        // ===== APLICAR EFECTO ButtonEffects =====
        player1.addListener(new ButtonEffects(actor -> {
            System.out.println("Seleccionaste a: Tony");
        }));

        player2.addListener(new ButtonEffects(actor -> {
            System.out.println("Seleccionaste: PLAYER_2");
            // game.setScreen(new BattleScreen(game, "PLAYER_2"));
        }));

        stage.addActor(player1);
        stage.addActor(player2);

        // ===== BOTÓN VOLVER =====
        Texture backTexture = new Texture("ui/back_button.png");
        Image btnBack = new ScaleFunction().scaleImage(backTexture, 120f);
        btnBack.setPosition(10, viewport.getWorldHeight() - btnBack.getHeight() - 10);

        // Usamos ButtonEffects también para el botón volver
        btnBack.addListener(new ButtonEffects(actor -> game.setScreen(new MenuScreen(game))));

        stage.addActor(btnBack);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        bgTexture.dispose();
        super.dispose();
    }
}
