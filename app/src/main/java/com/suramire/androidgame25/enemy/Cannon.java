package com.suramire.androidgame25.enemy;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.suramire.androidgame25.EnemyBullet;
import com.suramire.androidgame25.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suramire on 2017/12/10.
 */

public class Cannon extends Enemy {

    private List<Sprite> bullets;
    private long delay2;

    public List<Sprite> getBullets() {
        return bullets;
    }

    public void setBullets(List<Sprite> bullets) {
        this.bullets = bullets;
    }

    public Cannon(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
        bullets = new ArrayList<>();
    }

    @Override
    public void logic() {
        if(delay2++>50){
            fire();
            delay2=0;
        }
        if(bullets!=null){
            for (int i = 0; i <bullets.size(); i++) {
                bullets.get(i).logic();
            }
        }
    }

    public void fire(){
        if(bullets!=null){
            for (int i = 0; i < bullets.size(); i++) {
                EnemyBullet enemyBullet = (EnemyBullet) bullets.get(i);
                if(!enemyBullet.isVisiable()){
                    enemyBullet.setVisiable(true);
                    enemyBullet.setDead(false);
                    if(enemyBullet.isMirror()){
                        enemyBullet.setPosition(getX()+getWidth()-10,getY()+6);
                    }else{
                        enemyBullet.setPosition(getX()-10,getY()+6);
                    }

                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(bullets!=null){
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(canvas);
            }
        }
    }
}
