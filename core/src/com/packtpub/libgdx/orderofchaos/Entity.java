package com.packtpub.libgdx.orderofchaos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.packtpub.libgdx.orderofchaos.components.Component;
import com.packtpub.libgdx.orderofchaos.components.ComponentObserver;
import com.packtpub.libgdx.orderofchaos.components.graphicsComponent.GraphicsComponent;
import com.packtpub.libgdx.orderofchaos.components.inputComponent.InputComponent;
import com.packtpub.libgdx.orderofchaos.components.physicsComponent.AbilityHitbox;
import com.packtpub.libgdx.orderofchaos.components.physicsComponent.PhysicsComponent;
import com.packtpub.libgdx.orderofchaos.profile.ProfileManager;

import java.util.ArrayList;
import java.util.Hashtable;

public class Entity {
	private static final String TAG = Entity.class.getSimpleName();

	public static enum Direction {
		N,
		E,
		S,
		W,
		NW,
		NE,
		SW,
		SE;


		static public Direction getRandomNext() {
			return Direction.values()[MathUtils.random(Direction.values().length - 1)];
		}
	}

	public static enum State {
		IDLE,
		RUNNING,
		ABILITY_1,

		IMMOBILE;//This should always be last

		static public State getRandomNext() {
			//Ignore IMMOBILE which should be last state
			return State.values()[MathUtils.random(State.values().length - 2)];
		}
	}

	public static enum AnimationType {
		MOVE_W,
		MOVE_E,
		MOVE_N,
		MOVE_S,
		MOVE_NE,
		MOVE_NW,
		MOVE_SE,
		MOVE_SW,

		ABILITY_1_W,
		ABILITY_1_E,
		ABILITY_1_N,
		ABILITY_1_S,
		ABILITY_1_NE,
		ABILITY_1_NW,
		ABILITY_1_SE,
		ABILITY_1_SW,

		IDLE_W,
		IDLE_E,
		IDLE_N,
		IDLE_S,
		IDLE_NE,
		IDLE_NW,
		IDLE_SE,
		IDLE_SW,

		// keep simple idle for other purposes
		IDLE,

		IMMOBILE
	}
	
	private static final int MAX_COMPONENTS = 5;

	private Json _json;
	private EntityConfig _entityConfig;
	private Array<Component> _components;
	private InputComponent _inputComponent;
	private Timer _animationTimer = new Timer(0);
	private Timer[] _cooldownTimer = new Timer[3];
	private GraphicsComponent _graphicsComponent;
	private PhysicsComponent _physicsComponent;

	public Entity(Entity entity){
		set(entity);
	}

	private Entity set(Entity entity) {
		_inputComponent = entity._inputComponent;
		_graphicsComponent = entity._graphicsComponent;
		_physicsComponent = entity._physicsComponent;

		if( _components == null ){
			_components = new Array<Component>(MAX_COMPONENTS);
		}
		_components.clear();
		_components.add(_inputComponent);
		_components.add(_physicsComponent);
		_components.add(_graphicsComponent);

		_json = entity._json;

		_entityConfig = new EntityConfig(entity._entityConfig);
		return this;
	}

	public Entity(InputComponent inputComponent, PhysicsComponent physicsComponent, GraphicsComponent graphicsComponent){
		_entityConfig = new EntityConfig();
		_json = new Json();

		_components = new Array<Component>(MAX_COMPONENTS);

		_inputComponent = inputComponent;
		_physicsComponent = physicsComponent;
		_graphicsComponent = graphicsComponent;

		_components.add(_inputComponent);
		_components.add(_physicsComponent);
		_components.add(_graphicsComponent);
	}

	public EntityConfig getEntityConfig() {
		return _entityConfig;
	}

	public void sendMessage(Component.MESSAGE messageType, String ... args){
		String fullMessage = messageType.toString();

		for (String string : args) {
			fullMessage += Component.MESSAGE_TOKEN + string;
		}

		for(Component component: _components){
			component.receiveMessage(fullMessage);
		}
	}

	public void registerObserver(ComponentObserver observer){
		_inputComponent.addObserver(observer);
		_physicsComponent.addObserver(observer);
		_graphicsComponent.addObserver(observer);
	}

	public void unregisterObservers(){
		_inputComponent.removeAllObservers();
		_physicsComponent.removeAllObservers();
		_graphicsComponent.removeAllObservers();
	}

	public void update(MapManager mapMgr, Batch batch, float delta){
		_inputComponent.update(this, delta);
		_physicsComponent.update(this, mapMgr, delta);
		_graphicsComponent.update(this, mapMgr, batch, delta);
	}

	public void updateInput(float delta){
		_inputComponent.update(this, delta);
	}

	public void dispose(){
		for(Component component: _components){
			component.dispose();
		}
	}

	public Rectangle getCurrentBoundingBox(){
		return _physicsComponent._boundingBox;
	}

	public Vector2 getCurrentPosition(){
		return _graphicsComponent.getCurrentPosition();
	}

	public InputProcessor getInputProcessor(){
		return _inputComponent;
	}

	public void setEntityConfig(EntityConfig entityConfig){
		this._entityConfig = entityConfig;
	}

	public Animation<TextureRegion> getAnimation(Entity.AnimationType type){
		return _graphicsComponent.getAnimation(type);
	}

	static public EntityConfig getEntityConfig(String configFilePath){
		Json json = new Json();
		EntityConfig config = json.fromJson(EntityConfig.class, Gdx.files.internal(configFilePath));
		Array<Float> ability1HitboxVertices = new Array<Float>();
		if(config.getAbility1HitboxVertices() != null) {
			ability1HitboxVertices.addAll(config.getAbility1HitboxVertices());

			float[] temp = new float[ability1HitboxVertices.size];
			for (int i = 0; i < ability1HitboxVertices.size; i++) {
				temp[i] = ability1HitboxVertices.get(i);
			}
			config.setAbility1Hitbox(new AbilityHitbox(temp));
		}
		return config;
	}

	static public Array<EntityConfig> getEntityConfigs(String configFilePath){
		Json json = new Json();
		Array<EntityConfig> configs = new Array<EntityConfig>();

    	ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(configFilePath));

		for (JsonValue jsonVal : list) {
			configs.add(json.readValue(EntityConfig.class, jsonVal));
		}

		return configs;
	}

	public static EntityConfig loadEntityConfigByPath(String entityConfigPath){
		EntityConfig entityConfig = Entity.getEntityConfig(entityConfigPath);
		EntityConfig serializedConfig = ProfileManager.getInstance().getProperty(entityConfig.getEntityID(), EntityConfig.class);

		if( serializedConfig == null ){
			return entityConfig;
		}else{
			return serializedConfig;
		}
	}

	public static EntityConfig loadEntityConfig(EntityConfig entityConfig){
		EntityConfig serializedConfig = ProfileManager.getInstance().getProperty(entityConfig.getEntityID(), EntityConfig.class);

		if( serializedConfig == null ){
			return entityConfig;
		}else{
			return serializedConfig;
		}
	}

	public static Entity initEntity(EntityConfig entityConfig, Vector2 position){
		Json json = new Json();
		Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);
		entity.setEntityConfig(entityConfig);

		entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, json.toJson(entity.getEntityConfig()));
		entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(position));
		entity.sendMessage(Component.MESSAGE.INIT_BOUNDING_BOX, json.toJson(new Vector2(entityConfig.getWidth(), entityConfig.getHeight())));
		entity.sendMessage(Component.MESSAGE.INIT_STATE, json.toJson(entity.getEntityConfig().getState()));
		entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, json.toJson(entity.getEntityConfig().getDirection()));

		return entity;
	}

	public static Hashtable<String, Entity> initEntities(Array<EntityConfig> configs){
		Json json = new Json();
		Hashtable<String, Entity > entities = new Hashtable<String, Entity>();
		for( EntityConfig config: configs ){
			Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);

			entity.setEntityConfig(config);
			entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, json.toJson(entity.getEntityConfig()));
			entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(new Vector2(0,0)));
			entity.sendMessage(Component.MESSAGE.INIT_BOUNDING_BOX, json.toJson(new Vector2(config.getWidth(), config.getHeight())));
			entity.sendMessage(Component.MESSAGE.INIT_STATE, json.toJson(entity.getEntityConfig().getState()));
			entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, json.toJson(entity.getEntityConfig().getDirection()));

			entities.put(entity.getEntityConfig().getEntityID(), entity);
		}

		return entities;
	}

	public static Entity initEntity(EntityConfig entityConfig){
		Json json = new Json();
		Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);
		entity.setEntityConfig(entityConfig);

		entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, json.toJson(entity.getEntityConfig()));
		entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(new Vector2(0,0)));
		entity.sendMessage(Component.MESSAGE.INIT_STATE, json.toJson(entity.getEntityConfig().getState()));
		entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, json.toJson(entity.getEntityConfig().getDirection()));

		return entity;
	}

	public PhysicsComponent getPhysicsComponent(){return _physicsComponent;}
}
