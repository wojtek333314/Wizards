package com.brotherhood.wizards.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.brotherhood.wizards.enums.PlayerType;
import com.brotherhood.wizards.player.PlayerActor;
import com.brotherhood.wizards.processing.BodyContactProcessor;
import com.brotherhood.wizards.processing.GestureProcessor;
import com.brotherhood.wizards.utils.BodyData;

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
    private static ShapeRenderer shapeRenderer;

    private GestureProcessor gestureProcessor;//przetwarzanie swipów
    private BodyContactProcessor bodyContactProcessor;

    private World world;
    private PlayerActor player1;
    private PlayerActor player2;
    private SpriteBatch stageBatch;

    public GameStage(String playerNick,String opponentNick) {
        setUpGestureProcessor();
        world = new World(new Vector2(0,0), true);
        renderer = new Box2DDebugRenderer();

        createWalls();
        setUpPlayer1(playerNick);
        setUpPlayer2(opponentNick);
        setupCamera();
        setUpContactProcessor();
        shapeRenderer = new ShapeRenderer();
        stageBatch = new SpriteBatch();
    }

    /** Obsluga dotyku i gestow dla sceny */

    private void setUpGestureProcessor(){
        Gdx.input.setInputProcessor(this);
        gestureProcessor = new GestureProcessor();
        gestureProcessor.setGestureListener(new GestureProcessor.GestureListener() {
            @Override
            public void onSimpleAttackGesture() {
                System.out.println("simpleAttackGesture");
                player1.simpleAttack();
            }

            @Override
            public void onSimpleDefenceGesture() {
                System.out.println("simpleDefenceGesture");
            }

            @Override
            public void onDirectionalAttackGesture(float startX, float startY, float endX, float endY, GestureProcessor.GestureDirection horizontalDirection) {
                System.out.println("DirectionalAttack");
            }

            @Override
            public void onDirectionalDefenceGesture(float startX, float startY, float endX, float endY, GestureProcessor.GestureDirection horizontalDirection) {
                System.out.println("DirectionalDefence");
            }

            @Override
            public void onJumpGesture(GestureProcessor.GestureDirection direction, float wayX) {
                if (direction == GestureProcessor.GestureDirection.LEFT)
                    player1.jumpLeft(wayX);
                else if (direction == GestureProcessor.GestureDirection.RIGHT)
                    player1.jumpRight(wayX);
            }

            @Override
            public void onClick(float x, float y) {
                System.out.println("CLICK:" + x + ":" + y);
            }

        });
    }

    private void setUpPlayer1(String playerNick)
    {
        player1 = new PlayerActor(PlayerType.PLAYER_1,world,playerNick);
        addActor(player1);
    }

    private void setUpPlayer2(String opponentNick)
    {
        player2 = new PlayerActor(PlayerType.PLAYER_2,world,opponentNick);
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

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        gestureProcessor.touchDragged(screenX, screenY, pointer);
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        gestureProcessor.touchDown(x, y, pointer, button);
        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gestureProcessor.touchUp(screenX, screenY, pointer, button);
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
        accumulator += delta;
        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void draw() {
        super.draw();
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        stageBatch.begin();
        for(int i=0;i<bodies.size;i++){
            if((bodies.get(i).getUserData())!=null && ((BodyData) bodies.get(i).getUserData()).getSprite()!=null){
                Vector3 positionBodyPixels = camera.project(new Vector3(
                            bodies.get(i).getPosition().x,
                            bodies.get(i).getPosition().y,
                            0
                ));
                ((BodyData) bodies.get(i).getUserData()).getSprite().setPosition(positionBodyPixels.x,positionBodyPixels.y);
                ((BodyData) bodies.get(i).getUserData()).getSprite().draw(stageBatch);
            }
        }
        stageBatch.end();
        renderer.render(world, camera.combined);
    }
}
