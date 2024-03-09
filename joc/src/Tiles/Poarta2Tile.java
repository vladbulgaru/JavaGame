package Tiles;

import Graphics.Assets;


public class Poarta2Tile extends Tile{
    public Poarta2Tile(int id){
        super(Assets.poarta2, id);
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
