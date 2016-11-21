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
@Table(name = "notaMaturidade")
@NamedQueries({
    @NamedQuery(name = "NotaMaturidade.findAll", query = "SELECT n FROM NotaMaturidade n")})
public class NotaMaturidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota")
    private int nota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataNota")
    @Temporal(TemporalType.DATE)
    private Date dataNota;
    @JoinColumn(name = "idEmpreendimento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empreendimento empreendimento;

    public NotaMaturidade() {
    }

    public NotaMaturidade(Integer id) {
        this.id = id;
    }

    public NotaMaturidade(Integer id, int nota, Date dataNota) {
        this.id = id;
        this.nota = nota;
        this.dataNota = dataNota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getDataNota() {
        return dataNota;
    }

    public void setDataNota(Date dataNota) {
        this.dataNota = dataNota;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
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
        if (!(object instanceof NotaMaturidade)) {
            return false;
        }
        NotaMaturidade other = (NotaMaturidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade[ id=" + id + " ]";
    }
    
}
