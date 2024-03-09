package joc;

import observer.Observer;
import Character.Hero;
import Items.Diamond;

import javax.swing.JFrame;

import java.sql.*;

public class Main {


    public static void main(String[] args) {



    JFrame window = new JFrame();


        /// Operatia de inchiderew (fereastra sa poata fi inchisa atunci cand
        /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
        /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
        /// program
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        /// Avand in vedere ca dimensiunea ferestrei poate fi modificata
        /// si corespunzator continutul actualizat (aici ma refer la dalele
        /// randate) va recomand sa constrangeti deocamdata jucatorul
        /// sa se joace in fereastra stabilitata de voi. Puteti reveni asupra
        /// urmatorului apel ulterior.
        window.setResizable(false);


        window.setTitle("Comoara Blestemata");




        //GameWindow gameWindow = new GameWindow();

        GameWindow gameWindow = GameWindow.getInstance();





        window.add(gameWindow);



        /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
        /// ca tot ce contine sa poate fi afisat complet
        window.pack();

        /// Recomand ca fereastra sa apara in centrul ecranului. Pentru orice
        /// alte pozitie se va apela "window.setLocation(x, y)" etc.
        window.setLocationRelativeTo(null);

        /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
        /// care trebuie setata aceasta proprietate
        window.setVisible(true);






        gameWindow.startGame();


        //observatorii sunt adaugati la subiect


        Diamond.addObserver(gameWindow.hero);
        Diamond.releaseObserver(gameWindow.hero);



        /*
        gameWindow.diamond1.addObserver(gameWindow.hero);
        gameWindow.diamond2.addObserver(gameWindow.hero);
        gameWindow.diamond3.addObserver(gameWindow.hero);
        gameWindow.diamond4.addObserver(gameWindow.hero);
        gameWindow.diamond5.addObserver(gameWindow.hero);

         */


        /*
        gameWindow.diamond1.releaseObserver(gameWindow.hero);
        gameWindow.diamond2.releaseObserver(gameWindow.hero);
        gameWindow.diamond3.releaseObserver(gameWindow.hero);
        gameWindow.diamond4.releaseObserver(gameWindow.hero);
        gameWindow.diamond5.releaseObserver(gameWindow.hero);

         */






    }
}