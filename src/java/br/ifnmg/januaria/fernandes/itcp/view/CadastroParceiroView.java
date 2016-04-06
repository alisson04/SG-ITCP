package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.ParceiroBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import static br.ifnmg.januaria.fernandes.itcp.util.ValidadorCNPJ.isCNPJ;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroParceiroView")
@ViewScoped
public class CadastroParceiroView implements Serializable {
    
    private Parceiro parceiroSalvar = new Parceiro();
    private MensagensBean mensagensBean = new MensagensBean();
    private ParceiroBean parceiroBean = new ParceiroBean();
    //variáveis para campos não obrigatórios
    private String telefoneAlternativo;
    private boolean parceiroSendoVisualizado;
    
    public void salvarParceiroView() {

        //VERIFICA O TELEFONE ALTERNATIVO
        if (telefoneAlternativo.equals("")) {
            parceiroSalvar.setTelefoneAlternativoParceiro("Não possui");
        } else {
            parceiroSalvar.setTelefoneAlternativoParceiro(telefoneAlternativo);
        }
        parceiroBean.salvarParceiroBd(parceiroSalvar);
        parceiroSendoVisualizado = true;
    }

    public Parceiro getParceiroSalvar() {
        return parceiroSalvar;
    }

    public void setParceiroSalvar(Parceiro parceiroSalvar) {
        this.parceiroSalvar = parceiroSalvar;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

    public boolean isParceiroSendoVisualizado() {
        return parceiroSendoVisualizado;
    }

    public void setParceiroSendoVisualizado(boolean parceiroSendoVisualizado) {
        this.parceiroSendoVisualizado = parceiroSendoVisualizado;
    }
    
    
}
