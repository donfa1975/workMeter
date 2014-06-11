/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabricio.diaz
 */
@Entity
@Table(name = "tblWMResumenOtrosWeb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWMResumenOtrosWeb.findAll", query = "SELECT t FROM TblWMResumenOtrosWeb t"),
    @NamedQuery(name = "TblWMResumenOtrosWeb.findByIdReporteWebOtros", query = "SELECT t FROM TblWMResumenOtrosWeb t WHERE t.idReporteWebOtros = :idReporteWebOtros"),
    @NamedQuery(name = "TblWMResumenOtrosWeb.findByIdEmpleado", query = "SELECT t FROM TblWMResumenOtrosWeb t WHERE t.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "TblWMResumenOtrosWeb.findByFecha", query = "SELECT t FROM TblWMResumenOtrosWeb t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TblWMResumenOtrosWeb.findByUso", query = "SELECT t FROM TblWMResumenOtrosWeb t WHERE t.uso = :uso"),
    @NamedQuery(name = "TblWMResumenOtrosWeb.findByWorkstation", query = "SELECT t FROM TblWMResumenOtrosWeb t WHERE t.workstation = :workstation"),
    @NamedQuery(name = "TblWMResumenOtrosWeb.findByDireccion", query = "SELECT t FROM TblWMResumenOtrosWeb t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "TblWMResumenOtrosWeb.findByHora", query = "SELECT t FROM TblWMResumenOtrosWeb t WHERE t.hora = :hora")})
public class TblWMResumenOtrosWeb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idReporteWebOtros")
    private Integer idReporteWebOtros;
    @Column(name = "idEmpleado")
    private Integer idEmpleado;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "uso")
    private BigDecimal uso;
    @Size(max = 50)
    @Column(name = "workstation")
    private String workstation;
    @Size(max = 350)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "hora")
    private Short hora;

    public TblWMResumenOtrosWeb() {
    }

    public TblWMResumenOtrosWeb(Integer idReporteWebOtros) {
        this.idReporteWebOtros = idReporteWebOtros;
    }

    public Integer getIdReporteWebOtros() {
        return idReporteWebOtros;
    }

    public void setIdReporteWebOtros(Integer idReporteWebOtros) {
        this.idReporteWebOtros = idReporteWebOtros;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getUso() {
        return uso;
    }

    public void setUso(BigDecimal uso) {
        this.uso = uso;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Short getHora() {
        return hora;
    }

    public void setHora(Short hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporteWebOtros != null ? idReporteWebOtros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWMResumenOtrosWeb)) {
            return false;
        }
        TblWMResumenOtrosWeb other = (TblWMResumenOtrosWeb) object;
        if ((this.idReporteWebOtros == null && other.idReporteWebOtros != null) || (this.idReporteWebOtros != null && !this.idReporteWebOtros.equals(other.idReporteWebOtros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TblWMResumenOtrosWeb[ idReporteWebOtros=" + idReporteWebOtros + " ]";
    }
    
}
