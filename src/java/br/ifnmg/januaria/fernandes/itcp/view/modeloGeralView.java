package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.UploadArquivo;
import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.apache.commons.codec.digest.DigestUtils;

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

    //modificar a senha vars
    String novaSenha;
    String confirmaNovaSenha;
    String senhaAntiga;

    private Usuario usuarioLogado;
    private UsuarioBean userBean;
    private UploadArquivo arquivo;

    //Varial que determina se existe algum EPT cadastrado - Usado por várias paginas
    private boolean existeEptBd;
    private EmpreendimentoBean empreendimentoBean;

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

            //Verifica se existe Empreendimento no banco de dados
            empreendimentoBean = new EmpreendimentoBean();

            existeEptBd = true;
            if (empreendimentoBean.contarLinhasBean() == 0) {
                existeEptBd = false;
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS GERAIS
    public String geraPorcentagem(float porcentagem, float valor) {
        float resultado;

        resultado = (porcentagem / valor) * 100;

        DecimalFormat df = new DecimalFormat("00.00");
        System.out.println("Porcentagem: " + df.format(resultado));
        return df.format(resultado);
    }

    public String abreviaNomeUser(Usuario u) {
        int i = u.getNome().indexOf(" ");
        int x = u.getNome().lastIndexOf(" ");
        /* Busca na string, a posição do ' ' espaço, e retorna o indice dele */
        return (u.getNome().substring(0, i)
                + u.getNome().substring(x));
        /* Aqui é separada a String do primeiro caractere até o primeiro espaço*/
    }

    public String corrigeImagemBase64(String base64) {
        if (base64 != null) {
            int i = base64.indexOf(",");
            return base64.substring(i + 1);
        } else {
            System.out.println("Esta nulo!");
            return null;
        }
    }

    public String geraTipoImagemBase64(String base64) {
        if (base64 != null) {
            int i = base64.indexOf("/");
            System.out.println("Tipo da imagem: " + base64.substring(i + 1, (i + 4)));
            return base64.substring(i + 1, (i + 4));
        } else {
            System.out.println("Esta nulo!");
            return null;
        }
    }

    public String geraMsgGenericaCampoObrigatorioView() {
        try {
            return msgGenericaCampoObrigatorio();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS USUARIO
    public void editarUserLogadoView() {
        try {
            if (userBean.salvarBean(usuarioLogado) != null) {//Salvou com sucesso
                usuarioLogado = userBean.salvarBean(usuarioLogado);//Salva e atualiza o usuário
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('UserDia').hide()");
                msgGrowSaveGeneric();
            } else {
                msgPanelErroCustomizavel("Erro no e-mail", "Este e-mail já esta cadastrado no sistema!");
            }
        } catch (EmailException ex) {
            msgPanelErroCustomizavel("Impossível salvar", "Verifique a validade do e-mail e a conexão com a internet ");
        } catch (Exception ex) {
            throw new FacesException(ex);
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
            arquivo.fileUpload(event.getFile().getContents(), new java.util.Date().getTime() + ".jpg", "/image/");
            System.out.println("Nomed o arquivo: " + arquivo.getNome());
            usuarioLogado.setFotoPerfil(arquivo.getNome());
            arquivo.gravar();
            arquivo = new UploadArquivo();//Limpa a variável
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void modificarSenha() {
        try {
            if (DigestUtils.md5Hex(senhaAntiga).equals(usuarioLogado.getSenha())) {//Se a senha atual confere
                if (novaSenha.equals(confirmaNovaSenha)) {//Se a nova senha confere
                    usuarioLogado.setSenha(DigestUtils.md5Hex(novaSenha));
                    usuarioLogado = userBean.salvarBean(usuarioLogado);//Salva e atualiza o usuário
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('diaModificarSenha').hide()");
                    msgGrowSaveGeneric();
                } else {
                    msgPanelErroCustomizavel("Erro", "Verifire a nova senha e sua confirmação!");
                }
            } else {
                msgPanelErroCustomizavel("Erro", "A senha atual não confere!");
            }
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

    private String getRealPath() {
        ExternalContext externalContext
                = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response
                = (HttpServletResponse) externalContext.getResponse();

        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context
                = (ServletContext) aFacesContext.getExternalContext().getContext();

        return context.getRealPath("/");
    }

    public boolean renderisaFotoTopo() {
        File file2 = new File(getRealPath() + "image/imagenTopo");
        int count = file2.listFiles().length;

        if (count >0) {
            System.out.println("EX---------------------------");
            return true;
        } else {
            System.out.println("NA-----------------------");
            return false;
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

    public boolean isExisteEptBd() {
        return existeEptBd;
    }

    public void setExisteEptBd(boolean existeEptBd) {
        this.existeEptBd = existeEptBd;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaNovaSenha() {
        return confirmaNovaSenha;
    }

    public void setConfirmaNovaSenha(String confirmaNovaSenha) {
        this.confirmaNovaSenha = confirmaNovaSenha;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }
}
