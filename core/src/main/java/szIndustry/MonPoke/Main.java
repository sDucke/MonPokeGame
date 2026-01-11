package szIndustry.MonPoke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import szIndustry.MonPoke.utils.ui.TextManager;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.view.screens.MapScreen;
import szIndustry.MonPoke.view.screens.StartScreen;
import szIndustry.MonPoke.view.ui.menu.MenuScreen;
import szIndustry.MonPoke.view.ui.menu.PokemonList;

public class Main extends Game {

    public SpriteBatch batch;
    public Screens menu;
    public Screens pokemonList;
    public Screens map;
    public Screens sScreen;

    @Override
    public void create() {
        //global
        batch = new SpriteBatch();

        //cargamos la fuente
        TextManager.load();

        //importamos las pantallas
        menu = new MenuScreen(this);
        pokemonList = new PokemonList(this);
        map = new MapScreen(this);
        sScreen = new StartScreen(this);
        setScreen(sScreen);
    }

    // 2. Método para cambiar la pantalla
    public void changeScreen(Screen newScreen) {
        // Esto permite a cualquier pantalla (WorldScreen, BattleScreen) pedir al juego cambiar de estado.
        setScreen(newScreen);
    }
}
