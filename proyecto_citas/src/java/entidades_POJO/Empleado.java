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
 * @author Eric
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByDni", query = "SELECT e FROM Empleado e WHERE e.dni = :dni")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByApellidos", query = "SELECT e FROM Empleado e WHERE e.apellidos = :apellidos")
    , @NamedQuery(name = "Empleado.findByPassword", query = "SELECT e FROM Empleado e WHERE e.password = :password")
    , @NamedQuery(name = "Empleado.findByMesa", query = "SELECT e FROM Empleado e WHERE e.mesa = :mesa")
    , @NamedQuery(name = "Empleado.findByTipoTramite", query = "SELECT e FROM Empleado e WHERE e.tipoTramite = :tipoTramite")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "dni")
    private String dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mesa")
    private int mesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tramite")
    private int tipoTramite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dniEmpleado")
    private Collection<TramiteAltas> tramiteAltasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dniEmpleado")
    private Collection<Historial> historialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dniEmpleado")
    private Collection<TramiteSuspensiones> tramiteSuspensionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dniEmpleado")
    private Collection<TramiteBajas> tramiteBajasCollection;

    public Empleado() {
    }

    public Empleado(String dni) {
        this.dni = dni;
    }

    public Empleado(String dni, String nombre, String apellidos, String password, int mesa, int tipoTramite) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.mesa = mesa;
        this.tipoTramite = tipoTramite;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(int tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    @XmlTransient
    public Collection<TramiteAltas> getTramiteAltasCollection() {
        return tramiteAltasCollection;
    }

    public void setTramiteAltasCollection(Collection<TramiteAltas> tramiteAltasCollection) {
        this.tramiteAltasCollection = tramiteAltasCollection;
    }

    @XmlTransient
    public Collection<Historial> getHistorialCollection() {
        return historialCollection;
    }

    public void setHistorialCollection(Collection<Historial> historialCollection) {
        this.historialCollection = historialCollection;
    }

    @XmlTransient
    public Collection<TramiteSuspensiones> getTramiteSuspensionesCollection() {
        return tramiteSuspensionesCollection;
    }

    public void setTramiteSuspensionesCollection(Collection<TramiteSuspensiones> tramiteSuspensionesCollection) {
        this.tramiteSuspensionesCollection = tramiteSuspensionesCollection;
    }

    @XmlTransient
    public Collection<TramiteBajas> getTramiteBajasCollection() {
        return tramiteBajasCollection;
    }

    public void setTramiteBajasCollection(Collection<TramiteBajas> tramiteBajasCollection) {
        this.tramiteBajasCollection = tramiteBajasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dni != null ? dni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nombre:  " + nombre + "   Apellidos:  " + apellidos + "   DNI:  " + dni
                + "   Password:   " + password + "   Mesa:  " + mesa
                + "   Tipo de trámite:  " + tipoTramite;
    }
    
}
