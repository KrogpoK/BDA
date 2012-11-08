/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque;

import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name="PANIER")
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="IPANIER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany()
    @JoinTable(name = "REL_PANIER_OEUVRE",
      joinColumns={@JoinColumn(name="IPanier_ID", referencedColumnName="IPANIER_ID")},
      inverseJoinColumns={@JoinColumn(name="IOeuvre_ID", referencedColumnName="IOEUVRE_ID")}
            )
    private List<Oeuvre> listeOeuvre;

    public Panier()
    {
        listeOeuvre = new ArrayList<Oeuvre>();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Oeuvre> getListeOeuvre() {
        return listeOeuvre;
    }

    public void setListeOeuvre(List<Oeuvre> listeOeuvre) {
        this.listeOeuvre = listeOeuvre;
    }

    public boolean ajouterOeuvre(Oeuvre o) {
        if (!listeOeuvre.contains(o)) {
            listeOeuvre.add(o);
            return true;
        }
        return false;
    }

    public void supprimerOeuvre(int idOeuvre) {
        int i = 0;
        while (i < listeOeuvre.size()) {
            if (listeOeuvre.get(i).getId() == idOeuvre) {
                listeOeuvre.remove(i);
                break;
            }
            i++;
        }
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
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.Panier[ id=" + id + " ]";
    }
}
