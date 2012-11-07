/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.entity.adress;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ADRESSE")
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IADRESS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "SCOUNTRY")
    private String country;
    @Column(name = "SSTREET")
    private String street;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ICITY_ID", nullable = false)
    @ForeignKey(name = "FK_ADRESS_CITY")
    private City city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static Adresse buildMoke() {
        Adresse add = new Adresse();
        City city = new City();
        city.setName("Nice");
        city.setPostalCode("06100");
        add.setCity(city);
        add.setCountry("France");
        add.setStreet("avenue de la californie");
        return add;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.adress.Adresse[ id=" + id + " ]";
    }
}
