/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque.item;

import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.util.DateTool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "OEUVRE")
public abstract class Oeuvre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IOEUVRE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    @Column(name = "DPARUTION")
    @Temporal(TemporalType.DATE)
    protected Date dateParution;
    @Column(name = "STITRE")
    protected String titre;
    @Column(name = "SGENRE")
    protected String genre;
    @OneToMany(mappedBy = "oeuvre", cascade = CascadeType.ALL)
    List<Reservation> listeReservation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }

    public String getTitre() {
        return titre;
    }

    public String getTitre(String kw) {
        String rez = "";
        for (String partTitre : getTitre().split(" ")) {
            boolean found = false;
            for (String s : kw.split(" ")) {
                if (partTitre.equalsIgnoreCase(s)) {
                    found = true;
                }
            }
            if (found) {
                rez += ("<span style=\"color:red;\" ><strong>" + partTitre + "</strong></span>");
            } else {
                rez += partTitre;
            }
            rez += " ";
        }
        return rez;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }

    public String getGenre(String kw) {
        if (kw.toLowerCase().contains(genre.toLowerCase())) {
            return "<span style=\"color:red;\" ><strong>" + genre + "</genre></span";
        } else {
            return genre;
        }
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Reservation> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }

    public String getStrDateParution() {
        return DateTool.printDate(dateParution);
    }

    public abstract String getStrType();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oeuvre)) {
            return false;
        }
        Oeuvre other = (Oeuvre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre[ id=" + id + " ]";
    }
    
    public static String generateRandomTitle()
    {
        ArrayList<String> sujet = new ArrayList<String>();
        ArrayList<String> couleur = new ArrayList<String>();
        ArrayList<String> lieu = new ArrayList<String>();
       

        sujet.add("Le chien");
        sujet.add("Le chat");
        sujet.add("Le pinguin");
        sujet.add("L'elephant");
        sujet.add("Le ver de terre");
        sujet.add("Le mamouth");
        sujet.add("Le serpent");
        sujet.add("Le chiwawa");
        sujet.add("Le zebre");
        sujet.add("Le jaguar");
        sujet.add("Le boulet");
        sujet.add("Le poulet");
        sujet.add("Le coq");
        sujet.add("L'ane");
        sujet.add("Le cochon");
        sujet.add("Le rat");

        couleur.add("rouge");
        couleur.add("jaune");
        couleur.add("bleu");
        couleur.add("vert");
        couleur.add("gris");
        couleur.add("noir");
        couleur.add("cyan");
        couleur.add("violet");
        couleur.add("pourpre");
        couleur.add("sepia");
        couleur.add("orange");
        couleur.add("rose");
        couleur.add("magenta");
        couleur.add("abricot");
        couleur.add("bleu clair");
        couleur.add("bleu fonce");
        couleur.add("bleu marine");
        couleur.add("cuivre");
        couleur.add("blanc");

        lieu.add("de la chapelle");
        lieu.add("de la maison");
        lieu.add("de la piscine");
        lieu.add("de la tour");
        lieu.add("de la tourelle");
        lieu.add("de l'eglise");
        lieu.add("du restaurant");
        lieu.add("de la foret");
        lieu.add("de la colline");
        lieu.add("de la voisine");
        lieu.add("de la grand mere");
        lieu.add("de Tourcoing");
        lieu.add("de Paris");
        lieu.add("de Nice");
        lieu.add("de Marseille");
        lieu.add("de Lyon");
        lieu.add("de Brest");
        
        Random r = new Random();
        String titre = sujet.get(r.nextInt(sujet.size())) + " "
                + couleur.get(r.nextInt(couleur.size())) + " "
                + lieu.get(r.nextInt(lieu.size()));
        
        return titre;
    }
}
