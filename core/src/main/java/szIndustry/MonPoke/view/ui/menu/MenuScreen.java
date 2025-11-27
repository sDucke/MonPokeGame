package szIndustry.MonPoke.view.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.view.Screens;
import szIndustry.MonPoke.utils.ui.ButtonEffects;

public class MenuScreen extends Screens {

    private Stage stage;

    private Image bgImage, bannerImage, trainerImage;
    private Image playImage, loadImage, mapImage, listImage;

    public MenuScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        float width = V_WIDTH;
        float height = V_HEIGHT;

        // ===== Texturas =====
        Texture bgTexture = new Texture("backgrounds/bg3.png");
        Texture bannerTexture = new Texture("ui/banner_titulo.png");
        Texture trainerTexture = new Texture("ui/box_trainer.png");
        Texture listTexture = new Texture("ui/list_button.png");
        Texture loadTexture = new Texture("ui/load_button.png");
        Texture mapTexture = new Texture("ui/map_button.png");
        Texture playTexture = new Texture("ui/play_button.png");

        // ===== Crear imágenes y escalar =====
        bgImage = new Image(bgTexture);
        bgImage.setSize(width, height);

        bannerImage = scaleImage(bannerTexture, 700f);
        bannerImage.setPosition((width - bannerImage.getWidth()) / 2f, height - 600);

        trainerImage = scaleImage(trainerTexture, 340f);
        trainerImage.setPosition(5, height - trainerImage.getHeight() - 10);

        playImage = new Image(playTexture);
        playImage.setPosition((width - 700f) / 2f + 80, height - 500);
        playImage.addListener(new ButtonEffects(actor -> System.out.println("Iniciando nueva partida...")));

        listImage = new Image(listTexture);
        listImage.setPosition((width - 700f) / 2f + 700 - listTexture.getWidth() - 25, height - 300);
        listImage.addListener(new ButtonEffects(actor -> game.setScreen(new PokemonList(game))));

        loadImage = new Image(loadTexture);
        loadImage.setPosition(listImage.getX(), listImage.getY() - listTexture.getHeight() - 20);
        loadImage.addListener(new ButtonEffects(actor -> System.out.println("Cargando partida...")));

        mapImage = new Image(mapTexture);
        mapImage.setPosition(listImage.getX(), listImage.getY() - listTexture.getHeight() - loadTexture.getHeight() - 40);
        mapImage.addListener(new ButtonEffects(actor -> System.out.println("Abriendo mapa...")));

        // Trainer para seleccionar personaje
        trainerImage.addListener(new ButtonEffects(actor -> game.setScreen(new SelectPlayer(game))));

        // ===== Añadir todos los actores =====
        stage.addActor(bgImage);
        stage.addActor(bannerImage);
        stage.addActor(trainerImage);
        stage.addActor(playImage);
        stage.addActor(listImage);
        stage.addActor(loadImage);
        stage.addActor(mapImage);
    }

    // =======================
    // Método auxiliar para escalar imágenes
    // =======================
    private Image scaleImage(Texture t, float desiredWidth) {
        Image img = new Image(t);
        float scale = desiredWidth / t.getWidth();
        img.setSize(desiredWidth, t.getHeight() * scale);
        return img;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        super.dispose();
    }
}
