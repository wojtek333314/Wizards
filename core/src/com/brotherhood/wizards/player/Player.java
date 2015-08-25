package com.brotherhood.wizards.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brotherhood.wizards.enums.PlayerType;

/**
 * Created by Wojtek on 2015-08-25.
 */
public class Player extends Actor
{
    private Body body;
    private float   bodyX,bodyY,
                    bodyWidth,bodyHeight;
    private PlayerType playerType;
    private World   worldHandle;
    private float density;//gestosc body
    private float friction;//tarcie

    private Vector2 impulseJumpRight = new Vector2(1f,0),
                    impulseJumpLeft = new Vector2(-1,0);


    public Player(PlayerType playerType,World world)
    {
        this.playerType = playerType;
        this.worldHandle = world;
        createBody();
    }

    private void createBody()
    {
        switch(playerType)
        {
            case PLAYER_1:
                bodyX = 2;
                bodyY = 1;
                bodyWidth = 1;
                bodyHeight = 1;
                break;
            case PLAYER_2:
                bodyX = 0;
                bodyY = 8;
                bodyWidth = 2;
                bodyHeight = 2;
                break;
            case OPPONENT_1:
                    //todo
                break;
            case OPPONENT_2:
                    //todo
                break;
        }
        density = 0.5f;
        friction = 0.5f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(bodyX, bodyY));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bodyWidth, bodyHeight);
        Body body = worldHandle.createBody(bodyDef);
        body.createFixture(shape, density);
        body.getFixtureList().get(0).setFriction(friction);
        body.resetMassData();
        shape.dispose();

        this.body = body;
    }

    public void jumpRight()
    {
        body.applyLinearImpulse(impulseJumpRight,body.getWorldCenter(),true);
    }

    public void jumpLeft()
    {
        body.applyLinearImpulse(impulseJumpLeft,body.getWorldCenter(),true);
    }

    public float getBodyX() {
        return bodyX;
    }

    public float getBodyY() {
        return bodyY;
    }

    public Body getBody() {
        return body;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
