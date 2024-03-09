package Tiles;

import Graphics.Assets;


public class ZapadaTile extends Tile {
    public ZapadaTile(int id){
        super(Assets.zapada, id);
    }
    public boolean isSolid(){
        return false;
    }
}
