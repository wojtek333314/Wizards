package com.brotherhood.wizards.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Wojciech Osak on 2015-09-08.
 */
public class BodyData {
    private Sprite sprite;
    private Enum enumType;
    private boolean toDelete = false;//to delete from physic world

    public BodyData(Sprite sprite){
        setSprite(sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Enum getEnumType() {
        return enumType;
    }

    public void setEnumType(Enum enumType) {
        this.enumType = enumType;
    }

    public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }
}
