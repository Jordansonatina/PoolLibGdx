package com.jordansonatina.game;

import com.badlogic.gdx.math.Vector2;

public class Ball
{
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;

    private float radius;

    public Ball()
    {
        pos = new Vector2((float) Constants.WIDTH /2, (float) Constants.HEIGHT /2);
        vel = new Vector2(0, 0);
        acc = new Vector2(0, 0);

        radius = 10f;
    }

    public Ball(float x, float y)
    {
        pos = new Vector2(x, y);
        vel = new Vector2(0, 0);
        acc = new Vector2(0, 0);
        radius = 10f;
    }

    public void physics()
    {

        pos.add(vel);
        vel.add(acc);

        acc = new Vector2(0, 0);
    }

    public void bounce(Ball other)
    {
        Vector2 otherDirection = new Vector2(other.getPos().x - pos.x, other.getPos().y - pos.y);
        otherDirection.nor();

        double angleHitBall = Math.atan2(otherDirection.y, otherDirection.x);
        double angleCueBall = Math.abs(angleHitBall - Math.PI/2);
        double magnitudeCueBall = Math.sqrt(vel.x * vel.x + vel.y * vel.y);

        Vector2 newCueBallDirection = new Vector2((float)(magnitudeCueBall * Math.cos(angleCueBall)), (float)(magnitudeCueBall * Math.sin(angleCueBall)));
        newCueBallDirection.nor();

        double newCueBallVelocity = Math.abs((Math.sin(angleCueBall)*vel.x - Math.cos(angleCueBall)*vel.y) / (Math.cos(angleCueBall)*Math.sin(angleHitBall)+Math.sin(angleCueBall)*Math.cos(angleHitBall)));
        double newOtherBallVelocity = magnitudeCueBall - newCueBallVelocity;
        System.out.println("Magnitude of Cue Ball: " + magnitudeCueBall);
        System.out.println("New Vel: " + newCueBallVelocity);

        //vel = vel.mulAdd(newCueBallDirection, (float)newCueBallVelocity);
        vel = new Vector2((float)newCueBallVelocity * newCueBallDirection.x, (float)newCueBallVelocity * newCueBallDirection.y);
        other.vel = new Vector2((float)newOtherBallVelocity * otherDirection.x, (float)newOtherBallVelocity * otherDirection.y);
    }

    public boolean colliding(Ball other)
    {
        float distance = (float)Math.sqrt(Math.pow(pos.x - other.getPos().x, 2) + Math.pow(pos.y - other.getPos().y, 2));
        return distance <= radius*2;
    }

    private void addForce(Vector2 n)
    {
        acc.add(new Vector2(n.x, n.y));
    }

    public Vector2 getPos() {return pos;}

    public void setVel(Vector2 n) {vel = n;}
    public Vector2 getVel() {return vel;}

    public float getRadius() {return radius;}
}
