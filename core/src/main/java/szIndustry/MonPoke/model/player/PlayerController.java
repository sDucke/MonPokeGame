package szIndustry.MonPoke.model.player;

public class PlayerController {
    private final Player playerModel;

    public PlayerController(Player player) {
        this.playerModel = player;
    }

    public boolean keyDown(int keycode) {
        // Detectar tecla (W, A, S, D)
        // Llamar a playerModel.startMovement(direction)
        return true;
    }
}
