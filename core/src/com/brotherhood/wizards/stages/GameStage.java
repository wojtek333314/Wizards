package com.brotherhood.wizards.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.brotherhood.wizards.enums.PlayerType;
import com.brotherhood.wizards.player.Player;
import com.brotherhood.wizards.processing.BodyContactProcessor;
import com.brotherhood.wizards.processing.MouseSwipeProcessor;
import com.brotherhood.wizards.utils.PhysicWorldUtils;

/**
 * Created by Wojtek on 2015-08-25.
 */
public class GameStage extends Stage
{
    public static final int VIEWPORT_WIDTH = 10;
    public static final int VIEWPORT_HEIGHT = 20;
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private MouseSwipeProcessor mouseSwipeProcessor;//przetwarzanie swipów
    private BodyContactProcessor bodyContactProcessor;

    private World world;
    private Player player1,
                   player2;
    private Vector3 touchPoint;

    public GameStage() {
        mouseSwipeProcessor = new MouseSwipeProcessor()
        {
            @Override
            public void onSwipeXAxis(float wayX) {
                if(wayX > 0)
                    player1.jumpRight(wayX);
                if(wayX < 0)
                    player1.jumpLeft(wayX);

                super.onSwipeXAxis(wayX);
            }

            @Override
            public void onSwipeYAxis(float wayY) {
                super.onSwipeYAxis(wayY);
            }
        };


        world = PhysicWorldUtils.createWorld();
        renderer = new Box2DDebugRenderer();
        setUpContactProcessor();
        createWalls();
        setUpPlayer1();
        setUpPlayer2();
        setupCamera();
        setupTouchControlAreas();
    }

    private void setUpPlayer1()
    {
        player1 = new Player(PlayerType.PLAYER_1,world);
        addActor(player1);
    }

    private void setUpPlayer2()
    {
        player2 = new Player(PlayerType.PLAYER_2,world);
        addActor(player2);
    }

    public void createWalls() {
        //left wall
        BodyDef bodyDefLeft = new BodyDef();
        bodyDefLeft.position.set(new Vector2(0,0));
        Body bodyLeft = world.createBody(bodyDefLeft);
        PolygonShape shapeLeft = new PolygonShape();
        shapeLeft.setAsBox(0,VIEWPORT_HEIGHT);
        bodyLeft.createFixture(shapeLeft, 0);
        shapeLeft.dispose();

        //right wall

        BodyDef bodyDefRight = new BodyDef();
        bodyDefRight.position.set(new Vector2(VIEWPORT_WIDTH+.01f,0));
        Body bodyRight = world.createBody(bodyDefRight);
        PolygonShape shapeRight = new PolygonShape();
        shapeRight.setAsBox(0,VIEWPORT_HEIGHT);
        bodyRight.createFixture(shapeRight, 0);
        shapeRight.dispose();
    }


    private void setUpContactProcessor()
    {
        bodyContactProcessor = new BodyContactProcessor();
        world.setContactListener(bodyContactProcessor);
    }
    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mouseSwipeProcessor.touchDragged(screenX, screenY, pointer);
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        mouseSwipeProcessor.touchDown(x, y, pointer, button);
        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mouseSwipeProcessor.touchUp(screenX, screenY, pointer, button);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

}
