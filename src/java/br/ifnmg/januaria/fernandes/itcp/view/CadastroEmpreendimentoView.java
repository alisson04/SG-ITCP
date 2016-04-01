package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.ValidadorCNPJ;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroEmpreendimentoView")
@ViewScoped
public class CadastroEmpreendimentoView extends ValidadorCNPJ implements Serializable{

    private Empreendimento eptCadastrado = new Empreendimento();
    private String[] tiposEpt;//tipos de empreendimentos
    private String[] situacaoEpt;//Situação do empreendimentos
    private MensagensBean mensagensBean = new MensagensBean();
    private EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();

    //variáveis para campos não obrigatórios
    private String telefoneAlternativo;
    private String cnpj;
    private String faturamentoMensal;
    private String faturamentoAnual;
    private String site;

    public CadastroEmpreendimentoView() {
        tiposEpt = new String[3];
        tiposEpt[0] = "Associação";
        tiposEpt[1] = "Cooperativa";
        tiposEpt[2] = "Grupo não formalizado";
        
        situacaoEpt = new String[3];
        situacaoEpt[0] = "Pré-incubação";
        situacaoEpt[1] = "Incubação";
        situacaoEpt[2] = "Desincubação";
        situacaoEpt[2] = "Desincubado";        
    }

    public void salvarEptView() {

        //VERIFICA O TELEFONE ALTERNATIVO
        if (telefoneAlternativo.equals("")) {
            eptCadastrado.setTelefoneAlternativoEpt("Não possui");
        } else {
            eptCadastrado.setTelefoneAlternativoEpt(telefoneAlternativo);
        }

        //VERIFICA O faturamentoMensal
        if (faturamentoMensal.equals("")) {
            eptCadastrado.setFaturamentoMedioMensalEmp("Não possui");
        } else {
            eptCadastrado.setFaturamentoMedioMensalEmp(faturamentoMensal);
        }
        //VERIFICA O faturamentoAnual
        if (faturamentoAnual.equals("")) {
            eptCadastrado.setFaturamentoMedioAnualEmp("Não possui");
        } else {
            eptCadastrado.setFaturamentoMedioAnualEmp(faturamentoAnual);
        }
        //VERIFICA O site
        if (site.equals("")) {
            eptCadastrado.setSiteEpt("Não possui");
        } else {
            eptCadastrado.setSiteEpt(site);
        }
        //VERIFICA O CNPJ
        if (cnpj.equals("")) {
            eptCadastrado.setCnpjEpt("Não possui");
            empreendimentoBean.salvarEptBd(eptCadastrado);
        } else {
            //VERIFICA se o CNPJ é válido
            if (isCNPJ(eptCadastrado.getCnpjEpt())) {
                eptCadastrado.setCnpjEpt(cnpj);
                empreendimentoBean.salvarEptBd(eptCadastrado);
            } else {
                mensagensBean.messagemCaixa("ERROR", "Erro no CNPJ", "Este CNPJ não é valido!");
            }
        }
    }

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
}
