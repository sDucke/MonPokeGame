package szIndustry.MonPoke.model.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import szIndustry.MonPoke.controller.player.VirtualController;

public class Player {

    private float x, y;
    private float speed = 140f;

    private Texture texture;
    private float width, height;

    private Rectangle bounds;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;

        // CARGA UNA IMAGEN COMPLETA NORMAL (no spritesheet)
        texture = new Texture("characters/player1.png");

        width = texture.getWidth();
        height = texture.getHeight();

        bounds = new Rectangle(x, y, width, height);
    }

    // --------------------------------------------------------
    // UPDATE
    // --------------------------------------------------------
    public void update(float delta, VirtualController ctrl) {

        float moveSpeed = speed * delta;

        // Movimiento con teclado
        if (!ctrl.usarJoystick) {
            if (ctrl.moverArriba)    y += moveSpeed;
            if (ctrl.moverAbajo)     y -= moveSpeed;
            if (ctrl.moverIzquierda) x -= moveSpeed;
            if (ctrl.moverDerecha)   x += moveSpeed;
        }

        // Movimiento con joystick (solo magnitud)
        else {
            x += ctrl.joyX * moveSpeed;
            y += ctrl.joyY * moveSpeed;
        }

        // Actualizar hitbox
        bounds.setPosition(x, y);
    }

    // --------------------------------------------------------
    // DIBUJAR
    // --------------------------------------------------------
    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
