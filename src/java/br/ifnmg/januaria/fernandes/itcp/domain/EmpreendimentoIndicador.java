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
@Table(name = "EmpreendimentoIndicador")
@NamedQueries({
    @NamedQuery(name = "EmpreendimentoIndicador.findAll", query = "SELECT e FROM EmpreendimentoIndicador e")})
public class EmpreendimentoIndicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idIndicador")
    private int idIndicador;
    @Column(name = "nota")
    private Integer nota;
    @Column(name = "dataNota")
    @Temporal(TemporalType.DATE)
    private Date dataNota;
    @JoinColumn(name = "idEmpreendimentoFk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empreendimento empreendimento;

    public EmpreendimentoIndicador() {
    }

    public EmpreendimentoIndicador(Integer id) {
        this.id = id;
    }

    public EmpreendimentoIndicador(Integer id, int idIndicador, int nota, Date dataNota) {
        this.id = id;
        this.idIndicador = idIndicador;
        this.nota = nota;
        this.dataNota = dataNota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(int idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
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
        if (!(object instanceof EmpreendimentoIndicador)) {
            return false;
        }
        EmpreendimentoIndicador other = (EmpreendimentoIndicador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador[ id=" + id + " ]";
    }
    
}
