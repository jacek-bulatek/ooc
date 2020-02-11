package pl.noname.ooc.actors.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuActor extends VerticalGroup {
    private final Skin skin;
    private static float MARGIN = 20f;
    public MenuActor(Skin skin) {
        this.skin = skin;
        super.space(MARGIN);
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
