/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque.item;

import enterprise.web_jpa_war.util.DateTool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "CD")
public class CD extends Oeuvre implements Serializable {

    public final static String MAISONEDITION = "maisonEdition";
    public final static String INTERPRETE = "interprete";
    
    private static final long serialVersionUID = 1L;
    public static final String SUPPORT = "CD";
    
    @Column(name = "SMAISON_EDITION")
    private String maisonEdition;
    @Column(name = "SINTERPRETE")
    private String interprete;
    @Column(name = "INB_PISTE")
    private int nbPiste;

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition(String maisonEdiion) {
        this.maisonEdition = maisonEdiion;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public int getNbPiste() {
        return nbPiste;
    }

    public void setNbPiste(int nbPiste) {
        this.nbPiste = nbPiste;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CD)) {
            return false;
        }
        CD other = (CD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
        public static CD buildMoke() {

        CD f = new CD();
        f.setDateParution(DateTool.parseDate("2009-06-12"));
        f.setGenre(Oeuvre.generateRandomGenre());
        f.setLangue(Oeuvre.generateRandomLangue());
        f.setTitre(Oeuvre.generateRandomTitle());
        f.setGenre(Oeuvre.generateRandomGenre());
        f.setInterprete(CD.generateRandomInterprete());
        f.setNbPiste(12);
        f.setMaisonEdition(CD.generateRandomMaisonEdition());
        return f;
    }

    private static String generateRandomInterprete() {
        ArrayList<String> interprete = new ArrayList<String>();
        
        interprete.add("Gérard Gaston");
        interprete.add("Francois Dubois");
        interprete.add("Sélio Rodrigez");
        interprete.add("Michel Dupont");
        interprete.add("Fred le touriste");
        interprete.add("Ted Mosby");

        Random r = new Random();

        String interpretee = interprete.get(r.nextInt(interprete.size()));

        return interpretee;
    }

    private static String generateRandomMaisonEdition() {
        ArrayList<String> maisonEdition = new ArrayList<String>();
        
        maisonEdition.add("Gérard Gaston");
        maisonEdition.add("Francois Dubois");
        maisonEdition.add("Sélio Rodrigez");
        maisonEdition.add("Michel Dupont");
        maisonEdition.add("Fred le touriste");
        maisonEdition.add("Ted Mosby");

        Random r = new Random();

        String maisonEditione = maisonEdition.get(r.nextInt(maisonEdition.size()));

        return maisonEditione;
    }

    public static ArrayList<CD> buildMoke(int nb) {
        ArrayList<CD> cd = new ArrayList<CD>();

        for (int i = 0; i < nb; i++) {
            cd.add(buildMoke());
        }
        return cd;

    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.CD[ id=" + id + " ]";
    }
    
    
    @Override
    public String getStrType() {
       return SUPPORT;
    }
}
