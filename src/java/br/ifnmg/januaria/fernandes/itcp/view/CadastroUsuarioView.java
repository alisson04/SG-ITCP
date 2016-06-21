package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.GeraSenhaAleatoria;
import br.ifnmg.januaria.fernandes.itcp.util.ValidadorCNPJ;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroUsuarioView")
@ViewScoped
public class CadastroUsuarioView extends GeraSenhaAleatoria implements Serializable {

    private Usuario usuarioUsoGeral;
    private UsuarioBean bean;
    private List<String> tiposSexo;//tipos de empreendimentos
    private boolean usrSendoVisualizado;// P/ renderizar os campos de usurio nas telas
    private List<String> listaCargos; // lista de cargos da ITCP
    private MensagensBean mensagensBean;

    //variáveis para campos não obrigatórios
    private String telefoneAlternativoUser;

    public CadastroUsuarioView() {
        tiposSexo = new ArrayList<>();
        tiposSexo.add("Masculino");
        tiposSexo.add("Feminino");

        listaCargos = new ArrayList<>();
        listaCargos.add("Coordenador");
        listaCargos.add("Professor");
        listaCargos.add("Técnico Administrativo");
        listaCargos.add("Estagiário Remunerado");
        listaCargos.add("Estagiário Voluntário");
        listaCargos.add("Bolsista - PIBED");
        listaCargos.add("Bolsista - PIBIC");
        listaCargos.add("Bolsista - PROEXT");

        usuarioUsoGeral = new Usuario();
        bean = new UsuarioBean();
        mensagensBean = new MensagensBean();
    }

    public void salvarUsrView() {
        System.out.println("__________BEAN(salvarUsrBd): INÍCIO");
        if ((bean.buscarPorEmailBean(usuarioUsoGeral.getEmailUsuario()) == null)) {

            // SETA A DATA DE SAIDA
            usuarioUsoGeral.setDataSaidaUsuario(null);
            //SETA O STATUS
            usuarioUsoGeral.setStatusSistemaUsuario("Ativo");
            //GERA A SENHA ALEATORIA
            usuarioUsoGeral.setSenhaUsuario(gerarSenhaAleatoria());
            bean.enviarEmail(usuarioUsoGeral.getEmailUsuario(), "Sistema Sigitec", "Sua senha é: " + usuarioUsoGeral.getSenhaUsuario());
            //CRIPTOGRAFA A SENHA ALEATORIA
            usuarioUsoGeral.setSenhaUsuario(DigestUtils.md5Hex(usuarioUsoGeral.getSenhaUsuario()));

            bean.salvarUserBean(usuarioUsoGeral);
            usrSendoVisualizado = true;
            //telefoneAlternativoUsuario = "";//tira a informação apos o salvamento

            //FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroUsuario.xhtml");
            //return "CadastroUsuario.xhtml";
        } else {
            //FacesContext.getCurrentInstance().addMessage(null,
            //        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "E-mail já cadastrado!"));
            mensagensBean.messagemCaixa("ERROR", "Erro no E-mail", "Este E-mail já esta cadastrado no sistema");
            //return null;
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

    //SETS e GETS
    public Usuario getUsuarioUsoGeral() {
        return usuarioUsoGeral;
    }

    public void setUsuarioUsoGeral(Usuario usuarioUsoGeral) {
        this.usuarioUsoGeral = usuarioUsoGeral;
    }

    public boolean isUsrSendoVisualizado() {
        return usrSendoVisualizado;
    }

    public void setUsrSendoVisualizado(boolean usrSendoVisualizado) {
        this.usrSendoVisualizado = usrSendoVisualizado;
    }

    public String getTelefoneAlternativoUser() {
        return telefoneAlternativoUser;
    }

    public void setTelefoneAlternativoUser(String telefoneAlternativoUser) {
        this.telefoneAlternativoUser = telefoneAlternativoUser;
    }

    public List<String> getTiposSexo() {
        return tiposSexo;
    }

    public void setTiposSexo(List<String> tiposSexo) {
        this.tiposSexo = tiposSexo;
    }

    public List<String> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<String> listaCargos) {
        this.listaCargos = listaCargos;
    }

}
