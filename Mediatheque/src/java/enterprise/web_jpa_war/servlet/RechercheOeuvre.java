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
import java.io.IOException;
import java.util.ArrayList;
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

        ArrayList<Oeuvre> liste = new ArrayList<Oeuvre>();

        boolean tous = !"on".equals((String) request.getParameter("Film"))
                && !"on".equals((String) request.getParameter("CD"))
                && !"on".equals((String) request.getParameter("Livre"))
                && !"on".equals((String) request.getParameter("Periodique"));

        if ("on".equals((String) request.getParameter(Film.SUPPORT)) || tous) {
            List<Film> fList = mediaDS.getFilms();
            for (int i = 0; i < fList.size(); i++) {
                liste.add(fList.get(i));
            }
        }
        if ("on".equals((String) request.getParameter(CD.SUPPORT)) || tous) {
            List<CD> cList = mediaDS.getCDs();
            for (int i = 0; i < cList.size(); i++) {
                liste.add(cList.get(i));
            }
        }
        if ("on".equals((String) request.getParameter(Periodique.SUPPORT)) || tous) {
            List<Periodique> pList = mediaDS.getPeriodiques();
            for (int i = 0; i < pList.size(); i++) {
                liste.add(pList.get(i));
            }
        }
        if ("on".equals((String) request.getParameter(Livre.SUPPORT)) || tous) {
            List<Livre> lList = mediaDS.getLivres();
            for (int i = 0; i < lList.size(); i++) {
                liste.add(lList.get(i));
            }
        }

        String kw = (String) request.getParameter("motsClef");

        ArrayList<Oeuvre> res = appliFiltre(liste, kw);
        request.setAttribute("oeuvreList", res);
        request.setAttribute("kw", kw);
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
