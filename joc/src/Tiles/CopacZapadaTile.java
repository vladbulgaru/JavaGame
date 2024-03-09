package Tiles;

import Graphics.Assets;


public class CopacZapadaTile extends Tile {
    public CopacZapadaTile(int id){
        super(Assets.copacZapada, id);
    }
    public boolean isSolid(){
        return true;
    }
}
