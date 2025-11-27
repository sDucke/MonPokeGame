package szIndustry.MonPoke.view.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import szIndustry.MonPoke.Main;
import szIndustry.MonPoke.model.pokemon.PokemonModel;
import szIndustry.MonPoke.model.pokemon.PokemonStatsBox;
import szIndustry.MonPoke.view.Screens;

public class PokemonDetailScreen extends Screens {

    private Stage stage;
    private final PokemonModel model;

    public PokemonDetailScreen(Main game, PokemonModel model) {
        super(game);
        this.model = model;
    }

    @Override
    public void show() {

        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        // ===== FONDO =====
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        p.setColor(Color.valueOf("0D0F12"));
        p.fill();
        Image bg = new Image(new Texture(p));
        p.dispose();
        bg.setFillParent(true);
        stage.addActor(bg);

        // ===== BOTÓN VOLVER (Imagen escalada) =====
        Texture backTexture = new Texture("ui/back_button.png");
        Image btnBack = scaleImage(backTexture, 120f); // mismo método que MenuScreen
        btnBack.setPosition(10, viewport.getWorldHeight() - btnBack.getHeight() - 10);

        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PokemonList(game));
            }
        });

        stage.addActor(btnBack);

        // ===== CONTENIDO =====
        Table root = new Table();
        root.setFillParent(true);
        root.padTop(80);
        stage.addActor(root);

        Table content = new Table();
        content.defaults().pad(20);

        // Pokémon image
        Image img = scaleImage(new Texture(Gdx.files.internal(model.imagePath)), 300f);

        Label.LabelStyle titleStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label lblName = new Label(model.name, titleStyle);

        PokemonStatsBox statsBox = new PokemonStatsBox(model);

        content.add(img).row();
        content.add(lblName).padTop(10).row();
        content.add(statsBox).expandX().fillX();

        root.add(content).expand().center();
    }

    // ===== FUNCIÓN DE ESCALADO UNIVERSAL =====
    private Image scaleImage(Texture tex, float desiredWidth) {
        Image img = new Image(tex);
        float scale = desiredWidth / tex.getWidth();
        img.setSize(desiredWidth, tex.getHeight() * scale);
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
    }
}
