package com.brotherhood.wizards.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;

/**
 * Created by Wojtek on 2015-08-17.
 */
public class GameObject extends Rectangle{
    private SpriteBatch batch;
    private Texture texture;
    private String textureFileName;

    private OnClickListener onClickListener;
    private UpdateListener  updateListener;

    public GameObject(String textureFilename)
    {
        this.textureFileName = textureFilename;
        batch = new SpriteBatch();
        texture = new Texture(textureFilename);
    }

    /**
     * GETTERS
     */


    public String getTextureFileName() {
        return textureFileName;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Texture getTexture() {
        return texture;
    }


    /**
     * SETTERS
     */

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }

    /**
     * METHODS
     */
    public void setOnClickListener(OnClickListener listener)
    {
        onClickListener = listener;
    }

    public void setUpdateListener(UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    public void draw()
    {
        if(updateListener!=null)//update before draw
            updateListener.update();

       /* Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);//position of click
        if(sprite.getBoundingRectangle().contains(input.x, input.y)) {
            if(onClickListener!=null)
                onClickListener.onClick(input.x,input.y);

        }*/

        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
    }


    /**
     * INTERFACES
     */

    public interface OnClickListener{
        boolean onClick(float x,float y);
    }

    public interface UpdateListener{
        void update();
    }

}
