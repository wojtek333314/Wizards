package com.brotherhood.wizards.processing;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.brotherhood.wizards.enums.PlayerType;
import com.brotherhood.wizards.enums.SpellType;
import com.brotherhood.wizards.utils.BodyData;

/**
 * Created by Wojtek on 2015-08-26.
 * Klasa wykorzystywana do obslugi wszystkich kontaktow miedzy Body
 * Dla scian dziala poki co :)
 */
public class BodyContactProcessor implements ContactFilter,ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

    }

    private boolean isPlayerSpell(Body a,Body b){
        if(a.getUserData()==null || b.getUserData()==null ||
                ((BodyData) a.getUserData()).getEnumType()==null || ((BodyData) b.getUserData()).getEnumType()==null)
            return false;
        return (((BodyData) a.getUserData()).getEnumType().getDeclaringClass().equals(SpellType.class)
                || ((BodyData) a.getUserData()).getEnumType().getDeclaringClass().equals(PlayerType.class)) &&
                (((BodyData) b.getUserData()).getEnumType().getDeclaringClass().equals(SpellType.class)
                        || ((BodyData) b.getUserData()).getEnumType().getDeclaringClass().equals(PlayerType.class));
    }

    private void playerSpellReact(Body a,Body b){
        if(((BodyData) a.getUserData()).getEnumType().equals(SpellType.class))
            ((BodyData) a.getUserData()).setToDelete(true);
        else
            ((BodyData) b.getUserData()).setToDelete(true);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        Body a = fixtureA.getBody();
        Body b = fixtureB.getBody();

        if(isPlayerSpell(a,b)){
            playerSpellReact(a,b);
            return false;
        }

        return true;
    }
}
