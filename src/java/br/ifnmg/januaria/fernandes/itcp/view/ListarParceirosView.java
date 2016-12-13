package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.ParceiroBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarParceirosView")
@ViewScoped
public class ListarParceirosView extends MensagensGenericas implements Serializable {

    private Parceiro parceiroSelecionado;
    private Parceiro objSalvar = new Parceiro();
    private List<Parceiro> listaParceiros;
    private List<Parceiro> listaParceirosFiltrados;
    private ParceiroBean bean = new ParceiroBean();

    public ListarParceirosView() {
        try {
            listaParceiros = bean.listarBean();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public String geraMsgGenericaCampoObrigatorioView() {
        try {
            return msgGenericaCampoObrigatorio();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void transfereObj() {//Para botão de editar
        try {
            objSalvar = parceiroSelecionado;
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void reiniciaObj() {//Para botão de cadastrar
        try {
            objSalvar = new Parceiro();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void salvarView() {
        try {
            bean.salvarBean(objSalvar);
            objSalvar = new Parceiro();
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void excluirParceiroView() {
        try {
            bean.excluirBean(parceiroSelecionado);
            parceiroSelecionado = null;//Volta o usuario para o estado de nulo/ Não retire
            listaParceiros = bean.listarBean();
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
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

    public Parceiro getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(Parceiro objSalvar) {
        this.objSalvar = objSalvar;
    }
}
