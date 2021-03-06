package com.brotherhood.wizards.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brotherhood.wizards.constants.Constants;
import com.brotherhood.wizards.enums.PlayerType;
import com.brotherhood.wizards.spells.SpellActor;
import com.brotherhood.wizards.utils.BodyData;

/**
 * Created by Wojtek on 2015-08-25.
 * Obiekt przedstawiany podczas gry,tworzony przez obiekt Player'a przez odpowiednia metode
 * fabrykujaca.
 */
public class PlayerActor extends Actor
{
    private float bodyWidth,bodyHeight;
    private float density;//gestosc body
    private float friction;//tarcie
    private float impulsePower = 9f;//power of move
    private Body    body;
    private PlayerType playerType;
    private World   worldHandle;
    private Player playerProperties;
    private String nick;

    public PlayerActor(PlayerType playerType,World world,String nick)
    {
        this.nick = nick;
        this.playerType = playerType;
        this.worldHandle = world;
        this.playerProperties = new Player(nick);
        createBody();
    }

    private void createBody()
    {
        float bodyX = 0,bodyY = 0;
        switch(playerType)
        {
            case PLAYER_1:
                bodyX = 3;
                bodyY = 0.5f;
                bodyWidth = 0.5f;
                bodyHeight = 0.5f;
                break;
            case PLAYER_2:
                bodyWidth = 0.5f;
                bodyHeight = 0.5f;
                bodyX = 3;
                bodyY = Constants.VIEWPORT_HEIGHT - bodyHeight;
                break;
            case OPPONENT_1:
                    //todo
                break;
            case OPPONENT_2:
                    //todo
                break;
        }
        density = 0.5f;
        friction = 0.9f;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(bodyX, bodyY));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bodyWidth, bodyHeight);
        Body body = worldHandle.createBody(bodyDef);

        BodyData bodyData = new BodyData(null);//todo sprite
        bodyData.setEnumType(playerType);
        body.setUserData(bodyData);
        body.createFixture(shape, density);
        body.setLinearDamping(friction);
        body.getFixtureList().get(0).setFriction(friction);
        body.resetMassData();
        shape.dispose();

        this.body = body;
    }

    public void jumpRight(float swipeWayX)
    {
        impulsePower = ((swipeWayX/Gdx.graphics.getWidth()) * 2);
        body.setLinearVelocity(0,0);
        body.applyLinearImpulse(new Vector2(impulsePower, 0), body.getWorldCenter(), true);
    }

    public void jumpLeft(float swipeWayX)
    {
        impulsePower = ((-swipeWayX/ Gdx.graphics.getWidth()) * 2f);
        body.setLinearVelocity(0, 0);
        body.applyLinearImpulse(new Vector2(-impulsePower, 0), body.getWorldCenter(), true);
    }

    public void simpleAttack(){
        new SpellActor(playerProperties.getAttackSpell(),worldHandle).use(this);
    }

    public Body getBody() {
        return body;
    }

    public float getBodyHeight() {
        return bodyHeight;
    }

    public float getBodyWidth() {
        return bodyWidth;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Player getPlayerProperties() {
        return playerProperties;
    }
}
