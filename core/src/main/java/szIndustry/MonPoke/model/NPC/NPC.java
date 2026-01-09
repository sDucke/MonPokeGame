package szIndustry.MonPoke.model.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import szIndustry.MonPoke.controller.NPC.LogicNPC;
import szIndustry.MonPoke.model.player.Player;
import szIndustry.MonPoke.utils.ui.TextManager;
import szIndustry.MonPoke.view.ui.DialogueManager;

public class NPC {
    private String npcClass;
    private Vector2 position;
    private boolean showPrompt = false;
    private final float INTERACTION_RANGE = 1.5f;
    private LogicNPC logicNPC;

    public NPC(String npcClass, float x, float y) {
        this.npcClass = npcClass;
        this.position = new Vector2(x, y);

        // Inicializamos la lógica y el gestor de textos
        this.logicNPC = new LogicNPC(new DialogueManager());
        TextManager.load();
    }

    public void update(float delta, Player player) {
        float dist = position.dst(player.getX(), player.getY());
        showPrompt = (dist < INTERACTION_RANGE);

        if (showPrompt && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Gdx.app.log("NPC", "Interactuando con: " + npcClass);
            logicNPC.triggerInteraction(npcClass);
        }
    }

    public void draw(SpriteBatch batch) {
        // Aquí deberías dibujar la textura del NPC si la tuvieras.
        // Por ahora, si solo quieres lógica, este método puede quedar vacío
        // o dibujar un placeholder.
    }

    public void drawInteractionLabel(SpriteBatch batch, OrthographicCamera worldCamera) {
        if (showPrompt) {
            // 1. Convertimos la posición del NPC (Mundo) a Píxeles (Pantalla)
            // position.y + 1.2f para que el texto flote sobre su cabeza
            Vector3 screenPos = new Vector3(position.x, position.y + 1.5f, 0);
            worldCamera.project(screenPos);

            // 2. Dibujamos el texto en la pantalla.
            // Como estamos en píxeles, usamos escala 1.2f para que sea legible.
            TextManager.draw(batch, "[ESPACIO]", screenPos.x - 45, screenPos.y, Color.YELLOW, 1f);
        }
    }
}
