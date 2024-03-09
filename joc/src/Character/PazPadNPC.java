package Character;

import Graphics.Assets;
import Tiles.Tile;
import joc.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;


//obiect cu care eroul poate interactiona la coliziune
public class PazPadNPC extends Character{


    private static final int PP_WIDTH=32;
    private static final int PP_HEIGHT=32;



    public int getPpWidth(){
        return PP_WIDTH;
    }
    public int getPpHeight(){
        return PP_HEIGHT;
    }

    public PazPadNPC(GameWindow gw){

        super(gw);

        solid = new Rectangle(0,0, PP_WIDTH, PP_HEIGHT);

        Spawn();//apelam constructorul
        Assets.Init();//apelam functia Init() din clasa Assets
        getImage();//apelam functia

    }

    //coordonatele unde se va spawna npc-ul
    public void Spawn(){

        setX(Tile.TILE_WIDTH * 3);
        setY(Tile.TILE_HEIGHT * 14);
    }

    public void getImage(){
        pp = Assets.PazPad;
    }

    public void Update(){


    }
    public void Draw(Graphics2D g2){
        BufferedImage img = pp;
        g2.drawImage(img, getX(), getY(), PP_WIDTH, PP_HEIGHT, null);

    }

    public boolean isSolid(){
        return true;
    }


}
