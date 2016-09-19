package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.LoginBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author alisson
 */
@ViewScoped
@Named("LoginView")
public class LoginView extends MensagensGenericas implements Serializable {

    private Usuario usuarioLogado;
    private Usuario obj;
    private LoginBean bean;
    private UsuarioBean usrBean;
    private boolean existeUserBd;
    private boolean existeUserLogado;
    
    public LoginView() {
        bean = new LoginBean();
        obj = new Usuario();
        usuarioLogado = new Usuario();

        //Verifica se existe user no banco de dados
        usrBean = new UsuarioBean();
        existeUserBd = true;
        if (usrBean.contarLinhasBean() == 0) {
            System.out.println("Não há usuários cadastrados");
            existeUserBd = false;
        }
    }

    public void salvarCoordenador() {
        try {
            obj.setStatusSistema("Ativo");//SETA O STATUS
            obj.setCargo("Coordenador");//SETA O CARGO
            obj.setSenha(gerarSenhaAleatoria());//GERA A SENHA ALEATORIA e SETA
            String senha = obj.getSenha();//Salva a senha na variavel
            obj.setSenha(DigestUtils.md5Hex(obj.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA
            usrBean.salvarBean(obj);//Salva o usuário
            usrBean.enviarEmail(obj.getEmail(), "Sistema Sigitec", "Sua senha é: " + senha);//Manda o emaill
            senha = ""; //Limpa a senha
            obj = new Usuario();//Limpa o usuário salvo
            existeUserBd = true;//Atualiza variável para dizer q já existe um user no BD
            msgGrowSaveGeneric();//Mensagem de salvar
            msgGrowlInfoCustomizavel("Senha enviada", "A senha foi enviada ao seu e-mail!");
        } catch (EmailException ex) {
            System.out.println("ERRO no Endereço de e-mail");
            msgPanelErroCustomizavel("Impossível salvar", "Verifique o e-mail e a conexão com a internet ");
        }
    }
    public void reenviarSenha(){
        usrBean.buscarPorEmailBean(obj);
        
        String senha = obj.getSenha();//Salva a senha na variavel
    }

    public String logar() {
        try {
            usuarioLogado = bean.logar(usuarioLogado);
            if (usuarioLogado != null) {
                SessionUtil.setParam("USUARIOLogado", usuarioLogado);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/sigitec/inicio.xhtml");
                return "/sigitec/inicio.xhtml";
            } else {
                usuarioLogado = new Usuario();
                msgGrowlErroCustomizavel("Erro", "E-mail ou senha incorretos!");
                return "";
            }
        } catch (RuntimeException | IOException ex) {
            msgPanelErroInesperadoGeneric();
            System.out.println("RuntimeException: " + ex);
            return null;
        }
    }
    
    

    public String sair() {
        return bean.sair(usuarioLogado);
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

    //SETS E GETS
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isExisteUserBd() {
        return existeUserBd;
    }

    public Usuario getObj() {
        return obj;
    }

    public void setObj(Usuario obj) {
        this.obj = obj;
    }

    public boolean isExisteUserLogado() {
        return existeUserLogado;
    }

    public void setExisteUserLogado(boolean existeUserLogado) {
        this.existeUserLogado = existeUserLogado;
    }
}
