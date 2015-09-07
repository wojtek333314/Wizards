package com.brotherhood.wizards.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Wojciech Osak on 2015-09-08.
 */
public class BodyData {
    private Sprite sprite;

    public BodyData(Sprite sprite){
        setSprite(sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
