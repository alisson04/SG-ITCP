package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "Atividade")
@NamedQueries({
    @NamedQuery(name = "Atividade.findAll", query = "SELECT a FROM Atividade a")})
public class Atividade implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividade")
    private List<AtividadeRelacionamento> atividadeRelacionamentoList;

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtividade")
    private Integer idAtividade;
    @Size(max = 45)
    @Column(name = "nomeAtividade")
    private String nomeAtividade;
    @Size(max = 45)
    @Column(name = "descricaoAtividade")
    private String descricaoAtividade;
    @Size(max = 45)
    @Column(name = "statusAtividade")
    private String statusAtividade;
    @Size(max = 45)
    @Column(name = "dataInicioAtividade")
    private String dataInicioAtividade;
    @Size(max = 45)
    @Column(name = "dataFimAtividade")
    private String dataFimAtividade;

    public Atividade() {
    }

    public Atividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public String getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(String statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public String getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public void setDataInicioAtividade(String dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public String getDataFimAtividade() {
        return dataFimAtividade;
    }

    public void setDataFimAtividade(String dataFimAtividade) {
        this.dataFimAtividade = dataFimAtividade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtividade != null ? idAtividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividade)) {
            return false;
        }
        Atividade other = (Atividade) object;
        if ((this.idAtividade == null && other.idAtividade != null) || (this.idAtividade != null && !this.idAtividade.equals(other.idAtividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Atividade[ idAtividade=" + idAtividade + " ]";
    }

    public List<AtividadeRelacionamento> getAtividadeRelacionamentoList() {
        return atividadeRelacionamentoList;
    }

    public void setAtividadeRelacionamentoList(List<AtividadeRelacionamento> atividadeRelacionamentoList) {
        this.atividadeRelacionamentoList = atividadeRelacionamentoList;
    }

}
