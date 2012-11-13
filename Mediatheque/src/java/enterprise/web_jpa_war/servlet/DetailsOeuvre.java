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
import enterprise.web_jpa_war.util.DateTool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
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
                        List<Critique> critiques = null;
                        Critique critique = new Critique();
                    
                        em = emf.createEntityManager();
                        String typeOeuvre = request.getParameter("oeuvreType");
                        int id = Integer.parseInt(request.getParameter("id"));
                 
                        mediaDS = new MediaDS(em);
                        LinkedHashMap<String, String> keyVal = new LinkedHashMap();
                        request.setAttribute("id", id);
                        request.setAttribute("oeuvreType", typeOeuvre);
                        
                        if (request.getMethod().equals("POST")) {

                            Compte user = ((Adherent) request.getSession().getAttribute("user")).getCompte();
                            critique.setDescription(request.getParameter("description"));
                            critique.setNote(request.getParameter("note"));
                            critique.setTitreCritiqie("titre critique");
                            critique.setCompte(user);

                        }
                        if (typeOeuvre.equals("film")) {
                                   
                            Film film = mediaDS.getFilm(id);
                            if (request.getMethod().equals("POST")) {
                                critique.setOeuvre(film);
                                mediaDS.setCritique(critique);
                                utx.commit(); 
                            }
                            keyVal.put("Titre", film.getTitre());
                            keyVal.put("Realisateur", film.getRealisateur());
                            keyVal.put("Langue", film.getLangue());
                            keyVal.put("Duree",DateTool.printDate(film.getDuree()));
                            keyVal.put("Genre",film.getGenre());
                            keyVal.put("Date de parution:",DateTool.printDate(film.getDateParution()));
                            critiques = mediaDS.getCritiques(film);

                        }
                        else if (typeOeuvre.equals("cd")) {

                            CD cd = mediaDS.getCD(id);
                            if (request.getMethod().equals("POST")) {
                                critique.setOeuvre(cd);
                                mediaDS.setCritique(critique);
                                utx.commit(); 
                            }
                            keyVal.put("Titre", cd.getTitre());
                            keyVal.put("maisonEdition",cd.getMaisonEdition());
                            keyVal.put("interprete",cd.getInterprete());
                            keyVal.put("nbPiste", ""+cd.getNbPiste());
                            keyVal.put("Genre", cd.getGenre());
                            critiques = mediaDS.getCritiques(cd);
                        }
                        else if (typeOeuvre.equals("livre")) {

                            Livre livre = mediaDS.getLivre(id);
                            if (request.getMethod().equals("POST")) {
                                critique.setOeuvre(livre);
                                mediaDS.setCritique(critique);
                                utx.commit(); 
                            }
                            keyVal.put("Titre", livre.getTitre());
                            keyVal.put("Auteur:",livre.getAuteur());
                            keyVal.put("Editeur",livre.getEditeur());
                            keyVal.put("Genre", livre.getGenre());
                            critiques = mediaDS.getCritiques(livre);

                        }
                        else if (typeOeuvre.equals("periodique")) {

                            Periodique periodique = mediaDS.getPeriodique(id);
                            if (request.getMethod().equals("POST")) {
                                critique.setOeuvre(periodique);
                                mediaDS.setCritique(critique);
                                utx.commit();   
                            }
                            keyVal.put("Titre", periodique.getTitre());
                            keyVal.put("Theme",periodique.getTheme());
                            keyVal.put("Type",periodique.getType());
                            keyVal.put("Periodicite", periodique.getPeriodicite());
                            keyVal.put("Genre",periodique.getGenre());
                            critiques = mediaDS.getCritiques(periodique);
                        }
                        
                        request.setAttribute("keyVal",keyVal);              
                        request.setAttribute("critiques", critiques);
                        request.getRequestDispatcher("detailsOeuvre.jsp").forward(request, response);


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
