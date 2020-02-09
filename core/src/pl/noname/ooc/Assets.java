package pl.noname.ooc;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public enum  Assets {
    SPLASH("splash.png", Texture.class),
    UISKIN("skin/uiskin.json", Skin.class),
    MENU_BG("menubg.png", Texture.class),
    LOGO("logo.atlas", TextureAtlas.class),
    HERO_WALK("character_walking.png", Texture.class),
    HERO_RUN("character_running.png", Texture.class),
    MAP("map/map.tmx",TiledMap.class);

    private String filepath;
    Class type;
    private static AssetManager assetManager = new AssetManager();
    static {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    }

    Assets(String filepath, Class type) {
        this.filepath = filepath;
        this.type = type;
    }

    public void load() {
        assetManager.load(filepath, type);
    }

    public static void loadAll() {
        for(Assets asset : Assets.values()) {
            asset.load();
        }
    }

    public <T>T get() {
        return (T) type.cast(assetManager.get(filepath, type));
    }

    public void waitForFinish() {
        assetManager.finishLoadingAsset(filepath);
    }

    public static AssetManager getManager() {
        return assetManager;
    }
}
