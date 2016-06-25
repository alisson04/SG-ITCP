package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
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
@ManagedBean(name = "ListarPlanoAcaoView")
@ViewScoped
public class ListarPlanoAcaoView implements Serializable {

    PlanoAcaoBean bean = new PlanoAcaoBean();
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    private PlanoAcao planoAcaoSelecionado;
    private boolean btnEdicoesPlanoAcao;
    private List<PlanoAcao> listaPlanoAcao;
    private List<PlanoAcao> listaPlanoAcaoFiltrados;
    private List<Empreendimento> listaEmpreendimentos;
    private String[] listaNomeEpts;

    public ListarPlanoAcaoView() {
        btnEdicoesPlanoAcao=true;
    }

    public void ListarPlanosAcao() {
        try {
            listaEmpreendimentos = empreendimentoBean.listarTodosEptsBean();
            listaPlanoAcao = bean.listarTodosPlanos();

            //
            listaNomeEpts = new String[listaEmpreendimentos.size()];
            for (int i = 0; i < listaEmpreendimentos.size(); i++) {
                listaNomeEpts[i] = listaEmpreendimentos.get(i).getNomeEpt();
            }
        } catch (RuntimeException ex) {
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Planos: " + ex);
        }
    }
    
    public void excluirPlanoView(){
        bean.excluirPlanoBean(planoAcaoSelecionado);
        planoAcaoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Plano de ação excluido do sistema");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }

    public void onRowSelect(SelectEvent event) {
        btnEdicoesPlanoAcao = false;
        FacesMessage msg = new FacesMessage("Plano de ação selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //SETS E GETS

    public PlanoAcao getPlanoAcaoSelecionado() {
        return planoAcaoSelecionado;
    }

    public void setPlanoAcaoSelecionado(PlanoAcao planoAcaoSelecionado) {
        this.planoAcaoSelecionado = planoAcaoSelecionado;
    }

    public boolean isBtnEdicoesPlanoAcao() {
        return btnEdicoesPlanoAcao;
    }

    public void setBtnEdicoesPlanoAcao(boolean btnEdicoesPlanoAcao) {
        this.btnEdicoesPlanoAcao = btnEdicoesPlanoAcao;
    }

    public List<PlanoAcao> getListaPlanoAcao() {
        return listaPlanoAcao;
    }

    public void setListaPlanoAcao(List<PlanoAcao> listaPlanoAcao) {
        this.listaPlanoAcao = listaPlanoAcao;
    }

    public List<PlanoAcao> getListaPlanoAcaoFiltrados() {
        return listaPlanoAcaoFiltrados;
    }

    public void setListaPlanoAcaoFiltrados(List<PlanoAcao> listaPlanoAcaoFiltrados) {
        this.listaPlanoAcaoFiltrados = listaPlanoAcaoFiltrados;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public String[] getListaNomeEpts() {
        return listaNomeEpts;
    }

    public void setListaNomeEpts(String[] listaNomeEpts) {
        this.listaNomeEpts = listaNomeEpts;
    }
    
}
