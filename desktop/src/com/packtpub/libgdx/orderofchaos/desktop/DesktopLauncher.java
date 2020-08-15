package com.packtpub.libgdx.orderofchaos.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.packtpub.libgdx.orderofchaos.OrderOfChaos;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DesktopLauncher {
	public static String TAG = DesktopLauncher.class.getSimpleName();

	public static void main (String[] arg) throws FileNotFoundException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		PrintStream logFile = new PrintStream("game.log");
		System.setOut(logFile);
		System.setErr(logFile);

		Application app = new LwjglApplication(new OrderOfChaos(arg), config);
		app.setLogLevel(Application.LOG_ERROR);
		if(arg.length > 0){
			for(int i = 0; i < arg.length; i++){
				if(arg[i].toLowerCase().equals("debug")){
					app.setLogLevel(Application.LOG_DEBUG);
					app.debug(TAG,"Log level set to DEBUG");
				}
			}
		}

		config.title = "OOC";
		config.useGL30 = false;
		config.width = 1280;
		config.height = 720;

		Gdx.app = app;
	}
}
