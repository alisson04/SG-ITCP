package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "ListarMetasView")
@ViewScoped
public class ListarMetasView extends MensagensGenericas implements Serializable {

    private Meta metaSelecionada;
    private Meta objSalvar = new Meta();
    private List<PlanoAcao> listaPlanos;
    private List<Meta> listaMetas;
    private List<Meta> listaMetasFiltradas;
    private MetaBean bean = new MetaBean();
    private PlanoAcaoBean planoAcaoBean = new PlanoAcaoBean();
    
    private List<Empreendimento> listaEmpreendimentos;
    private EmpreendimentoBean eptbean = new EmpreendimentoBean();

    public ListarMetasView() {
        try {
            listaEmpreendimentos = eptbean.listarBean();
            listaMetas = bean.listarBean();
            listaPlanos = planoAcaoBean.listarBean();
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

    public void salvarView() {
        try {
            if (objSalvar.getPrazo().before(objSalvar.getPlanoAcao().getDataInicio())) {
                msgPanelErroCustomizavel("Erro na data", "O prazo da meta deve ser maior ou igual a 'data de início' do plano de ação!");
            } else if (objSalvar.getPrazo().after(objSalvar.getPlanoAcao().getDataFim())) {
                msgPanelErroCustomizavel("Erro na data", "O prazo da meta deve ser menor ou igual a 'data de fim' do plano de ação'!");
            } else {
                bean.salvarBean(objSalvar);
                objSalvar = new Meta();
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVarEditarDialog').hide()");
                msgGrowSaveGeneric();
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void transfereObj() {//Para botão de editar
        try {
            objSalvar = metaSelecionada;
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void reiniciaObj() {//Para botão de editar
        try {
            objSalvar = new Meta();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String converteData(Date data) {//Necesário por não se aceitar "paterrn" no local
        try {
            if (data != null) {
                SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
                return forma.format(data);
            } else {
                return "";
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void excluirView() {
        try {
            bean.excluirBean(metaSelecionada);
            metaSelecionada = null;//Volta o usuario para o estado de nulo/ Não retire
            msgGrowDeleteGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS e GETS
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

    public Meta getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(Meta objSalvar) {
        this.objSalvar = objSalvar;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }
}
