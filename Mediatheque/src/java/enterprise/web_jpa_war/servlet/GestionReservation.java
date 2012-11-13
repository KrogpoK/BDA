/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.configuration.Configuration;
import enterprise.web_jpa_war.entity.mediatheque.Emprunt;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.facade.impl.AdherentDS;
import enterprise.web_jpa_war.facade.impl.EmpruntDS;
import enterprise.web_jpa_war.facade.impl.MediaDS;
import enterprise.web_jpa_war.facade.impl.ReservationDS;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import enterprise.web_jpa_war.util.DateTool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "GestionReservation", urlPatterns = {"/GestionReservation"})
public class GestionReservation extends AbstractServlet {

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
            utx.begin();
            em = emf.createEntityManager();
            mediaDS = new MediaDS(em);
            adherentDS = new AdherentDS(em);
            reservationDS = new ReservationDS(em);
            empruntDS = new EmpruntDS(em);

            ArrayList<Reservation> listeReservationDisponible = new ArrayList<Reservation>();
            ArrayList<Reservation> listeReservationEnAttente = new ArrayList<Reservation>();
            ArrayList<Integer> listeFileAttente = new ArrayList<Integer>();
            ArrayList<Integer> listeJourRestantEmprunt = new ArrayList<Integer>();
            List<Emprunt> listeEmpruntsCourants;

            int idAdherent = ((Adherent) request.getSession().getAttribute("user")).getId();
            Adherent adherent = adherentDS.getAdherent(idAdherent);

            String[] strId = request.getParameterValues("idOeuvreList[]");
            if (strId != null) {
                int nbReservation = strId.length;
                int[] tabId = new int[strId.length];
                int i = 0;
                for (String s : strId) {
                    tabId[i] = Integer.parseInt(s);
                    i++;
                }
                //reconstruit les oeuvre a partir du tableau recu en parametre
                Oeuvre[] tabOeuvre = new Oeuvre[nbReservation];
                for (i = 0; i < nbReservation; i++) {
                    Oeuvre o = mediaDS.getOeuvre(tabId[i]);
                    tabOeuvre[i] = o;
                }
                //cree la reservation pour chaque oeuvres
                for (Oeuvre o : tabOeuvre) {
                    Reservation resa = new Reservation();
                    resa.setCompte(adherent.getCompte());
                    resa.setDebut(new Date());
                    resa.setOeuvre(o);
                    if (mediaDS.estDisponible(o)) {
                        resa.setDispo(new Date());
//                        listeReservationDisponible.add(resa);
                    } else {
//                        listeReservationEnAttente.add(resa);
//                        listeFileAttente.add(mediaDS.getPlaceAttenteReservation(o));
                    }
                    adherent.getCompte().getPanier().supprimerOeuvre(o.getId());
                    reservationDS.creerReservation(resa);
                }
            }
            utx.commit();
            for(Reservation r : reservationDS.getReservationsActives(adherent))
            {
                //si la reservation n'est pas disponible
                if(r.getDispo() == null)
                {
                    listeReservationEnAttente.add(r);
                    listeFileAttente.add(mediaDS.getPlaceAttenteReservation(r.getOeuvre()));
                }
                else {
                    listeReservationDisponible.add(r);
                }
            }
            listeEmpruntsCourants = empruntDS.getEmpruntsActifs(adherent);
            if (listeEmpruntsCourants != null) {
                for (Emprunt e : listeEmpruntsCourants) {
                    Configuration c = mediaDS.getConfiguration(e.geteOuvrage().getOeuvre().getStrType());
//                        listeJourRestantEmprunt.add(c.getNbJours() - DateTool.getDifference(new Date(), e.getDateDebutEmprunt()));
                }
            }
            request.setAttribute("listeResaDispo", listeReservationDisponible);
            request.setAttribute("listeResaAttente", listeReservationEnAttente);
            request.setAttribute("placeFileAttente", listeFileAttente);
            request.setAttribute("listeEmprunts", empruntDS.getEmprunts(adherent));

            request.getRequestDispatcher("Reservation.jsp").forward(request, response);
            
            em.close();
        } catch (Exception ex) {
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
