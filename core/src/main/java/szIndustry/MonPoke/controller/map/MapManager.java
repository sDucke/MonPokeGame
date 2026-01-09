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

public class MapManager {
    private TiledMap map;
    private Array<Rectangle> collisionRects;
    private float unitScale;

    public MapManager(String path, float unitScale) {
        this.unitScale = unitScale;
        this.map = new TmxMapLoader().load(path);
        this.collisionRects = new Array<>();
        loadCollisionLayer();
    }

    private void loadCollisionLayer() {
        // En tu TMX la capa se llama "collisions"
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("collisions");

        if (layer != null) {
            for (int x = 0; x < layer.getWidth(); x++) {
                for (int y = 0; y < layer.getHeight(); y++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                    if (cell != null && cell.getTile() != null) {
                        TiledMapTile tile = cell.getTile();

                        // En tu TMX, los tiles 1043, 1101, etc. tienen un 'objectgroup'
                        // LibGDX guarda esos objetos dentro del Tile.
                        if (tile.getObjects().getCount() > 0) {
                            for (MapObject obj : tile.getObjects()) {
                                if (obj instanceof RectangleMapObject) {
                                    Rectangle rect = ((RectangleMapObject) obj).getRectangle();

                                    // CREAR RECTÁNGULO EN EL MUNDO:
                                    // x, y es la posición del tile.
                                    // rect.x / 16f es el offset dentro del tile (si el objeto no ocupa todo el tile)
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
            Gdx.app.log("MapManager", "Colisiones cargadas: " + collisionRects.size);
        } else {
            Gdx.app.log("MapManager", "ERROR: No existe la capa 'collisions'");
        }
    }

    public boolean isColliding(Rectangle playerBounds) {
        for (Rectangle rect : collisionRects) {
            if (rect.overlaps(playerBounds)) return true;
        }
        return false;
    }

    public TiledMap getMap() { return map; }

    public void dispose() {
        if (map != null) map.dispose();
    }
}
