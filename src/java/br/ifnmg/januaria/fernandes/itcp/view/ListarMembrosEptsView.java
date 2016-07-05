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
import org.primefaces.context.RequestContext;
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

    public ListarMembrosEptsView() {
    }

    public void ListarMembrosEpts() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            listaEmpreendimentos = empreendimentoBean.listarTodosEptsBean();
            listaMembrosEmpreendimentos = bean.listarTodosMembrosEpts();
            
        } catch (RuntimeException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Erro inesperado", "Erro ao tentar listar os membros, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void excluirMembroView(){
        bean.excluirMembroBean(membroEmpreendimentoSelecionado);
        membroEmpreendimentoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Exclusão realizada com sucesso!");
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
        FacesMessage msg = new FacesMessage("Membro de empreendimento selecionado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //SETS E GETS
    public MembroEmpreendimento getMembroEmpreendimentoSelecionado() {
        return membroEmpreendimentoSelecionado;
    }

    public void setMembroEmpreendimentoSelecionado(MembroEmpreendimento membroEmpreendimentoSelecionado) {
        this.membroEmpreendimentoSelecionado = membroEmpreendimentoSelecionado;
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
