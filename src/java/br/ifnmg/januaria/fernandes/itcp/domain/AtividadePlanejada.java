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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "AtividadePlanejada")
@NamedQueries({
    @NamedQuery(name = "AtividadePlanejada.findAll", query = "SELECT a FROM AtividadePlanejada a")})
public class AtividadePlanejada implements Serializable, EntityConverter {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividadePlanejada")
    private List<HorasTrabalhadas> horasTrabalhadasList;

    @JoinTable(name = "AtividadeUsuario", joinColumns = {
        @JoinColumn(name = "idAtividadeFk", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuarioFk", referencedColumnName = "id")})
    @ManyToMany
    private List<Usuario> usuarioList;
    
    @JoinTable(name = "AtividadeParceiro", joinColumns = {
        @JoinColumn(name = "idAtividadeFk", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idParceiroFk", referencedColumnName = "id")})
    @ManyToMany
    private List<Parceiro> parceiroList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividadePlanejada")
    private List<AtividadeUsuario> atividadeUsuarioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividadePlanejada")
    private List<AtividadeParceiro> atividadeParceiroList;

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "metafk", referencedColumnName = "idMeta")
    @ManyToOne
    private Meta meta;

    public AtividadePlanejada() {
    }

    public AtividadePlanejada(Integer id) {
        this.id = id;
    }

    public AtividadePlanejada(Integer id, String nome, Date dataInicio, Date dataFim) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof AtividadePlanejada)) {
            return false;
        }
        AtividadePlanejada other = (AtividadePlanejada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada[ id=" + id + " ]";
    }

    public List<AtividadeParceiro> getAtividadeParceiroList() {
        return atividadeParceiroList;
    }

    public void setAtividadeParceiroList(List<AtividadeParceiro> atividadeParceiroList) {
        this.atividadeParceiroList = atividadeParceiroList;
    }

    public List<AtividadeUsuario> getAtividadeUsuarioList() {
        return atividadeUsuarioList;
    }

    public void setAtividadeUsuarioList(List<AtividadeUsuario> atividadeUsuarioList) {
        this.atividadeUsuarioList = atividadeUsuarioList;
    }

    public List<Parceiro> getParceiroList() {
        return parceiroList;
    }

    public void setParceiroList(List<Parceiro> parceiroList) {
        this.parceiroList = parceiroList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<HorasTrabalhadas> getHorasTrabalhadasList() {
        return horasTrabalhadasList;
    }

    public void setHorasTrabalhadasList(List<HorasTrabalhadas> horasTrabalhadasList) {
        this.horasTrabalhadasList = horasTrabalhadasList;
    }
}
