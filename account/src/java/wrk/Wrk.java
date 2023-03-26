/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wrk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Wrk {

    private Connection jdbcConnection;

    public Wrk() {
    }

    private boolean dbConnect() {
        boolean ok = false;
        String piloteODBCPathDb = "jdbc:mysql://localhost:3306/meylans_account?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            jdbcConnection = DriverManager.getConnection(piloteODBCPathDb, "meylans_snake", "Snake123EMF$$");
            ok = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ok;
    }

    private boolean dbDisconnect() {
        boolean ok = false;
        // On vérifie si une connexion est toujours présente (donc pas nulle)
        if (jdbcConnection != null) {
            try {
                // On essaie de fermer la connexion, puis "vide" la variable.
                jdbcConnection.close();
                jdbcConnection = null;
                ok = true;
            } catch (SQLException e) {

            }
        }
        return ok;
    }

    public String login(String login, String password) {
        //On prépare nos variables.
        String resultat = "";
        Statement stmt = null;
        ResultSet rs = null;
        //On essaie de se connecter à la base de données. 
        if (dbConnect()) {
            try {
                if ((stmt = jdbcConnection.createStatement()) != null) {
                    //String de requête SQL pour get user from db
                    String sql = "SELECT * FROM account WHERE login='" + login + "' AND password='" + password + "'";
                    //On exécute la requête et on stocke la réponse dans un "ResulSet"
                    if ((rs = stmt.executeQuery(sql)) != null) {
                        while (rs.next()) {
                            //On stocke le nom récupéré de la colonne "login". 
                            resultat = rs.getString("login");
                        }
                    }
                    //On ferme le tout pour optimiser les performances.
                    rs.close();
                    rs = null;
                    stmt.close();
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
            } //On repasse les variables pour vérifier que tout est bien fermé.
            finally {
                dbDisconnect();
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resultat;
    }
}
