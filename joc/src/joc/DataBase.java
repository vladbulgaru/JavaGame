package joc;

import Character.Hero;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;


import static joc.GameWindow.updateScor;
import static joc.GameWindow.updateViata;


//baza de date a jocului unde vor fi salvate scorul si viata eroului
public class DataBase {

    public static void database(Hero hero){

        int scor  = hero.getHeroScore();
        int viata = hero.getLife();

        Statement stmt = null;
        Connection c = null;


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hallOfFame.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();


            String sql = "CREATE TABLE IF NOT EXISTS hallOfFame" + "(SCOR INT NOT NULL, " + "VIATA INT NOT NULL)";



            stmt.executeUpdate(sql);
            System.out.println("Opened database successfully");

            sql = "INSERT INTO hallOfFame (SCOR, VIATA) VALUES (" + scor + "," + viata + ");";
            stmt.executeUpdate(sql);

            String selectDataSql = "SELECT SCOR FROM hallOfFame";
            ResultSet rs = stmt.executeQuery(selectDataSql);

            while (rs.next()) {

                updateScor = rs.getInt("SCOR");

            }

            String selectDataSql2 = "SELECT VIATA FROM hallOfFame";
            ResultSet rs2 = stmt.executeQuery(selectDataSql2);

            while (rs2.next()) {

                updateViata = rs2.getInt("VIATA");

            }

            rs.close();
            rs2.close();

            stmt.close();
            c.commit();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }


    }

}
