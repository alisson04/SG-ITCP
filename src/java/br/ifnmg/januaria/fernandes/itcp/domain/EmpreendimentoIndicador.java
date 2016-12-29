package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "EmpreendimentoIndicador")
@NamedQueries({
    @NamedQuery(name = "EmpreendimentoIndicador.findAll", query = "SELECT e FROM EmpreendimentoIndicador e")})
public class EmpreendimentoIndicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpreendimentoIndicadorPK empreendimentoIndicadorPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota")
    private Integer nota;
    
    @JoinColumn(name = "idEmpreendimentoFk", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empreendimento empreendimento;

    public EmpreendimentoIndicador() {
    }

    public EmpreendimentoIndicador(EmpreendimentoIndicadorPK empreendimentoIndicadorPK) {
        this.empreendimentoIndicadorPK = empreendimentoIndicadorPK;
    }

    public EmpreendimentoIndicador(EmpreendimentoIndicadorPK empreendimentoIndicadorPK, Integer nota) {
        this.empreendimentoIndicadorPK = empreendimentoIndicadorPK;
        this.nota = nota;
    }

    public EmpreendimentoIndicador(int idEmpreendimentoFk, int idIndicador, Date dataNota) {
        this.empreendimentoIndicadorPK = new EmpreendimentoIndicadorPK(idEmpreendimentoFk, idIndicador, dataNota);
    }

    public EmpreendimentoIndicadorPK getEmpreendimentoIndicadorPK() {
        return empreendimentoIndicadorPK;
    }

    public void setEmpreendimentoIndicadorPK(EmpreendimentoIndicadorPK empreendimentoIndicadorPK) {
        this.empreendimentoIndicadorPK = empreendimentoIndicadorPK;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
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
        hash += (empreendimentoIndicadorPK != null ? empreendimentoIndicadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpreendimentoIndicador)) {
            return false;
        }
        EmpreendimentoIndicador other = (EmpreendimentoIndicador) object;
        if ((this.empreendimentoIndicadorPK == null && other.empreendimentoIndicadorPK != null) || (this.empreendimentoIndicadorPK != null && !this.empreendimentoIndicadorPK.equals(other.empreendimentoIndicadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador[ empreendimentoIndicadorPK=" + empreendimentoIndicadorPK + " ]";
    }
    
}
