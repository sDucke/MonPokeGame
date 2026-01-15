package szIndustry.MonPoke.controller.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import szIndustry.MonPoke.view.ui.DialogueManager;

public class LogicNPC {
    private JsonValue root;
    private DialogueManager dialogueManager;

    public LogicNPC(DialogueManager dialogueManager) {
        this.dialogueManager = dialogueManager;
        try {
            JsonReader reader = new JsonReader();
            root = reader.parse(Gdx.files.internal("data/dialogos.json"));
        } catch (Exception e) {
            Gdx.app.error("LogicNPC", "Error al cargar dialogos.json");
        }
    }

    /**
     * Procesa la clase del mapa "NPCTEST-cap1" y dispara el diálogo
     */
    public void triggerInteraction(String rawData) {
        if (rawData == null || !rawData.contains("-")) return;

        // 1. Separar Nombre y Capítulo
        String[] parts = rawData.split("-");
        String npcId = parts[0];
        String capId = parts[1];

        // 2. Extraer el texto del JSON
        String textoParaMostrar = "NPC no encontrado";
        if (root.has(npcId) && root.get(npcId).has(capId)) {
            textoParaMostrar = root.get(npcId).getString(capId);
        }

        // 3. ENVIAR A LA CLASE DE DIÁLOGOS
        // Aquí llamamos a la clase encargada de la UI
        dialogueManager.show(textoParaMostrar);
    }
}
