package com.brotherhood.wizards.processing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojtek on 2015-08-26.
 */
public class GestureProcessor implements InputProcessor {

    private float   wayX,
                    wayY,
                    startX,startY,
                    endX,endY,
                    marginX,marginY;
    private GestureListener gestureListener;
    private GestureDirection verticalDirection,//pionowy kierunek
                             horizontalDirection;//poziomy kierunek
    private List<Point>     movementPath = new ArrayList<Point>();
    private boolean          unTouched = false;

    public GestureProcessor()
    {
        marginX = Gdx.app.getGraphics().getWidth() * .2f;
        marginY = Gdx.app.getGraphics().getHeight() * .2f;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        startX = screenX;
        startY = screenY;
        unTouched = false;
        movementPath.clear();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        endX = screenX;
        endY = screenY;
        wayX = endX-startX;
        wayY = endY-startY;

        horizontalDirection = startX-endX < 0 ? GestureDirection.RIGHT : GestureDirection.LEFT;
        verticalDirection = startY-endY < 0 ? GestureDirection.DOWN : GestureDirection.UP;

        if(Math.abs(startX-endX)<=marginX && Math.abs(startY-endY)>marginY){
            if(verticalDirection==GestureDirection.UP)
                gestureListener.onSimpleAttackGesture();
            else
                gestureListener.onSimpleDefenceGesture();

        }else if(Math.abs(startX-endX)>marginX && Math.abs(startY-endY)<=marginY){
                gestureListener.onJumpGesture(horizontalDirection,wayX);

        }else if(Math.abs(startX-endX)>marginX && Math.abs(startY-endY)>marginY){
            if(verticalDirection==GestureDirection.UP)
                gestureListener.onDirectionalAttackGesture(startX, startY, endX, endY, horizontalDirection);
            else
                gestureListener.onDirectionalDefenceGesture(startX,startY,endX,endY,horizontalDirection);
        }else
            gestureListener.onClick(endX,endY);

        unTouched = true;

        return false;
    }

    public boolean isUnTouched() {
        return unTouched;
    }

    public void setGestureListener(GestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        movementPath.add(new Point(screenX,screenY));
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

    public List<Point> getMovementPath() {
        return movementPath;
    }

    public interface GestureListener{
        void onSimpleAttackGesture();
        void onSimpleDefenceGesture();
        void onDirectionalAttackGesture(float startX,float startY,float endX,float endY,GestureDirection horizontalDirection);
        void onDirectionalDefenceGesture(float startX,float startY,float endX,float endY,GestureDirection horizontalDirection);
        void onJumpGesture(GestureDirection direction,float wayX);
        void onClick(float x,float y);
    }

    public enum GestureDirection{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public class Point{
        public int x,y;

        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
