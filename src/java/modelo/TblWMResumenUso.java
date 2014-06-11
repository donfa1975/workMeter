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
@Table(name = "tblWMResumenUso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWMResumenUso.findAll", query = "SELECT t FROM TblWMResumenUso t"),
    @NamedQuery(name = "TblWMResumenUso.findByIdReporte", query = "SELECT t FROM TblWMResumenUso t WHERE t.idReporte = :idReporte"),
    @NamedQuery(name = "TblWMResumenUso.findByIdEmpleado", query = "SELECT t FROM TblWMResumenUso t WHERE t.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "TblWMResumenUso.findByFecha", query = "SELECT t FROM TblWMResumenUso t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TblWMResumenUso.findByUso", query = "SELECT t FROM TblWMResumenUso t WHERE t.uso = :uso"),
    @NamedQuery(name = "TblWMResumenUso.findByWorkstation", query = "SELECT t FROM TblWMResumenUso t WHERE t.workstation = :workstation"),
    @NamedQuery(name = "TblWMResumenUso.findByDescripcion", query = "SELECT t FROM TblWMResumenUso t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TblWMResumenUso.findByProduce", query = "SELECT t FROM TblWMResumenUso t WHERE t.produce = :produce"),
    @NamedQuery(name = "TblWMResumenUso.findByHora", query = "SELECT t FROM TblWMResumenUso t WHERE t.hora = :hora")})
public class TblWMResumenUso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idReporte")
    private Integer idReporte;
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
    @Size(max = 150)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Produce")
    private Boolean produce;
    @Column(name = "hora")
    private Short hora;

    public TblWMResumenUso() {
    }

    public TblWMResumenUso(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getProduce() {
        return produce;
    }

    public void setProduce(Boolean produce) {
        this.produce = produce;
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
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWMResumenUso)) {
            return false;
        }
        TblWMResumenUso other = (TblWMResumenUso) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TblWMResumenUso[ idReporte=" + idReporte + " ]";
    }
    
}
