package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "Empreendimento")
@NamedQueries({
    @NamedQuery(name = "Empreendimento.findAll", query = "SELECT e FROM Empreendimento e")})
public class Empreendimento implements Serializable, EntityConverter {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empreendimento")
    private List<NotaMaturidade> notaMaturidadeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empreendimento")
    private List<EmpreendimentoIndicador> empreendimentoIndicadorList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empreendimento")
    private List<AcompanhamentoEpt> visitaEptList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empreendimento")
    private List<PlanoAcao> planoAcaoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empreendimento")
    private List<MembroEmpreendimento> membroEmpreendimentoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "telefone")
    private String telefone;
    @Size(max = 45)
    @Column(name = "telefoneAlternativo")
    private String telefoneAlternativo;
    @Size(max = 45)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "situacao")
    private String situacao;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @Column(name = "dataIncubacao")
    @Temporal(TemporalType.DATE)
    private Date dataIncubacao;
    @Size(max = 255)
    @Column(name = "razaoSocial")
    private String razaoSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "AtividadeExercida")
    private String atividadeExercida;
    @Size(max = 255)
    @Column(name = "site")
    private String site;

    public Empreendimento() {
    }

    public Empreendimento(Integer idEpt) {
        this.id = idEpt;
    }

    public Empreendimento(Integer idEpt, String nome, String enderecoEpt, String situacaoEpt, String tipoEpt, String atividadeExercidaEpt) {
        this.id = idEpt;
        this.nome = nome;
        this.endereco = enderecoEpt;
        this.situacao = situacaoEpt;
        this.tipo = tipoEpt;
        this.atividadeExercida = atividadeExercidaEpt;
    }
    
    @Override
    public Integer getIdConverter(){
        return id;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataIncubacao() {
        return dataIncubacao;
    }

    public void setDataIncubacao(Date dataIncubacao) {
        this.dataIncubacao = dataIncubacao;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAtividadeExercida() {
        return atividadeExercida;
    }

    public void setAtividadeExercida(String atividadeExercida) {
        this.atividadeExercida = atividadeExercida;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
        if (!(object instanceof Empreendimento)) {
            return false;
        }
        Empreendimento other = (Empreendimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento[ id=" + id + " ]";
    }

    public List<MembroEmpreendimento> getMembroEmpreendimentoList() {
        return membroEmpreendimentoList;
    }

    public void setMembroEmpreendimentoList(List<MembroEmpreendimento> membroEmpreendimentoList) {
        this.membroEmpreendimentoList = membroEmpreendimentoList;
    }

    public List<PlanoAcao> getPlanoAcaoList() {
        return planoAcaoList;
    }

    public void setPlanoAcaoList(List<PlanoAcao> planoAcaoList) {
        this.planoAcaoList = planoAcaoList;
    }

    public List<AcompanhamentoEpt> getVisitaEptList() {
        return visitaEptList;
    }

    public void setVisitaEptList(List<AcompanhamentoEpt> visitaEptList) {
        this.visitaEptList = visitaEptList;
    }

    public List<EmpreendimentoIndicador> getEmpreendimentoIndicadorList() {
        return empreendimentoIndicadorList;
    }

    public void setEmpreendimentoIndicadorList(List<EmpreendimentoIndicador> empreendimentoIndicadorList) {
        this.empreendimentoIndicadorList = empreendimentoIndicadorList;
    }

    public List<NotaMaturidade> getNotaMaturidadeList() {
        return notaMaturidadeList;
    }

    public void setNotaMaturidadeList(List<NotaMaturidade> notaMaturidadeList) {
        this.notaMaturidadeList = notaMaturidadeList;
    }
    
}
