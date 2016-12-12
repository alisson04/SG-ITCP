package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "modeloGeralView")
@ViewScoped
public class modeloGeralView extends MensagensGenericas implements Serializable {

    //Incubadora VARS
    private Incubadora inc;
    private IncubadoraBean incBean;

    private Usuario usuarioLogado;
    private UsuarioBean userBean;
    private UploadArquivo arquivo;

    //CONSTRUTOR
    @PostConstruct
    public void init() {
        try {
            //Incubadora Constru
            incBean = new IncubadoraBean();

            //User logado Constru
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session = (HttpSession) request.getSession();
            usuarioLogado = (Usuario) session.getAttribute("USUARIOLogado");
            userBean = new UsuarioBean();
            arquivo = new UploadArquivo();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS USUARIO
    public void editarUserLogadoView() {
        try {
            if (userBean.salvarBean(usuarioLogado) != null){//Salvou com sucesso
                usuarioLogado = userBean.salvarBean(usuarioLogado);//Salva o usuário
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('UserDia').hide()");
                msgGrowSaveGeneric();
            } else {
                msgPanelErroCustomizavel("Erro no e-mail", "Este e-mail já esta cadastrado no sistema!");
            }
        }catch (EmailException ex) {
            msgPanelErroCustomizavel("Impossível salvar", "Verifique a validade do e-mail e a conexão com a internet ");
        } catch (Exception ex) {
            throw new FacesException(ex);
        } finally {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockUiGeral').hide()");
        }
    }

    public String[] geraTiposDeCargosView() {
        try {
            return userBean.geraTiposDeCargosBean();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void uploadAction(FileUploadEvent event) {//Upa a foto e seta no user
        try {
            arquivo.fileUpload(event, ".jpg", "/image/");
            System.out.println("Nomed o arquivo: " + arquivo.getNome());
            usuarioLogado.setFotoPerfil(arquivo.getNome());
            arquivo.gravar();
            arquivo = new UploadArquivo();//Limpa a variável
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS INC
    public String geraFotoTopo() {
        try {
            if (incBean.contarLinhasBean() != 0) {//Caso existe INC cadastrada
                inc = incBean.listarBean().get(0);
                System.out.println("cadastrada: " + inc.getId());
                if (inc.getFotoTelaGeral() == null) {
                    inc.setFotoTelaGeral("fotoTopoGeral.jpg");
                }
            } else {
                inc = new Incubadora();
                inc.setFotoTelaGeral("fotoTopoGeral.jpg");
                System.out.println("Não incubadora cadastrada");
            }

            return inc.getFotoTelaGeral();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS E GETS
    public Incubadora getInc() {
        return inc;
    }

    public void setInc(Incubadora inc) {
        this.inc = inc;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
