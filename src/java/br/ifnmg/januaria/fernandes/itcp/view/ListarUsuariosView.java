package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

@ViewScoped
@Named("ListarUsuariosView")
public class ListarUsuariosView extends MensagensGenericas implements Serializable {
    //Variáveis
    private Usuario objSelecionado;
    private Usuario objSalvar;

    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosFiltrados;
    private UsuarioBean bean;
    private UploadArquivo arquivo;

    //Construtor
    public ListarUsuariosView() {
        objSalvar = new Usuario();
        bean = new UsuarioBean();

        listaUsuarios = bean.listarBean();//Atualiza a lista de usuários
        arquivo = new UploadArquivo();
    }
    
    //METODOS
    public String geraMsgGenericaCampoObrigatorioView(){
        return msgGenericaCampoObrigatorio();
    }
    
    public void transfereObj() {//Para botão de editar
        objSalvar = objSelecionado;
    }

    public void reiniciaObj() {//Para botão de cadastrar
        objSalvar = new Usuario();
        objSalvar.setFotoPerfil("profileGeral.png");
    }

    public void salvarView() {
        try {
            if ((bean.buscarPorEmailBean(objSalvar) == null//Verifica se o e-mail já esta cadastrado
                    ||bean.buscarPorEmailBean(objSalvar).getId().equals(objSalvar.getId()))) {
                objSalvar.setStatusSistema("Ativo");//SETA O STATUS
                objSalvar.setSenha(bean.gerarSenhaAleatoria());//GERA A SENHA ALEATORIA
                bean.enviarEmail(objSalvar.getEmail(), "Sistema Sigitec", "Sua senha é: " + objSalvar.getSenha());//Manda o emaill
                objSalvar.setSenha(DigestUtils.md5Hex(objSalvar.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA
                bean.salvarBean(objSalvar);//Salva o usuário
                objSalvar = new Usuario();//Limpa o usuário salvo
                objSalvar.setFotoPerfil("profileGeral.png");
                listaUsuarios = bean.listarBean();//Atualiza a lista de usuários
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarDialog').hide()");
                msgGrowSaveGeneric();
            } else {
                msgPanelErroCustomizavel("Erro no e-mail", "Este e-mail já esta cadastrado no sistema!");
            }
        } catch (EmailException ex) {
            msgPanelErroCustomizavel("Impossível salvar", "Verifique a validade do e-mail e a conexão com a internet ");
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }

    public void uploadAction(FileUploadEvent event) {//Upa a foto e seta no user
        arquivo.fileUpload(event, ".jpg", "/image/");
        System.out.println("Nomed o arquivo: " + arquivo.getNome());
        objSalvar.setFotoPerfil(arquivo.getNome());
        arquivo.gravar();
        arquivo = new UploadArquivo();//Limpa a variável
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

    public String[] geraTiposDeCargosView() {
        return bean.geraTiposDeCargosBean();
    }

    //SETS E GETS
    public Usuario getObjSelecionado() {
        return objSelecionado;
    }

    public void setObjSelecionado(Usuario objSelecionado) {
        this.objSelecionado = objSelecionado;
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
