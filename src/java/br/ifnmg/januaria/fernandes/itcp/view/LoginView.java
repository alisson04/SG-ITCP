package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.bean.LoginBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.PropertyNotFoundException;
import javax.faces.FacesException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ViewScoped
@Named("LoginView")
public class LoginView extends MensagensGenericas implements Serializable {

    //Incubadora VARS
    private Incubadora inc;
    private IncubadoraBean incBean;
    private boolean existeInc;

    private Usuario usuarioLogado;
    private Usuario obj;
    private LoginBean bean;
    private UsuarioBean usrBean;
    private boolean existeUserBd;
    private boolean existeUserLogado;

    //CONSTRUTOR
    public LoginView() {
        try {
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

            //Incubadora CONSTRUTOR
            inc = new Incubadora();
            incBean = new IncubadoraBean();
            existeInc = true;

            if (incBean.contarLinhasBean() == 0) {
                System.out.println("Não incubadora cadastrada");
                existeInc = false;
            } else {
                inc = incBean.listarBean().get(0);
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public String geraMsgGenericaCampoObrigatorioView() {
        try {
            return msgGenericaCampoObrigatorio();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String geraImagemFundo() {
        try {
            if (existeInc) {
                return inc.getFotoFundoLogin();
            } else {
                return "fotoFundoPadrao.jpg";
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void salvarCoordenador() {
        try {
            obj.setStatusSistema("Ativo");//SETA O STATUS
            obj.setCargo("Coordenador");//SETA O CARGO
            obj.setSenha(gerarSenhaAleatoria());//GERA A SENHA ALEATORIA e SETA
            usrBean.enviarEmail(obj.getEmail(), "Sistema Sigitec", "Sua senha é: " + obj.getSenha());//Manda o emaill
            obj.setSenha(DigestUtils.md5Hex(obj.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA
            usrBean.salvarBean(obj);//Salva o usuário
            obj = new Usuario();//Limpa o usuário salvo
            existeUserBd = true;//Atualiza variável para dizer q já existe um user no BD
            msgGrowSaveGeneric();//Mensagem de salvar
            msgGrowlInfoCustomizavel("Senha enviada", "A senha foi enviada ao seu e-mail!");
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void gerarNovaSenha() {
        try {
            System.out.println("TESTE--------------------");
            obj = usrBean.buscarPorEmailBean(obj);//Busca o user no BD
            obj.setSenha(gerarSenhaAleatoria());//GERA A SENHA ALEATORIA e SETA
            usrBean.enviarEmail(obj.getEmail(), "Sistema Sigitec", "Sua nova senha é: " + obj.getSenha());//Manda o emaill
            obj.setSenha(DigestUtils.md5Hex(obj.getSenha()));//CRIPTOGRAFA A SENHA ALEATORIA
            usrBean.salvarBean(obj);//Salva o usuário
            obj = new Usuario();//Limpa o usuário salvo
            msgGrowlInfoCustomizavel("Nova senha", "A nova senha foi enviada ao seu e-mail!");
        } catch (EmailException ex) {
            System.out.println("ERRO no Endereço de e-mail: " + ex);
            obj = new Usuario();//Limpa o usuário salvo
            msgPanelErroCustomizavel("Impossível salvar", "Verifique o e-mail e a conexão com a internet ");
        } catch (NullPointerException | PropertyNotFoundException ex) {
            System.out.println("ERRO no obj: " + ex);
            obj = new Usuario();//Limpa o usuário salvo
            msgPanelErroCustomizavel("E-mail desconhecido", "Esse e-mail não esta cadastrado no sistema!");
        }
    }

    public String logar() {
        try {
            usuarioLogado = bean.logar(usuarioLogado);
            if (usuarioLogado != null) {
                SessionUtil.setParam("USUARIOLogado", usuarioLogado);

                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('blockLogar').show()");

                FacesContext.getCurrentInstance().getExternalContext().redirect("/sigitec/inicio.xhtml");
                return "/sigitec/inicio.xhtml";
            } else {
                usuarioLogado = new Usuario();
                msgGrowlErroCustomizavel("Erro", "E-mail ou senha incorretos!");
                return "";
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String sair() {
        try {
            return bean.sair(usuarioLogado);
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public String gerarSenhaAleatoria() {
        try {
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
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
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

    public Incubadora getInc() {
        return inc;
    }

    public void setInc(Incubadora inc) {
        this.inc = inc;
    }

    public boolean isExisteInc() {
        return existeInc;
    }

    public void setExisteInc(boolean existeInc) {
        this.existeInc = existeInc;
    }
}
