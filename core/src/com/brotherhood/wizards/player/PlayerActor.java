package com.brotherhood.wizards.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.brotherhood.wizards.enums.PlayerType;
import com.brotherhood.wizards.serverUtils.ServerConstants;
import com.brotherhood.wizards.stages.GameStage;
import com.brotherhood.wizards.utils.SharedPreferences;

/**
 * Created by Wojtek on 2015-08-25.
 * Obiekt przedstawiany podczas gry,tworzony przez obiekt Player'a przez odpowiednia metode
 * fabrykujaca.
 */
public class PlayerActor extends Actor
{
    private float   bodyX,bodyY,
                    bodyWidth,bodyHeight;

    private float density;//gestosc body
    private float friction;//tarcie
    private float impulsePower = 9f;//power of move
    private Body    body;
    private PlayerType playerType;
    private World   worldHandle;
    private Player playerProperties;

    public PlayerActor(PlayerType playerType,World world)
    {
        this.playerType = playerType;
        this.worldHandle = world;
        loadPlayerFromCache();
        createBody();
    }

    private void loadPlayerFromCache()
    {
        this.playerProperties = new Player(SharedPreferences.getString(ServerConstants.USER_CACHE_KEY));
    }

    private void createBody()
    {
        switch(playerType)
        {
            case PLAYER_1:
                bodyX = 2;
                bodyY = 0.5f;
                bodyWidth = 0.5f;
                bodyHeight = 0.5f;
                break;
            case PLAYER_2:
                bodyWidth = 0.5f;
                bodyHeight = 0.5f;
                bodyX = 3;
                bodyY = GameStage.VIEWPORT_HEIGHT - bodyHeight;
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
        body.createFixture(shape, density);
        body.setLinearDamping(friction);
        body.getFixtureList().get(0).setFriction(friction);
        body.resetMassData();
        shape.dispose();

        this.body = body;
    }

    public void jumpRight(float swipeWayX)
    {
        impulsePower = (float) ((swipeWayX/Gdx.graphics.getWidth()) * playerProperties.getSpellBook().getSpell(0).getSpeed());
        body.setLinearVelocity(0,0);
        body.applyLinearImpulse(new Vector2(impulsePower,0),body.getWorldCenter(),true);
    }

    public void jumpLeft(float swipeWayX)
    {
        impulsePower = (float) ((-swipeWayX/ Gdx.graphics.getWidth()) * playerProperties.getSpellBook().getSpell(0).getSpeed());
        body.setLinearVelocity(0,0);
        body.applyLinearImpulse(new Vector2(-impulsePower, 0), body.getWorldCenter(), true);
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
