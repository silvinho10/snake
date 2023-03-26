/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package emf133;

import beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MES
 */
public class Servlet extends HttpServlet {

    HttpSession session;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try ( PrintWriter out = response.getWriter()) {
            session = request.getSession();

             Ctrl ctrl = new Ctrl();
             if (request.getParameter("action").equals("classement")) {
                 String userConnected = ctrl.getScore();
                 out.println(userConnected);
                 System.out.println(userConnected);
             }
        }catch(Exception e){
            System.out.println("error");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        //origine à mettre à * et mettre Access-Control-Allow-Credentials à false TODO
        response.setHeader("Access-Control-Allow-Origin", "http://localhost");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try ( PrintWriter out = response.getWriter()) {
            
            Ctrl ctrl = new Ctrl();
            /* LOGIN */
            if (request.getParameter("action").equals("signIn")) {
                System.out.println("Connect");
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
                    // Lancement ctrl qui lance le check login sur micro service Account
                    String userConnected = ctrl.signIn(login, password);
                    if (userConnected != null && !userConnected.equals("KO")) {
                        //Creation de la session
                        session = request.getSession();
                        //Creation bean user pour stock en session
                        User user = new User(login);
                        session.setAttribute("user", user);
                        out.println("<user>"+userConnected+"</user>");
                        System.out.println("connected");
                    } else {
                        out.println("<font color=red>Either user name or password is wrong.</font>");
                    }
                } else {
                    out.println("<font color=red>Either user name or password is null.</font>");
                }
            } else if (request.getParameter("action").equals("signOut")) {
                System.out.println("Disconnect");
                /* SIGNOUT */
                session = request.getSession(false); //suppression de la session
                if (session != null) {
                    session.invalidate();
                    out.println("disconnected");
                }
                
            } else if (request.getParameter("action").equals("score")) {
                System.out.println("Score");

                /* INSERT SCORE - marche seulement si logé */
                if(session != null && session.getAttribute("user") != null){
                    String login = request.getParameter("login");
                    String score = request.getParameter("score");
                    System.out.println("still connected "+login + " score : "+score);
                    if (login != null && score != null && !login.isEmpty() && !score.isEmpty()) {
                        String scored = ctrl.score(login, score);
                        if (scored != null && !scored.equals("error")) {                      
                            out.println(scored);
                        } else {
                            out.println("<font color=red>Either user name or score is wrong.</font>");
                        }
                    }
                } else {
                    out.println("<font color=red>error not logged in</font>");
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet snake module 133";
    }

}
