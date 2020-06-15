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
@Table(name = "tramite_suspensiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TramiteSuspensiones.findAll", query = "SELECT t FROM TramiteSuspensiones t")
    , @NamedQuery(name = "TramiteSuspensiones.findByIdSuspension", query = "SELECT t FROM TramiteSuspensiones t WHERE t.idSuspension = :idSuspension")
    , @NamedQuery(name = "TramiteSuspensiones.findByFechaIniSus", query = "SELECT t FROM TramiteSuspensiones t WHERE t.fechaIniSus = :fechaIniSus")
    , @NamedQuery(name = "TramiteSuspensiones.findByFechaFinSus", query = "SELECT t FROM TramiteSuspensiones t WHERE t.fechaFinSus = :fechaFinSus")
    , @NamedQuery(name = "TramiteSuspensiones.findByMotivoSus", query = "SELECT t FROM TramiteSuspensiones t WHERE t.motivoSus = :motivoSus")
    , @NamedQuery(name = "TramiteSuspensiones.findByCorrientePago", query = "SELECT t FROM TramiteSuspensiones t WHERE t.corrientePago = :corrientePago")})
public class TramiteSuspensiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_suspension")
    private Integer idSuspension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_ini_sus")
    private String fechaIniSus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_fin_sus")
    private String fechaFinSus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "motivo_sus")
    private String motivoSus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "corriente_pago")
    private boolean corrientePago;
    @JoinColumn(name = "dni_empleado", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Empleado dniEmpleado;
    @JoinColumn(name = "tipo_tramite", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tramites tipoTramite;
    @JoinColumn(name = "dni_usuario", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Usuario dniUsuario;

    public TramiteSuspensiones() {
    }

    public TramiteSuspensiones(Integer idSuspension) {
        this.idSuspension = idSuspension;
    }

    public TramiteSuspensiones(Integer idSuspension, String fechaIniSus, String fechaFinSus, String motivoSus, boolean corrientePago) {
        this.idSuspension = idSuspension;
        this.fechaIniSus = fechaIniSus;
        this.fechaFinSus = fechaFinSus;
        this.motivoSus = motivoSus;
        this.corrientePago = corrientePago;
    }

    public Integer getIdSuspension() {
        return idSuspension;
    }

    public void setIdSuspension(Integer idSuspension) {
        this.idSuspension = idSuspension;
    }

    public String getFechaIniSus() {
        return fechaIniSus;
    }

    public void setFechaIniSus(String fechaIniSus) {
        this.fechaIniSus = fechaIniSus;
    }

    public String getFechaFinSus() {
        return fechaFinSus;
    }

    public void setFechaFinSus(String fechaFinSus) {
        this.fechaFinSus = fechaFinSus;
    }

    public String getMotivoSus() {
        return motivoSus;
    }

    public void setMotivoSus(String motivoSus) {
        this.motivoSus = motivoSus;
    }

    public boolean getCorrientePago() {
        return corrientePago;
    }

    public void setCorrientePago(boolean corrientePago) {
        this.corrientePago = corrientePago;
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
        hash += (idSuspension != null ? idSuspension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TramiteSuspensiones)) {
            return false;
        }
        TramiteSuspensiones other = (TramiteSuspensiones) object;
        if ((this.idSuspension == null && other.idSuspension != null) || (this.idSuspension != null && !this.idSuspension.equals(other.idSuspension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_POJO.TramiteSuspensiones[ idSuspension=" + idSuspension + " ]";
    }
    
}
