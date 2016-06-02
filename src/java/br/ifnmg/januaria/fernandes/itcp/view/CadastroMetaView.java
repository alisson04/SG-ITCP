package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroMetaView")
@ViewScoped
public class CadastroMetaView implements Serializable {
    
    private Meta metaSalvar;
    private MensagensBean mensagensBean;
    private MetaBean bean;
    private boolean metaSendoVisualizada;
    
    public CadastroMetaView(){
        metaSalvar = new Meta();
        mensagensBean = new MensagensBean();
        bean = new MetaBean();
    }
    
    public void salvarParceiroView() {
        bean.salvarMetaBd(metaSalvar);
        metaSendoVisualizada = true;
    }
}
