package joc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    public boolean up; //flag pentru tasta sus apasata
    public boolean down;//flag pentru tasta jos apasata
    public boolean left;//flag pentru tasta stanga apasata
    public boolean right;//flag pentru tasta dreapta apasata

    public boolean info;//flag pentru tasta de meniu apasata(tasta p sau P)
    public boolean quit;//flag pentru tasta de iesire din joc
    public boolean attack;//flag pentru tasta de atac

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }


        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            info = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_Q){
            quit = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            attack = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }


        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            info = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_Q){
            quit = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            attack = false;
        }

    }


}