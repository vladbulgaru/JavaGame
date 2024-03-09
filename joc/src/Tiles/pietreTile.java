package Tiles;

import Graphics.Assets;

public class pietreTile extends Tile{
    public pietreTile(int id){
        super(Assets.pietre, id);
    }
    public boolean isSolid(){
        return false;
    }

}
