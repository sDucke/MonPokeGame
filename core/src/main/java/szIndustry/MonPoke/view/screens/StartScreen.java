package szIndustry.MonPoke.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.utils.ui.TextManager;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.ui.menu.MenuScreen;

public class StartScreen extends Screens {

    private float stateTime;

    public StartScreen(Main game) {
        super(game);
        this.stateTime = 0f;
        TextManager.load();
    }

    @Override
    public void render(float delta) {
        // 1. Fondo Blanco
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta;

        // 2. Renderizado estático
        game.batch.begin();

        // Aseguramos que el batch use el tamaño de la ventana
        game.batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Calculamos el centro de la pantalla
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        // Dibujamos el texto:
        // - Escala 4.0f (Mucho más grande)
        // - Posición centrada (restamos un margen aproximado para el ancho del texto)
        TextManager.draw(
            game.batch,
            "Luminar Labs",
            centerX - 250,
            centerY,
            Color.MAGENTA,
            2.0f
        );

        game.batch.end();

        // Cambio de pantalla tras 3 segundos o toque
        if (stateTime > 5f || Gdx.input.justTouched()) {
            game.setScreen(new MenuScreen(game));
        }
    }
}
