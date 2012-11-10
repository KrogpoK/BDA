/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Compte;
import enterprise.web_jpa_war.entity.mediatheque.Critique;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import enterprise.web_jpa_war.facade.impl.MediaDS;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isso
 */
@WebServlet(name = "DetailsOeuvre", urlPatterns = {"/DetailsOeuvre"})
public class DetailsOeuvre extends AbstractServlet {
    private String idOeuvre;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
                    try {
                        super.utx.begin();
                    
                    Critique tmp = new Critique();
                    
                    em = emf.createEntityManager();
                    String typeOeuvre = request.getParameter("oeuvreType");
                    int id = Integer.parseInt(request.getParameter("id"));

                    request.setAttribute("id", id);
                    request.setAttribute("oeuvreType", typeOeuvre);
                    mediaDS = new MediaDS(em);
                
                    if (request.getMethod().equals("POST")) {

                        Compte user = ((Adherent) request.getSession().getAttribute("user")).getCompte();
                        tmp.setDescription(request.getParameter("description"));
                        tmp.setNote(request.getParameter("note"));
                        tmp.setTitreCritiqie("titre critique");
                        tmp.setCompte(user);

                    }
                    if (typeOeuvre.equals("film")){

                        Film film = mediaDS.getFilm(id);
                        request.setAttribute("film", film);
                        if (request.getMethod().equals("POST")) {
                            tmp.setOeuvre(film);
                            mediaDS.setCritique(tmp);
                            utx.commit();
                            
                        }
                       
                        List<Critique> critiques = mediaDS.getCritiques(film);
                        request.setAttribute("critiques", critiques);
                        request.getRequestDispatcher("Film.jsp").forward(request, response);

                    }
                   
                
                else if (typeOeuvre.equals("cd")) {
               
                    CD cd  = mediaDS.getCD(id);
                    request.setAttribute("cd", cd);
                    if (request.getMethod().equals("POST")) {
                            tmp.setOeuvre(cd);
                            mediaDS.setCritique(tmp);
                            utx.commit();
                            
                     }
                    List<Critique> critiques = mediaDS.getCritiques(cd);
                    request.setAttribute("critiques", critiques);    
                    request.getRequestDispatcher("Cd.jsp").forward(request, response);
                }
                
                else if (typeOeuvre.equals("livre")) {
                
                    Livre livre = mediaDS.getLivre(id);
                    request.setAttribute("livre", livre);
                     if (request.getMethod().equals("POST")) {
                            tmp.setOeuvre(livre);
                            mediaDS.setCritique(tmp);
                            utx.commit();
                            
                     }
                    List<Critique> critiques = mediaDS.getCritiques(livre);
                    request.setAttribute("critiques", critiques);  
                    request.getRequestDispatcher("Livre.jsp").forward(request, response);
                }
                else if (typeOeuvre.equals("periodique")) {
                
                    Periodique periodique = mediaDS.getPeriodique(id);
                    request.setAttribute("periodique", periodique);
                    if (request.getMethod().equals("POST")) {
                            tmp.setOeuvre(periodique);
                            mediaDS.setCritique(tmp);
                            utx.commit();
                            
                     }
                    List<Critique> critiques = mediaDS.getCritiques(periodique);
                    request.setAttribute("critiques", critiques);    
                    request.getRequestDispatcher("Periodique.jsp").forward(request, response);
                }
                else {
                    // TODO: en cas d'erreur
                }
                }
                   catch (Exception ex) {
                    throw new ServletException(ex);
                  }
                 
                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
