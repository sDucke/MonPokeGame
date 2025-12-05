package szIndustry.MonPoke.view.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.model.pokemon.PokemonModel;
import szIndustry.MonPoke.utils.ui.ButtonEffects;
import szIndustry.MonPoke.utils.ui.ScaleFunction;
import szIndustry.MonPoke.view.Screens;

public class PokemonList extends Screens {

    private Stage stage;

    public PokemonList(Main game) { super(game); }

    @Override
    public void show() {

        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        // ===== FONDO =====
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        p.setColor(Color.valueOf("0D0F12"));
        p.fill();
        Image bg = new Image(new Texture(p));
        p.dispose();
        bg.setFillParent(true);
        stage.addActor(bg);

        // ===== BOTÓN VOLVER =====
        Texture backTexture = new Texture("ui/back_button.png");
        Image btnBack = new ScaleFunction().scaleImage(backTexture, 120f);
        btnBack.setPosition(10, viewport.getWorldHeight() - btnBack.getHeight() - 10);

        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnBack.addListener(new ButtonEffects(actor -> game.setScreen(new MenuScreen(game))));// ← back
            }
        });

        stage.addActor(btnBack);

        // ===== GRID DE POKÉMON =====
        Table root = new Table();
        root.setFillParent(true);
        root.top().padTop(90);
        stage.addActor(root);

        Table cards = new Table().top();
        cards.defaults().pad(15);

        int COLS = 3;

        for (int i = 1; i <= 12; i++) {

            PokemonModel model = new PokemonModel(
                "Pokémon " + i,
                "pkm/primalGroudon.png",
                120, 150, 80, 95,
                "Fire Burst", "Earth Power"
            );

            cards.add(new PokemonCard(game, model))
                .width(240).height(330);

            if (i % COLS == 0) cards.row();
        }

        ScrollPane scroll = new ScrollPane(cards);
        scroll.setScrollingDisabled(true, false);

        root.add(scroll).expand().fill();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
