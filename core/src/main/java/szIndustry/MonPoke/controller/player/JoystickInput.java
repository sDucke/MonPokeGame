package szIndustry.MonPoke.controller.player;

public class JoystickInput {
    private final VirtualController ctrl;

    public JoystickInput(VirtualController ctrl) {
        this.ctrl = ctrl;
    }

    /** Llamar desde tu UI del joystick */
    public void onJoystickMove(float x, float y) {

        ctrl.usarJoystick = true;

        // Guardamos valores normalizados:
        ctrl.joyX = x; // -1 izquierda, +1 derecha
        ctrl.joyY = y; // -1 abajo, +1 arriba

        // Convertimos joystick a estados
        ctrl.isLeft = (x < -0.2f);
        ctrl.isRight   = (x >  0.2f);
        ctrl.isDown     = (y < -0.2f);
        ctrl.isUp    = (y >  0.2f);
    }

    /** Botón de interacción en móvil */
    public void onInteractPress() {
        ctrl.interactuar = true;
    }

    public void onInteractRelease() {
        ctrl.interactuar = false;
    }
}
