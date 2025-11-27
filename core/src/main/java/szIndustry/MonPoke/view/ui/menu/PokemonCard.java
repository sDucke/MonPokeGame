package szIndustry.MonPoke.view.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.model.pokemon.PokemonModel;

public class PokemonCard extends Table {

    private final Main game;
    private final PokemonModel model;

    public PokemonCard(Main game, PokemonModel model) {
        this.game = game;
        this.model = model;

        build();
        addListeners();
    }

    private void build() {
        // ===== FONDO DE LA TARJETA =====
        Texture bgTex = new Texture(Gdx.files.internal("ui/bgCard.png"));
        this.setBackground(new TextureRegionDrawable(bgTex));
        this.setSize(240, 330);
        this.pad(6);

        // ===== CONTENIDO =====
        Table content = new Table();
        this.add(content).expand().fill();

        // Imagen del Pokémon
        Image img = new Image(new Texture(Gdx.files.internal(model.imagePath)));
        img.setScaling(com.badlogic.gdx.utils.Scaling.fit);

        // Nombre del Pokémon
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label lbl = new Label(model.name, style);

        content.add(img).size(180, 180).row();
        content.add(lbl).padTop(10);
    }

    private void addListeners() {
        this.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setColor(0.9f, 0.9f, 0.9f, 1f); // efecto al presionar
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setColor(1f, 1f, 1f, 1f); // restaurar color
                game.setScreen(new PokemonDetailScreen(game, model));
            }

            public void enter(InputEvent event, float x, float y, int pointer, Actor from) {
                setColor(0.95f, 0.95f, 0.95f, 1f);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor to) {
                setColor(1f, 1f, 1f, 1f);
            }
        });
    }
}
