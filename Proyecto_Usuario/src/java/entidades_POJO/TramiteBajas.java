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
@Table(name = "tramite_bajas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TramiteBajas.findAll", query = "SELECT t FROM TramiteBajas t")
    , @NamedQuery(name = "TramiteBajas.findByIdBaja", query = "SELECT t FROM TramiteBajas t WHERE t.idBaja = :idBaja")
    , @NamedQuery(name = "TramiteBajas.findByFechaBaja", query = "SELECT t FROM TramiteBajas t WHERE t.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "TramiteBajas.findByMotivo", query = "SELECT t FROM TramiteBajas t WHERE t.motivo = :motivo")
    , @NamedQuery(name = "TramiteBajas.findByCorrientePago", query = "SELECT t FROM TramiteBajas t WHERE t.corrientePago = :corrientePago")})
public class TramiteBajas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_baja")
    private Integer idBaja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_baja")
    private String fechaBaja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "motivo")
    private String motivo;
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

    public TramiteBajas() {
    }

    public TramiteBajas(Integer idBaja) {
        this.idBaja = idBaja;
    }

    public TramiteBajas(Integer idBaja, String fechaBaja, String motivo, boolean corrientePago) {
        this.idBaja = idBaja;
        this.fechaBaja = fechaBaja;
        this.motivo = motivo;
        this.corrientePago = corrientePago;
    }

    public Integer getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(Integer idBaja) {
        this.idBaja = idBaja;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
        hash += (idBaja != null ? idBaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TramiteBajas)) {
            return false;
        }
        TramiteBajas other = (TramiteBajas) object;
        if ((this.idBaja == null && other.idBaja != null) || (this.idBaja != null && !this.idBaja.equals(other.idBaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_POJO.TramiteBajas[ idBaja=" + idBaja + " ]";
    }
    
}
