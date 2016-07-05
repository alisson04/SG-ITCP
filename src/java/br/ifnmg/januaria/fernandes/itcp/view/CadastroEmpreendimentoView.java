package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.ValidadorCNPJ;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        //VERIFICA O CNPJ
        if (eptCadastrado.getCnpjEpt().equals("")) {
            empreendimentoBean.salvarEptBean(eptCadastrado);
            eptSendoVisualizado = true;
        } else if (isCNPJ(eptCadastrado.getCnpjEpt())) {//VERIFICA se o CNPJ é válido
            empreendimentoBean.salvarEptBean(eptCadastrado);
            eptSendoVisualizado = true;
        } else {
            mensagensBean.messagemCaixa("ERROR", "Erro no CNPJ", "Este CNPJ não é valido!");
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
}
