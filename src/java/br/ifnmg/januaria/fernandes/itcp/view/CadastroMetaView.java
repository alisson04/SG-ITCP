package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroMetaView")
@ViewScoped
public class CadastroMetaView implements Serializable {

    private Meta metaSalvar = new Meta();
    private MensagensBean mensagensBean = new MensagensBean();
    private MetaBean bean = new MetaBean();
    private PlanoAcaoBean beanPlanoAcao = new PlanoAcaoBean();
    private List<PlanoAcao> listaPlanosAcao;
    private boolean metaSendoVisualizada;

    public CadastroMetaView() {
        listaPlanosAcao = beanPlanoAcao.listarTodosPlanos();
    }

    public void salvarMetaView() {
        bean.salvarMetaBd(metaSalvar);
        metaSendoVisualizada = true;
    }

    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }

    //SETES e GETS
    public Meta getMetaSalvar() {
        return metaSalvar;
    }

    public void setMetaSalvar(Meta metaSalvar) {
        this.metaSalvar = metaSalvar;
    }

    public boolean isMetaSendoVisualizada() {
        return metaSendoVisualizada;
    }

    public void setMetaSendoVisualizada(boolean metaSendoVisualizada) {
        this.metaSendoVisualizada = metaSendoVisualizada;
    }

    public List<PlanoAcao> getListaPlanosAcao() {
        return listaPlanosAcao;
    }

    public void setListaPlanosAcao(List<PlanoAcao> listaPlanosAcao) {
        this.listaPlanosAcao = listaPlanosAcao;
    }
}
