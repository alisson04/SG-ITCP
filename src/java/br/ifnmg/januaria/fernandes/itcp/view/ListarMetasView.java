package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ManagedBean(name="ListarMetasView")
@ViewScoped
public class ListarMetasView implements Serializable{
    private Meta metaSelecionada;
    private List<PlanoAcao> listaPlanos;
    private List<Meta> listaMetas;
    private List<Meta> listaMetasFiltradas;
    private MetaBean bean= new MetaBean();
    private PlanoAcaoBean planoAcaoBean = new PlanoAcaoBean();
    
    public ListarMetasView() {
    }
    
    public void editarView() {
        try {
            bean.salvarBean(metaSelecionada);
            metaSelecionada = null;//Volta o usuario para o estado de nulo/ Não retire
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            context.execute("PF('dlgEdicaoPronta').show()");
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Erro inesperado", "Erro ao tentar editar o empreendimento, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void listarView() {
        try {
            listaMetas = bean.listarBean();
            listaPlanos = planoAcaoBean.listarTodosPlanos();
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
    
    public void excluirView(){
        bean.excluirBean(metaSelecionada);
        metaSelecionada = null;//Volta o usuario para o estado de nulo/ Não retire
        FacesMessage msg = new FacesMessage("Meta excluida do sistema");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Meta getMetaSelecionada() {
        return metaSelecionada;
    }

    public void setMetaSelecionada(Meta metaSelecionada) {
        this.metaSelecionada = metaSelecionada;
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
}