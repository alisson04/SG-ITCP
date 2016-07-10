package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
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
@ManagedBean(name = "CadastroAtividadePlanejadaView")
@ViewScoped
public class CadastroAtividadePlanejadaView implements Serializable {
    private AtividadePlanejada atividadeCadastrar = new AtividadePlanejada();
    private MensagensBean mensagensBean = new MensagensBean();
    private AtividadePlanejadaBean bean = new AtividadePlanejadaBean();
    private MetaBean metaBean = new MetaBean();
    private List<Meta> listaMetas;
    private boolean objetoFoiCadastrado;

    public CadastroAtividadePlanejadaView() {
        listaMetas = metaBean.listarBean();
    }

    public void salvarView() {
        System.out.println("T" + atividadeCadastrar.getNome());
        System.out.println("T" + atividadeCadastrar.getDescricao());
        System.out.println("T" + atividadeCadastrar.getDataFim());
        System.out.println("T" + atividadeCadastrar.getDataInicio());
        System.out.println("T" + atividadeCadastrar.getId());
        System.out.println("T" + atividadeCadastrar.getMeta().getNome());
        bean.salvarBean(atividadeCadastrar);
        objetoFoiCadastrado = true;
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

    public AtividadePlanejada getAtividadeCadastrar() {
        return atividadeCadastrar;
    }

    public void setAtividadeCadastrar(AtividadePlanejada atividadeCadastrar) {
        this.atividadeCadastrar = atividadeCadastrar;
    }

    public List<Meta> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(List<Meta> listaMetas) {
        this.listaMetas = listaMetas;
    }

    public boolean isObjetoFoiCadastrado() {
        return objetoFoiCadastrado;
    }

    public void setObjetoFoiCadastrado(boolean objetoFoiCadastrado) {
        this.objetoFoiCadastrado = objetoFoiCadastrado;
    }
    
}
