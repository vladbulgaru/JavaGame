package Tiles;

import Graphics.Assets;


public class Capcana2Tile extends Tile {
    public Capcana2Tile(int id){
        super(Assets.capcana2, id);
    }

    public boolean isSolid(){
        return false;
    }
}
