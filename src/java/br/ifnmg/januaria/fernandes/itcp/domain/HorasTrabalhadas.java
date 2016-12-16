/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.domain;

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
import javax.validation.constraints.NotNull;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "HorasTrabalhadas")
@NamedQueries({
    @NamedQuery(name = "HorasTrabalhadas.findAll", query = "SELECT h FROM HorasTrabalhadas h")})
public class HorasTrabalhadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataTrabalho")
    @Temporal(TemporalType.DATE)
    private Date dataTrabalho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horasInicio")
    @Temporal(TemporalType.TIME)
    private Date horasInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horasFim")
    @Temporal(TemporalType.TIME)
    private Date horasFim;
    @JoinColumn(name = "idUsuarioFk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idAtividadeFk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AtividadePlanejada atividadePlanejada;

    public HorasTrabalhadas() {
    }

    public HorasTrabalhadas(Integer id) {
        this.id = id;
    }

    public HorasTrabalhadas(Integer id, Date dataTrabalho, Date horasInicio, Date horasFim) {
        this.id = id;
        this.dataTrabalho = dataTrabalho;
        this.horasInicio = horasInicio;
        this.horasFim = horasFim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataTrabalho() {
        return dataTrabalho;
    }

    public void setDataTrabalho(Date dataTrabalho) {
        this.dataTrabalho = dataTrabalho;
    }

    public Date getHorasInicio() {
        return horasInicio;
    }

    public void setHorasInicio(Date horasInicio) {
        this.horasInicio = horasInicio;
    }

    public Date getHorasFim() {
        return horasFim;
    }

    public void setHorasFim(Date horasFim) {
        this.horasFim = horasFim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AtividadePlanejada getAtividadePlanejada() {
        return atividadePlanejada;
    }

    public void setAtividadePlanejada(AtividadePlanejada atividadePlanejada) {
        this.atividadePlanejada = atividadePlanejada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorasTrabalhadas)) {
            return false;
        }
        HorasTrabalhadas other = (HorasTrabalhadas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas[ id=" + id + " ]";
    }
    
}
