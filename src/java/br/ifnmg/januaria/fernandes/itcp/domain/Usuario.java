package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.logar", query = "SELECT u FROM Usuario u WHERE u.emailUsuario = :emailUsuario AND u.senhaUsuario = :senhaUsuario"),
    @NamedQuery(name = "Usuario.buscarPorCodigo", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.buscarPorCargo", query = "SELECT u FROM Usuario u WHERE u.cargoUsuario = :cargoUsuario")})

public class Usuario implements Serializable {

    @OneToMany(mappedBy = "usuario")
    private List<AtividadeRelacionamento> atividadeRelacionamentoList;

       private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "emailUsuario")
    private String emailUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "senhaUsuario")
    private String senhaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefoneUsuario")
    private String telefoneUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefoneAlternativoUsuario")
    private String telefoneAlternativoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataNascimentoUsuario")
    private String dataNascimentoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rgUsuario")
    private String rgUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cpfUsuario")
    private String cpfUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cargoUsuario")
    private String cargoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataEntradaUsuario")
    private String dataEntradaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataSaidaUsuario")
    private String dataSaidaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sexoUsuario")
    private String sexoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "statusSistemaUsuario")
    private String statusSistemaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "enderecoUsuario")
    private String enderecoUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario, String telefoneUsuario, String telefoneAlternativoUsuario, String dataNascimentoUsuario, String rgUsuario, String cpfUsuario, String cargoUsuario, String dataEntradaUsuario, String dataSaidaUsuario, String sexoUsuario, String statusSistemaUsuario, String enderecoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.telefoneAlternativoUsuario = telefoneAlternativoUsuario;
        this.dataNascimentoUsuario = dataNascimentoUsuario;
        this.rgUsuario = rgUsuario;
        this.cpfUsuario = cpfUsuario;
        this.cargoUsuario = cargoUsuario;
        this.dataEntradaUsuario = dataEntradaUsuario;
        this.dataSaidaUsuario = dataSaidaUsuario;
        this.sexoUsuario = sexoUsuario;
        this.statusSistemaUsuario = statusSistemaUsuario;
        this.enderecoUsuario = enderecoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getTelefoneAlternativoUsuario() {
        return telefoneAlternativoUsuario;
    }

    public void setTelefoneAlternativoUsuario(String telefoneAlternativoUsuario) {
        this.telefoneAlternativoUsuario = telefoneAlternativoUsuario;
    }

    public String getDataNascimentoUsuario() {
        return dataNascimentoUsuario;
    }

    public void setDataNascimentoUsuario(String dataNascimentoUsuario) {
        this.dataNascimentoUsuario = dataNascimentoUsuario;
    }

    public String getRgUsuario() {
        return rgUsuario;
    }

    public void setRgUsuario(String rgUsuario) {
        this.rgUsuario = rgUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getCargoUsuario() {
        return cargoUsuario;
    }

    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    public String getDataEntradaUsuario() {
        return dataEntradaUsuario;
    }

    public void setDataEntradaUsuario(String dataEntradaUsuario) {
        this.dataEntradaUsuario = dataEntradaUsuario;
    }

    public String getDataSaidaUsuario() {
        return dataSaidaUsuario;
    }

    public void setDataSaidaUsuario(String dataSaidaUsuario) {
        this.dataSaidaUsuario = dataSaidaUsuario;
    }

    public String getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public String getStatusSistemaUsuario() {
        return statusSistemaUsuario;
    }

    public void setStatusSistemaUsuario(String statusSistemaUsuario) {
        this.statusSistemaUsuario = statusSistemaUsuario;
    }

    public String getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public void setEnderecoUsuario(String enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.Usuario[ idUsuario=" + idUsuario + " ]";
    }

    public List<AtividadeRelacionamento> getAtividadeRelacionamentoList() {
        return atividadeRelacionamentoList;
    }

    public void setAtividadeRelacionamentoList(List<AtividadeRelacionamento> atividadeRelacionamentoList) {
        this.atividadeRelacionamentoList = atividadeRelacionamentoList;
    }

     }
