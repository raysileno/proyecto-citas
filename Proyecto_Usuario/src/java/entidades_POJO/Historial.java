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
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h")
    , @NamedQuery(name = "Historial.findByIdHistorial", query = "SELECT h FROM Historial h WHERE h.idHistorial = :idHistorial")
    , @NamedQuery(name = "Historial.findByFechaCita", query = "SELECT h FROM Historial h WHERE h.fechaCita = :fechaCita")
    , @NamedQuery(name = "Historial.findByHoraCita", query = "SELECT h FROM Historial h WHERE h.horaCita = :horaCita")
    , @NamedQuery(name = "Historial.findByHoraFinalizado", query = "SELECT h FROM Historial h WHERE h.horaFinalizado = :horaFinalizado")
    , @NamedQuery(name = "Historial.findByRealizado", query = "SELECT h FROM Historial h WHERE h.realizado = :realizado")})
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_historial")
    private Integer idHistorial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_cita")
    private String fechaCita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "hora_cita")
    private String horaCita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "hora_finalizado")
    private String horaFinalizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "realizado")
    private boolean realizado;
    @JoinColumn(name = "dni_empleado", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Empleado dniEmpleado;
    @JoinColumn(name = "tipo_tramite", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tramites tipoTramite;
    @JoinColumn(name = "dni_usuario", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Usuario dniUsuario;

    public Historial() {
    }

    public Historial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Historial(Integer idHistorial, String fechaCita, String horaCita, String horaFinalizado, boolean realizado) {
        this.idHistorial = idHistorial;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.horaFinalizado = horaFinalizado;
        this.realizado = realizado;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getHoraFinalizado() {
        return horaFinalizado;
    }

    public void setHoraFinalizado(String horaFinalizado) {
        this.horaFinalizado = horaFinalizado;
    }

    public boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public Empleado getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(Empleado dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
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
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_POJO.Historial[ idHistorial=" + idHistorial + " ]";
    }
    
}
