package szIndustry.MonPoke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.screens.MenuScreen;

public class Main extends Game {

    // Aquí puedes declarar variables globales como el AssetManager
    // private AssetManager assetManager;
    public Screens menu;

    @Override
    public void create() {
        // Podrías inicializar el AssetService o AudioService aquí.

        menu = new MenuScreen(this);
        setScreen(menu);
    }

    // 2. Método para cambiar la pantalla
    public void changeScreen(Screen newScreen) {
        // Esto permite a cualquier pantalla (WorldScreen, BattleScreen) pedir al juego cambiar de estado.
        setScreen(newScreen);
    }
}
