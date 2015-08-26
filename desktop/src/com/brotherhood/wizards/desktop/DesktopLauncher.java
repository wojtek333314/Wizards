package com.brotherhood.wizards.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brotherhood.wizards.MainActivity;
import com.brotherhood.wizards.constants.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.APP_WIDTH;
		config.height = Constants.APP_HEIGHT;
        config.title = "Wizards";
		new LwjglApplication(new MainActivity(), config);
	}
}
