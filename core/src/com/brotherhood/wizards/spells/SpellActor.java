package com.brotherhood.wizards.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brotherhood.wizards.player.PlayerActor;
import com.brotherhood.wizards.spells.dto.SpellDTO;
import com.brotherhood.wizards.utils.BodyData;

/**
 * Created by Wojciech Osak on 2015-09-06.
 */
public class SpellActor extends Actor {
    private float   bodyX,bodyY,bodyWidth,bodyHeight;
    private float density;//gestosc body
    private Body body;
    private World worldHandle;
    private SpellDTO properties;

    public SpellActor(SpellDTO spellDTO,World world){
        worldHandle = world;
        properties = spellDTO;
        createBody();
    }

    private void createBody(){
        bodyX = 2;
        bodyY = 0.5f;
        bodyWidth = 0.1f;
        bodyHeight = 0.1f;
        density = 0.5f;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(bodyX, bodyY));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bodyWidth, bodyHeight);
        Body body = worldHandle.createBody(bodyDef);
        body.createFixture(shape, density);
        shape.dispose();

        this.body = body;
    }

    public void use(PlayerActor playerActor){
        //todo position for playeractor
        Texture texture = new Texture(Gdx.files.internal("tempSkill.png"));
        Sprite sprite = new Sprite(texture, -24,-20, 24, 20);
        body.setUserData(new BodyData(sprite));
        body.setTransform(playerActor.getBody().getPosition().x + playerActor.getWidth() / 2
                , playerActor.getBody().getPosition().y + playerActor.getBodyHeight() + bodyHeight*2
                ,0);
        body.applyLinearImpulse(new Vector2(0, properties.getSpeed()), body.getWorldCenter(), true);
    }
}
