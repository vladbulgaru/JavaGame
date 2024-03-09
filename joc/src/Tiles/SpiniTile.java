package Tiles;

import Graphics.Assets;


public class SpiniTile extends Tile {
    public SpiniTile(int id){
        super(Assets.spini, id);
    }
    public boolean isSolid(){
        return false;
    }
}
