package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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
    @Column(name = "idIndicadorFk")
    private int idIndicadorFk;

    public EmpreendimentoIndicadorPK() {
    }

    public EmpreendimentoIndicadorPK(int idEmpreendimentoFk, int idIndicadorFk) {
        this.idEmpreendimentoFk = idEmpreendimentoFk;
        this.idIndicadorFk = idIndicadorFk;
    }

    public int getIdEmpreendimentoFk() {
        return idEmpreendimentoFk;
    }

    public void setIdEmpreendimentoFk(int idEmpreendimentoFk) {
        this.idEmpreendimentoFk = idEmpreendimentoFk;
    }

    public int getIdIndicadorFk() {
        return idIndicadorFk;
    }

    public void setIdIndicadorFk(int idIndicadorFk) {
        this.idIndicadorFk = idIndicadorFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpreendimentoFk;
        hash += (int) idIndicadorFk;
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
        if (this.idIndicadorFk != other.idIndicadorFk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK[ idEmpreendimentoFk=" + idEmpreendimentoFk + ", idIndicadorFk=" + idIndicadorFk + " ]";
    }
    
}
