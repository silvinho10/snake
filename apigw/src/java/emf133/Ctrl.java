/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emf133;

import services.Account;
import services.Score;

/**
 *
 * @author MES
 */
public class Ctrl {

    public Ctrl() {
    }

    public String signIn(String login, String password) {
        Account account = new Account();
        return account.signIn(login, password);
    }

    public String score(String login, String scored) {
        Score score = new Score();
        return score.score(login, Integer.parseInt(scored));
    }
    
     public String getScore() {
        Score score = new Score();
        return score.classement();
    }

}
