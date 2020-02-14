package pl.noname.ooc.actors.play;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import pl.noname.ooc.Assets;
import pl.noname.ooc.inputProcessors.WorldInputProcessor;
import pl.noname.ooc.screens.Play;


public class World extends Actor {
    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private final static int[] WALKABLE_TILES = {19, 18, 15, 14, 8, 7, 6, 4, 3, 2, 1};
    private Character hero;
    private Character hero2;
    private WorldInputProcessor inputProcessor;
    private Group onMapObjects = new Group();
    private List blockedTiles;
    
    public World(Play screen) {
    	blockedTiles = new ArrayList<>();
        map = Assets.MAP.get();
        setupMapObjectsLayer();
        hero = new Character();
        hero.setPosition(44*64,47);
        hero.setWorld(this);
        hero2 = new Character();
        hero2.setPosition(47*64,47);
        hero2.setWorld(this);
        onMapObjects.addActor(hero);
        onMapObjects.addActor(hero2);
        inputProcessor = new WorldInputProcessor(hero, screen);
        renderer = new IsometricTiledMapRenderer(map);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w,h);
        camera.update();
    }

    public InputProcessor getInputProcessor() {return inputProcessor;}
    
    public Character getHero() {return hero;}
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        renderer.setView(camera);
        renderer.render();
        batch.begin();
        onMapObjects.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        onMapObjects.act(delta);
        Update();
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
        TiledMapTileLayer layer2 = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell2 = layer2.getCell(99-(int)isoPos.y, (int)isoPos.x);
        
        if(cell != null) {
            if(isBlocked(cell2.getTile()))
            	return false;
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

    public void setBlocked(Vector2 pos) {
    	Vector2 isoPos = PositionToCell(pos);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = layer.getCell(99-(int)isoPos.y, (int)isoPos.x);
        blockedTiles.add(cell.getTile());
    	
    }
    
    public void clearBlocked(Vector2 pos) {
    	Vector2 isoPos = PositionToCell(pos);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = layer.getCell(99-(int)isoPos.y, (int)isoPos.x);
        blockedTiles.remove(cell.getTile());
    }
    
    public boolean isBlocked(TiledMapTile tile) {
    	return blockedTiles.contains(tile);
    }
    
    public OrthographicCamera getCamera() {
        return camera;
    }

    public void Update() {
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
    
    private void setupMapObjectsLayer() {
    	TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
    	TiledMapTileSet tileSet = new TiledMapTileSet();
    	int tileId = 0;
    	for(int row = 0; row < 100; row++) {
    		for(int col = 0; col < 100; col++) {
    			TiledMapTile tile = new StaticTiledMapTile(new TextureRegion());
    			tile.setId(tileId++);
    			TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
    			cell.setTile(tile);
    			layer.setCell(col, row, cell);
    		}
    	}
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
