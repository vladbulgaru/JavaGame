package Tiles;

import Graphics.Assets;


public class LavaTile extends Tile {
    public LavaTile(int id){
        super(Assets.lava, id);
    }
    public boolean isSolid(){
        return false;
    }
}
