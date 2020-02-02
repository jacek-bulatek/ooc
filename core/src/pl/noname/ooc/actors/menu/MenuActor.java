package pl.noname.ooc.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import pl.noname.ooc.OOC;

public class MenuActor extends VerticalGroup {
    private final Skin skin;
    private static float MARGIN = 20f;
    public MenuActor(Skin skin) {
        this.skin = skin;
        super.space(MARGIN);
    }

    public void addButton(String label, ChangeListener onclick) {
        Actor newBtn = new TextButton(label, skin);
        newBtn.addListener(onclick);
        addActor(newBtn);
    }

    public void addButton(String label, final Runnable onclick) {
        Actor newBtn = new TextButton(label, skin);
        newBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onclick.run();
            }
        });
        addActor(newBtn);
    }

    public void addButton(String label) {
        addActor(new TextButton(label, skin));
    }


}
