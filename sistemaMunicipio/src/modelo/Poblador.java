/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josej
 */
@Entity
@Table(name = "poblador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poblador.findAll", query = "SELECT p FROM Poblador p"),
    @NamedQuery(name = "Poblador.findByIdpoblador", query = "SELECT p FROM Poblador p WHERE p.idpoblador = :idpoblador"),
    @NamedQuery(name = "Poblador.findByNombre", query = "SELECT p FROM Poblador p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Poblador.findByApellidop", query = "SELECT p FROM Poblador p WHERE p.apellidop = :apellidop"),
    @NamedQuery(name = "Poblador.findByApellidom", query = "SELECT p FROM Poblador p WHERE p.apellidom = :apellidom"),
    @NamedQuery(name = "Poblador.findByEdad", query = "SELECT p FROM Poblador p WHERE p.edad = :edad"),
    @NamedQuery(name = "Poblador.findByFechanacimiento", query = "SELECT p FROM Poblador p WHERE p.fechanacimiento = :fechanacimiento")})
public class Poblador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpoblador")
    private Integer idpoblador;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidop")
    private String apellidop;
    @Column(name = "apellidom")
    private String apellidom;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;

    public Poblador() {
    }

    public Poblador(Integer idpoblador) {
        this.idpoblador = idpoblador;
    }

    public Integer getIdpoblador() {
        return idpoblador;
    }

    public void setIdpoblador(Integer idpoblador) {
        this.idpoblador = idpoblador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpoblador != null ? idpoblador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poblador)) {
            return false;
        }
        Poblador other = (Poblador) object;
        if ((this.idpoblador == null && other.idpoblador != null) || (this.idpoblador != null && !this.idpoblador.equals(other.idpoblador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Poblador[ idpoblador=" + idpoblador + " ]";
    }
    
}
