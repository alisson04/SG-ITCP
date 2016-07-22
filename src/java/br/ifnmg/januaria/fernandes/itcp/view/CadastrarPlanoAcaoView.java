package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MetaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
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
import org.primefaces.event.FlowEvent;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastrarPlanoAcaoView")
@ViewScoped
public class CadastrarPlanoAcaoView implements Serializable {

    PlanoAcaoBean bean = new PlanoAcaoBean();
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    MetaBean metaBean = new MetaBean();

    private PlanoAcao objSalvar = new PlanoAcao();
    private Meta metaSalvar = new Meta();
    private Meta metaSelecionada = new Meta();

    private AtividadePlanejada atividadeSalvar = new AtividadePlanejada();
    private AtividadePlanejada atividadeSelecionada = new AtividadePlanejada();

    private List<Empreendimento> listaEmpreendimentos;
    private List<Meta> listaSalvarMetas = new ArrayList();
    private List<AtividadePlanejada> listaSalvarAtividades = new ArrayList();

    int contador = 0;

    private boolean skip;

    public void adicionarAtividade() {
        boolean existe = false;
        for (int i = 0; i < listaSalvarAtividades.size(); i++) {
            if (listaSalvarAtividades.get(i).getNome().equals(atividadeSalvar.getNome())) {
                System.err.println("IF Tamanho das atividades: " + listaSalvarAtividades.size());
                existe = true;
                break;
            }
        }

        if (existe == false) {
            contador = contador + 1;
            atividadeSalvar.setId(contador);//Atribui um ID ficticio só para ser usado na tela
            listaSalvarAtividades.add(atividadeSalvar);
            System.err.println("Tamanho das atividades: " + listaSalvarAtividades.size());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarAtividadeDialog').hide()");
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERRO", "Já existe uma atividade com esse nome!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void adicionarMeta() {
        boolean existe = false;
        for (int i = 0; i < listaSalvarMetas.size(); i++) {
            if (listaSalvarMetas.get(i).getNome().equals(metaSalvar.getNome())) {
                System.err.println("IF Tamanho das metas: " + listaSalvarMetas.size());
                existe = true;
                break;
            }
        }

        if (existe == false) {
            contador = contador + 1;
            metaSalvar.setIdMeta(contador);//Atribui um ID ficticio só para ser usado na tela
            listaSalvarMetas.add(metaSalvar);
            System.err.println("Tamanho das metas: " + listaSalvarMetas.size());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERRO", "Já existe uma meta com esse nome!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public CadastrarPlanoAcaoView() {
        try {
            listaEmpreendimentos = empreendimentoBean.listarBean();
        } catch (RuntimeException ex) {
            System.out.println("BEAN(ListarEmpreendimentosView): Erro ao Carregar lista de Planos: " + ex);
        }
    }

    public void transfereObj(String obj) {//Para botão de editar
        if (obj.equals("atividade")) {
            atividadeSalvar = atividadeSelecionada;
        } else {
            metaSalvar = metaSelecionada;
        }
    }

    public void reiniciaObj(String obj) {//Para botão de cadastrar
        if (obj.equals("atividade")) {
            atividadeSalvar = new AtividadePlanejada();
        } else {
            metaSalvar = new Meta();
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            System.err.println("IF===============");
            return "confirm";
        } else {
            System.err.println("ELSE===============");
            return event.getNewStep();
        }
    }

    public void salvarView() {
        try {

            for (int i = 0; i < listaSalvarMetas.size(); i++) {
                List<AtividadePlanejada> listaAuxAtividades = new ArrayList();
                for (int x = 0; x < listaSalvarAtividades.size(); x++) {
                    System.err.println("===============ID META: " + listaSalvarMetas.get(i).getIdMeta());
                    System.err.println("===============ID META ATI: " + listaSalvarAtividades.get(x).getMeta().getIdMeta());
                    if (listaSalvarMetas.get(i).getIdMeta().equals(listaSalvarAtividades.get(x).getMeta().getIdMeta())) {
                        listaAuxAtividades.add(listaSalvarAtividades.get(x));
                        System.err.println("IF número " + i);
                    }
                    System.err.println("Rodando atividade " + x);
                }
                listaSalvarMetas.get(i).setAtividadePlanejadaList(listaAuxAtividades);
                System.err.println("Rodando meta " + i);
            }
            
            objSalvar.setMetaList(listaSalvarMetas);//Setando a lista de metas no plano
            
            for (int i = 0; i < objSalvar.getMetaList().size(); i++) {//seta os IDs de metas e atividades como null
                objSalvar.getMetaList().get(i).setIdMeta(null);
                for (int x = 0; x < objSalvar.getMetaList().get(i).getAtividadePlanejadaList().size(); x++) {
                    objSalvar.getMetaList().get(i).getAtividadePlanejadaList().get(x).setId(null);
                }
            }

            bean.salvarBean(objSalvar);
            objSalvar = new PlanoAcao();
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com sucesso!", "Todas as informações foram salvas com sucesso!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
            Logger.getLogger(ListarPlanoAcaoView.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro inesperado", "Erro ao tentar salvar, contate o administrador do sistema!");
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
    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public PlanoAcao getObjSalvar() {
        return objSalvar;
    }

    public void setObjSalvar(PlanoAcao objSalvar) {
        this.objSalvar = objSalvar;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Meta getMetaSalvar() {
        return metaSalvar;
    }

    public void setMetaSalvar(Meta metaSalvar) {
        this.metaSalvar = metaSalvar;
    }

    public AtividadePlanejada getAtividadeSalvar() {
        return atividadeSalvar;
    }

    public void setAtividadeSalvar(AtividadePlanejada atividadeSalvar) {
        this.atividadeSalvar = atividadeSalvar;
    }

    public Meta getMetaSelecionada() {
        return metaSelecionada;
    }

    public void setMetaSelecionada(Meta metaSelecionada) {
        this.metaSelecionada = metaSelecionada;
    }

    public List<Meta> getListaSalvarMetas() {
        return listaSalvarMetas;
    }

    public void setListaSalvarMetas(List<Meta> listaSalvarMetas) {
        this.listaSalvarMetas = listaSalvarMetas;
    }

    public List<AtividadePlanejada> getListaSalvarAtividades() {
        return listaSalvarAtividades;
    }

    public void setListaSalvarAtividades(List<AtividadePlanejada> listaSalvarAtividades) {
        this.listaSalvarAtividades = listaSalvarAtividades;
    }

    public AtividadePlanejada getAtividadeSelecionada() {
        return atividadeSelecionada;
    }

    public void setAtividadeSelecionada(AtividadePlanejada atividadeSelecionada) {
        this.atividadeSelecionada = atividadeSelecionada;
    }
}
