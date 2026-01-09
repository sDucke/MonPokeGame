package szIndustry.MonPoke.view.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import szIndustry.MonPoke.utils.ui.TextManager;

public class DialogueManager {
    private String text = "";
    private boolean visible = false;

    public void show(String text) {
        this.text = text;
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    public void draw(SpriteBatch batch) {
        if (visible) {
            TextManager.draw(batch, text, 2, 2, com.badlogic.gdx.graphics.Color.WHITE, 0.015f);
        }
    }
}
