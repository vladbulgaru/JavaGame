package Tiles;

import Graphics.Assets;

public class FlowerTile extends Tile{
    public FlowerTile(int id){
        super(Assets.flowers, id);
    }
    public boolean isSolid(){
        return false;
    }
}
