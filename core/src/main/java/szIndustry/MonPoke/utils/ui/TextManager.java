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
            // Cargamos la fuente
            font = new BitmapFont(Gdx.files.internal("font/main.fnt"));

            // Filtros para que no se pixele al escalarlo a 4.0f
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            // IMPORTANTE: Desactivamos el marcado de colores por si el archivo .fnt trae basura
            font.getData().markupEnabled = false;
        }
    }

    public static void draw(SpriteBatch batch, String msg, float x, float y, Color color, float scale) {
        load();

        // 1. Aseguramos que el Batch no tenga un tinte previo que bloquee el color
        batch.setColor(Color.WHITE);

        // 2. Aplicamos el color deseado a la fuente
        font.setColor(color);

        // 3. Aplicamos la escala
        font.getData().setScale(scale);

        // 4. Dibujamos
        font.draw(batch, msg, x, y);

        // 5. Reset del color de la fuente para la siguiente llamada (evita contaminación)
        font.setColor(Color.WHITE);
    }

    public static void dispose() {
        if (font != null) {
            font.dispose();
            font = null;
        }
    }
}
