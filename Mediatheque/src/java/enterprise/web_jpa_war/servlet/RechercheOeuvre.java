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

        if (Film.SUPPORT.equals((String) request.getParameter("typeSupport"))) {
            List<Film> fList = mediaDS.getFilms();
            for (Film f : fList) {
                listOeuvres.add(f);
            }
            listOeuvres = triParams(listOeuvres, request);
            fList = new ArrayList<Film>();
            for (Oeuvre o : listOeuvres) {
                fList.add((Film) o);
            }
            request.setAttribute("listFilms", fList);

        } else if (Livre.SUPPORT.equals((String) request.getParameter(CD.SUPPORT))) {
            List<CD> cList = mediaDS.getCDs();
            for (int i = 0; i < cList.size(); i++) {
                //liste.add(cList.get(i));
            }
        } else if (CD.SUPPORT.equals((String) request.getParameter(Periodique.SUPPORT))) {
            List<Periodique> pList = mediaDS.getPeriodiques();
            for (int i = 0; i < pList.size(); i++) {
                //liste.add(pList.get(i));
            }
        } else if (Periodique.SUPPORT.equals((String) request.getParameter(Livre.SUPPORT))) {
            List<Livre> lList = mediaDS.getLivres();
            for (int i = 0; i < lList.size(); i++) {
                //liste.add(lList.get(i));
            }
        } else {
            listOeuvres = (ArrayList) mediaDS.getOeuvres();
            listOeuvres = triParams(listOeuvres, request);
            request.setAttribute("listOeuvres", listOeuvres);
        }

        String keyWord = (String) request.getParameter("titreSearch");

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

    private ArrayList<Oeuvre> triParams(ArrayList<Oeuvre> listOeuvres, HttpServletRequest request) {
        String titre = (String) request.getParameter("titreSearch");
        String genre = (String) request.getParameter("genreSearch");
        String dateParution = (String) request.getParameter("dateParutionSearch");
        String dateParutionAvant = (String) request.getParameter("dateParutionAvantSearch");
        boolean boolDateParutionAvant = ("avant".equals(dateParutionAvant)) ? true : false;
        listOeuvres = triTitre(listOeuvres, titre);
        listOeuvres = triGenre(listOeuvres, genre);
        listOeuvres = triDate(listOeuvres, dateParution, boolDateParutionAvant);
        return listOeuvres;
    }

    private ArrayList<Oeuvre> triTitre(ArrayList<Oeuvre> listOeuvres, String titre) {
        if (!"".equals(titre)) {
            for (Oeuvre o : listOeuvres) {
                if (!o.getTitre().contains(titre)) {
                    listOeuvres.remove(o);
                }
            }
        }
        return listOeuvres;
    }

    private ArrayList<Oeuvre> triGenre(ArrayList<Oeuvre> listOeuvres, String genre) {
        if (!"".equals(genre)) {
            for (Oeuvre o : listOeuvres) {
                if (!o.getGenre().equals(genre)) {
                    listOeuvres.remove(o);
                }
            }
        }
        return listOeuvres;
    }

    private ArrayList<Oeuvre> triDate(ArrayList<Oeuvre> listOeuvres, String dateParutionStr, boolean boolDateParutionAvant) {
        if (!"".equals(dateParutionStr)) {
            Date dateParution = DateTool.parseDate(dateParutionStr);
            for (Oeuvre o : listOeuvres) {
                if (!(boolDateParutionAvant && o.getDateParution().before(dateParution) || !boolDateParutionAvant && o.getDateParution().after(dateParution))) {
                    listOeuvres.remove(o);
                }
            }
        }
        return listOeuvres;
    }
}
