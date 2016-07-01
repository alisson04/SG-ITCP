package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.ParceiroBean;
import br.ifnmg.januaria.fernandes.itcp.dao.ParceiroDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
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
@ManagedBean(name="ListarParceirosView")
@ViewScoped
public class ListarParceirosView {
    private Parceiro parceiroSelecionado;
    private List<Parceiro> listaParceiros;
    private List<Parceiro> listaParceirosFiltrados;
    private ParceiroBean bean;
    
    public ListarParceirosView(){
        bean = new ParceiroBean();
        parceiroSelecionado = new Parceiro();
    }
    
    public void listarTodosParceiros() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            listaParceiros = bean.listarTodosParceiros();
        } catch (RuntimeException ex) {
            //FacesUtil.adicionarMsgErro("Erro ao carregar pesquisa:" + ex.getMessage());
            System.out.println("VIEW(listarTodosParceiros): Erro ao Carregar lista de Parceiros: " + ex);
        }
    }
    
    public void excluirParceiroView(){
        bean.excluirParceiroBean(parceiroSelecionado);
        parceiroSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Parceiro excluido do sistema");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Parceiro " + parceiroSelecionado.getNomeParceiro()+ " selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //SETS E GETS
    public List<Parceiro> getListaParceiros() {
        return listaParceiros;
    }

    public void setListaParceiros(List<Parceiro> listaParceiros) {
        this.listaParceiros = listaParceiros;
    }

    public List<Parceiro> getListaParceirosFiltrados() {
        return listaParceirosFiltrados;
    }

    public void setListaParceirosFiltrados(List<Parceiro> listaParceirosFiltrados) {
        this.listaParceirosFiltrados = listaParceirosFiltrados;
    }

    public Parceiro getParceiroSelecionado() {
        return parceiroSelecionado;
    }

    public void setParceiroSelecionado(Parceiro parceiroSelecionado) {
        this.parceiroSelecionado = parceiroSelecionado;
    }
}