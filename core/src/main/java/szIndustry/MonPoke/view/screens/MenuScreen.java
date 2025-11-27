package szIndustry.MonPoke.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.view.Screens;

public class MenuScreen extends Screens {

    private Sprite bgSprite;
    private Sprite bannerSprite;
    private Sprite trainerSprite;
    private Sprite playButton;
    private Sprite loadButton;
    private Sprite mapButton;
    private Sprite listButton;

    int width, height;

    public MenuScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        // Texturas
        Texture bgTexture = new Texture("bg3.png");
        Texture bannerTexture = new Texture("banner_titulo.png");
        Texture trainerTexture = new Texture("box_trainer.png");
        Texture listTexture = new Texture("list_button.png");
        Texture loadTexture = new Texture("load_button.png");
        Texture mapTexture = new Texture("map_button.png");
        Texture playTexture = new Texture("play_button.png");

        // Sprites
        bgSprite = new Sprite(bgTexture);
        bannerSprite = new Sprite(bannerTexture);
        trainerSprite = new Sprite(trainerTexture);
        listButton = new Sprite(listTexture);
        loadButton = new Sprite(loadTexture);
        mapButton = new Sprite(mapTexture);
        playButton = new Sprite(playTexture);

        // ===== FONDO =====
        bgSprite.setSize(width, height);

        // ===== BANNER =====
        float originalW = bannerTexture.getWidth();
        float originalH = bannerTexture.getHeight();
        float desiredWidth = 700;
        float scale = desiredWidth / originalW;
        float desiredHeight = originalH * scale;

        bannerSprite.setSize(desiredWidth, desiredHeight);
        float bannerX = (width - desiredWidth) / 2f;
        float bannerY = height - desiredHeight - 5;
        bannerSprite.setPosition(bannerX, bannerY);

        // ===== TRAINER LEFT BOX =====
        trainerSprite.setSize(400, 500);
        trainerSprite.setPosition(10, height - 350);

        // ===== ESCALA BOTONES =====
        float buttonScale = 0.75f;
        float spacing = 8;

        playButton.setSize(playButton.getWidth() * buttonScale, playButton.getHeight() * buttonScale);
        listButton.setSize(listButton.getWidth() * buttonScale, listButton.getHeight() * buttonScale);
        loadButton.setSize(loadButton.getWidth() * buttonScale, loadButton.getHeight() * buttonScale);
        mapButton.setSize(mapButton.getWidth() * buttonScale, mapButton.getHeight() * buttonScale);

        // ===== ALTURA BASE DE TODOS LOS BOTONES =====
        float buttonsBaseY = height - 550;

        // ===== PLAY A LA IZQUIERDA =====
        float playX = bannerX + 20;
        playButton.setPosition(playX, buttonsBaseY);

        // ===== BOTONES A LA DERECHA =====
        float rightX = bannerX + desiredWidth - listButton.getWidth() - 20;

        listButton.setPosition(rightX, buttonsBaseY);
        loadButton.setPosition(rightX, buttonsBaseY - listButton.getHeight() - spacing);
        mapButton.setPosition(rightX, buttonsBaseY - listButton.getHeight() - loadButton.getHeight() - (spacing * 2));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        bgSprite.draw(batch);
        bannerSprite.draw(batch);
        trainerSprite.draw(batch);
        playButton.draw(batch);
        listButton.draw(batch);
        loadButton.draw(batch);
        mapButton.draw(batch);
        batch.end();
    }
}
