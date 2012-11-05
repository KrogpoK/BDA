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

    private static final long serialVersionUID = 1L;
    public static final String SUPPORT = "Film";
    @Column(name = "SREALISATEUR")
    private String realisateur;
    @Column(name = "SLANGUE")
    private String langue;
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

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
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

    public static Film buildMoke(String titre, String genre) {

        Film f = new Film();
        f.setDateParution(DateTool.parseDate("2009-06-12"));
        f.setDuree(DateTool.parseTime("03:59:43"));
        f.setGenre(genre);
        f.setLangue("Francais");
        f.setRealisateur("Moi");
        f.setTitre(titre);
        f.setType("Polar");
        return f;

    }

    public static Film buildMoke() {
        ArrayList<String> genre = new ArrayList<String>();
        
        genre.add("action");
        genre.add("amour");
        genre.add("thriller");
        genre.add("policier");
        genre.add("nouvelle");
        genre.add("anime");
        genre.add("horreur");
        genre.add("suspense");
        genre.add("roman");
        genre.add("biographie");

        Random r = new Random();

        String genr = genre.get(r.nextInt(genre.size()));

        return buildMoke(Oeuvre.generateRandomTitle(), genr);
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
