package szIndustry.MonPoke.utils.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ScaleFunction {

    public ScaleFunction() {}

    public Image scaleImage(Texture tex, float desiredWidth) {
        Image img = new Image(tex);
        float scale = desiredWidth / tex.getWidth();
        img.setSize(desiredWidth, tex.getHeight() * scale);

        return img;
    }

    //Escala una textura a un tamaño deseado, devolviendo una nueva imagen escalada con un alto específico.
    public Image scaleImageByHeight(Texture tex, float desiredHeight) {
        Image img = new Image(tex);
        float scale = desiredHeight / tex.getHeight();
        img.setSize(tex.getWidth() * scale, desiredHeight);

        return img;
    }
}
