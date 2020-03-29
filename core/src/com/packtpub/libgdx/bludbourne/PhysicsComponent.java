package com.packtpub.libgdx.bludbourne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public abstract class PhysicsComponent extends ComponentSubject implements Component{
    private static final String TAG = PhysicsComponent.class.getSimpleName();

    public abstract void update(Entity entity, MapManager mapMgr, float delta);

    protected Vector2 _nextEntityPosition;
    protected Vector2 _currentEntityPosition;
    protected Entity.Direction _currentDirection;
    protected Json _json;
    protected Vector2 _velocity;
    protected Vector2 _entitySize;

    protected Array<Entity> _tempEntities;

    public Rectangle _boundingBox;
    protected BoundingBoxLocation _boundingBoxLocation;
    protected Ray _selectionRay;
    protected final float _selectRayMaximumDistance = 32.0f;

    public static enum BoundingBoxLocation{
        BOTTOM_LEFT,
        BOTTOM_CENTER,
        CENTER,
    }

    PhysicsComponent(){
        this._nextEntityPosition = new Vector2(0,0);
        this._currentEntityPosition = new Vector2(0,0);
        this._velocity = new Vector2(2f,2f);
        this._boundingBox = new Rectangle();
        this._json = new Json();
        this._tempEntities = new Array<Entity>();
        _boundingBoxLocation = BoundingBoxLocation.BOTTOM_LEFT;
        _selectionRay = new Ray(new Vector3(), new Vector3());
        this._entitySize = new Vector2(0,0);
    }

    protected boolean isCollisionWithMapEntities(Entity entity, MapManager mapMgr){
        _tempEntities.clear();
        _tempEntities.addAll(mapMgr.getCurrentMapEntities());
        _tempEntities.addAll(mapMgr.getCurrentMapQuestEntities());
        boolean isCollisionWithMapEntities = false;

        for(Entity mapEntity: _tempEntities){
            //Check for testing against self
            if( mapEntity.equals(entity) ){
                continue;
            }

            Rectangle targetRect = mapEntity.getCurrentBoundingBox();
            if (_boundingBox.overlaps(targetRect) ){
                //Collision
                entity.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
                isCollisionWithMapEntities = true;
                break;
            }
        }
        _tempEntities.clear();
        return isCollisionWithMapEntities;
    }

    protected boolean isCollision(Entity entitySource, Entity entityTarget){
        boolean isCollisionWithMapEntities = false;

        if( entitySource.equals(entityTarget) ){
            return false;
        }

        if (entitySource.getCurrentBoundingBox().overlaps(entityTarget.getCurrentBoundingBox()) ){
            //Collision
            entitySource.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
            isCollisionWithMapEntities = true;
        }

        return isCollisionWithMapEntities;
    }

    protected boolean isCollisionWithMapLayer(Entity entity, MapManager mapMgr){
        MapLayer mapCollisionLayer =  mapMgr.getCollisionLayer();

        if( mapCollisionLayer == null ){
            return false;
        }

        Rectangle rectangle = null;

        for( MapObject object: mapCollisionLayer.getObjects()){
            if(object instanceof RectangleMapObject) {
                rectangle = ((RectangleMapObject)object).getRectangle();
                if( _boundingBox.overlaps(rectangle) ){
                    //Collision
                    entity.sendMessage(MESSAGE.COLLISION_WITH_MAP);
                    return true;
                }
            }
        }

        return false;
    }

    protected void setNextPositionToCurrent(Entity entity){
        this._currentEntityPosition.x = _nextEntityPosition.x;
        this._currentEntityPosition.y = _nextEntityPosition.y;

        //Gdx.app.debug(TAG, "SETTING Current Position " + entity.getEntityConfig().getEntityID() + ": (" + _currentEntityPosition.x + "," + _currentEntityPosition.y + ")");
        entity.sendMessage(MESSAGE.CURRENT_POSITION, _json.toJson(_currentEntityPosition));
    }

    protected void calculateNextPosition(float deltaTime){
        if( _currentDirection == null ) return;

        if( deltaTime > .7) return;

        float testX = _currentEntityPosition.x;
        float testY = _currentEntityPosition.y;

        _velocity.scl(deltaTime);

        switch (_currentDirection) {
            case W:
                testX -=  _velocity.x;
                break;
            case E:
                testX += _velocity.x;
                break;
            case N:
                testY += _velocity.y;
                break;
            case S:
                testY -= _velocity.y;
                break;
            case NE:
                testX += 0.7f * _velocity.x;
                testY += 0.7f * _velocity.y;
                break;
            case NW:
                testX -= 0.7f * _velocity.x;
                testY += 0.7f * _velocity.y;
                break;
            case SE:
                testX += 0.7f * _velocity.x;
                testY -= 0.7f * _velocity.y;
                break;
            case SW:
                testX -= 0.7f * _velocity.x;
                testY -= 0.7f * _velocity.y;
                break;
            default:
                break;
        }

        _nextEntityPosition.x = testX;
        _nextEntityPosition.y = testY;

        //velocity
        _velocity.scl(1 / deltaTime);
    }

    protected void initBoundingBox(float percentageWidthReduced, float percentageHeightReduced, float entityWidth, float entityHeight){
        //Update the current bounding box
        float width;
        float height;

        _entitySize.x =  entityWidth;
        _entitySize.y = entityHeight;

        float widthReductionAmount = 1.0f - percentageWidthReduced; //.8f for 20% (1 - .20)
        float heightReductionAmount = 1.0f - percentageHeightReduced; //.8f for 20% (1 - .20)

        if( widthReductionAmount > 0 && widthReductionAmount < 1){
            width = _entitySize.x * widthReductionAmount;
        }else{
            width = _entitySize.x;
        }

        if( heightReductionAmount > 0 && heightReductionAmount < 1){
            height = _entitySize.y * heightReductionAmount;
        }else{
            height = _entitySize.y;
        }

        if( width == 0 || height == 0){
            Gdx.app.debug(TAG, "Width and Height are 0!! " + width + ":" + height);
        }

        //Need to account for the unitscale, since the map coordinates will be in pixels
        float minX;
        float minY;

        if( Map.UNIT_SCALE > 0 ) {
            minX = _nextEntityPosition.x / Map.UNIT_SCALE;
            minY = _nextEntityPosition.y / Map.UNIT_SCALE;
        }else{
            minX = _nextEntityPosition.x;
            minY = _nextEntityPosition.y;
        }

        _boundingBox.setWidth(width);
        _boundingBox.setHeight(height);

        switch(_boundingBoxLocation){
            case BOTTOM_LEFT:
                _boundingBox.set(minX, minY, width, height);
                break;
            case BOTTOM_CENTER:
                _boundingBox.setCenter(minX + _entitySize.x/2, minY + _entitySize.y/4);
                break;
            case CENTER:
                _boundingBox.setCenter(minX + _entitySize.x/2, minY + _entitySize.y/2);
                break;
        }

        //Gdx.app.debug(TAG, "SETTING Bounding Box for " + entity.getEntityConfig().getEntityID() + ": (" + minX + "," + minY + ")  width: " + width + " height: " + height);
    }

    protected void updateBoundingBoxPosition(Vector2 position){
        //Need to account for the unitscale, since the map coordinates will be in pixels
        float minX;
        float minY;

        if( Map.UNIT_SCALE > 0 ) {
            minX = position.x / Map.UNIT_SCALE;
            minY = position.y / Map.UNIT_SCALE;
        }else{
            minX = position.x;
            minY = position.y;
        }

        switch(_boundingBoxLocation){
            case BOTTOM_LEFT:
                _boundingBox.set(minX, minY, _boundingBox.getWidth(), _boundingBox.getHeight());
                break;
            case BOTTOM_CENTER:
                _boundingBox.setCenter(minX + _entitySize.x/2, minY + _entitySize.y/4);
                break;
            case CENTER:
                _boundingBox.setCenter(minX + _entitySize.x/2, minY + _entitySize.y/2);
                break;
        }

        //Gdx.app.debug(TAG, "SETTING Bounding Box for " + entity.getEntityConfig().getEntityID() + ": (" + minX + "," + minY + ")  width: " + width + " height: " + height);
    }
}
