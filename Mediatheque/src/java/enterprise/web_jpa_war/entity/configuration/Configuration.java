/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.configuration;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author user
 */
@Entity
public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "IDCONFIGURATION")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOMSUPPORT")
    private String nomSupport;
       
    @Column(name = "ESTRENOUVELABLE")
    private boolean estRenouvelable;
          
    @Column(name = "NBOUVRAGESMEMETYPE")
    private int nbOuvragesMemeType;
             
    @Column(name = "NBJOURS")
    private int nbJours;
                   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomSupport() {
        return nomSupport;
    }

    public void setNomSupport(String nomSupport) {
        this.nomSupport = nomSupport;
    }

    public boolean isEstRenouvelable() {
        return estRenouvelable;
    }

    public void setEstRenouvelable(boolean estRenouvelable) {
        this.estRenouvelable = estRenouvelable;
    }

    public int getNbOuvragesMemeType() {
        return nbOuvragesMemeType;
    }

    public void setNbOuvragesMemeType(int nbOuvragesMemeType) {
        this.nbOuvragesMemeType = nbOuvragesMemeType;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuration)) {
            return false;
        }
        Configuration other = (Configuration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.configuration.Configuration[ id=" + id + " ]";
    }
    
}