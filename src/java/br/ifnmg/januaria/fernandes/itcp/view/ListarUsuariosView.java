/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.view;

/**
 *
 * @author alisson
 */
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
 
@ManagedBean(name="ListarUsuariosView")
@ViewScoped
public class ListarUsuariosView implements Serializable {
    private Usuario usuarioSelecionado;
    private boolean btnVisualizarUsr;
    private boolean btnEdicoesUsr;

    public ListarUsuariosView() {
        btnEdicoesUsr = true;
    }
    
    public void onRowSelect(SelectEvent event) {
        btnEdicoesUsr = false;
        FacesMessage msg = new FacesMessage("Usuário " + usuarioSelecionado.getNomeUsuario() + " selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public boolean isBtnEdicoesUsr() {
        return btnEdicoesUsr;
    }

    public void setBtnEdicoesUsr(boolean btnEdicoesUsr) {
        this.btnEdicoesUsr = btnEdicoesUsr;
    }
}
