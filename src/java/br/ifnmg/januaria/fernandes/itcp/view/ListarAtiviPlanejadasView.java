package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarAtiviPlanejadasView")
@ViewScoped
public class ListarAtiviPlanejadasView extends MensagensGenericas implements Serializable {
    private AtividadePlanejada objSelecionado;
    private AtividadePlanejada objSalvar = new AtividadePlanejada();
    private AtividadePlanejadaBean bean = new AtividadePlanejadaBean();
    private List<AtividadePlanejada> listaObjs;
    private List<AtividadePlanejada> listaObjsFiltradas;

    private List<PlanoAcao> listaPlanos;
    private PlanoAcao planoSelecionado;
    private PlanoAcaoBean planoBean;

    private List<Meta> listaMetas;

    private MetaBean metaBean = new MetaBean();
    private Meta metaSelecionada;//Recebe a seleção do SelectOneMenu

    private List<Empreendimento> listaEmpreendimentos;
    private EmpreendimentoBean eptBean;
    private Empreendimento eesSelecionado;//Recebe a seleção do SelectOneMenu

    public ListarAtiviPlanejadasView() {
        try {
            planoBean = new PlanoAcaoBean();
            listaPlanos = planoBean.listarBean();

            listaObjs = bean.listarBean();
            listaMetas = metaBean.listarBean();
            metaSelecionada = new Meta();

            eptBean = new EmpreendimentoBean();
            listaEmpreendimentos = eptBean.listarBean();
        } catch (RuntimeException ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            msgPanelErroInesperadoGeneric();
        }
    }
    
    //METODOS
    public String geraMsgGenericaCampoObrigatorioView(){
        return msgGenericaCampoObrigatorio();
    }

    public void filtraPlanosPorEes() {//Acontece ao selecionar um EES
        List<PlanoAcao> listaAux = new ArrayList();

        if (eesSelecionado != null) {
            for (int i = 0; i < listaPlanos.size(); i++) {
                if (listaPlanos.get(i).getEmpreendimento().equals(eesSelecionado)) {
                    listaAux.add(listaPlanos.get(i));
                }
            }
            listaPlanos = listaAux;
        } else {
            listaPlanos = planoBean.listarBean();
        }
    }

    public void filtraMetasPorPlano() {//Acontece ao selecionar um Plano
        List<Meta> listaAux = new ArrayList();
        
        if (planoSelecionado != null) {
            for (int i = 0; i < listaMetas.size(); i++) {
                if (listaMetas.get(i).getPlanoAcao().equals(planoSelecionado)) {
                    listaAux.add(listaMetas.get(i));
                }
            }
            listaMetas = listaAux;
        } else {
            listaMetas = metaBean.listarBean();
        }
    }

    public void transfereObj() {//Para botão de editar
        objSalvar = objSelecionado;
    }

    public void reiniciaObj() {//Para botão de cadastrar
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

    public List<Meta> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(List<Meta> listaMetas) {
        this.listaMetas = listaMetas;
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

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public Meta getMetaSelecionada() {
        return metaSelecionada;
    }

    public void setMetaSelecionada(Meta metaSelecionada) {
        this.metaSelecionada = metaSelecionada;
    }

    public Empreendimento getEesSelecionado() {
        return eesSelecionado;
    }

    public void setEesSelecionado(Empreendimento eesSelecionado) {
        this.eesSelecionado = eesSelecionado;
    }

    public List<PlanoAcao> getListaPlanos() {
        return listaPlanos;
    }

    public void setListaPlanos(List<PlanoAcao> listaPlanos) {
        this.listaPlanos = listaPlanos;
    }

    public PlanoAcao getPlanoSelecionado() {
        return planoSelecionado;
    }

    public void setPlanoSelecionado(PlanoAcao planoSelecionado) {
        this.planoSelecionado = planoSelecionado;
    }
}
