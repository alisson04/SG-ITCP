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
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class usuarioBean implements Serializable {

    // VARIAVEIS
    String nomeDessaClasse;
    private Usuario usuarioUsoGeral;
    private Usuario usuarioLogado; // Usuario que esta logado no momento
    private List<Usuario> listaUsuarios; //
    private List<Usuario> listaUsuariosFiltrados;
    private List<String> listaCargos; // lista de cargos da ITCP
    private String confirmarSenha; // Variavel para comparar com a senha
    private boolean usrSendoVisualizado;// P/ renderizar os campos de usurio nas telas
    private boolean usrSendoEditado;// P/ renderizar o campo de senha
    public String telefoneAlternativoUsuario;//Deve ser publico para a pagina poder acessar/foi colocado para evitar o bug de validaço de campos

    private MensagensBean mensagensBean;
    

    // CONSTRUTOR
    @PostConstruct
    public void preencheCargos() {
        listaCargos = new ArrayList<>();
        listaCargos.add("Coordenador");
        listaCargos.add("Professor");
        listaCargos.add("Técnico Administrativo");
        listaCargos.add("Estagiário Remunerado");
        listaCargos.add("Estagiário Voluntário");
        listaCargos.add("Bolsista - PIBED");
        listaCargos.add("Bolsista - PIBIC");
        listaCargos.add("Bolsista - PROEXT");

        mensagensBean = new MensagensBean();
        usuarioLogado = new Usuario();
        nomeDessaClasse = "Usuario";
        usrSendoEditado = false;
    }

    public void messagemCaixa() {
        mensagensBean.messagemCaixa("FATAL", "Titulo", "mensagem");
    }

    public void enviarEmail(String enviarPara, String assunto, String mensagem) {
        System.out.println("__________BEAN(enviarEmail): Para:" + enviarPara);
        System.out.println("__________BEAN(enviarEmail): Assunto" + assunto);
        System.out.println("__________BEAN(enviarEmail): Mensagem" + mensagem);
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.enviarEmail(enviarPara, assunto, mensagem);
        System.out.println("__________BEAN(enviarEmail): Fim");
    }

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
                usuarioUsoGeral.setDataSaidaUsuario("Ainda atuante");
                //SETA O STATUS
                usuarioUsoGeral.setStatusSistemaUsuario("Ativo");
                //GERA A SENHA ALEATORIA
                usuarioUsoGeral.setSenhaUsuario(gerarSenhaAleatoria());
                enviarEmail(usuarioUsoGeral.getEmailUsuario(), "Sistema Sigitec", "Sua senha é: " + usuarioUsoGeral.getSenhaUsuario());
                //CRIPTOGRAFA A SENHA ALEATORIA
                usuarioUsoGeral.setSenhaUsuario(DigestUtils.md5Hex(usuarioUsoGeral.getSenhaUsuario()));

                usuarioDAO.salvarGenerico(usuarioUsoGeral);
                usrSendoVisualizado = true;
                telefoneAlternativoUsuario = "";//tira a informação apos o salvamento
                FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");
                return "CadastroUsuario.xhtml";

            } else {
                System.out.println("BEAN(salvarUsrBd): Já tem esse email no BANCO");
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

    // SETS E GETS
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public boolean isUsrSendoVisualizado() {
        return usrSendoVisualizado;
    }

    public void setUsrSendoVisualizado(boolean usrSendoVisualizado) {
        this.usrSendoVisualizado = usrSendoVisualizado;
    }

    public Usuario getUsuarioUsoGeral() {
        if (usuarioUsoGeral == null) {
            usuarioUsoGeral = new Usuario();
        }
        return usuarioUsoGeral;
    }

    public void setUsuarioUsoGeral(Usuario usuarioUsoGeral) {
        this.usuarioUsoGeral = usuarioUsoGeral;
    }

    public List<String> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<String> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuariosFiltrados(List<Usuario> listaUsuariosFiltrados) {
        this.listaUsuariosFiltrados = listaUsuariosFiltrados;
    }

    public List<Usuario> getListaUsuariosFiltrados() {
        return listaUsuariosFiltrados;
    }

    public String getTelefoneAlternativoUsuario() {
        return telefoneAlternativoUsuario;
    }

    public void setTelefoneAlternativoUsuario(String telefoneAlternativoUsuario) {
        this.telefoneAlternativoUsuario = telefoneAlternativoUsuario;
    }

    public boolean isUsrSendoEditado() {
        return usrSendoEditado;
    }

    public void setUsrSendoEditado(boolean usrSendoEditado) {
        this.usrSendoEditado = usrSendoEditado;
    }

}
