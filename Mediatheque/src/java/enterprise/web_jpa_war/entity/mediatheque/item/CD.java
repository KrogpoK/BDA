/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.mediatheque.item;

import java.io.Serializable;
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

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.mediatheque.item.CD[ id=" + id + " ]";
    }
    
    
    @Override
    public String getStrType() {
       return SUPPORT;
    }
}
