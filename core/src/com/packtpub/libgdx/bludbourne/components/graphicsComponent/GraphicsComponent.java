package com.packtpub.libgdx.bludbourne.components.graphicsComponent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.packtpub.libgdx.bludbourne.components.Component;
import com.packtpub.libgdx.bludbourne.components.ComponentSubject;
import com.packtpub.libgdx.bludbourne.Entity;
import com.packtpub.libgdx.bludbourne.MapManager;
import com.packtpub.libgdx.bludbourne.Utility;

import java.util.Hashtable;

public abstract class GraphicsComponent extends ComponentSubject implements Component {
    protected TextureRegion _currentFrame = null;
    protected float _frameTime = 0f;
    protected Entity.State _currentState;
    protected Entity.Direction _currentDirection;
    protected Json _json;
    protected Vector2 _currentPosition;
    protected Hashtable<Entity.AnimationType, Animation<TextureRegion>> _animations;
    protected ShapeRenderer _shapeRenderer;

    protected GraphicsComponent(){
        _currentPosition = new Vector2(0,0);
        _currentState = Entity.State.RUNNING;
        _currentDirection = Entity.Direction.S;
        _json = new Json();
        _animations = new Hashtable<>();
        _shapeRenderer = new ShapeRenderer();
    }

    public abstract void update(Entity entity, MapManager mapManager, Batch batch, float delta);

    protected void updateAnimations(float delta){
        _frameTime = (_frameTime + delta)%5; //Want to avoid overflow

        //Look into the appropriate variable when changing position
        switch (_currentDirection) {
            case S:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_S);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if(_currentState == Entity.State.RUNNING) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_S);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_S);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if(_currentState == Entity.State.IMMOBILE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            case W:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_W);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if (_currentState == Entity.State.RUNNING) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_W);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_W);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IMMOBILE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            case N:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_N);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if (_currentState == Entity.State.RUNNING) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_N);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_N);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IMMOBILE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            case E:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_E);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if (_currentState == Entity.State.RUNNING) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_E);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_E);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IMMOBILE) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            case NE:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_NE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if (_currentState == Entity.State.RUNNING) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_NE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_NE);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IMMOBILE) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            case SE:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_SE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if (_currentState == Entity.State.RUNNING) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_SE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_SE);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IMMOBILE) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            case NW:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_NW);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if (_currentState == Entity.State.RUNNING) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_NW);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_NW);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IMMOBILE) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            case SW:
                if(_currentState == Entity.State.ABILITY_1) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ABILITY_1_SW);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                else if (_currentState == Entity.State.RUNNING) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.MOVE_SW);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IDLE) {
					Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IDLE_SW);
                    if( animation == null )
                        animation = _animations.get(Entity.AnimationType.IDLE);
                    _currentFrame = animation.getKeyFrame(_frameTime);
                } else if(_currentState == Entity.State.IMMOBILE) {
                    Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
                    if( animation == null ) return;
                    _currentFrame = animation.getKeyFrame(_frameTime);
                }
                break;
            default:
                break;
        }
    }

    //Specific to two frame animations where each frame is stored in a separate texture
    protected Animation loadAnimation(String firstTexture, String secondTexture, Array<GridPoint2> points, float frameDuration, int frameWidth, int frameHeight){
        Utility.loadTextureAsset(firstTexture);
        Texture texture1 = Utility.getTextureAsset(firstTexture);

        Utility.loadTextureAsset(secondTexture);
        Texture texture2 = Utility.getTextureAsset(secondTexture);

        TextureRegion[][] texture1Frames = TextureRegion.split(texture1, frameWidth, frameHeight);
        TextureRegion[][] texture2Frames = TextureRegion.split(texture2, frameWidth, frameHeight);

        GridPoint2 point = points.first();

		Animation animation = new Animation(frameDuration, texture1Frames[point.x][point.y],texture2Frames[point.x][point.y]);
		animation.setPlayMode(Animation.PlayMode.LOOP);

        return animation;
    }

    protected Animation loadAnimation(String textureName, Array<GridPoint2> points, float frameDuration, int frameWidth, int frameHeight){
        Utility.loadTextureAsset(textureName);
        Texture texture = Utility.getTextureAsset(textureName);

        TextureRegion[][] textureFrames = TextureRegion.split(texture, frameWidth, frameHeight);

        TextureRegion[] animationKeyFrames = new TextureRegion[points.size];

        for(int i=0; i < points.size; i++){
			animationKeyFrames[i] = textureFrames[points.get(i).x][points.get(i).y];
        }

        Animation animation = new Animation(frameDuration, (Object[])animationKeyFrames);
		animation.setPlayMode(Animation.PlayMode.LOOP);

        return animation;
    }

    public Vector2 getCurrentPosition(){return _currentPosition;}

    public Animation<TextureRegion> getAnimation(Entity.AnimationType type){
        return _animations.get(type);
    }
}
