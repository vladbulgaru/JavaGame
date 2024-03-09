package Tiles;

import Graphics.Assets;


public class groapaTile extends Tile{
    public groapaTile(int id){
        super(Assets.groapa, id);
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
