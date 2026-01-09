package szIndustry.MonPoke.model.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import szIndustry.MonPoke.controller.player.VirtualController;
import szIndustry.MonPoke.controller.map.MapManager;

public class Player {
    private float x, y;
    private float speed = 5f;

    private Texture texUp, texDown, texLeft, texRight;
    private Texture currentTexture;

    private final float WIDTH = 1f;
    private final float HEIGHT = 1f;
    private Rectangle bounds;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;

        texUp = new Texture("characters/Player/arriba.png");
        texDown = new Texture("characters/Player/abajo.png");
        texLeft = new Texture("characters/Player/izquierda.png");
        texRight = new Texture("characters/Player/derecha.png");

        currentTexture = texDown;
        // Usamos 0.8f para que la colisión sea ligeramente más pequeña que el sprite
        // Esto evita que el jugador se bloquee en pasillos estrechos
        bounds = new Rectangle(x, y, 0.8f, 0.8f);
    }

    public void update(float delta, VirtualController ctrl, MapManager mapManager) {
        float moveAmount = speed * delta;
        float oldX = x;
        float oldY = y;

        // 1. APLICAR MOVIMIENTO SEGÚN INPUT
        if (!ctrl.usarJoystick) {
            if (ctrl.isUp) { y += moveAmount; currentTexture = texUp; }
            else if (ctrl.isDown) { y -= moveAmount; currentTexture = texDown; }

            if (ctrl.isLeft) { x -= moveAmount; currentTexture = texLeft; }
            else if (ctrl.isRight) { x += moveAmount; currentTexture = texRight; }
        } else {
            x += ctrl.joyX * moveAmount;
            y += ctrl.joyY * moveAmount;

            if (Math.abs(ctrl.joyX) > Math.abs(ctrl.joyY)) {
                currentTexture = (ctrl.joyX > 0) ? texRight : texLeft;
            } else if (Math.abs(ctrl.joyY) > 0.1f) {
                currentTexture = (ctrl.joyY > 0) ? texUp : texDown;
            }
        }

        // 2. SISTEMA DE COLISIÓN DESLIZANTE
        // Probar X
        bounds.setPosition(x, oldY + 0.1f); // +0.1f es un pequeño offset para centrar el bound
        if (mapManager.isColliding(bounds)) {
            x = oldX;
        }

        // Probar Y
        bounds.setPosition(x, y + 0.1f);
        if (mapManager.isColliding(bounds)) {
            y = oldY;
        }

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
