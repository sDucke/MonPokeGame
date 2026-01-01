package szIndustry.MonPoke.model.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import szIndustry.MonPoke.controller.player.VirtualController;

public class Player {

    private float x, y;
    private float speed = 140f;

    // Texturas para cada dirección
    private Texture texUp, texDown, texLeft, texRight;
    private Texture currentTexture; // La que se está dibujando actualmente

    private float width, height;
    private Rectangle bounds;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;

        // Carga de las 4 imágenes individuales
        texUp = new Texture("characters/Player/arriba.png");
        texDown = new Texture("characters/Player/abajo.png");
        texLeft = new Texture("characters/Player/izquierda.png");
        texRight = new Texture("characters/Player/derecha.png");

        // Empezamos mirando hacia abajo por defecto
        currentTexture = texDown;

        width = currentTexture.getWidth();
        height = currentTexture.getHeight();

        bounds = new Rectangle(x, y, width, height);
    }

    public void update(float delta, VirtualController ctrl) {
        float moveSpeed = speed * delta;

        // --- MOVIMIENTO Y CAMBIO DE TEXTURA ---
        if (!ctrl.usarJoystick) {
            if (ctrl.moverArriba) {
                y += moveSpeed;
                currentTexture = texUp;
            } else if (ctrl.moverAbajo) {
                y -= moveSpeed;
                currentTexture = texDown;
            }

            if (ctrl.moverIzquierda) {
                x -= moveSpeed;
                currentTexture = texLeft;
            } else if (ctrl.moverDerecha) {
                x += moveSpeed;
                currentTexture = texRight;
            }
        }
        else {
            // Movimiento con joystick
            x += ctrl.joyX * moveSpeed;
            y += ctrl.joyY * moveSpeed;

            // Determinar textura según el eje predominante del joystick
            if (Math.abs(ctrl.joyX) > Math.abs(ctrl.joyY)) {
                currentTexture = (ctrl.joyX > 0) ? texRight : texLeft;
            } else if (Math.abs(ctrl.joyY) > 0.1f) { // Umbral pequeño para evitar cambios erráticos
                currentTexture = (ctrl.joyY > 0) ? texUp : texDown;
            }
        }

        // Actualizar hitbox
        bounds.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        // Dibujamos siempre la textura actual
        batch.draw(currentTexture, x, y, width, height);
    }

    // Es importante liberar la memoria de las texturas
    public void dispose() {
        texUp.dispose();
        texDown.dispose();
        texLeft.dispose();
        texRight.dispose();
    }

    public Rectangle getBounds() { return bounds; }
    public float getX() { return x; }
    public float getY() { return y; }
}
