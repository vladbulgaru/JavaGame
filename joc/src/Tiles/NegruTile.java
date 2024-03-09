package Tiles;

import Graphics.Assets;


public class NegruTile extends Tile {
    public NegruTile(int id){
        super(Assets.negru, id);
    }
    public boolean isSolid(){
        return true;
    }
}
