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
    
    private Meta metaSalvar;
    private MensagensBean mensagensBean;
    private MetaBean bean;
    private PlanoAcaoBean beanPlanoAcao;
    private List<PlanoAcao> listaPlanosAcao;
    private String[] listaNomePlanosAcao;
    private boolean metaSendoVisualizada;
    private String nomePlanoSelecionado;
    
    public CadastroMetaView(){
        beanPlanoAcao = new PlanoAcaoBean();
        metaSalvar = new Meta();
        mensagensBean = new MensagensBean();
        bean = new MetaBean();
        listaPlanosAcao = beanPlanoAcao.listarTodosPlanos();
        listaNomePlanosAcao = new String[listaPlanosAcao.size()];
        for (int i = 0; i < listaPlanosAcao.size(); i++) {
            listaNomePlanosAcao[i] = listaPlanosAcao.get(i).getDescricao();
        }
    }
    
    public void salvarMetaView() {
        for (int i = 0; i < listaPlanosAcao.size(); i++) {
            if (listaPlanosAcao.get(i).getDescricao().equals(nomePlanoSelecionado)) {
                metaSalvar.setIdPlanoAcaoFk(listaPlanosAcao.get(i));
            }
        }
        
        SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
        String folderName = forma.format(metaSalvar.getDataInicio());
        bean.salvarMetaBd(metaSalvar);
        metaSendoVisualizada = true;
    }
    
    public String conveteData(Date data){
        SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
        return forma.format(data);
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

    public String[] getListaNomePlanosAcao() {
        return listaNomePlanosAcao;
    }

    public void setListaNomePlanosAcao(String[] listaNomePlanosAcao) {
        this.listaNomePlanosAcao = listaNomePlanosAcao;
    }

    public String getNomePlanoSelecionado() {
        return nomePlanoSelecionado;
    }

    public void setNomePlanoSelecionado(String nomePlanoSelecionado) {
        this.nomePlanoSelecionado = nomePlanoSelecionado;
    }
    
}
