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
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import enterprise.web_jpa_war.facade.impl.AdherentDS;
import enterprise.web_jpa_war.facade.impl.MediaDS;
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            utx.begin();
            em = emf.createEntityManager();
            adherentDS = new AdherentDS(em);
            mediaDS = new MediaDS(em);
            
            Adherent a = null;
            //rendre un emprunt
            if ("rendre".equals(request.getParameter("action"))) {
                String empruntStr = request.getParameter("idEmprunt");
                int idEmprunt = Integer.parseInt(empruntStr);
                
                Emprunt e = adherentDS.getEmprunt(idEmprunt);
                a = e.geteCompte().getProprietaire();
                Ouvrage o = e.geteOuvrage();

                //attribuer la reservation au prochain mec
                List<Reservation> liste = adherentDS.getReservationsByOeuvre(o.getOeuvre());
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
                    } //sinon, elle retrouve l'oeuvre retrouve sa liberte
                    else {
                        o.setDisponibilite(Ouvrage.DISPO_LIBRE);
                    }
                }
                
                Configuration config = mediaDS.getConfiguration(o.getOeuvre().getStrType());
                e.setDateFinEmprunt(new Date());
                int nbJoursEmpruntes = DateTool.getDifference(e.getDateDebutEmprunt(), e.getDateFinEmprunt());
                if (nbJoursEmpruntes > config.getNbJours()) {
                    double montantPenalite = Emprunt.PENALITE_JOURNALIERE * (nbJoursEmpruntes - config.getNbJours());
                    e.geteCompte().setSolde(e.geteCompte().getSolde() - montantPenalite);
                    request.setAttribute("penalite", montantPenalite);
                }
                
            }
            //creation d'un emprunt
            if ("creer".equals(request.getParameter("action"))) {
                // si en parametre on a l'id d'une oeuvre : 
                if (request.getParameter("idOeuvre") != null) {
                    int idOeuvre = Integer.parseInt(request.getParameter("idOeuvre"));
                    Oeuvre o = mediaDS.getOeuvre(idOeuvre);
                    List<Ouvrage> listeOurage = mediaDS.getListeOuvrage(o);
                    if (listeOurage != null && !listeOurage.isEmpty()) {
                        List<Ouvrage> ouvragesDisponibles = new ArrayList<Ouvrage>();
                        for (int i = 0; i < listeOurage.size(); i++) {
                            if (listeOurage.get(i).getDisponibilite() == Ouvrage.DISPO_LIBRE) {
                                ouvragesDisponibles.add(listeOurage.get(i));
                            }
                        }
                        if (ouvragesDisponibles.size() == 0) {
                            request.setAttribute("error", "aucun ouvrage disponible");
                        } else {
                            Emprunt e = new Emprunt();
                            e.setDateDebutEmprunt(new Date());
                            String strIdAdherent = request.getParameter("idAdherent");
                            int idAdherent = Integer.parseInt(strIdAdherent);
                            a=adherentDS.getAdherent(idAdherent);
                            e.seteCompte(a.getCompte());
                            e.seteOuvrage(ouvragesDisponibles.get(0));
                            adherentDS.ajouterEmprunt(e);
                            e.geteOuvrage().setDisponibilite(Ouvrage.DISPO_EMPRUNTE);
                            //supression de la reservation correspondante
                            String strResaId = request.getParameter("idResa");
                            int idResa = Integer.parseInt(strResaId);
                            System.out.println("avant ds : "+ idResa);
                            adherentDS.supprimerReservation(adherentDS.getReservation(idResa));
                        }
                    }
                }
            }

            //a la fin, liste les emprunts courants de l'adherent
            utx.commit();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String date = request.getParameter("date");
            System.out.println(date);
            if (a == null) {
                a = adherentDS.getAdherent(nom, prenom, DateTool.parseDate(date));
                System.out.println("cherche  adherent : " + a);
            }
            if (a == null) {
                request.setAttribute("error", "adherent non trouve");
                System.out.println("error : non trouve");
            } else {
                System.out.println("compte ID : " + a.getCompte().getId());
                List<Emprunt> listeEmprunts = adherentDS.getEmprunts(a);
                List<Reservation> listeReservation = adherentDS.getReservationsActives(a);
                request.setAttribute("listeEmprunts", listeEmprunts);
                request.setAttribute("listeReservation", listeReservation);
                System.out.println("nbEmprunts -> " + listeEmprunts.size());
                System.out.println("nbResa -> " + listeReservation.size());
            }
            em.close();
            
            request.getRequestDispatcher("GestionEmprunt.jsp").forward(request, response);
            
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
