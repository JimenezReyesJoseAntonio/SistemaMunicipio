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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomina.findAll", query = "SELECT n FROM Nomina n"),
    @NamedQuery(name = "Nomina.findByIdnomina", query = "SELECT n FROM Nomina n WHERE n.idnomina = :idnomina"),
    @NamedQuery(name = "Nomina.findByFechainicio", query = "SELECT n FROM Nomina n WHERE n.fechainicio = :fechainicio"),
    @NamedQuery(name = "Nomina.findByFechafin", query = "SELECT n FROM Nomina n WHERE n.fechafin = :fechafin"),
    @NamedQuery(name = "Nomina.findByMontopago", query = "SELECT n FROM Nomina n WHERE n.montopago = :montopago"),
    @NamedQuery(name = "Nomina.findByDeducciones", query = "SELECT n FROM Nomina n WHERE n.deducciones = :deducciones"),
    @NamedQuery(name = "Nomina.findByOtrospagos", query = "SELECT n FROM Nomina n WHERE n.otrospagos = :otrospagos")})
public class Nomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnomina")
    private Integer idnomina;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montopago")
    private Double montopago;
    @Column(name = "deducciones")
    private Double deducciones;
    @Column(name = "otrospagos")
    private Double otrospagos;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado idempleado;

    public Nomina() {
    }

    public Nomina(Integer idnomina) {
        this.idnomina = idnomina;
    }

    public Integer getIdnomina() {
        return idnomina;
    }

    public void setIdnomina(Integer idnomina) {
        this.idnomina = idnomina;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Double getMontopago() {
        return montopago;
    }

    public void setMontopago(Double montopago) {
        this.montopago = montopago;
    }

    public Double getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(Double deducciones) {
        this.deducciones = deducciones;
    }

    public Double getOtrospagos() {
        return otrospagos;
    }

    public void setOtrospagos(Double otrospagos) {
        this.otrospagos = otrospagos;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnomina != null ? idnomina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomina)) {
            return false;
        }
        Nomina other = (Nomina) object;
        if ((this.idnomina == null && other.idnomina != null) || (this.idnomina != null && !this.idnomina.equals(other.idnomina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Nomina[ idnomina=" + idnomina + " ]";
    }
    
}
