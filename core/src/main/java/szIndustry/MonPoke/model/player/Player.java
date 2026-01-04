package szIndustry.MonPoke.model.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import szIndustry.MonPoke.controller.player.VirtualController;

public class Player {
    private float x, y;
    private float speed = 5f; // 8 unidades (tiles) por segundo

    private Texture texUp, texDown, texLeft, texRight;
    private Texture currentTexture;

    private final float WIDTH = 1f;  // 1 Tile de ancho
    private final float HEIGHT = 1f; // 1 Tile de alto
    private Rectangle bounds;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;

        texUp = new Texture("characters/Player/arriba.png");
        texDown = new Texture("characters/Player/abajo.png");
        texLeft = new Texture("characters/Player/izquierda.png");
        texRight = new Texture("characters/Player/derecha.png");

        currentTexture = texDown;
        bounds = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void update(float delta, VirtualController ctrl) {
        float moveAmount = speed * delta;

        if (!ctrl.usarJoystick) {
            if (ctrl.moverArriba) {
                y += moveAmount;
                currentTexture = texUp;
            } else if (ctrl.moverAbajo) {
                y -= moveAmount;
                currentTexture = texDown;
            }

            if (ctrl.moverIzquierda) {
                x -= moveAmount;
                currentTexture = texLeft;
            } else if (ctrl.moverDerecha) {
                x += moveAmount;
                currentTexture = texRight;
            }
        } else {
            x += ctrl.joyX * moveAmount;
            y += ctrl.joyY * moveAmount;

            if (Math.abs(ctrl.joyX) > Math.abs(ctrl.joyY)) {
                currentTexture = (ctrl.joyX > 0) ? texRight : texLeft;
            } else if (Math.abs(ctrl.joyY) > 0.1f) {
                currentTexture = (ctrl.joyY > 0) ? texUp : texDown;
            }
        }
        // Actualizar la posición de la colisión
        bounds.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(currentTexture, x, y, WIDTH, HEIGHT);
    }

    public void dispose() {
        texUp.dispose(); texDown.dispose();
        texLeft.dispose(); texRight.dispose();
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
