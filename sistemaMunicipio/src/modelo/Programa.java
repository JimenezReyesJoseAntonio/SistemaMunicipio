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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josej
 */
@Entity
@Table(name = "programa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p"),
    @NamedQuery(name = "Programa.findByIdprograma", query = "SELECT p FROM Programa p WHERE p.idprograma = :idprograma"),
    @NamedQuery(name = "Programa.findByNombreprograma", query = "SELECT p FROM Programa p WHERE p.nombreprograma = :nombreprograma"),
    @NamedQuery(name = "Programa.findByPresupuesto", query = "SELECT p FROM Programa p WHERE p.presupuesto = :presupuesto"),
    @NamedQuery(name = "Programa.findByNumerobeneficiarios", query = "SELECT p FROM Programa p WHERE p.numerobeneficiarios = :numerobeneficiarios")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprograma")
    private Integer idprograma;
    @Column(name = "nombreprograma")
    private String nombreprograma;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "presupuesto")
    private Double presupuesto;
    @Column(name = "numerobeneficiarios")
    private Integer numerobeneficiarios;

    public Programa() {
    }

    public Programa(Integer idprograma) {
        this.idprograma = idprograma;
    }

    public Integer getIdprograma() {
        return idprograma;
    }

    public void setIdprograma(Integer idprograma) {
        this.idprograma = idprograma;
    }

    public String getNombreprograma() {
        return nombreprograma;
    }

    public void setNombreprograma(String nombreprograma) {
        this.nombreprograma = nombreprograma;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Integer getNumerobeneficiarios() {
        return numerobeneficiarios;
    }

    public void setNumerobeneficiarios(Integer numerobeneficiarios) {
        this.numerobeneficiarios = numerobeneficiarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprograma != null ? idprograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.idprograma == null && other.idprograma != null) || (this.idprograma != null && !this.idprograma.equals(other.idprograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Programa[ idprograma=" + idprograma + " ]";
    }
    
}
