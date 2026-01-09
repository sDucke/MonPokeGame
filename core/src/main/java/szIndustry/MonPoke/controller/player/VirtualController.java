package szIndustry.MonPoke.controller.player;

public class VirtualController {

    // Movimiento básico
    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;

    // Interacción con objetos/NPC
    public boolean interactuar;

    // Movimiento por joystick (móvil)
    public float joyX = 0f; // -1 izquierda / +1 derecha
    public float joyY = 0f; // -1 abajo / +1 arriba

    // Si el joystick está activo debe ignorar teclado
    public boolean usarJoystick = false;

    public void reset() {
        isUp = isDown = isLeft = isRight = false;
        interactuar = false;
    }
}
