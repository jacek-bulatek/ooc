package com.packtpub.libgdx.bludbourne;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.utils.ObjectMap;
import com.packtpub.libgdx.bludbourne.Entity.AnimationType;
import com.packtpub.libgdx.bludbourne.InventoryItem.ItemTypeID;

public class EntityConfig {
    private Array<AnimationConfig> animationConfig;
    private Array<ItemTypeID> inventory;
    private Entity.State state = Entity.State.IDLE;
    private Entity.Direction direction = Entity.Direction.S;
    private String entityID;
    private String conversationConfigPath;
    private String questConfigPath;
    private String currentQuestID;
    private String itemTypeID;
    private ObjectMap<String, String> entityProperties;
    private int width = 16;
    private int height = 16;
    private float cdAbility1 = 2;   // Ability 1 cooldown time
    private float cdAbility2 = 2;
    private float cdAbility3 = 2;
    private float cdAbility4 = 2;
    private Array<Float> ability1HitboxVertices;
    private Polygon ability1Hitbox;

    public static enum EntityProperties{
        ENTITY_HEALTH_POINTS,
        ENTITY_ATTACK_POINTS,
        ENTITY_DEFENSE_POINTS,
        ENTITY_HIT_DAMAGE_TOTAL,
        ENTITY_XP_REWARD,
        ENTITY_GP_REWARD,
        NONE
    }

    EntityConfig(){
        animationConfig = new Array<AnimationConfig>();
        inventory = new Array<ItemTypeID>();
        entityProperties = new ObjectMap<String, String>();
    }

    EntityConfig(EntityConfig config){
        state = config.getState();
        direction = config.getDirection();
        entityID = config.getEntityID();
        conversationConfigPath = config.getConversationConfigPath();
        questConfigPath = config.getQuestConfigPath();
        currentQuestID = config.getCurrentQuestID();
        itemTypeID = config.getItemTypeID();
        width = config.getWidth();
        height = config.getHeight();
        cdAbility1 = config.getCdAbility1();
        cdAbility2 = config.getCdAbility2();
        cdAbility3 = config.getCdAbility3();

        ability1HitboxVertices = new Array<Float>();
        if(config.getAbility1HitboxVertices() != null) {
            ability1HitboxVertices.addAll(config.getAbility1HitboxVertices());

            float[] temp = new float[ability1HitboxVertices.size];
            for (int i = 0; i < ability1HitboxVertices.size; i++) {
                temp[i] = ability1HitboxVertices.get(i);
            }
            ability1Hitbox = new Polygon(temp);
        }

        animationConfig = new Array<AnimationConfig>();
        animationConfig.addAll(config.getAnimationConfig());

        inventory = new Array<ItemTypeID>();
        inventory.addAll(config.getInventory());

        entityProperties = new ObjectMap<String, String>();
        entityProperties.putAll(config.entityProperties);
    }

    public ObjectMap<String, String> getEntityProperties() {
        return entityProperties;
    }

    public void setEntityProperties(ObjectMap<String, String> entityProperties) {
        this.entityProperties = entityProperties;
    }

    public void setPropertyValue(String key, String value){
        entityProperties.put(key, value);
    }

    public String getPropertyValue(String key){
        Object propertyVal = entityProperties.get(key);
        if( propertyVal == null ) return new String();
        return propertyVal.toString();
    }

    public String getCurrentQuestID() {
        return currentQuestID;
    }

    public void setCurrentQuestID(String currentQuestID) {
        this.currentQuestID = currentQuestID;
    }

    public String getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(String itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    public String getQuestConfigPath() {
        return questConfigPath;
    }

    public void setQuestConfigPath(String questConfigPath) {this.questConfigPath = questConfigPath;}

    public int getWidth() {return width;}

    public int getHeight() {return height;}
    
    public float getCdAbility1() {return cdAbility1;}
    
    public float getCdAbility2() {return cdAbility2;}
    
    public float getCdAbility3() {return cdAbility3;}
    
    public float getCdAbility4() {return cdAbility4;}

    public String getConversationConfigPath() {
        return conversationConfigPath;
    }

    public void setConversationConfigPath(String conversationConfigPath) {
        this.conversationConfigPath = conversationConfigPath;
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public Entity.Direction getDirection() {
        return direction;
    }

    public void setDirection(Entity.Direction direction) {
        this.direction = direction;
    }

    public Entity.State getState() {
        return state;
    }

    public void setState(Entity.State state) {
        this.state = state;
    }

    public Array<AnimationConfig> getAnimationConfig() {
        return animationConfig;
    }

    public void addAnimationConfig(AnimationConfig animationConfig) {
        this.animationConfig.add(animationConfig);
    }

    public Array<ItemTypeID> getInventory() {
        return inventory;
    }

    public void setInventory(Array<ItemTypeID> inventory) {
        this.inventory = inventory;
    }

    public Array<Float> getAbility1HitboxVertices(){return ability1HitboxVertices;}

    public Polygon getAbilty1Hitbox(){return ability1Hitbox;}

    public void setAbility1Hitbox(Polygon ability1Hitbox){this.ability1Hitbox = ability1Hitbox;}

    static public class AnimationConfig{
        private float frameDuration = 1.0f;
        private AnimationType animationType;
        private Array<String> texturePaths;
        private Array<GridPoint2> gridPoints;

        public AnimationConfig(){
            animationType = AnimationType.IDLE;
            texturePaths = new Array<String>();
            gridPoints = new Array<GridPoint2>();
        }

        public float getFrameDuration() {return frameDuration;}

        public void setFrameDuration(float frameDuration) {
            this.frameDuration = frameDuration;
        }

        public Array<String> getTexturePaths() {
            return texturePaths;
        }

        public void setTexturePaths(Array<String> texturePaths) {
            this.texturePaths = texturePaths;
        }

        public Array<GridPoint2> getGridPoints() {
            return gridPoints;
        }

        public void setGridPoints(Array<GridPoint2> gridPoints) {
            this.gridPoints = gridPoints;
        }

        public AnimationType getAnimationType() {
            return animationType;
        }

        public void setAnimationType(AnimationType animationType) {
            this.animationType = animationType;
        }
    }

}
