package pl.noname.ooc.actors.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import pl.noname.ooc.Assets;
import pl.noname.ooc.screens.Play;


public class World extends Actor {
    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private final static int[] WALKABLE_TILES = {19, 18, 15, 14, 8, 7, 6, 4, 3, 2, 1};
    private Play screen;
    public World(Play screen) {
        this.screen = screen;
        map = Assets.MAP.get();
        renderer = new IsometricTiledMapRenderer(map);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w,h);
        camera.update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        renderer.setView(camera);
        renderer.render();
        batch.begin();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Update(screen.getHero());
    }

    public Vector2 PositionToCell(Vector2 pos) {
        pos.x = (pos.x-10f)/32f;
        pos.y = (pos.y-10f)/32f+100f;
        Vector2 cell = i2c(pos);
        cell.x -= 100f;
        return cell;
    }

    public boolean isWalkable(Vector2 pos) {
        Vector2 isoPos = PositionToCell(pos);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        TiledMapTileLayer.Cell cell = layer.getCell(99-(int)isoPos.y, (int)isoPos.x);

        if(cell != null) {
            int tile_id = cell.getTile().getId();
            boolean flag = false;
            for(int id : WALKABLE_TILES) {
                if(tile_id == id) {
                    flag = true;
                    break;
                }
            }
            return flag;
        }
        return false;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void Update(Character hero) {
        Vector2 heroPos = hero.getStandPosition();
        camera.position.x = heroPos.x;
        camera.position.y = heroPos.y;
        camera.update();
    }

    static private Vector2 c2i(Vector2 c) {
        Vector2 i = new Vector2();
        i.x = c.x-c.y;
        i.y = (c.x+c.y)/2f;
        return i;
    }

    static private  Vector2 i2c(Vector2 i) {
        Vector2 c = new Vector2();
        c.x = (i.x + i.y*2f)/2f;
        c.y = -i.x + c.x;
        return c;
    }

    public void DebugTile(int x, int y, ShapeRenderer sr) {
        float x1 = (x)*32f+10f;
        float y1 = (y-100)*32f+10f;
        float x2 = x1+32f;
        float y2 = y1+32;
        Vector2 p1 = c2i(new Vector2(x1, y1));
        Vector2 p2 = c2i(new Vector2(x2, y1));
        Vector2 p3 = c2i(new Vector2(x2, y2));
        Vector2 p4 = c2i(new Vector2(x1, y2));
        sr.line(p1, p2);
        sr.line(p2, p3);
        sr.line(p3, p4);
        sr.line(p4, p1);
    }
/*
 * Func: DrawDebug
 * 
 * Highlights tile that hero is currently on
 * 
    public void DrawDebug(ShapeRenderer sr) {
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        float x = (camera.position.x-10f)/32f;
        float y = (camera.position.y-10f)/32f+100f;
        Vector2 tn = PositionToCell(new Vector2(camera.position.x , camera.position.y));
        sr.setColor(Color.LIME);
        DebugTile((int)Math.floor(tn.x),(int)Math.floor(tn.y), sr);
        sr.end();
    }
*/
}
