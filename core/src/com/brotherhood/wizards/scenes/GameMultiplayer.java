package com.brotherhood.wizards.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.brotherhood.wizards.stages.GameStage;

/**
 * Created by Wojtek on 2015-08-25.
 */
public class GameMultiplayer implements Screen {
    private GameStage stage;

    public GameMultiplayer()
    {
        stage = new GameStage();
    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
