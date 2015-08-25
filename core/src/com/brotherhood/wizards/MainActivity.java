package com.brotherhood.wizards;

import com.badlogic.gdx.Game;
import com.brotherhood.wizards.scenes.GameMultiplayer;

public class MainActivity extends Game
{

	@Override
	public void create() {
		setScreen(new GameMultiplayer());
	}
}
