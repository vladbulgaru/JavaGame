package Tiles;

import Graphics.Assets;


public class ComoaraTile extends Tile {
    public ComoaraTile(int id){
        super(Assets.comoara, id);
    }
    public boolean isSolid(){
        return true;
    }
}
