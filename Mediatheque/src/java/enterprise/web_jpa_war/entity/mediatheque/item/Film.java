/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque.item;

import enterprise.web_jpa_war.util.DateTool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "Film")
public class Film extends Oeuvre implements Serializable {

    public final static String REALISATEUR = "realisateur";
    public final static String ACTEURPRINCIPAL = "acteurPrincipal";
    private static final long serialVersionUID = 1L;
    public static final String SUPPORT = "Film";
    @Column(name = "SREALISATEUR")
    private String realisateur;
    @Column(name = "SACTEURPRINCIPAL")
    private String acteurPrincipal;
    @Column(name = "STYPE")
    private String type;
    @Column(name = "TDUREE")
    @Temporal(TemporalType.TIME)
    private Date duree;

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getActeurPrincipal() {
        return acteurPrincipal;
    }

    public void setActeurPrincipal(String acteurPrincipal) {
        this.acteurPrincipal = acteurPrincipal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDuree() {
        return duree;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static Film buildMoke() {

        Film f = new Film();
        f.setDateParution(DateTool.parseDate("2009-06-12"));
        f.setDuree(DateTool.parseTime("03:59:43"));
        f.setGenre(Oeuvre.generateRandomGenre());
        f.setLangue(Oeuvre.generateRandomLangue());
        f.setRealisateur(Film.generateRandomRealisateur());
        f.setActeurPrincipal(Film.generateRandomActeurPrincipal());
        f.setTitre(Oeuvre.generateRandomTitle());
        f.setType("Polar");
        return f;
    }

    private static String generateRandomRealisateur() {
        ArrayList<String> realisateur = new ArrayList<String>();

        realisateur.add("Gérard Gaston");
        realisateur.add("Francois Dubois");
        realisateur.add("Sélio Rofrigez");
        realisateur.add("Michel Dupont");
        realisateur.add("Fred le touriste");

        Random r = new Random();

        String realisateu = realisateur.get(r.nextInt(realisateur.size()));

        return realisateu;
    }

    private static String generateRandomActeurPrincipal() {
        ArrayList<String> acteurPrincipal = new ArrayList<String>();

        acteurPrincipal.add("Hubert le couvert");
        acteurPrincipal.add("Guy Copain");
        acteurPrincipal.add("pas d'inspiration");
        acteurPrincipal.add("pas envie de réfléchir");
        acteurPrincipal.add("cool");

        Random r = new Random();

        String acteurPrincipa = acteurPrincipal.get(r.nextInt(acteurPrincipal.size()));

        return acteurPrincipa;
    }

    public static ArrayList<Film> buildMoke(int nb) {
        ArrayList<Film> al = new ArrayList<Film>();

        for (int i = 0; i < nb; i++) {
            al.add(buildMoke());
        }
        return al;

    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.Film[ id=" + id + " ]";
    }

    @Override
    public String getStrType() {
        return SUPPORT;
    }
}
