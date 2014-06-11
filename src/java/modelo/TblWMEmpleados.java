/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

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
 * @author fabricio.diaz
 */
@Entity
@Table(name = "tblWMEmpleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWMEmpleados.findAll", query = "SELECT t FROM TblWMEmpleados t"),
    @NamedQuery(name = "TblWMEmpleados.findByIdEmpleado", query = "SELECT t FROM TblWMEmpleados t WHERE t.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "TblWMEmpleados.findByEmpleado", query = "SELECT t FROM TblWMEmpleados t WHERE t.empleado = :empleado"),
    @NamedQuery(name = "TblWMEmpleados.findByUsuario", query = "SELECT t FROM TblWMEmpleados t WHERE t.usuario = :usuario"),
    @NamedQuery(name = "TblWMEmpleados.findByPerfil", query = "SELECT t FROM TblWMEmpleados t WHERE t.perfil = :perfil")})
public class TblWMEmpleados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEmpleado")
    private Integer idEmpleado;
    @Size(max = 250)
    @Column(name = "empleado")
    private String empleado;
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "perfil")
    private Short perfil;
    @JoinColumn(name = "idproceso", referencedColumnName = "idProceso")
    @ManyToOne
    private TblWMProcesos idproceso;

    public TblWMEmpleados() {
    }

    public TblWMEmpleados(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Short getPerfil() {
        return perfil;
    }

    public void setPerfil(Short perfil) {
        this.perfil = perfil;
    }

    public TblWMProcesos getIdproceso() {
        return idproceso;
    }

    public void setIdproceso(TblWMProcesos idproceso) {
        this.idproceso = idproceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWMEmpleados)) {
            return false;
        }
        TblWMEmpleados other = (TblWMEmpleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TblWMEmpleados[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
