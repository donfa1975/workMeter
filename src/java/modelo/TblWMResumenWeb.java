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
@Table(name = "tblWMResumenWeb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWMResumenWeb.findAll", query = "SELECT t FROM TblWMResumenWeb t"),
    @NamedQuery(name = "TblWMResumenWeb.findByIdReporteWeb", query = "SELECT t FROM TblWMResumenWeb t WHERE t.idReporteWeb = :idReporteWeb"),
    @NamedQuery(name = "TblWMResumenWeb.findByIdEmpleado", query = "SELECT t FROM TblWMResumenWeb t WHERE t.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "TblWMResumenWeb.findByFecha", query = "SELECT t FROM TblWMResumenWeb t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TblWMResumenWeb.findByUso", query = "SELECT t FROM TblWMResumenWeb t WHERE t.uso = :uso"),
    @NamedQuery(name = "TblWMResumenWeb.findByWorkstation", query = "SELECT t FROM TblWMResumenWeb t WHERE t.workstation = :workstation"),
    @NamedQuery(name = "TblWMResumenWeb.findByIdWeb", query = "SELECT t FROM TblWMResumenWeb t WHERE t.idWeb = :idWeb"),
    @NamedQuery(name = "TblWMResumenWeb.findByHora", query = "SELECT t FROM TblWMResumenWeb t WHERE t.hora = :hora")})
public class TblWMResumenWeb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idReporteWeb")
    private Integer idReporteWeb;
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
    @Column(name = "idWeb")
    private Integer idWeb;
    @Column(name = "hora")
    private Short hora;

    public TblWMResumenWeb() {
    }

    public TblWMResumenWeb(Integer idReporteWeb) {
        this.idReporteWeb = idReporteWeb;
    }

    public Integer getIdReporteWeb() {
        return idReporteWeb;
    }

    public void setIdReporteWeb(Integer idReporteWeb) {
        this.idReporteWeb = idReporteWeb;
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

    public Integer getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Integer idWeb) {
        this.idWeb = idWeb;
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
        hash += (idReporteWeb != null ? idReporteWeb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWMResumenWeb)) {
            return false;
        }
        TblWMResumenWeb other = (TblWMResumenWeb) object;
        if ((this.idReporteWeb == null && other.idReporteWeb != null) || (this.idReporteWeb != null && !this.idReporteWeb.equals(other.idReporteWeb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TblWMResumenWeb[ idReporteWeb=" + idReporteWeb + " ]";
    }
    
}
