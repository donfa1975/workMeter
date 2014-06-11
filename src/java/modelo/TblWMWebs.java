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
@Table(name = "tblWMWebs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWMWebs.findAll", query = "SELECT t FROM TblWMWebs t"),
    @NamedQuery(name = "TblWMWebs.findByIdWebs", query = "SELECT t FROM TblWMWebs t WHERE t.idWebs = :idWebs"),
    @NamedQuery(name = "TblWMWebs.findByKeywords", query = "SELECT t FROM TblWMWebs t WHERE t.keywords = :keywords"),
    @NamedQuery(name = "TblWMWebs.findByWeb", query = "SELECT t FROM TblWMWebs t WHERE t.web = :web")})
public class TblWMWebs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idWebs")
    private Integer idWebs;
    @Size(max = 450)
    @Column(name = "keywords")
    private String keywords;
    @Size(max = 150)
    @Column(name = "web")
    private String web;

    public TblWMWebs() {
    }

    public TblWMWebs(Integer idWebs) {
        this.idWebs = idWebs;
    }

    public Integer getIdWebs() {
        return idWebs;
    }

    public void setIdWebs(Integer idWebs) {
        this.idWebs = idWebs;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWebs != null ? idWebs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWMWebs)) {
            return false;
        }
        TblWMWebs other = (TblWMWebs) object;
        if ((this.idWebs == null && other.idWebs != null) || (this.idWebs != null && !this.idWebs.equals(other.idWebs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TblWMWebs[ idWebs=" + idWebs + " ]";
    }
    
}
