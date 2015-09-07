package com.brotherhood.wizards;

import com.badlogic.gdx.Game;
import com.brotherhood.wizards.player.Player;
import com.brotherhood.wizards.scenes.GameMultiplayer;
import com.brotherhood.wizards.utils.SharedPreferences;

public class MainActivity extends Game
{

	@Override
	public void create() {
		SharedPreferences.putString("userNick", "wojtas");


		new Player("wojtas")
		{
			@Override
			public void onLoadFinished(String json) {
				super.onLoadFinished(json);

				new Player("tester"){
					@Override
					public void onLoadFinished(String json) {
						super.onLoadFinished(json);
						setScreen(new GameMultiplayer("wojtas", "tester"));
					}
				};

			}
		};

	}
}
