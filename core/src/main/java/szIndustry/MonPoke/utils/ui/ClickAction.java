package szIndustry.MonPoke.utils.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;

// Esta interfaz nos permitirá definir qué debe pasar al hacer clic
// sin acoplar el efecto a una clase específica.
public interface ClickAction {
    void onClick(Actor actor);
}
