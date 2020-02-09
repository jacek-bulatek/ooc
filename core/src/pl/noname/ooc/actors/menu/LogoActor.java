package pl.noname.ooc.actors.menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;
import pl.noname.ooc.Utility;

public class LogoActor extends VerticalGroup {
    private Actor order;
    private Actor of;
    private Actor chaos;
    private final static float FADE_DURATION = 0.75f;

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public LogoActor() {
        this.space(10f);
        TextureAtlas atlas = Assets.LOGO.get();
        order = Utility.actorFromSprite(atlas.createSprite("order"));
        of = Utility.actorFromSprite(atlas.createSprite("of"));
        chaos = Utility.actorFromSprite(atlas.createSprite("chaos"));
        order.setColor(0f,0f,0f,0f);
        of.setColor(0f,0f,0f,0f);
        chaos.setColor(0f,0f,0f,0f);
        addActor(order);
        addActor(of);
        addActor(chaos);
        setVisible(false);
    }

    @Override
    public void layout() {
        super.layout();
        chaos.moveBy(0, 20f);
    }

    @Override
    public void setVisible(boolean visible) {
        if(visible) {
            float delay = 0.0f;
            for(Actor actor : getChildren()) {
                actor.addAction(Actions.delay(delay, Actions.fadeIn(FADE_DURATION)));
                delay += FADE_DURATION;
            }
        }
        super.setVisible(visible);
    }
}
