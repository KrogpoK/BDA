/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Panier;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import enterprise.web_jpa_war.facade.impl.AdherentDS;
import enterprise.web_jpa_war.facade.impl.MediaDS;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "GestionPanier", urlPatterns = {"/GestionPanier"})
public class GestionPanier extends AbstractServlet {

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

            int idAdherent = ((Adherent) request.getSession().getAttribute("user")).getId();
            if ("add".equals(request.getParameter("action"))) {
                String support = request.getParameter("oeuvreType");

                int id = Integer.parseInt(request.getParameter("oeuvreId"));

                Oeuvre o = null;
                if (Film.SUPPORT.equals(support)) {
                    o = mediaDS.getFilm(id);
                } else if (CD.SUPPORT.equals(support)) {
                    o = mediaDS.getCD(id);
                } else if (Livre.SUPPORT.equals(support)) {
                    o = mediaDS.getLivre(id);
                } else if (Periodique.SUPPORT.equals(support)) {
                    o = mediaDS.getPeriodique(id);
                }
                if (o != null) {
                    adherentDS.ajouterAuPanier(idAdherent, o);
                }
                request.getRequestDispatcher("rechercheOeuvre.jsp").forward(request, response);
            } else if ("remove".equals(request.getParameter("action"))) {
                int idOeuvre = Integer.parseInt(request.getParameter("oeuvreId"));
                adherentDS.supprimerDuPanier(idAdherent, idOeuvre);
                request.setAttribute("listeOeuvre", getListeOeuvre(idAdherent));
                request.getRequestDispatcher("Panier.jsp").forward(request, response);
            } else {
                request.setAttribute("listeOeuvre", getListeOeuvre(idAdherent));
                request.getRequestDispatcher("Panier.jsp").forward(request, response);
            }
            utx.commit();
            em.close();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private List<Oeuvre> getListeOeuvre(int idAdherent) {
        Adherent adherent = adherentDS.getAdherent(idAdherent);
        Panier panier = adherent.getCompte().getPanier();
        return panier.getListeOeuvre();
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
