/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.configuration.Configuration;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import enterprise.web_jpa_war.facade.impl.AdherentDS;
import enterprise.web_jpa_war.facade.impl.MediaDS;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class RegenDb extends AbstractServlet {

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
            response.setContentType("text/html;charset=UTF-8");

            utx.begin();
            em = emf.createEntityManager();
            mediaDS = new MediaDS(em);
            adherentDS = new AdherentDS(em);

            for (Adherent a : Adherent.buildMock(10)) {
                adherentDS.creerAdherent(a);
            }
            Adherent admin = Adherent.buildMoke("Admin", "Admin", "1990-05-24");
            admin.setAdmin(true);
            adherentDS.creerAdherent(admin);
            Adherent ouioui = Adherent.buildMoke("oui", "oui", "1990-05-24");
            adherentDS.creerAdherent(ouioui);
            for (Film f : Film.buildMoke(1000)) {
                mediaDS.creerFilm(f);
                Ouvrage o = new Ouvrage();
                Random r = new Random();
                o.setDisponibilite(0);
                o.setDateArrivee(new Date());
                o.setNbEmprunts(0);
                o.setOeuvre(f);
                mediaDS.creerOuvrage(o);
            }

            Configuration cFilm = new Configuration();
            cFilm.setEstRenouvelable(false);
            cFilm.setNbJours(5);
            cFilm.setNbOuvragesMemeType(4);
            cFilm.setNomSupport(Film.SUPPORT);
            em.persist(cFilm);


            utx.commit();
            em.close();

            request.getRequestDispatcher("ListPersonne").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException();
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
