package szIndustry.MonPoke.model.pokemon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PokemonStatsBox extends Table {

    public PokemonStatsBox(PokemonModel model) {

        Texture pixel = generatePixel();

        setBackground(new TextureRegionDrawable(new TextureRegion(pixel)));
        setColor(0.12f, 0.14f, 0.18f, 1);
        pad(20);

        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        add(new Label("HP: " + model.hp, style)).left().row();
        add(new Label("ATK: " + model.attack, style)).left().row();
        add(new Label("DEF: " + model.defense, style)).left().row();
        add(new Label("SPD: " + model.speed, style)).left().padBottom(15).row();

        add(new Label("Habilidad 1: " + model.ability1, style)).left().row();
        add(new Label("Habilidad 2: " + model.ability2, style)).left().row();
    }

    private Texture generatePixel() {
        Pixmap p = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        p.setColor(Color.WHITE);
        p.fill();
        Texture t = new Texture(p);
        p.dispose();
        return t;
    }
}
