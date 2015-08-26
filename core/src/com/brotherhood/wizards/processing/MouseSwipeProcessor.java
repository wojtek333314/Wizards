package com.brotherhood.wizards.processing;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by Wojtek on 2015-08-26.
 */
public class MouseSwipeProcessor implements InputProcessor {

    private float   wayX,
                    wayY,
                    startX,startY,
                    endX,endY;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        startX = screenX;
        startY = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        endX = screenX;
        endY = screenY;
        wayX = endX-startX;
        wayY = endY-startY;

        if(Math.abs(wayX)>Math.abs(wayY))
            onSwipeXAxis(wayX);
        if(Math.abs(wayX)<Math.abs(wayY))
            onSwipeYAxis(wayY);
        if(Math.abs(wayX)==Math.abs(wayY))
        {
            onSwipeXAxis(wayX);
            onSwipeYAxis(wayY);
        }


        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void onSwipeXAxis(float wayX)
    {

    }

    public void onSwipeYAxis(float wayY)
    {

    }

}
