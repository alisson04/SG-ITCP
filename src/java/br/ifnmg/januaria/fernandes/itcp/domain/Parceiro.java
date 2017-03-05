package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "Parceiro")
@NamedQueries({
    @NamedQuery(name = "Parceiro.findAll", query = "SELECT p FROM Parceiro p")})
public class Parceiro implements Serializable, EntityConverter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "telefone")
    private String telefone;
    @Size(max = 45)
    @Column(name = "telefoneAlternativo")
    private String telefoneAlternativo;
    @Size(max = 45)
    @Column(name = "endereco")
    private String endereco;
    
    @ManyToMany(mappedBy = "parceiroList")
    private List<AcompanhamentoEpt> acompanhamentoEptList;
    
    @ManyToMany(mappedBy = "parceiroList")
    private List<AtividadePlanejada> atividadePlanejadaList;

    public Parceiro() {
    }
    
    @Override
    public Integer getIdConverter(){
        return id;
    }

    public Parceiro(Integer id) {
        this.id = id;
    }

    public Parceiro(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<AcompanhamentoEpt> getAcompanhamentoEptList() {
        return acompanhamentoEptList;
    }

    public void setAcompanhamentoEptList(List<AcompanhamentoEpt> acompanhamentoEptList) {
        this.acompanhamentoEptList = acompanhamentoEptList;
    }

    public List<AtividadePlanejada> getAtividadePlanejadaList() {
        return atividadePlanejadaList;
    }

    public void setAtividadePlanejadaList(List<AtividadePlanejada> atividadePlanejadaList) {
        this.atividadePlanejadaList = atividadePlanejadaList;
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
        if (!(object instanceof Parceiro)) {
            return false;
        }
        Parceiro other = (Parceiro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Parceiro[ id=" + id + " ]";
    }
    
}
