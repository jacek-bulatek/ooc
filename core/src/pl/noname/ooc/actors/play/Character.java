package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import pl.noname.ooc.Assets;

public class Character extends Actor {
    public enum State {
        STAY(0f),
        WALK(100f),
        RUN(200f);

        float velocity;
        State(float velocity) {
            this.velocity = velocity;
        }
    }
    public enum Direction {
        S(0f, -1f),
        W(-1f, 0),
        E(1f, 0),
        N(0f, 1f),
        SW(-0.7f, -0.7f),
        NW(-0.7f, 0.7f),
        SE(0.7f, -0.7f),
        NE(0.7f, 0.7f);

        float dx, dy;
        Direction(float dx, float dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
    private final static float VELOCITY = 100f;
    private final static int DIRECTION_NUM = Direction.values().length;
    private final static int FRAME_NUM = 8;
    private float stateTime = 0.0f;
    private float moveTime = 0.0f;
    private final TextureRegion[][] walkFrame = new TextureRegion[DIRECTION_NUM][FRAME_NUM];
    private final TextureRegion[][] runFrame = new TextureRegion[DIRECTION_NUM][FRAME_NUM];

    private final Animation<TextureRegion>[] walkAnimations = new Animation[DIRECTION_NUM];
    private final Animation<TextureRegion>[] runAnimations = new Animation[DIRECTION_NUM];

    private Direction direction = Direction.N;
    private State state = State.STAY;
    private World world;
    public Character() {
        Texture walk_texture = Assets.HERO_WALK.get();
        Texture run_texture = Assets.HERO_RUN.get();

        final int frameWidth = walk_texture.getWidth()/FRAME_NUM;
        final int frameHeight = walk_texture.getHeight()/DIRECTION_NUM;

        for(int i=0; i<DIRECTION_NUM; ++i) {
            for (int j = 0; j < FRAME_NUM; ++j) {
                walkFrame[i][j] = new TextureRegion(walk_texture, j * frameWidth, i * frameHeight, frameWidth, frameHeight);
                runFrame[i][j] = new TextureRegion(run_texture, j * frameWidth, i * frameHeight, frameWidth, frameHeight);

            }
            walkAnimations[i] = new Animation<TextureRegion>(0.1f, walkFrame[i]);
            runAnimations[i] = new Animation<TextureRegion>(0.09f, runFrame[i]);
        }
        setSize(walkFrame[0][0].getRegionWidth(),walkFrame[0][0].getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        if(state == State.WALK || state == State.RUN) {
            moveTime += delta;
            world.clearBlocked(getStandPosition());
            if(moveTime > 2f) {
                state = State.RUN;
            }
            float dx = this.direction.dx*state.velocity*delta;
            float dy = this.direction.dy*state.velocity*delta;
            if(world.isWalkable(getStandPosition().add(dx,dy))) {
                moveBy(dx, dy);
            }
            world.setBlocked(getStandPosition());
        } else {
            moveTime = 0.0f;
        }
    }

    public void setWorld(World world) {
        this.world = world;
        world.setBlocked(getStandPosition());
    }

    public void setState(State state) {
        if(state == State.STAY || (state == State.WALK && this.state == State.STAY))
        this.state = state;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(state == State.WALK) {
            TextureRegion currentFrame = walkAnimations[direction.ordinal()].getKeyFrame(stateTime, true);
            batch.draw(currentFrame, getX(), getY());
        } else if(state == State.RUN) {
            TextureRegion currentFrame = runAnimations[direction.ordinal()].getKeyFrame(stateTime, true);
            batch.draw(currentFrame, getX(), getY());
        } else {
            batch.draw(walkFrame[direction.ordinal()][0], getX(), getY());
        }
    }

    public void setMovement(Direction direction) {
        this.direction = direction;
        this.state = State.WALK;
    }

    public void stopMovement() {
        this.state = State.STAY;
    }

    public Vector2 getStandPosition() {
        return new Vector2(getX()+getWidth()/2f, getY()+20f);
    }
}
