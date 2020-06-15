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
@Table(name = "tramite_altas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TramiteAltas.findAll", query = "SELECT t FROM TramiteAltas t")
    , @NamedQuery(name = "TramiteAltas.findByIdAlta", query = "SELECT t FROM TramiteAltas t WHERE t.idAlta = :idAlta")
    , @NamedQuery(name = "TramiteAltas.findByFechaAlta", query = "SELECT t FROM TramiteAltas t WHERE t.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "TramiteAltas.findByTipoPlan", query = "SELECT t FROM TramiteAltas t WHERE t.tipoPlan = :tipoPlan")
    , @NamedQuery(name = "TramiteAltas.findByPrecio", query = "SELECT t FROM TramiteAltas t WHERE t.precio = :precio")
    , @NamedQuery(name = "TramiteAltas.findByCorrientePago", query = "SELECT t FROM TramiteAltas t WHERE t.corrientePago = :corrientePago")})
public class TramiteAltas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_alta")
    private Integer idAlta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_alta")
    private String fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo_plan")
    private String tipoPlan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private int precio;
    @Column(name = "corriente_pago")
    private Boolean corrientePago;
    @JoinColumn(name = "dni_empleado", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Empleado dniEmpleado;
    @JoinColumn(name = "tipo_tramite", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tramites tipoTramite;
    @JoinColumn(name = "dni_usuario", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Usuario dniUsuario;

    public TramiteAltas() {
    }

    public TramiteAltas(Integer idAlta) {
        this.idAlta = idAlta;
    }

    public TramiteAltas(Integer idAlta, String fechaAlta, String tipoPlan, int precio) {
        this.idAlta = idAlta;
        this.fechaAlta = fechaAlta;
        this.tipoPlan = tipoPlan;
        this.precio = precio;
    }

    public Integer getIdAlta() {
        return idAlta;
    }

    public void setIdAlta(Integer idAlta) {
        this.idAlta = idAlta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Boolean getCorrientePago() {
        return corrientePago;
    }

    public void setCorrientePago(Boolean corrientePago) {
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
        hash += (idAlta != null ? idAlta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TramiteAltas)) {
            return false;
        }
        TramiteAltas other = (TramiteAltas) object;
        if ((this.idAlta == null && other.idAlta != null) || (this.idAlta != null && !this.idAlta.equals(other.idAlta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_POJO.TramiteAltas[ idAlta=" + idAlta + " ]";
    }
    
}
