package com.brotherhood.wizards.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by Wojciech Osak on 2015-09-08.
 */
public abstract class UnitsHelper {
    public static Camera worldCamera;//must be set when engine starts!

    public static Vector3 physicUnitToPixels(float physicX, float physicY){
        Vector3 vector3 = new Vector3(physicX,physicY,0);
        return worldCamera.project(vector3);
    }

    public static Vector3 pixelsToPhysicUnit(float physicX, float physicY){
        Vector3 vector3 = new Vector3(physicX,physicY,0);
        return worldCamera.unproject(vector3);
    }

    public static Vector3 physicUnitToPixels(Vector2 pos){
        Vector3 tmp = new Vector3(pos.x,pos.y,0);
        return worldCamera.project(tmp);
    }
}
