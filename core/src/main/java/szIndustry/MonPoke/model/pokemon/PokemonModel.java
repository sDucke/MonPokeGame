package szIndustry.MonPoke.model.pokemon;

public class PokemonModel {

    public String name;
    public String imagePath;

    public int hp;
    public int attack;
    public int defense;
    public int speed;

    public String ability1;
    public String ability2;

    public PokemonModel(String name, String imagePath,
                        int hp, int attack, int defense, int speed,
                        String ability1, String ability2) {

        this.name = name;
        this.imagePath = imagePath;

        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;

        this.ability1 = ability1;
        this.ability2 = ability2;
    }
}
