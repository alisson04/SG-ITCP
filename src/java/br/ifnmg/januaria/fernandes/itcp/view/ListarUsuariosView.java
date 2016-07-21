package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "ListarUsuariosView")
@ViewScoped
public class ListarUsuariosView implements Serializable {

    private Usuario objSelecionado;
    private Usuario objSalvar = new Usuario();
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
        
        listaUsuarios = bean.listarBean();
    }

    public void transfereObj() {//Para botão de editar
        objSalvar = objSelecionado;
    }

    public void reiniciaObj() {//Para botão de cadastrar
        objSalvar = new Usuario();
    }

    public void salvarView() {
        try {
            if ((bean.buscarPorEmailBean(objSalvar.getEmailUsuario()) == null)) {
                // SETA A DATA DE SAIDA
                objSalvar.setDataSaidaUsuario(null);
                //SETA O STATUS
                objSalvar.setStatusSistemaUsuario("Ativo");
                //GERA A SENHA ALEATORIA
                objSalvar.setSenhaUsuario(gerarSenhaAleatoria());
                bean.enviarEmail(objSalvar.getEmailUsuario(), "Sistema Sigitec", "Sua senha é: " + objSalvar.getSenhaUsuario());
                //CRIPTOGRAFA A SENHA ALEATORIA
                objSalvar.setSenhaUsuario(DigestUtils.md5Hex(objSalvar.getSenhaUsuario()));
                bean.salvarBean(objSalvar);
                objSalvar = new Usuario();
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarDialog').hide()");
                context.execute("PF('dlgEdicaoPronta').show()");
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro no e-mail", "Este e-mail já esta cadastrado no sistema!");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao salvar, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public String gerarSenhaAleatoria() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        String senhaAleatoria = "";
        int index = -1;
        for (int i = 0; i < 10; i++) {
            index = random.nextInt(letras.length());
            senhaAleatoria += letras.substring(index, index + 1);
        }
        System.out.println("SENHA: " + senhaAleatoria);
        return senhaAleatoria;
    }

    public void ListarView() {
        try {
            listaUsuarios = bean.listarBean();
        } catch (RuntimeException ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar listar, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void excluirView() {
        try {
            bean.excluirBean(objSelecionado);
            objSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            FacesMessage msg = new FacesMessage("Exclusão realizada com sucesso sistema");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (RuntimeException ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar excluir, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
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
        if (objSelecionado.getStatusSistemaUsuario().equals("Ativo")) {
            objSelecionado.setStatusSistemaUsuario("Desativado");
            bean.salvarBean(objSelecionado);
            FacesMessage msg = new FacesMessage("O usuário foi desativado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            objSelecionado.setStatusSistemaUsuario("Ativo");
            bean.salvarBean(objSelecionado);
            FacesMessage msg = new FacesMessage("O usuário foi ativado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Usuario getObjSelecionado() {
        return objSelecionado;
    }

    public void setObjSelecionado(Usuario objSelecionado) {
        this.objSelecionado = objSelecionado;
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

    public Usuario getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(Usuario objSalvar) {
        this.objSalvar = objSalvar;
    }
}
