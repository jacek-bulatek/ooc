package com.mygdx.game.view;

import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.view.characters.MainCharacter;

import static java.sql.Types.NULL;

/**
 * Created by Jacek on 2020-01-18.
 */

public class View implements Disposable {
    private Array<Drawables> componentDrawables;
    private FileHandle basicView;
    public MainCharacter mainCharacter;
    float[] cameraPosition;

    public static Resolution  resolution = new Resolution();

    public View(FileHandle basicView)
    {
        cameraPosition = new float[] {0, 0};
        mainCharacter = new MainCharacter(1280/2 - 50, 720/2);
        this.basicView = basicView;
        componentDrawables = new Array<Drawables>();
        buildBasic(basicView);
    }

    public void setCameraPosition(float[] cameraPosition) {this.cameraPosition = cameraPosition;}

    public void display(SpriteBatch spriteBatch, float elapsedTime)
    {
        mainCharacter.getSprite(elapsedTime).draw(spriteBatch);
        for(Drawables drawable : componentDrawables)
        {
            Sprite tmp = new Sprite(drawable.getSprite(elapsedTime));
            tmp.translate(-cameraPosition[0], -cameraPosition[1]);
            tmp.draw(spriteBatch);
        }
    }

    public void addComponentDrawable(Drawables drawable)
    {
        componentDrawables.add(drawable);
    }

    public static void setResolution(Resolution _resolution) { resolution = _resolution; }

    private void buildBasic(FileHandle file)
    {
        if(file == null)
            return;
        else
            return;
    }

    @Override
    public void dispose()
    {

    }
}
