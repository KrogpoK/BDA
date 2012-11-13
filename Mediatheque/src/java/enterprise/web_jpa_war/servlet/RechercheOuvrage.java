/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import enterprise.web_jpa_war.facade.impl.MediaDS;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeremiefabre
 */
@WebServlet(name = "RechercheOuvrage", urlPatterns = {"/RechercheOuvrage"})
public class RechercheOuvrage extends AbstractServlet {

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
        mediaDS = new MediaDS(emf.createEntityManager());

        ArrayList<Ouvrage> listOuvrages = new ArrayList<Ouvrage>();

        String titre = (String) request.getParameter("titreSearch");
        String genre = (String) request.getParameter("genreSearch");
        String dateParution = (String) request.getParameter("dateParutionSearch");
        String dateParutionIndicateur = (String) request.getParameter("dateParutionIndicateurSearch");
        String langue = (String) request.getParameter("langueSearch");
        String dateArrivee = (String) request.getParameter("dateArriveeSearch");
        String dateArriveeIndicateur = (String) request.getParameter("dateArriveeIndicateurSearch");
        String disponibilite = (String) request.getParameter("disponibiliteSearch");
        String nbEmprunts = (String) request.getParameter("nbEmpruntdSearch");
        String nbEmpruntsIndicateur = (String) request.getParameter("nbEmpruntsIndicateurSearch");


        HashMap<String, String> mapParamsOuvrage = new HashMap<String, String>();

        mapParamsOuvrage.put(Oeuvre.TITRE, titre);
        mapParamsOuvrage.put(Oeuvre.GENRE, genre);
        mapParamsOuvrage.put(Oeuvre.DATEPARUTION, dateParution);
        mapParamsOuvrage.put(Oeuvre.DATEPARUTIONINDICATEUR, dateParutionIndicateur);
        mapParamsOuvrage.put(Oeuvre.LANGUE, langue);
        mapParamsOuvrage.put(Ouvrage.DATEARRIVEE, dateArrivee);
        mapParamsOuvrage.put(Ouvrage.DATEARRIVEEINDICATEUR, dateArriveeIndicateur);
        mapParamsOuvrage.put(Ouvrage.DISPONIBILITE, disponibilite);
        mapParamsOuvrage.put(Ouvrage.NBEMPRUNTS, nbEmprunts);
        mapParamsOuvrage.put(Ouvrage.NBEMPRUNTSINDICATEUR, nbEmprunts);

        List<Ouvrage> oList = mediaDS.getOuvrages(mapParamsOuvrage);
        request.setAttribute("listOuvrages", oList);
        String typeSupport = (String) request.getParameter("typeSupport");

        String keyWord = titre;

        //ArrayList<Oeuvre> res = appliFiltre(liste, keyWord);
        request.setAttribute("keyWord", keyWord);
        request.getRequestDispatcher("rechercheOuvrage.jsp").forward(request, response);
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
