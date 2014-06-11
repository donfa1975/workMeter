/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
 * @author fabricio.diaz
 */
@Entity
@Table(name = "tblWMProcesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWMProcesos.findAll", query = "SELECT t FROM TblWMProcesos t"),
    @NamedQuery(name = "TblWMProcesos.findByIdProceso", query = "SELECT t FROM TblWMProcesos t WHERE t.idProceso = :idProceso"),
    @NamedQuery(name = "TblWMProcesos.findByProceso", query = "SELECT t FROM TblWMProcesos t WHERE t.proceso = :proceso"),
    @NamedQuery(name = "TblWMProcesos.findBySubGerente", query = "SELECT t FROM TblWMProcesos t WHERE t.subGerente = :subGerente"),
    @NamedQuery(name = "TblWMProcesos.findByJefe", query = "SELECT t FROM TblWMProcesos t WHERE t.jefe = :jefe")})
public class TblWMProcesos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProceso")
    private Integer idProceso;
    @Size(max = 150)
    @Column(name = "Proceso")
    private String proceso;
    @Size(max = 50)
    @Column(name = "SubGerente")
    private String subGerente;
    @Size(max = 50)
    @Column(name = "Jefe")
    private String jefe;
    @OneToMany(mappedBy = "idproceso")
    private Collection<TblWMEmpleados> tblWMEmpleadosCollection;

    public TblWMProcesos() {
    }

    public TblWMProcesos(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getSubGerente() {
        return subGerente;
    }

    public void setSubGerente(String subGerente) {
        this.subGerente = subGerente;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    @XmlTransient
    public Collection<TblWMEmpleados> getTblWMEmpleadosCollection() {
        return tblWMEmpleadosCollection;
    }

    public void setTblWMEmpleadosCollection(Collection<TblWMEmpleados> tblWMEmpleadosCollection) {
        this.tblWMEmpleadosCollection = tblWMEmpleadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWMProcesos)) {
            return false;
        }
        TblWMProcesos other = (TblWMProcesos) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TblWMProcesos[ idProceso=" + idProceso + " ]";
    }
    
}
