package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "ListarUsuariosView")
@ViewScoped
public class ListarUsuariosView implements Serializable {

    private Usuario usuarioSelecionado;
    private String[] cargos;//para a tela de listar usuarios
    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosFiltrados;
    private UsuarioBean bean = new UsuarioBean();

    public ListarUsuariosView() {
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

    public void ListarTodosUsers() {
        listaUsuarios = bean.listarTodosUsuariosBean();
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Usuário " + usuarioSelecionado.getNomeUsuario() + " selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void excluirUsrView(){
        bean.excluirUsrBean(usuarioSelecionado);
        usuarioSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Usuário excluido do sistema");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }

    public String btAtivarDesativarUser(Usuario userAtivarDesativar) {
        if (userAtivarDesativar == null) {
            return "Ativar ou desativar";
        } else if (userAtivarDesativar.getStatusSistemaUsuario().equals("Ativo")) {
            return "Desativar";
        } else {
            return "Ativar";
        }
    }

    public void ativarDesativarUsrView() {
        if (usuarioSelecionado.getStatusSistemaUsuario().equals("Ativo")) {
            usuarioSelecionado.setStatusSistemaUsuario("Desativado");
            bean.salvarUserBean(usuarioSelecionado);
            FacesMessage msg = new FacesMessage("O usuário foi desativado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            usuarioSelecionado.setStatusSistemaUsuario("Ativo");
            bean.salvarUserBean(usuarioSelecionado);
            FacesMessage msg = new FacesMessage("O usuário foi ativado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public String[] getCargos() {
        return cargos;
    }

    public void setCargos(String[] cargos) {
        this.cargos = cargos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaUsuariosFiltrados() {
        return listaUsuariosFiltrados;
    }

    public void setListaUsuariosFiltrados(List<Usuario> listaUsuariosFiltrados) {
        this.listaUsuariosFiltrados = listaUsuariosFiltrados;
    }

}
