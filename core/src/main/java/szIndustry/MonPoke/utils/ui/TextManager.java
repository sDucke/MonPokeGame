package szIndustry.MonPoke.utils.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextManager {
    public static BitmapFont font;

    public static void load() {
        if (font == null) {
            // Se recomienda usar un archivo .fnt generado para la resolución de tu juego
            font = new BitmapFont(Gdx.files.internal("font/main.fnt"));
            // Suaviza los bordes para que no se vea pixelado al escalar
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public static void draw(SpriteBatch batch, String msg, float x, float y, Color color, float scale) {
        load();
        font.setColor(color);
        // Ajusta la escala: 1f es tamaño original.
        // En mundos de 20 unidades, valores como 0.02f son correctos.
        font.getData().setScale(scale);
        font.draw(batch, msg, x, y);
    }

    public static void dispose() {
        if (font != null) font.dispose();
    }
}
