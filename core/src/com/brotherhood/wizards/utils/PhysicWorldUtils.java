package com.brotherhood.wizards.utils;

import com.badlogic.gdx.physics.box2d.World;
import com.brotherhood.wizards.constants.Constants;

/**
 * Created by Wojtek on 2015-08-25.
 */
public class PhysicWorldUtils {


    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }


}