package Tiles;

import Graphics.Assets;


public class Capcana3Tile extends Tile {
    public Capcana3Tile(int id){
        super(Assets.capcana3, id);
    }

    public boolean isSolid(){
        return false;
    }
}
