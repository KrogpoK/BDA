/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity;

import enterprise.web_jpa_war.entity.adress.Adresse;
import enterprise.web_jpa_war.entity.mediatheque.Compte;
import enterprise.web_jpa_war.util.DateTool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ADHERENT")
public class Adherent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IADHERENT_ID")
    private Integer id;
    @Column(name = "SNOM")
    private String nom;
    @Column(name = "SPRENOM")
    private String prenom;
    @Column(name = "SMAIL")
    private String mail;
    @Temporal(TemporalType.DATE)
    @Column(name = "DDATE_NAISSANCE")
    private Date dateNaissance;
    @Column(name="SLOGIN")
    private String login;
    @Column(name="SPASS")
    private String pass;
    @Column(name="BIS_ADMIN")
    private boolean admin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    @ForeignKey(name = "FK_ADHERENT_ADRESS")
    private Adresse adress;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "ICOMPTE_ID")
    private Compte compte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Adresse getAdress() {
        return adress;
    }

    public void setAdress(Adresse adress) {
        this.adress = adress;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
        this.compte.setProprietaire(this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getStrDateDeNaissance()
    {
        return DateTool.printDate(dateNaissance);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adherent)) {
            return false;
        }
        Adherent other = (Adherent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static Adherent buildMoke(String nom, String prenom, String bDate) {
        Adherent user = new Adherent();
        user.setDateNaissance(DateTool.parseDate(bDate));
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setMail("nini@gmail.com");
        user.setAdress(Adresse.buildMoke());
        user.setCompte(Compte.buildMoke());
        user.getCompte().setProprietaire(user);
        user.setLogin(nom+""+prenom);
        user.setAdmin(false);
        user.setPass("pass");

        return user;

    }

    public static Adherent buildMock() {
        ArrayList<String> noms = new ArrayList<String>();
        noms.add("Pierre");
        noms.add("Paul");
        noms.add("Jaques");
        noms.add("Jean");
        noms.add("Gustave");
        noms.add("Robert");
        noms.add("Bertrand");
        noms.add("Geraldine");
        noms.add("Francoise");
        noms.add("Solange");
        noms.add("Jasmine");
        noms.add("Aladin");
        noms.add("Genie");

        Random r = new Random();
        String nom = noms.get(r.nextInt(noms.size()));
        String prenom = noms.get(r.nextInt(noms.size()));
        int annee = 1900 + r.nextInt(110);
        int mois = 1 + r.nextInt(12);
        int jour = 1 + r.nextInt(28);

        return buildMoke(nom, prenom, annee + "-" + mois + "-" + jour);
    }

    public static ArrayList<Adherent> buildMock(int nb) {
        ArrayList<Adherent> liste = new ArrayList<Adherent>();
        for (int i = 0; i < nb; i++) {
            liste.add(buildMock());
        }

        return liste;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.Adherent[ id=" + id + " ]";
    }
}
