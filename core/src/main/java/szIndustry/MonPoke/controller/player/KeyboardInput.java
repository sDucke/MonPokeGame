package szIndustry.MonPoke.controller.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KeyboardInput extends InputAdapter {

    private final VirtualController ctrl;

    public KeyboardInput(VirtualController ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public boolean keyDown(int keycode) {

        // Si estás usando joystick, ignora teclado
        if (ctrl.usarJoystick) return false;

        switch (keycode) {

            case Input.Keys.W:
            case Input.Keys.UP:
                ctrl.moverArriba = true;
                return true;

            case Input.Keys.S:
            case Input.Keys.DOWN:
                ctrl.moverAbajo = true;
                return true;

            case Input.Keys.A:
            case Input.Keys.LEFT:
                ctrl.moverIzquierda = true;
                return true;

            case Input.Keys.D:
            case Input.Keys.RIGHT:
                ctrl.moverDerecha = true;
                return true;

            case Input.Keys.E: // botón de interactuar
                ctrl.interactuar = true;
                return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (ctrl.usarJoystick) return false;

        switch (keycode) {

            case Input.Keys.W:
            case Input.Keys.UP:
                ctrl.moverArriba = false;
                return true;

            case Input.Keys.S:
            case Input.Keys.DOWN:
                ctrl.moverAbajo = false;
                return true;

            case Input.Keys.A:
            case Input.Keys.LEFT:
                ctrl.moverIzquierda = false;
                return true;

            case Input.Keys.D:
            case Input.Keys.RIGHT:
                ctrl.moverDerecha = false;
                return true;

            case Input.Keys.E:
                ctrl.interactuar = false;
                return true;
        }

        return false;
    }
}
