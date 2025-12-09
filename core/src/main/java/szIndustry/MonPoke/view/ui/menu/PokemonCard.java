package szIndustry.MonPoke.view.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.model.pokemon.PokemonModel;
import szIndustry.MonPoke.utils.ui.ButtonEffects;

public class PokemonCard extends Table {

    private final Main game;
    private final PokemonModel model;

    public PokemonCard(Main game, PokemonModel model) {
        this.game = game;
        this.model = model;

        build();
        addButtonEffect();
    }

    private void build() {

        // ===== FONDO DE LA CARD =====
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

    /**
     * Uso DIRECTO del sistema ButtonEffects
     * Reemplaza cualquier efecto manual anterior.
     */
    private void addButtonEffect() {
        this.addListener(new ButtonEffects(actor -> {
            game.setScreen(new PokemonDetailScreen(game, model));
        }));
    }
}
