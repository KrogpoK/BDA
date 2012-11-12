/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import enterprise.web_jpa_war.facade.impl.MediaDS;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import enterprise.web_jpa_war.util.DateTool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class RechercheOeuvre extends AbstractServlet {

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

        ArrayList<Oeuvre> listOeuvres = new ArrayList<Oeuvre>();

        String titre = (String) request.getParameter("titreSearch");
        String genre = (String) request.getParameter("genreSearch");
        String dateParution = (String) request.getParameter("dateParutionSearch");
        String dateParutionIndicateur = (String) request.getParameter("dateParutionIndicateurSearch");
        String langue = (String) request.getParameter("langueSearch");

        HashMap<String, String> mapParamsOeuvre = new HashMap<String, String>();

        mapParamsOeuvre.put(Oeuvre.TITRE, titre);
        mapParamsOeuvre.put(Oeuvre.GENRE, genre);
        mapParamsOeuvre.put(Oeuvre.DATEPARUTION, dateParution);
        mapParamsOeuvre.put(Oeuvre.DATEPARUTIONINDICATEUR, dateParutionIndicateur);
        mapParamsOeuvre.put(Oeuvre.LANGUE, langue);

        String typeSupport = (String) request.getParameter("typeSupport");
        if (Film.SUPPORT.equals(typeSupport)) {
            String realisateur = (String) request.getParameter("realisateurSearch");
            String acteurPrincipal = (String) request.getParameter("acteurPrincipalSearch");

            mapParamsOeuvre.put(Film.REALISATEUR, realisateur);
            mapParamsOeuvre.put(Film.ACTEURPRINCIPAL, acteurPrincipal);

            List<Film> fList = mediaDS.getFilms(mapParamsOeuvre);
            request.setAttribute("listFilms", fList);
        } else if (Livre.SUPPORT.equals(typeSupport)) {
            String auteur = (String) request.getParameter("auteurSearch");
            String editeur = (String) request.getParameter("editeurSearch");

            mapParamsOeuvre.put(Livre.AUTEUR, auteur);
            mapParamsOeuvre.put(Livre.EDITEUR, editeur);

            List<Livre> lList = mediaDS.getLivres(mapParamsOeuvre);
            request.setAttribute("listLivres", lList);
        } else if (CD.SUPPORT.equals(typeSupport)) {
            String interprete = (String) request.getParameter("interpreteSearch");
            String maisonEdition = (String) request.getParameter("maisonEditionSearch");

            mapParamsOeuvre.put(CD.INTERPRETE, interprete);
            mapParamsOeuvre.put(CD.MAISONEDITION, maisonEdition);

            List<CD> cList = mediaDS.getCDs(mapParamsOeuvre);
            request.setAttribute("listCDs", cList);
        } else if (Periodique.SUPPORT.equals(typeSupport)) {
            String theme = (String) request.getParameter("themeSearch");
            String periodicite = (String) request.getParameter("periodiciteSearch");

            mapParamsOeuvre.put(Periodique.THEME, theme);
            mapParamsOeuvre.put(Periodique.PERIODICITE, periodicite);

            List<Periodique> pList = mediaDS.getPeriodiques(mapParamsOeuvre);
            request.setAttribute("listPeriodiques", pList);
        } else {
            List<Oeuvre> oList = mediaDS.getOeuvres(mapParamsOeuvre);
            request.setAttribute("listOeuvres", oList);
        }

        String keyWord = titre;

        //ArrayList<Oeuvre> res = appliFiltre(liste, keyWord);
        request.setAttribute("keyWord", keyWord);
        request.getRequestDispatcher("rechercheOeuvre.jsp").forward(request, response);
    }

    private ArrayList<Oeuvre> appliFiltre(ArrayList<Oeuvre> liste, String kw) {
        String[] allKw = kw.split(" ");
        HashMap<Integer, ArrayList<Oeuvre>> hm = new HashMap<Integer, ArrayList<Oeuvre>>();

        for (Oeuvre o : liste) {
            int nbMatches = 0;
            for (String s : allKw) {
                for (String partTitre : o.getTitre().split(" ")) {
                    if (partTitre.equalsIgnoreCase(s)) {
                        nbMatches++;
                    }
                }

                if (o.getGenre().equalsIgnoreCase(s)) {
                    nbMatches++;
                }
            }
            if (hm.get(nbMatches) == null) {
                hm.put(nbMatches, new ArrayList<Oeuvre>());
            }
            hm.get(nbMatches).add(o);
        }

        ArrayList<Oeuvre> res = new ArrayList<Oeuvre>();
        int nbKey = hm.keySet().size();
        Integer[] reverse = new Integer[nbKey];

        int i = 0;
        for (int key : hm.keySet()) {
            reverse[nbKey - 1 - i] = key;
            i++;
        }

        for (int key : reverse) {
            if (key != 0) {
                for (Oeuvre o : hm.get(key)) {
                    res.add(o);
                }
            }
        }

        return res;
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
