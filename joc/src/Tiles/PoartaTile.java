package Tiles;

import Graphics.Assets;

public class PoartaTile extends Tile{

    public PoartaTile(int id){
        super(Assets.poarta, id);
    }
    public boolean isSolid(){
        return true;
    }

}
