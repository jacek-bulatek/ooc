package com.packtpub.libgdx.orderofchaos.components.graphicsComponent;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import com.packtpub.libgdx.orderofchaos.Entity;
import com.packtpub.libgdx.orderofchaos.EntityConfig;
import com.packtpub.libgdx.orderofchaos.EntityConfig.AnimationConfig;
import com.packtpub.libgdx.orderofchaos.MapManager;
import com.packtpub.libgdx.orderofchaos.OrderOfChaos;
import com.packtpub.libgdx.orderofchaos.components.ComponentObserver;

public class PlayerGraphicsComponent extends GraphicsComponent {

    private static final String TAG = PlayerGraphicsComponent.class.getSimpleName();

    protected Vector2 _previousPosition;

    public PlayerGraphicsComponent(){
        _previousPosition = new Vector2(0,0);
    }

    @Override
    public void receiveMessage(String message) {
        //Gdx.app.debug(TAG, "Got message " + message);
        String[] string = message.split(MESSAGE_TOKEN);

        if( string.length == 0 ) return;

        //Specifically for messages with 1 object payload
        if( string.length == 2 ) {
            if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_POSITION.toString())) {
                _currentPosition = _json.fromJson(Vector2.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.INIT_START_POSITION.toString())) {
                _currentPosition = _json.fromJson(Vector2.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_STATE.toString())) {
                _currentState = _json.fromJson(Entity.State.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_DIRECTION.toString())) {
                _currentDirection = _json.fromJson(Entity.Direction.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.LOAD_ANIMATIONS.toString())) {
                EntityConfig entityConfig = _json.fromJson(EntityConfig.class, string[1]);
                Array<AnimationConfig> animationConfigs = entityConfig.getAnimationConfig();

                for( AnimationConfig animationConfig : animationConfigs ){
                    Array<String> textureNames = animationConfig.getTexturePaths();
                    Array<GridPoint2> points = animationConfig.getGridPoints();
                    Entity.AnimationType animationType = animationConfig.getAnimationType();
                    float frameDuration = animationConfig.getFrameDuration();
                    Animation<TextureRegion> animation = null;

                    if( textureNames.size == 1) {
                        animation = loadAnimation(textureNames.get(0), points, frameDuration, entityConfig.getWidth(), entityConfig.getHeight());
                    }else if( textureNames.size == 2){
                        animation = loadAnimation(textureNames.get(0), textureNames.get(1), points, frameDuration, entityConfig.getWidth(), entityConfig.getHeight());
                    }

                    _animations.put(animationType, animation);
                }
            }
        }
    }

    @Override
    public void update(Entity entity, MapManager mapMgr, Batch batch, float delta){
        updateAnimations(delta);

        //Player has moved
        if( _previousPosition.x != _currentPosition.x ||
                _previousPosition.y != _currentPosition.y){
            notify("", ComponentObserver.ComponentEvent.PLAYER_HAS_MOVED);
            _previousPosition = _currentPosition.cpy();
        }

        Camera camera = mapMgr.getCamera();
        camera.position.set(_currentPosition.x, _currentPosition.y, 0f);
        camera.update();

        batch.begin();
        batch.draw(_currentFrame, _currentPosition.x, _currentPosition.y, 1, 1);
        batch.end();
        if(OrderOfChaos.DEBUG_DRAW){
            drawDebug(entity, camera);
        }
    }

    @Override
    public void dispose(){
    }

}
