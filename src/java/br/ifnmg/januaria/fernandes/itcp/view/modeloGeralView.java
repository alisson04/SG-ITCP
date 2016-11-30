package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.dao.ProdutoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.domain.Produto;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
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
        //Incubadora Constru
        incBean = new IncubadoraBean();

        //User logado Constru
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        usuarioLogado = (Usuario) session.getAttribute("USUARIOLogado");
        userBean = new UsuarioBean();
        arquivo = new UploadArquivo();
    }

    //METODOS USUARIO
    public void editarUserLogadoView() {
        try {
            if ((userBean.buscarPorEmailBean(usuarioLogado) == null//Verifica se o e-mail já esta cadastrado
                    || userBean.buscarPorEmailBean(usuarioLogado).getId().equals(usuarioLogado.getId()))) {
                usuarioLogado = userBean.salvarBean(usuarioLogado);//Salva o usuário
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('UserDia').hide()");
                msgGrowSaveGeneric();
            } else {
                msgPanelErroCustomizavel("Erro no e-mail", "Este e-mail já esta cadastrado no sistema!");
            }
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }

    public String[] geraTiposDeCargosView() {
        return userBean.geraTiposDeCargosBean();
    }

    public void uploadAction(FileUploadEvent event) {//Upa a foto e seta no user
        arquivo.fileUpload(event, ".jpg", "/image/");
        System.out.println("Nomed o arquivo: " + arquivo.getNome());
        usuarioLogado.setFotoPerfil(arquivo.getNome());
        arquivo.gravar();
        arquivo = new UploadArquivo();//Limpa a variável
    }

    //METODOS INC
    public String geraFotoTopo() {
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
