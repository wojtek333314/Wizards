package com.brotherhood.wizards.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.brotherhood.wizards.utils.UnitsHelper;

/**
 * Created by Wojciech Osak on 2015-09-10.
 */
public class ParticleSystem {
    private ParticleEffectPool explosionEffect;
    private Array<ParticleEffectPool.PooledEffect> effects = new Array();
    private Batch stageBatch;

    public ParticleSystem(Batch batch){
        stageBatch = batch;
        createExplosionEffect();
    }

    private void createExplosionEffect(){
        ParticleEffect bombEffect = new ParticleEffect();
        bombEffect.load(Gdx.files.internal("effects/explosion.p"), Gdx.files.internal("effects"));
        explosionEffect = new ParticleEffectPool(bombEffect, 1, 2);
    }

    public void createExplosionEffect(float x,float y){
        ParticleEffectPool.PooledEffect effect = explosionEffect.obtain();
        float a[] = {0.1f,0.5f,0.4f};
        effect.getEmitters().get(0).getTint().setColors(a);

        float centerDistanceY = 0;
        if(y>Gdx.app.getGraphics().getHeight()/2){
            centerDistanceY = (y-Gdx.app.getGraphics().getHeight()/2)*2;
            y-=centerDistanceY;
        }else
        {
            centerDistanceY = ((Gdx.app.getGraphics().getHeight()/2)-y)*2;
            y+=centerDistanceY;
        }
        effect.setPosition(x, y);
        effects.add(effect);
    }

    public void createExplosionEffect(Vector2 position,float color[]){
        ParticleEffectPool.PooledEffect effect = explosionEffect.obtain();
        effect.getEmitters().get(0).getTint().setColors(color);
        Vector3 pxPos =  UnitsHelper.physicUnitToPixels(position);
        effect.setPosition(pxPos.x, pxPos.y);
        effects.add(effect);
    }

    public void createExplosionEffect(float x,float y,float color[]){
        ParticleEffectPool.PooledEffect effect = explosionEffect.obtain();
        effect.getEmitters().get(0).getTint().setColors(color);

        float centerDistanceY = 0;
        if(y>Gdx.app.getGraphics().getHeight()/2){
            centerDistanceY = (y-Gdx.app.getGraphics().getHeight()/2)*2;
            y-=centerDistanceY;
        }else
        {
            centerDistanceY = ((Gdx.app.getGraphics().getHeight()/2)-y)*2;
            y+=centerDistanceY;
        }
        effect.setPosition(x, y);
        effects.add(effect);
    }


    public void drawEffects(float deltaTime){
        for (int i = effects.size - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);
            effect.draw(stageBatch,deltaTime);
            if (effect.isComplete()) {
                effect.free();
                effects.removeIndex(i);
            }
        }
    }

    public void resetAllEffects(){
        for (int i = effects.size - 1; i >= 0; i--)
            effects.get(i).free();
        effects.clear();
    }
}
