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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "PERIODIQUE")
public class Periodique extends Oeuvre implements Serializable {

    public final static String THEME = "theme";
    public final static String PERIODICITE = "periodicite";
    
    private static final long serialVersionUID = 1L;
    public static final String SUPPORT = "Periodique";
    @Column(name = "STHEME")
    private String theme;
    @Column(name = "STYPE_PERIODIQUE")
    private String type;
    @Column(name = "SPERIODICITE")
    private String periodicite;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodique)) {
            return false;
        }
        Periodique other = (Periodique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   public static Periodique buildMoke() {

        Periodique f = new Periodique();
        f.setDateParution(DateTool.parseDate("2009-06-12"));
        f.setGenre(Oeuvre.generateRandomGenre());
        f.setLangue(Oeuvre.generateRandomLangue());
        f.setTitre(Oeuvre.generateRandomTitle());
        f.setType("Polar");
        f.setTheme(generateRandomTheme());
        f.setPeriodicite(generateRandomPeriodicite());
        return f;
    }

    private static String generateRandomTheme() {
        ArrayList<String> theme = new ArrayList<String>();

        theme.add("Sport");
        theme.add("Politique");
        theme.add("Histoire");
        theme.add("People");

        Random r = new Random();

        String themeu = theme.get(r.nextInt(theme.size()));

        return themeu;
    }

    private static String generateRandomPeriodicite() {
        ArrayList<String> perio = new ArrayList<String>();

        perio.add("Mensuel");
        perio.add("Bi-Mensuel");
        perio.add("Hebdomadaire d'inspiration");

        Random r = new Random();

        String periou = perio.get(r.nextInt(perio.size()));

        return periou;
    }

    public static ArrayList<Periodique> buildMoke(int nb) {
        ArrayList<Periodique> al = new ArrayList<Periodique>();

        for (int i = 0; i < nb; i++) {
            al.add(buildMoke());
        }
        return al;

    }
    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.Periodique[ id=" + id + " ]";
    }
    
    
    @Override
    public String getStrType() {
        return SUPPORT;
    }
}
