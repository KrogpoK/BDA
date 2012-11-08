/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.util.DateTool;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "COMPTE")
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "FSOLDE")
    private double solde;
    @Column(name = "BCOTIS_VALIDE")
    private boolean cotisValide;
    @Column(name = "DCREATION")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ICARTE_MAG_ID")
    private CarteMagnetique carteMagnetique;
    @OneToOne(mappedBy = "compte", cascade = CascadeType.ALL)
    @JoinColumn(name = "IADHERENT_ID")
    private Adherent proprietaire;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="IPANIER_ID", nullable=true)
    private Panier panier;
    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Reservation> listeReservation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public boolean isCotisValide() {
        return cotisValide;
    }

    public void setCotisValide(boolean cotisValide) {
        this.cotisValide = cotisValide;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public CarteMagnetique getCarteMagnetique() {
        return carteMagnetique;
    }

    public void setCarteMagnetique(CarteMagnetique carteMagnetique) {
        this.carteMagnetique = carteMagnetique;
    }

    public Adherent getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Adherent proprietaire) {
        this.proprietaire = proprietaire;
    }

    public List<Reservation> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }

    public Panier getPanier() {
        if(panier == null)
        {
            panier = new Panier();
        }
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
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
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static Compte buildMoke() {
        Compte compte = new Compte();
        CarteMagnetique cm = new CarteMagnetique();
        Random r = new Random();
        int n = r.nextInt(876542345);
        cm.setNumCarte("" + n);
        compte.setCarteMagnetique(cm);
        compte.setCotisValide(true);
        compte.setSolde(98.09);

        Date creation = DateTool.parseDate("2012-06-21");
        compte.setDateCreation(creation);
        return compte;


    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.Compte[ id=" + id + " ]";
    }
}
