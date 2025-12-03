package szIndustry.MonPoke.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.controller.player.KeyboardInput;
import szIndustry.MonPoke.controller.player.VirtualController;
import szIndustry.MonPoke.model.player.Player;
import szIndustry.MonPoke.view.Screens;

public class TestScreens extends Screens {

    public TestScreens(Main game) {
        super(game);
        // Crear controlador virtual
        ctrl = new VirtualController();

        // Crear player
        player = new Player(200, 200);

        // Activar input del teclado
        Gdx.input.setInputProcessor(new KeyboardInput(ctrl));
    }

    private Player player;
    private VirtualController ctrl;

    @Override
    public void render(float delta) {
        // Limpiar pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar lógica del jugador
        player.update(delta, ctrl);

        // Dibujar
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        // Libera recursos si usas texturas internas en Player
        // Aunque tu Player solo usa una textura, LibGDX lo limpia al cerrar el juego.
    }
}
