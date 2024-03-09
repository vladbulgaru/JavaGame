package Tiles;

import Graphics.Assets;


public class ApaTile extends Tile {
    public ApaTile(int id){
        super(Assets.apa, id);
    }
    public boolean isSolid(){
        return false;
    }
}
