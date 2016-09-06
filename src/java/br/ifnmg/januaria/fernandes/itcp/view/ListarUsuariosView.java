package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

@ViewScoped
@Named("ListarUsuariosView")
public class ListarUsuariosView extends MensagensGenericas implements Serializable {

    private Usuario objSelecionado;
    private Usuario objSalvar;
    private String[] cargos;//para a tela de listar usuarios
    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosFiltrados;
    private UsuarioBean bean;

    public ListarUsuariosView() {
        objSalvar = new Usuario();
        bean = new UsuarioBean();

        cargos = new String[8];
        cargos[0] = "Coordenador";
        cargos[1] = "Professor";
        cargos[2] = "Técnico Administrativo";
        cargos[3] = "Estagiário Remunerado";
        cargos[4] = "Estagiário Voluntário";
        cargos[5] = "Bolsista - PIBED";
        cargos[6] = "Bolsista - PIBIC";
        cargos[7] = "Bolsista - PROEXT";

        listaUsuarios = bean.listarBean();//Atualiza a lista de usuários
    }

    public void transfereObj() {//Para botão de editar
        objSalvar = objSelecionado;
    }

    public void reiniciaObj() {//Para botão de cadastrar
        objSalvar = new Usuario();
    }

    public void salvarView() {
        try {
            if ((bean.buscarPorEmailBean(objSalvar.getEmail()) == null)) {
                objSalvar.setStatusSistema("Ativo");//SETA O STATUS
                objSalvar.setSenha(gerarSenhaAleatoria());//GERA A SENHA ALEATORIA
                bean.enviarEmail(objSalvar.getEmail(), "Sistema Sigitec", "Sua senha é: " + objSalvar.getSenha());//Manda o emaill
                objSalvar.setSenha(DigestUtils.md5Hex(objSalvar.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA
                bean.salvarBean(objSalvar);//Salva o usuário
                objSalvar = new Usuario();//Limpa o usuário salvo
                listaUsuarios = bean.listarBean();//Atualiza a lista de usuários
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarDialog').hide()");
                msgGrowSaveGeneric();
            } else {
                msgPanelErroCustomizavel("Erro no e-mail", "Este e-mail já esta cadastrado no sistema!");
            }
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
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

    public void excluirView() {
        try {
            bean.excluirBean(objSelecionado);//Exclui o usuário
            objSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            listaUsuarios = bean.listarBean();//Atualiza a lista de usuários
            msgGrowDeleteGeneric();
        } catch (RuntimeException ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
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

    public String btAtivarDesativarUser(Usuario userAtivarDesativar) {//Determina o nome do botão 
        if (userAtivarDesativar == null) {
            return "Ativar ou desativar";
        } else if (userAtivarDesativar.getStatusSistema().equals("Ativo")) {
            return "Desativar";
        } else {
            return "Ativar";
        }
    }

    public void ativarDesativarUsrView() {
        if (objSelecionado.getStatusSistema().equals("Ativo")) {
            objSelecionado.setStatusSistema("Desativado");
            bean.salvarBean(objSelecionado);
        } else {
            objSelecionado.setStatusSistema("Ativo");
            bean.salvarBean(objSelecionado);
        }
        msgGrowUpdateGeneric();
        listaUsuarios = bean.listarBean();//Atualiza a lista de usuários
    }

    //SETS E GETS
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
