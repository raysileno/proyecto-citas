/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_POJO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jluis
 */
@Entity
@Table(name = "tramites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tramites.findAll", query = "SELECT t FROM Tramites t")
    , @NamedQuery(name = "Tramites.findById", query = "SELECT t FROM Tramites t WHERE t.id = :id")
    , @NamedQuery(name = "Tramites.findByNombreTramite", query = "SELECT t FROM Tramites t WHERE t.nombreTramite = :nombreTramite")})
public class Tramites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "nombre_tramite")
    private String nombreTramite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTramite")
    private Collection<Citas> citasCollection;

    public Tramites() {
    }

    public Tramites(Integer id) {
        this.id = id;
    }

    public Tramites(Integer id, String nombreTramite) {
        this.id = id;
        this.nombreTramite = nombreTramite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTramite() {
        return nombreTramite;
    }

    public void setNombreTramite(String nombreTramite) {
        this.nombreTramite = nombreTramite;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
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
        if (!(object instanceof Tramites)) {
            return false;
        }
        Tramites other = (Tramites) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Tramites[ id=" + id + " ]";
    }
    
}
