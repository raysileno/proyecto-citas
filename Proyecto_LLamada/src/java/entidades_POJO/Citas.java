/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_POJO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jluis
 */
@Entity
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
    , @NamedQuery(name = "Citas.findByCodigoCita", query = "SELECT c FROM Citas c WHERE c.codigoCita = :codigoCita")
    , @NamedQuery(name = "Citas.findByFecha", query = "SELECT c FROM Citas c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Citas.findByHora", query = "SELECT c FROM Citas c WHERE c.hora = :hora")
    , @NamedQuery(name = "Citas.findByLlamado", query = "SELECT c FROM Citas c WHERE c.llamado = :llamado")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo_cita")
    private String codigoCita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "hora")
    private String hora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "llamado")
    private boolean llamado;
    @JoinColumn(name = "tipo_tramite", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tramites tipoTramite;
    @JoinColumn(name = "dni_usuario", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Usuario dniUsuario;

    public Citas() {
    }

    public Citas(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Citas(String codigoCita, String fecha, String hora, boolean llamado) {
        this.codigoCita = codigoCita;
        this.fecha = fecha;
        this.hora = hora;
        this.llamado = llamado;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean getLlamado() {
        return llamado;
    }

    public void setLlamado(boolean llamado) {
        this.llamado = llamado;
    }

    public Tramites getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(Tramites tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Usuario getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(Usuario dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCita != null ? codigoCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.codigoCita == null && other.codigoCita != null) || (this.codigoCita != null && !this.codigoCita.equals(other.codigoCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Citas[ codigoCita=" + codigoCita + " ]";
    }
    
}
