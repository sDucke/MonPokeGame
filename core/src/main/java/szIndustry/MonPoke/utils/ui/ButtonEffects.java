package szIndustry.MonPoke.utils.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ButtonEffects extends ClickListener {

    public interface ClickAction {
        void onClick(Actor actor);
    }

    private final ClickAction clickAction;
    private final float hoverScale = 1.08f;
    private final float clickScale = 1.15f;
    private final float animationDuration = 0.15f;

    public ButtonEffects(ClickAction action) {
        this.clickAction = action;
    }

    public ButtonEffects() {
        this.clickAction = null;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        Actor actor = event.getListenerActor();
        // Elimina cualquier acción previa para evitar conflicto
        actor.clearActions();
        // Escala suavemente al hover
        actor.addAction(Actions.scaleTo(hoverScale, hoverScale, animationDuration));
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        Actor actor = event.getListenerActor();
        actor.clearActions();
        actor.addAction(Actions.scaleTo(1f, 1f, animationDuration));
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Actor actor = event.getListenerActor();
        actor.clearActions();

        // Animación click: primero escala a clickScale rápido, luego vuelve a hoverScale
        actor.addAction(Actions.sequence(
            Actions.scaleTo(clickScale, clickScale, animationDuration / 2f),
            Actions.scaleTo(hoverScale, hoverScale, animationDuration / 2f)
        ));

        if (clickAction != null) {
            clickAction.onClick(actor);
        }
    }
}
