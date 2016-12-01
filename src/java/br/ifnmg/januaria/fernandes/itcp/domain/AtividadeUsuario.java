package br.ifnmg.januaria.fernandes.itcp.domain;

import java.io.Serializable;
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

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "AtividadeUsuario")
@NamedQueries({
    @NamedQuery(name = "AtividadeUsuario.findAll", query = "SELECT a FROM AtividadeUsuario a")})
public class AtividadeUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "atividadeFk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AtividadePlanejada atividadePlanejada;
    @JoinColumn(name = "usuarioFk", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public AtividadeUsuario() {
    }

    public AtividadeUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AtividadePlanejada getAtividadePlanejada() {
        return atividadePlanejada;
    }

    public void setAtividadePlanejada(AtividadePlanejada atividadePlanejada) {
        this.atividadePlanejada = atividadePlanejada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof AtividadeUsuario)) {
            return false;
        }
        AtividadeUsuario other = (AtividadeUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifnmg.januaria.fernandes.itcp.domain.AtividadeUsuario[ id=" + id + " ]";
    }
    
}
