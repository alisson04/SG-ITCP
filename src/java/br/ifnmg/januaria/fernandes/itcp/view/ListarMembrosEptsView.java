package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MembroEmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarMembrosEptsView")
@ViewScoped
public class ListarMembrosEptsView extends MensagensGenericas implements Serializable {

    private MembroEmpreendimentoBean bean;
    private EmpreendimentoBean empreendimentoBean;
    private MembroEmpreendimento membroEmpreendimentoSelecionado;
    private MembroEmpreendimento objSalvar;
    private List<MembroEmpreendimento> listaMembrosEmpreendimentos;
    private List<MembroEmpreendimento> listaMembrosEmpreendimentosFiltrados;
    private List<Empreendimento> listaEmpreendimentos;
    private boolean existeEptBd;

    public ListarMembrosEptsView() {
        bean = new MembroEmpreendimentoBean();
        empreendimentoBean = new EmpreendimentoBean();
        objSalvar = new MembroEmpreendimento();
        
        //Verifica se existe Empreendimento no banco de dados
        existeEptBd = true;
        if (empreendimentoBean.contarLinhasBean() == 0) {
            System.out.println("Não há EES cadastrados");
            existeEptBd = false;
        }
    }

    public void ListarMembrosEpts() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            listaEmpreendimentos = empreendimentoBean.listarBean();
            listaMembrosEmpreendimentos = bean.listarBean();
            
        } catch (RuntimeException ex) {
            msgPanelErroInesperadoGeneric();
        }
    }
    
    public void transfereObj(){//Para botão de editar
        objSalvar = membroEmpreendimentoSelecionado;
    }
    
    public void reiniciaObj(){//Para botão de editar
        System.out.println("objSalvar Reiniciado ====================== ");
        objSalvar = new MembroEmpreendimento();
    }
    
    public void excluirMembroView(){
        bean.excluirBean(membroEmpreendimentoSelecionado);
        membroEmpreendimentoSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
        msgGrowDeleteGeneric();
    }
    
    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new MembroEmpreendimento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarDlgEditar').hide()");
            context.execute("PF('dlgEdicaoPronta').show()");
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
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

    public MembroEmpreendimento getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(MembroEmpreendimento objSalvar) {
        this.objSalvar = objSalvar;
    }

    public boolean isExisteEptBd() {
        return existeEptBd;
    }

    public void setExisteEptBd(boolean existeEptBd) {
        this.existeEptBd = existeEptBd;
    }
}
