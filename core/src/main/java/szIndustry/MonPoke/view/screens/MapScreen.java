package szIndustry.MonPoke.view.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.model.player.Player;
import szIndustry.MonPoke.view.Screens;

public class MapScreen extends Screens {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player playerModel;

    public MapScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {
        playerModel = new Player(5, 5); // Instancia el Modelo

        // Configuración de la cámara para tu mundo 2D (ej. 16x16 tiles)
        camera = new OrthographicCamera();
        // ... set viewport y setToOrtho ...

        batch = new SpriteBatch();

        // 4. Configurar el InputProcessor (el Controlador):

    }

    @Override
    public void render(float delta) {
        // 1. Limpiar, 2. Actualizar cámara, 3. Llamar al MapRenderer (luego), 4. Dibujar el Player.
    }
    // ... dispose() para liberar batch y otros recursos ...
}
