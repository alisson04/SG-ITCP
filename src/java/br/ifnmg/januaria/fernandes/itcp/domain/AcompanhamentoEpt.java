package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "AcompanhamentoEpt")
@NamedQueries({
    @NamedQuery(name = "AcompanhamentoEpt.findAll", query = "SELECT a FROM AcompanhamentoEpt a")})
public class AcompanhamentoEpt implements Serializable, EntityConverter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "objetivo")
    private String objetivo;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataAcompanhamento")
    @Temporal(TemporalType.DATE)
    private Date dataAcompanhamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaFim")
    @Temporal(TemporalType.TIME)
    private Date horaFim;
    
    @JoinTable(name = "AcompanhamentoEptParceiro", joinColumns = {
        @JoinColumn(name = "idAcompanhamentoFk", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idParceiroFk", referencedColumnName = "id")})
    @ManyToMany
    private List<Parceiro> parceiroList;
    
    @JoinTable(name = "AcompanhamentoEptUsuario", joinColumns = {
        @JoinColumn(name = "idAcompanhamentoFk", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuarioFk", referencedColumnName = "id")})
    @ManyToMany
    private List<Usuario> usuarioList;
    
    @JoinColumn(name = "idEmpreendimentoFk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empreendimento empreendimento;

    public AcompanhamentoEpt() {
    }
    
    @Override
    public Integer getIdConverter(){
        return id;
    }

    public AcompanhamentoEpt(Integer id) {
        this.id = id;
    }

    public AcompanhamentoEpt(Integer id, String objetivo, Date dataAcompanhamento, Date horaInicio, Date horaFim) {
        this.id = id;
        this.objetivo = objetivo;
        this.dataAcompanhamento = dataAcompanhamento;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAcompanhamento() {
        return dataAcompanhamento;
    }

    public void setDataAcompanhamento(Date dataAcompanhamento) {
        this.dataAcompanhamento = dataAcompanhamento;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
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
        if (!(object instanceof AcompanhamentoEpt)) {
            return false;
        }
        AcompanhamentoEpt other = (AcompanhamentoEpt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt[ id=" + id + " ]";
    }
    
}
