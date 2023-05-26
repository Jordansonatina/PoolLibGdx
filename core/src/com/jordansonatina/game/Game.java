package com.jordansonatina.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
public class Game extends ApplicationAdapter {
	Ball cueBall;

	Ball eightBall;

	ShapeRenderer renderer;
	@Override
	public void create () {
		renderer = new ShapeRenderer();
		cueBall = new Ball(200, 200);

		cueBall.setVel(new Vector2(1*3, 0.8f*3));

		eightBall = new Ball(300, 300);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0.5f, 0, 1);

		cueBall.physics();
		eightBall.physics();

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(Color.WHITE);
		renderer.circle(cueBall.getPos().x, cueBall.getPos().y, cueBall.getRadius(),100);

		renderer.setColor(Color.BLACK);
		renderer.circle(eightBall.getPos().x, eightBall.getPos().y, eightBall.getRadius(),100);
		renderer.end();

		if (cueBall.colliding(eightBall))
		{
			cueBall.bounce(eightBall);
		}

	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
