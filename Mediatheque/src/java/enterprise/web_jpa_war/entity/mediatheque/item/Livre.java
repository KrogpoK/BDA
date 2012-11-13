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
@Table(name = "LIVRE")
public class Livre extends Oeuvre implements Serializable {

    public final static String AUTEUR = "auteur";
    public final static String EDITEUR = "editeur";
    
    private static final long serialVersionUID = 1L;
    public static final String SUPPORT = "Livre";
    @Column(name = "SAUTEUR")
    private String auteur;
    @Column(name = "SEDITEUR")
    private String editeur;

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static Livre buildMoke() {

        Livre f = new Livre();
        f.setDateParution(DateTool.parseDate("2009-06-12"));
        f.setGenre(Oeuvre.generateRandomGenre());
        f.setLangue(Oeuvre.generateRandomLangue());
        f.setTitre(Oeuvre.generateRandomTitle());
        f.setAuteur(Livre.generateRandomAuteur());
        f.setEditeur(Livre.generateRandomEditeur());
        
        return f;
    }

    private static String generateRandomAuteur() {
        ArrayList<String> auteur = new ArrayList<String>();

        auteur.add("Gérard Gaston");
        auteur.add("Francois Dubois");
        auteur.add("Sélio Rodrigez");
        auteur.add("Michel Dupont");
        auteur.add("Fred le touriste");
        auteur.add("Ted Mosby");

        
        Random r = new Random();

        String auteuru = auteur.get(r.nextInt(auteur.size()));

        return auteuru;
    }

    private static String generateRandomEditeur() {
        ArrayList<String> editeur = new ArrayList<String>();

        editeur.add("Gérard Gaston");
        editeur.add("Francois Dubois");
        editeur.add("Sélio Rodrigez");
        editeur.add("Michel Dupont");
        editeur.add("Fred le touriste");
        editeur.add("Ted Mosby");

        Random r = new Random();

        String editeuru = editeur.get(r.nextInt(editeur.size()));

        return editeuru;
    }

    public static ArrayList<Livre> buildMoke(int nb) {
        ArrayList<Livre> al = new ArrayList<Livre>();

        for (int i = 0; i < nb; i++) {
            al.add(buildMoke());
        }
        return al;

    }
    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.Livre[ id=" + id + " ]";
    }

    @Override
    public String getStrType() {
        return SUPPORT;
    }
}
