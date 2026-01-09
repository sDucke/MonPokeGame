package szIndustry.MonPoke.controller.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import szIndustry.MonPoke.model.NPC.NPC;

public class MapManager {
    private TiledMap map;
    private Array<Rectangle> collisionRects;
    private float unitScale;
    private Array<NPC> npcs;
    private Array<Object> worldObjects;

    public MapManager(String path, float unitScale) {
        this.unitScale = unitScale;
        this.map = new TmxMapLoader().load(path);

        // --- LOGS DE INICIO ---
        Gdx.app.log("MapManager", "Cargando mapa: " + path);
        Gdx.app.log("MapManager", "Capas detectadas: " + map.getLayers().getCount());

        if (map.getTileSets().getTileSet(0) != null) {
            Gdx.app.log("MapManager", "Tileset cargado correctamente: " + map.getTileSets().getTileSet(0).getName());
        }

        this.collisionRects = new Array<>();
        this.npcs = new Array<>();
        this.worldObjects = new Array<>();

        loadCollisionLayer();
        loadNPCLayer();
        loadObjectLayer();
    }

    private void loadCollisionLayer() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("collisions");
        if (layer == null) {
            Gdx.app.log("MapManager", "ADVERTENCIA: No existe la capa 'collisions'");
            return;
        }

        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null && cell.getTile() != null) {
                    TiledMapTile tile = cell.getTile();
                    if (tile.getObjects().getCount() > 0) {
                        for (MapObject obj : tile.getObjects()) {
                            if (obj instanceof RectangleMapObject) {
                                Rectangle rect = ((RectangleMapObject) obj).getRectangle();
                                collisionRects.add(new Rectangle(
                                    x + (rect.x / 16f),
                                    y + (rect.y / 16f),
                                    rect.width / 16f,
                                    rect.height / 16f
                                ));
                            }
                        }
                    }
                }
            }
        }
        Gdx.app.log("MapManager", "Rectangulos de colision cargados: " + collisionRects.size);
    }

    private void loadNPCLayer() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("NPC");
        if (layer == null) {
            Gdx.app.log("MapManager", "INFO: No se encontro capa de 'NPC'");
            return;
        }

        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null && cell.getTile() != null) {
                    String type = cell.getTile().getProperties().get("class", String.class);
                    if (type == null) type = cell.getTile().getProperties().get("type", String.class);

                    if (type != null) {
                        npcs.add(new NPC(type, x, y));
                        collisionRects.add(new Rectangle(x, y, 1f, 1f));
                        Gdx.app.log("MapManager", "NPC detectado: " + type + " en posicion [" + x + "," + y + "]");
                    }
                }
            }
        }
        Gdx.app.log("MapManager", "Total NPCs cargados: " + npcs.size);
    }

    private void loadObjectLayer() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("objects");
        if (layer == null) {
            Gdx.app.log("MapManager", "INFO: No se encontro capa de 'objects'");
            return;
        }

        int objectsCount = 0;
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null && cell.getTile() != null) {
                    String type = cell.getTile().getProperties().get("type", String.class);
                    if (type == null) type = cell.getTile().getProperties().get("class", String.class);

                    if (type != null) {
                        objectsCount++;
                        Gdx.app.log("MapManager", "Objeto detectado: " + type + " en [" + x + "," + y + "]");
                        // worldObjects.add(new WorldObject(type, x, y));
                    }
                }
            }
        }
        Gdx.app.log("MapManager", "Total Objetos cargados: " + objectsCount);
    }

    public boolean isColliding(Rectangle playerBounds) {
        for (Rectangle rect : collisionRects) {
            if (rect.overlaps(playerBounds)) return true;
        }
        return false;
    }

    public TiledMap getMap() { return map; }
    public Array<NPC> getNpcs() { return npcs; }
    public void dispose() { if (map != null) map.dispose(); }
}
