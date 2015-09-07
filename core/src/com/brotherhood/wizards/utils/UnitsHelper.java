package com.brotherhood.wizards.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by Wojciech Osak on 2015-09-08.
 */
public abstract class UnitsHelper {
    public static float worldUnitToPixels(Camera camera,float worldUnit){
        Vector3 vector3 = new Vector3(worldUnit,0,0);
        return camera.project(vector3).x;
    }
}
