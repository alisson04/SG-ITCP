package br.ifnmg.januaria.fernandes.itcp.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;
import br.ifnmg.januaria.fernandes.itcp.dao.UsuarioDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.EnviarEmail;
import br.ifnmg.januaria.fernandes.itcp.util.FacesUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class usuarioBean implements Serializable {

    // VARIAVEIS
    private UsuarioDAO dao;
    String nomeDessaClasse;
    private Usuario usuarioUsoGeral;
    private Usuario usuarioLogado; // Usuario que esta logado no momento
    private List<Usuario> listaUsuarios; //
    private List<Usuario> listaUsuariosFiltrados;
    
    private String confirmarSenha; // Variavel para comparar com a senha
    
    private boolean usrSendoEditado;// P/ renderizar o campo de senha
    public String telefoneAlternativoUsuario;//Deve ser publico para a pagina poder acessar/foi colocado para evitar o bug de validaço de campos

    private MensagensBean mensagensBean;

    // CONSTRUTOR
    @PostConstruct
    public void preencheCargos() {
        

        mensagensBean = new MensagensBean();
        usuarioLogado = new Usuario();
        nomeDessaClasse = "Usuario";
        usrSendoEditado = false;
        
        dao = new UsuarioDAO();
    }

    public void messagemCaixa() {
        mensagensBean.messagemCaixa("FATAL", "Titulo", "mensagem");
    }
/*
    // CRUD
    public String salvarUsrBd() {
        System.out.println("__________BEAN(salvarUsrBd): INÍCIO");
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            //usuarioDAO.salvarGenerico(usuarioUsoGeral);
            //VERIFICA SE O EMAIL JA EXISTE NO DB
            if ((usuarioDAO.buscarPorEmail(usuarioUsoGeral.getEmailUsuario()) == null)) {

                //VERIFICA O TELEFONE ALTERNATIVO
                if (telefoneAlternativoUsuario.equals("")) {
                    usuarioUsoGeral.setTelefoneAlternativoUsuario("Não possui");
                } else {
                    usuarioUsoGeral.setTelefoneAlternativoUsuario(telefoneAlternativoUsuario);
                }
                // SETA A DATA DE SAIDA
                usuarioUsoGeral.setDataSaidaUsuario(null);
                //SETA O STATUS
                usuarioUsoGeral.setStatusSistemaUsuario("Ativo");
                //GERA A SENHA ALEATORIA
                usuarioUsoGeral.setSenhaUsuario(gerarSenhaAleatoria());
                enviarEmail(usuarioUsoGeral.getEmailUsuario(), "Sistema Sigitec", "Sua senha é: " + usuarioUsoGeral.getSenhaUsuario());
                //CRIPTOGRAFA A SENHA ALEATORIA
                usuarioUsoGeral.setSenhaUsuario(DigestUtils.md5Hex(usuarioUsoGeral.getSenhaUsuario()));

                usuarioDAO.salvarUsr(usuarioUsoGeral);
                usrSendoVisualizado = true;
                telefoneAlternativoUsuario = "";//tira a informação apos o salvamento
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");
                return "CadastroUsuario.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "E-mail já cadastrado!"));
                mensagensBean.messagemCaixa("ERROR", "Erro no E-mail", "Este E-mail já esta cadastrado no sistema");
                return null;
            }
        } catch (IOException ex) {
            Logger.getLogger(usuarioBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String editarUsrBd() throws IOException {
        System.out.println("__________BEAN(editarUsrBd): INÍCIO");
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            //VERIFICA SE O EMAIL JA EXISTE NO DB
            if (usuarioDAO.buscarPorEmail(usuarioUsoGeral.getEmailUsuario()) == null
                    || usuarioUsoGeral.getEmailUsuario().equals(((Usuario) session.getAttribute("USUARIOLogado")).getEmailUsuario())) {
                if (usuarioUsoGeral.getSenhaUsuario().equals(confirmarSenha)) {
                    usuarioUsoGeral.setSenhaUsuario(DigestUtils.md5Hex(usuarioUsoGeral.getSenhaUsuario()));
                    if (telefoneAlternativoUsuario.equals("")) {
                        usuarioUsoGeral.setTelefoneAlternativoUsuario("Não possui");
                    } else {
                        usuarioUsoGeral.setTelefoneAlternativoUsuario(telefoneAlternativoUsuario);
                    }

                    enviarEmail(usuarioUsoGeral.getEmailUsuario(), "Sistema Sigitec", "Suas informações no sistema foram editadas");
                    usuarioDAO.salvarGenerico(usuarioUsoGeral);
                    usrSendoVisualizado = true;
                    telefoneAlternativoUsuario = "";//tira a informação apos o salvamento
                    FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");
                    return "CadastroUsuario.xhtml";
                } else {
                    System.out.println("__________BEAN(salvarUsrBd): Já tem esse email no BANCO");
                    mensagensBean.messagemCaixa("ERROR", "Erro na senha", "senhas incompatíveis");
                    return null;
                }

            } else {
                System.out.println("__________BEAN(salvarUsrBd): Já tem esse email no BANCO");
                mensagensBean.messagemCaixa("ERROR", "Erro no E-mail", "Este E-mail já esta cadastrado no sistema");
                return null;

            }
        } catch (RuntimeException ex) {
            System.out.println("BEAN(editarUsrBd): Erro ao editar: " + ex);
            return null;
        }
    }

    public String ativarDesativarUsrBd(Integer idUsrDesativar, String statusSistemaUsuario) {//deve ser "Integer", Não mude.
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioUsoGeral = usuarioDAO.buscarPorCodigo(idUsrDesativar);
            usuarioUsoGeral.setStatusSistemaUsuario(statusSistemaUsuario);
            usuarioDAO.salvarGenerico(usuarioUsoGeral);
            if (statusSistemaUsuario.equals("Ativo")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("ListarUsrAtivos.xhtml");
                return "ListarUsrAtivos.xhtml";
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("ListarUsrInativos.xhtml");
                return "ListarUsrInativos.xhtml";
            }
        } catch (IOException ex) {
            Logger.getLogger(usuarioBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void listarUsrsAtivosInativosTela(String ativoOuInativo) {
        System.out.println("BEAN(listarUsrsAtivosBd): INÍCIO: " + ativoOuInativo);
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            listaUsuarios = usuarioDAO.listarObjsFiltradosGenerico(nomeDessaClasse, "statusSistemaUsuario", ativoOuInativo);
        } catch (RuntimeException ex) {
            //FacesUtil.adicionarMsgErro("Erro ao carregar pesquisa:" + ex.getMessage());
            System.out.println("BEAN(listarUsrsAtivosBd): Erro ao Carregar lista de usuarios: " + ex);
        }
    }

    public String visualizarUsrTela(int idUsrVisualizar) {
        System.out.println("BEAN(visualizarUsr): INÍCIO");
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioUsoGeral = usuarioDAO.buscarPorCodigo(idUsrVisualizar);
            usrSendoVisualizado = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");
            return "CadastroUsuario.xhtml";

        } catch (IOException ex) {
            Logger.getLogger(usuarioBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String salvarUsrTela() throws IOException {
        usuarioUsoGeral = null;
        usrSendoVisualizado = false;
        usrSendoEditado = false;
        FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");
        return "CadastroUsuario.xhtml";
    }

    public String editarUsrTela(int idEditarUsr) {
        try {
            System.out.println("_________BEAN(editarUsr): INÍCIO");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioUsoGeral = usuarioDAO.buscarPorCodigo(idEditarUsr);
            telefoneAlternativoUsuario = usuarioUsoGeral.getTelefoneAlternativoUsuario();
            usrSendoVisualizado = false;
            usrSendoEditado = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(usuarioBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "CadastroUsuario.xhtml";
    }

    //Carrega os dados na tela p/ funções de "visualizar" e "editarUsrBd".
    public void carregarCadastro() {
        try {
            String valor = FacesUtil.getParam("usuarioSelecionado");//Pega o nome do parametro da tela
            if (valor != null) {
                Integer id = Integer.parseInt(valor);

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioUsoGeral = usuarioDAO.buscarPorCodigo(id);
                usrSendoVisualizado = true;
                System.out.println("BEAN(carregarCadastro): setou o 'usuaariSalvo' como True");
            } else {
                System.out.println("BEAN(carregarCadastro): Não passou nada!");
            }
        } catch (RuntimeException ex) {
            System.out.println("BEAN(carregarCadastro): Erro ao tentar obter os dados do usuario " + ex);
            //FacesUtil.adicionarMsgErro("Erro ao tentar obter os dados do usuario:" + ex.getMessage());
        }
    }

    

    // SETS E GETS
    */
}
