package com.suramire.androidgame25.item;

import android.graphics.Bitmap;

import com.suramire.androidgame25.enums.ItemType;

import java.util.List;

/**
 * 砖块类
 */

public class Brick extends ItemSprite {
    //表示道具类型 枚举
    protected ItemType itemType;
    protected ItemSprite itemSprite;
    //标志道具是否已显示
    private boolean hasItem;

    //region Getter&Setter
    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
    public boolean hasItem() {
        return hasItem;
    }

    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

    public ItemSprite getItemSprite() {
        return itemSprite;
    }

    public void setItemSprite(ItemSprite itemSprite) {
        this.itemSprite = itemSprite;
    }

    //endregion

    public Brick(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
    }

    @Override
    public void logic() {
        if(isJumping()){
            if(hasItem){
                itemSprite.setVisiable(true);
                itemSprite.setPosition(getX(),getY()-getHeight());
                hasItem = false;
            }
            move(0,mSpeedY++);
            if(mSpeedY>4){
                setJumping(false);
            }
        }
        if(!hasItem){
            setFrameSequenceIndex(4);
        }
    }

    /**
     * 为砖块添加道具
     * @param e 是否添加标志位
     * @param bitmap 道具图片（单帧方式）
     */
    public void createItem(boolean e,Bitmap bitmap,ItemType type){
        setItemType(type);
        if(e){
            switch (type){
                case Mushroom:{
                    //蘑菇默认往右移动
                    itemSprite = new Mushroom(bitmap);
                    itemSprite.setMirror(true);
                }break;
                case Coin:{

                }break;
                case Flower:{
                    //花默认不移动
                    itemSprite = new Flower(bitmap);
                    itemSprite.setRunning(false);
                }break;
                case Star:{
                    itemSprite = new Star(bitmap);
                    itemSprite.setMirror(true);
                    itemSprite.setJumping(true);

                }break;
            }

            hasItem = true;
        }
    }

    /**
     * 为砖块添加道具
     * @param e 是否添加标志位
     * @param bitmaps 道具图片（多帧方式）
     */
    public void createItem(boolean e,List<Bitmap> bitmaps,ItemType type){
        setItemType(type);
        if(e){
            switch (type){

                case Coin:{
                    itemSprite = new Coin(40,40,bitmaps);
                    itemSprite.setRunning(false);
                }break;

            }

            hasItem = true;
        }
    }
}