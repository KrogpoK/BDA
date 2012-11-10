/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.configuration.Configuration;
import enterprise.web_jpa_war.entity.mediatheque.Emprunt;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import enterprise.web_jpa_war.util.DateTool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "GestionEmprunt", urlPatterns = {"/GestionEmprunt"})
public class GestionEmprunt extends AbstractServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String empruntStr = request.getParameter("idEmprunt");
        int idEmprunt = Integer.parseInt(empruntStr);

        Emprunt e = adherentDS.getEmprunt(idEmprunt);
        Adherent a = e.geteCompte().getProprietaire();
        Ouvrage o = e.geteOuvrage();

        //attribuer la reservation au prochain mec
        List<Reservation> liste = adherentDS.getReservationsByAdherent(a.getId());
        if (liste != null) {
            Reservation r = liste.get(0);
            int i = 0;
            while (i < liste.size() && r.getDispo() != null) {
                r = liste.get(i);
                i++;
            }
            // si un gen recoit la reservation
            if (r.getDispo() == null) {
                r.setDispo(new Date());
                o.setDisponibilite(Ouvrage.DISPO_RESERVE);
            }
            //sinon, elle retrouve l'oeuvre retrouve sa liberte
            else {
                 o.setDisponibilite(Ouvrage.DISPO_LIBRE);
            }
        }

        Configuration config = mediaDS.getConfiguration(o.getOeuvre().getStrType());
        e.setDateFinEmprunt(new Date());
        int nbJoursEmpruntes = DateTool.getDifference(e.getDateDebutEmprunt(), e.getDateFinEmprunt());
        if (nbJoursEmpruntes > config.getNbJours()) {
            double montantPenalite = Emprunt.PENALITE_JOURNALIERE * nbJoursEmpruntes;
            e.geteCompte().setSolde(e.geteCompte().getSolde() - montantPenalite);
            request.setAttribute("penalite", montantPenalite);
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
