package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MembroEmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
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
@ManagedBean(name = "ListarMembrosEptsView")
@ViewScoped
public class ListarMembrosEptsView implements Serializable {

    MembroEmpreendimentoBean bean = new MembroEmpreendimentoBean();
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    private MembroEmpreendimento membroEmpreendimentoSelecionado;
    private List<MembroEmpreendimento> listaMembrosEmpreendimentos;
    private List<MembroEmpreendimento> listaMembrosEmpreendimentosFiltrados;
    private List<Empreendimento> listaEmpreendimentos;
    private String[] listaNomeEpts;

    public ListarMembrosEptsView() {
    }

    public void ListarMembrosEpts() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            listaEmpreendimentos = empreendimentoBean.listarTodosEptsBean();
            listaMembrosEmpreendimentos = bean.listarTodosMembrosEpts();
            listaNomeEpts = new String[listaEmpreendimentos.size()];
            for (int i = 0; i < listaEmpreendimentos.size(); i++) {
                listaNomeEpts[i] = listaEmpreendimentos.get(i).getNomeEpt();
            }
        } catch (RuntimeException ex) {
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Empreendimentos: " + ex);
        }
    }
    
    public void excluirMembroView(){
        bean.excluirMembroBean(membroEmpreendimentoSelecionado);
        membroEmpreendimentoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Membro excluido do sistema");
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
        System.out.println("BEAN(ListarEmpreendimentosView): onRowSelect: ");
        FacesMessage msg = new FacesMessage(membroEmpreendimentoSelecionado.getNomeMembroEmpreendimento() + " selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //SETS E GETS
    public MembroEmpreendimento getMembroEmpreendimentoSelecionado() {
        return membroEmpreendimentoSelecionado;
    }

    public void setMembroEmpreendimentoSelecionado(MembroEmpreendimento membroEmpreendimentoSelecionado) {
        this.membroEmpreendimentoSelecionado = membroEmpreendimentoSelecionado;
    }

    public String[] getListaNomeEpts() {
        return listaNomeEpts;
    }

    public void setListaNomeEpts(String[] listaNomeEpts) {
        this.listaNomeEpts = listaNomeEpts;
    }

    public List<MembroEmpreendimento> getListaMembrosEmpreendimentos() {
        return listaMembrosEmpreendimentos;
    }

    public void setListaMembrosEmpreendimentos(List<MembroEmpreendimento> listaMembrosEmpreendimentos) {
        this.listaMembrosEmpreendimentos = listaMembrosEmpreendimentos;
    }

    public List<MembroEmpreendimento> getListaMembrosEmpreendimentosFiltrados() {
        return listaMembrosEmpreendimentosFiltrados;
    }

    public void setListaMembrosEmpreendimentosFiltrados(List<MembroEmpreendimento> listaMembrosEmpreendimentosFiltrados) {
        this.listaMembrosEmpreendimentosFiltrados = listaMembrosEmpreendimentosFiltrados;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

}
