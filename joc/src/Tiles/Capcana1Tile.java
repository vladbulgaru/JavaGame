package Tiles;

import Graphics.Assets;


public class Capcana1Tile extends Tile {
    public Capcana1Tile(int id){
        super(Assets.capcana1, id);
    }

    public boolean isSolid(){
        return false;
    }
}
