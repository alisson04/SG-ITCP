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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alisson
 */
@Embeddable
public class EmpreendimentoIndicadorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idEmpreendimentoFk")
    private int idEmpreendimentoFk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idIndicador")
    private int idIndicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataNota")
    @Temporal(TemporalType.DATE)
    private Date dataNota;

    public EmpreendimentoIndicadorPK() {
    }

    public EmpreendimentoIndicadorPK(int idEmpreendimentoFk, int idIndicador, Date dataNota) {
        this.idEmpreendimentoFk = idEmpreendimentoFk;
        this.idIndicador = idIndicador;
        this.dataNota = dataNota;
    }

    public int getIdEmpreendimentoFk() {
        return idEmpreendimentoFk;
    }

    public void setIdEmpreendimentoFk(int idEmpreendimentoFk) {
        this.idEmpreendimentoFk = idEmpreendimentoFk;
    }

    public int getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(int idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Date getDataNota() {
        return dataNota;
    }

    public void setDataNota(Date dataNota) {
        this.dataNota = dataNota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpreendimentoFk;
        hash += (int) idIndicador;
        hash += (dataNota != null ? dataNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpreendimentoIndicadorPK)) {
            return false;
        }
        EmpreendimentoIndicadorPK other = (EmpreendimentoIndicadorPK) object;
        if (this.idEmpreendimentoFk != other.idEmpreendimentoFk) {
            return false;
        }
        if (this.idIndicador != other.idIndicador) {
            return false;
        }
        if ((this.dataNota == null && other.dataNota != null) || (this.dataNota != null && !this.dataNota.equals(other.dataNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK[ idEmpreendimentoFk=" + idEmpreendimentoFk + ", idIndicador=" + idIndicador + ", dataNota=" + dataNota + " ]";
    }
    
}
