package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name="ListarMetasView")
@ViewScoped
public class ListarMetasView implements Serializable{
    private Meta metaSelecionada;
    private boolean btnEdicoesMeta;
    private List<PlanoAcao> listaPlanos;
    private List<Meta> listaMetas;
    private List<Meta> listaMetasFiltradas;
    private MetaBean bean;
    private PlanoAcaoBean planoAcaoBean;    
    private String planoSelecionado;
    private String[] listaNomePlanosAcao;
    
    public ListarMetasView() {
        btnEdicoesMeta = true; //recebe true para desabillitar o botão da tela
        bean = new MetaBean();
        planoAcaoBean = new PlanoAcaoBean();
    }
    
    public void listarTodasMetas() {
        try {
            listaMetas = bean.listarTodasMetas();
            listaPlanos = planoAcaoBean.listarTodosPlanos();
            listaNomePlanosAcao = new String[listaPlanos.size()];
            for (int i = 0; i < listaPlanos.size(); i++) {
                listaNomePlanosAcao[i] = listaPlanos.get(i).getDescricao();
            }
        } catch (RuntimeException ex) {
            //FacesUtil.adicionarMsgErro("Erro ao carregar pesquisa:" + ex.getMessage());
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Empreendimentos: " + ex);
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
    
    public void excluirMetaView(){
        bean.excluirMetaBean(metaSelecionada);
        metaSelecionada = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Meta excluida do sistema");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowSelect(SelectEvent event) {
        btnEdicoesMeta = false;
        FacesMessage msg = new FacesMessage("Meta " + metaSelecionada.getNome()+ " selecionada!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Meta getMetaSelecionada() {
        return metaSelecionada;
    }

    public void setMetaSelecionada(Meta metaSelecionada) {
        this.metaSelecionada = metaSelecionada;
    }

    public boolean isBtnEdicoesMeta() {
        return btnEdicoesMeta;
    }

    public void setBtnEdicoesMeta(boolean btnEdicoesMeta) {
        this.btnEdicoesMeta = btnEdicoesMeta;
    }

    public List<Meta> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(List<Meta> listaMetas) {
        this.listaMetas = listaMetas;
    }

    public List<Meta> getListaMetasFiltradas() {
        return listaMetasFiltradas;
    }

    public void setListaMetasFiltradas(List<Meta> listaMetasFiltradas) {
        this.listaMetasFiltradas = listaMetasFiltradas;
    }

    public List<PlanoAcao> getListaPlanos() {
        return listaPlanos;
    }

    public void setListaPlanos(List<PlanoAcao> listaPlanos) {
        this.listaPlanos = listaPlanos;
    }

    public String getPlanoSelecionado() {
        return planoSelecionado;
    }

    public void setPlanoSelecionado(String planoSelecionado) {
        this.planoSelecionado = planoSelecionado;
    }

    public String[] getListaNomePlanosAcao() {
        return listaNomePlanosAcao;
    }

    public void setListaNomePlanosAcao(String[] listaNomePlanosAcao) {
        this.listaNomePlanosAcao = listaNomePlanosAcao;
    }
    
    
}
