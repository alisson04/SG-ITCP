package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
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
@ManagedBean(name = "ListarAtiviPlanejadasView")
@ViewScoped
public class ListarAtiviPlanejadasView implements Serializable {

    private AtividadePlanejada objSelecionado;
    private AtividadePlanejada objSalvar = new AtividadePlanejada();
    private List<Meta> listaSelectOneMenu;
    private List<AtividadePlanejada> listaObjs;
    private List<AtividadePlanejada> listaObjsFiltradas;
    private AtividadePlanejadaBean bean = new AtividadePlanejadaBean();
    private MetaBean metaBean = new MetaBean();

    public ListarAtiviPlanejadasView() {
    }
    
    public void transfereObj(){//Para botão de editar
        objSalvar = objSelecionado;
    }

    public void reiniciaObj(){//Para botão de cadastrar
        System.out.println("objSalvar Reiniciado ====================== ");
        objSalvar = new AtividadePlanejada();
    }

    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new AtividadePlanejada();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
            context.execute("PF('dlgEdicaoPronta').show()");
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar editar a atividade, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void listarView() {
        try {
            listaObjs = bean.listarBean();
            listaSelectOneMenu = metaBean.listarBean();
        } catch (RuntimeException ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar listar as atividades, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void excluirView() {
        try {
            bean.excluirBean(objSelecionado);
            objSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            FacesMessage msg = new FacesMessage("Atividade excluida com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (RuntimeException ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar excluir a atividade, contate o administrador do sistema!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
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

    public AtividadePlanejada getObjSelecionado() {
        return objSelecionado;
    }

    public void setObjSelecionado(AtividadePlanejada objSelecionado) {
        this.objSelecionado = objSelecionado;
    }

    public List<Meta> getListaSelectOneMenu() {
        return listaSelectOneMenu;
    }

    public void setListaSelectOneMenu(List<Meta> listaSelectOneMenu) {
        this.listaSelectOneMenu = listaSelectOneMenu;
    }

    public List<AtividadePlanejada> getListaObjs() {
        return listaObjs;
    }

    public void setListaObjs(List<AtividadePlanejada> listaObjs) {
        this.listaObjs = listaObjs;
    }

    public List<AtividadePlanejada> getListaObjsFiltradas() {
        return listaObjsFiltradas;
    }

    public void setListaObjsFiltradas(List<AtividadePlanejada> listaObjsFiltradas) {
        this.listaObjsFiltradas = listaObjsFiltradas;
    }

    public AtividadePlanejada getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(AtividadePlanejada objSalvar) {
        this.objSalvar = objSalvar;
    }
}
