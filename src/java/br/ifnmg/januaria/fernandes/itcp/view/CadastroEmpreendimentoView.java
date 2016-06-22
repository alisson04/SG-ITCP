package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.ValidadorCNPJ;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroEmpreendimentoView")
@ViewScoped
public class CadastroEmpreendimentoView extends ValidadorCNPJ implements Serializable {

    private Empreendimento eptCadastrado = new Empreendimento();
    private String[] tiposEpt;//tipos de empreendimentos
    private String[] situacaoEpt;//Situação do empreendimentos
    private MensagensBean mensagensBean = new MensagensBean();
    private EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    private boolean eptSendoVisualizado;

    //variáveis para campos não obrigatórios
    private String telefoneAlternativo;
    private String cnpj;
    private String faturamentoMensal;
    private String faturamentoAnual;
    private String site;
    private String atividadeExercidaEpt;
    private String dataIncubacaoEpt;
    private String dataCriacaoEpt;
    private String telefoneEpt;
    private String enderecoEpt;
    private String emailEpt;

    public CadastroEmpreendimentoView() {
        tiposEpt = new String[3];
        tiposEpt[0] = "Associação";
        tiposEpt[1] = "Cooperativa";
        tiposEpt[2] = "Grupo não formalizado";

        situacaoEpt = new String[5];
        situacaoEpt[0] = "Não incubado";
        situacaoEpt[1] = "Pré-incubação";
        situacaoEpt[2] = "Incubação";
        situacaoEpt[3] = "Desincubação";
        situacaoEpt[4] = "Desincubado";
    }

    public void salvarEptView() throws IOException {
        //E-MAIL EMPREENDIMENTO
        if (emailEpt.equals("")) {
            eptCadastrado.setEmailEpt("Não registrado");
        } else {
            eptCadastrado.setEmailEpt(emailEpt);
        } 

        //TELEFONE EMPREENDIMENTO
        if (enderecoEpt.equals("")) {
            eptCadastrado.setEnderecoEpt("Não registrado");
        } else {
            eptCadastrado.setEnderecoEpt(enderecoEpt);
        }        

        //TELEFONE EMPREENDIMENTO
        if (telefoneEpt.equals("")) {
            eptCadastrado.setTelefoneEpt("Não registrado");
        } else {
            eptCadastrado.setTelefoneEpt(telefoneEpt);
        }

        //ATIVIDADE EXERCICIDA
        if (atividadeExercidaEpt.equals("")) {
            eptCadastrado.setAtividadeExercidaEpt("Não registrado");
        } else {
            eptCadastrado.setAtividadeExercidaEpt(atividadeExercidaEpt);
        }

        //VERIFICA O TELEFONE ALTERNATIVO
        if (telefoneAlternativo.equals("")) {
            eptCadastrado.setTelefoneAlternativoEpt("Não registrado");
        } else {
            eptCadastrado.setTelefoneAlternativoEpt(telefoneAlternativo);
        }

        //VERIFICA O faturamentoMensal
        if (faturamentoMensal.equals("")) {
            eptCadastrado.setFaturamentoMedioMensalEmp("Não registrado");
        } else {
            eptCadastrado.setFaturamentoMedioMensalEmp(faturamentoMensal);
        }
        //VERIFICA O faturamentoAnual
        if (faturamentoAnual.equals("")) {
            eptCadastrado.setFaturamentoMedioAnualEmp("Não registrado");
        } else {
            eptCadastrado.setFaturamentoMedioAnualEmp(faturamentoAnual);
        }
        //VERIFICA O site
        if (site.equals("")) {
            eptCadastrado.setSiteEpt("Não registrado");
        } else {
            eptCadastrado.setSiteEpt(site);
        }
        //VERIFICA O CNPJ
        if (cnpj.equals("")) {
            eptCadastrado.setCnpjEpt("Não registrado");
            empreendimentoBean.salvarEptBd(eptCadastrado);
            eptSendoVisualizado = true;
        } else //VERIFICA se o CNPJ é válido
        if (isCNPJ(eptCadastrado.getCnpjEpt())) {
            eptCadastrado.setCnpjEpt(cnpj);
            empreendimentoBean.salvarEptBd(eptCadastrado);
            eptSendoVisualizado = true;
        } else {
            mensagensBean.messagemCaixa("ERROR", "Erro no CNPJ", "Este CNPJ não é valido!");
        }
    }

    //SETS e GETS
    public Empreendimento getEptCadastrado() {
        return eptCadastrado;
    }

    public void setEptCadastrado(Empreendimento eptCadastrado) {
        this.eptCadastrado = eptCadastrado;
    }

    public String[] getTiposEpt() {
        return tiposEpt;
    }

    public void setTiposEpt(String[] tiposEpt) {
        this.tiposEpt = tiposEpt;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFaturamentoMensal() {
        return faturamentoMensal;
    }

    public void setFaturamentoMensal(String faturamentoMensal) {
        this.faturamentoMensal = faturamentoMensal;
    }

    public String getFaturamentoAnual() {
        return faturamentoAnual;
    }

    public void setFaturamentoAnual(String faturamentoAnual) {
        this.faturamentoAnual = faturamentoAnual;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String[] getSituacaoEpt() {
        return situacaoEpt;
    }

    public void setSituacaoEpt(String[] situacaoEpt) {
        this.situacaoEpt = situacaoEpt;
    }

    public boolean isEptSendoVisualizado() {
        return eptSendoVisualizado;
    }

    public void setEptSendoVisualizado(boolean eptSendoVisualizado) {
        this.eptSendoVisualizado = eptSendoVisualizado;
    }

    public String getAtividadeExercidaEpt() {
        return atividadeExercidaEpt;
    }

    public void setAtividadeExercidaEpt(String atividadeExercidaEpt) {
        this.atividadeExercidaEpt = atividadeExercidaEpt;
    }

    public String getDataIncubacaoEpt() {
        return dataIncubacaoEpt;
    }

    public void setDataIncubacaoEpt(String dataIncubacaoEpt) {
        this.dataIncubacaoEpt = dataIncubacaoEpt;
    }

    public String getDataCriacaoEpt() {
        return dataCriacaoEpt;
    }

    public void setDataCriacaoEpt(String dataCriacaoEpt) {
        this.dataCriacaoEpt = dataCriacaoEpt;
    }

    public String getTelefoneEpt() {
        return telefoneEpt;
    }

    public void setTelefoneEpt(String telefoneEpt) {
        this.telefoneEpt = telefoneEpt;
    }

    public String getEnderecoEpt() {
        return enderecoEpt;
    }

    public void setEnderecoEpt(String enderecoEpt) {
        this.enderecoEpt = enderecoEpt;
    }

    public String getEmailEpt() {
        return emailEpt;
    }

    public void setEmailEpt(String emailEpt) {
        this.emailEpt = emailEpt;
    }

    
    
}
