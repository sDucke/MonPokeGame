package szIndustry.MonPoke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.screens.MapScreen;
import szIndustry.MonPoke.view.ui.menu.MenuScreen;
import szIndustry.MonPoke.view.ui.menu.PokemonList;

public class Main extends Game {

    // Aquí puedes declarar variables globales como el AssetManager
    // private AssetManager assetManager;
    public Screens menu;
    public Screens pokemonList;
    public Screens map;

    @Override
    public void create() {

        menu = new MenuScreen(this);
        pokemonList = new PokemonList(this);
        map = new MapScreen(this);
        setScreen(menu);
    }

    // 2. Método para cambiar la pantalla
    public void changeScreen(Screen newScreen) {
        // Esto permite a cualquier pantalla (WorldScreen, BattleScreen) pedir al juego cambiar de estado.
        setScreen(newScreen);
    }
}
