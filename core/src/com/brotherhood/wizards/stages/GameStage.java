package com.brotherhood.wizards.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.brotherhood.wizards.enums.PlayerType;
import com.brotherhood.wizards.player.Player;
import com.brotherhood.wizards.utils.PhysicWorldUtils;

/**
 * Created by Wojtek on 2015-08-25.
 */
public class GameStage extends Stage
{
    private static final int VIEWPORT_WIDTH = 10;
    private static final int VIEWPORT_HEIGHT = 20;
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private World world;
    private Player player1;
     private Vector3 touchPoint;


    public GameStage() {
        world = PhysicWorldUtils.createWorld();
        setUpPlayer1();
        renderer = new Box2DDebugRenderer();
        setupCamera();
        setupTouchControlAreas();
    }

    private void setUpPlayer1()
    {
        player1 = new Player(PlayerType.PLAYER_1,world);
        addActor(player1);
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        System.out.println(screenX);

        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);
        Vector3 touchPos = new Vector3(x,y, 0);
        camera.unproject(touchPos);

        if(touchPos.x > player1.getBody().getPosition().x)
        {
            player1.jumpRight();
            System.out.println("right");
        }
        if(touchPos.x < player1.getBody().getPosition().x)
        {
            player1.jumpLeft();
            System.out.println("left");
        }

        return super.touchDown(x, y, pointer, button);
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

        //TODO: Implement interpolation

    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    /**
     * Helper function to get the actual coordinates in my world
     * @param x
     * @param y
     */
    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }
}
