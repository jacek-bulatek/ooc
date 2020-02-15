package pl.noname.ooc.actors.play;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
    private Character hero;
    private Character hero2;
    private WorldInputProcessor inputProcessor;
    private Group onMapObjects = new Group();
    private List blockedTiles;
    
    public World(Play screen) {
    	blockedTiles = new ArrayList<>();
        map = Assets.MAP.get();
        hero = new Character();
        hero.setPosition(CellToPosition(40, 40).x, CellToPosition(40,50).y);
        hero.setWorld(this);
        hero2 = new Character();
        hero2.setPosition(CellToPosition(60, 20).x, CellToPosition(60,20).y);
        hero2.setWorld(this);
        onMapObjects.addActor(hero);
        onMapObjects.addActor(hero2);
        inputProcessor = new WorldInputProcessor(hero, screen);
        renderer = new OrthogonalTiledMapRenderer(map);
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
    	Vector2 cell = new Vector2();
        cell.x = (int) (pos.x / 32f);
        cell.y = (int) (pos.y / 32f);
        return cell;
    }
    
    public Vector2 CellToPosition(int x, int y) {
    	Vector2 pos = new Vector2();
    	pos.x = x * 32;
    	pos.y = y * 32;
    	return pos;
    }

    public boolean isWalkable(Vector2 pos) {
        Vector2 cellPos = PositionToCell(pos);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = layer.getCell((int)cellPos.x, (int)cellPos.y);
        
        if(cell != null) {
            if(isBlocked(cell))
            	return false;
            else
            	return true;
        }
        return false;
    }

    public void setBlocked(Vector2 pos) {
    	Vector2 cellPos = PositionToCell(pos);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = layer.getCell((int)cellPos.x, (int)cellPos.y);
        blockedTiles.add(cell);
    	
    }
    
    public void clearBlocked(Vector2 pos) {
    	Vector2 cellPos = PositionToCell(pos);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = layer.getCell((int)cellPos.x, (int)cellPos.y);
        blockedTiles.remove(cell);
    }
    
    public boolean isBlocked(TiledMapTileLayer.Cell cell) {
    	return blockedTiles.contains(cell) || cell.getTile().getId() != 100;
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
}
