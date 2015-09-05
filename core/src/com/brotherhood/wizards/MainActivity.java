package com.brotherhood.wizards;

import com.badlogic.gdx.Game;
import com.brotherhood.wizards.player.Player;
import com.brotherhood.wizards.scenes.GameMultiplayer;
import com.brotherhood.wizards.utils.SharedPreferences;

public class MainActivity extends Game
{

	@Override
	public void create() {

		SharedPreferences.putString("klucz2","haeuas");
		final Player player = new Player()
		{
			@Override
			public void onLoadFinished(String json) {
				super.onLoadFinished(json);
				setScreen(new GameMultiplayer());
			}
		};

	}
}
