package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroPlanoAcaoView")
@ViewScoped
public class CadastroPlanoAcaoView implements Serializable{
    
    private PlanoAcao planoAcaoSalvar;
    private MensagensBean mensagensBean;
    private PlanoAcaoBean planoAcaoBean;
    private boolean planoAcaoSendoVisualizado;
    
    public CadastroPlanoAcaoView(){
        planoAcaoSalvar = new PlanoAcao();
        mensagensBean = new MensagensBean();
        planoAcaoBean = new PlanoAcaoBean();
    }
    
    public void salvarPlanoAcao() {
        planoAcaoBean.salvarParceiroBd(planoAcaoSalvar);
        planoAcaoSendoVisualizado = true;
    }

    //SETS E GETS
    public PlanoAcao getPlanoAcaoSalvar() {
        return planoAcaoSalvar;
    }

    public void setPlanoAcaoSalvar(PlanoAcao planoAcaoSalvar) {
        this.planoAcaoSalvar = planoAcaoSalvar;
    }

    public boolean isPlanoAcaoSendoVisualizado() {
        return planoAcaoSendoVisualizado;
    }

    public void setPlanoAcaoSendoVisualizado(boolean planoAcaoSendoVisualizado) {
        this.planoAcaoSendoVisualizado = planoAcaoSendoVisualizado;
    }
    
    
}
