/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.adress.Adresse;
import enterprise.web_jpa_war.entity.adress.City;
import enterprise.web_jpa_war.entity.mediatheque.Compte;
import enterprise.web_jpa_war.facade.impl.AdherentDS;
import enterprise.web_jpa_war.servlet.common.AbstractServlet;
import enterprise.web_jpa_war.util.DateTool;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "CreatePerson", urlPatterns = {"/CreatePerson"})
public class CreatePerson extends AbstractServlet {

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
            em = emf.createEntityManager();
            adherentDS = new AdherentDS(em);
//            AdherentDao dao = new AdherentDao(em);

            Adherent a = new Adherent();
            Adresse ad = new Adresse();
            City c = new City();
            a.setNom(request.getParameter("nom"));
            a.setPrenom(request.getParameter("prenom"));
            a.setMail(request.getParameter("mail"));
            a.setDateNaissance(DateTool.parseDate(request.getParameter("bDate")));
            ad.setStreet(request.getParameter("rue"));
            ad.setCountry(request.getParameter("pays"));
            c.setName(request.getParameter("ville"));
            c.setPostalCode(request.getParameter("CP"));
            
            a.setAdress(ad);
            ad.setCity(c);
            a.setCompte(Compte.buildMoke());
            adherentDS.creerAdherent(a);
            
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
