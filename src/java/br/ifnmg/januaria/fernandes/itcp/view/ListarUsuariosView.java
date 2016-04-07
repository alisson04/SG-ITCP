package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
 
@ManagedBean(name="ListarUsuariosView")
@ViewScoped
public class ListarUsuariosView implements Serializable {
    private Usuario usuarioSelecionado;
    private boolean btnEdicoesUsr;
    private String[] cargos;//para a tela de listar usuarios

    public ListarUsuariosView() {
        btnEdicoesUsr = true;
        cargos = new String[8];
        cargos[0] = "Coordenador";
        cargos[1] = "Professor";
        cargos[2] = "Técnico Administrativo";
        cargos[3] = "Estagiário Remunerado";
        cargos[4] = "Estagiário Voluntário";
        cargos[5] = "Bolsista - PIBED";
        cargos[6] = "Bolsista - PIBIC";
        cargos[7] = "Bolsista - PROEXT";
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
    
    public String[] getCargos() {
        return cargos;
    }

    public void setCargos(String[] cargos) {
        this.cargos = cargos;
    }
}
