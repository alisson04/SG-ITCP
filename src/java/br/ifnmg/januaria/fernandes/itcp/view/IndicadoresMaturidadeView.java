package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@ManagedBean(name = "IndicadoresMaturidadeView")
@ViewScoped
public class IndicadoresMaturidadeView extends MensagensGenericas implements Serializable {

    //Empreendimento Vars
    private Empreendimento empreendimentoSelecionado;
    private List<Empreendimento> listaEmpreendimentos;
    private List<Empreendimento> listaEmpreendimentosFiltrados;
    private EmpreendimentoBean eptBean;

    //EmpreendimentoIndicador Vars
    private List<EmpreendimentoIndicador> listaEptIndSalvar;
    private EmpreendimentoIndicadorBean bean;

    //Indicador Vars
    private GerenciadorIndicadores gerenIndicadores;//Para gerar os indicadores
    private List<Indicador> listaIndicadores;//Para guardar os indicadores que serão usados na tela

    //Construtor
    public IndicadoresMaturidadeView() {
        listaEptIndSalvar = new ArrayList();
        eptBean = new EmpreendimentoBean();
        bean = new EmpreendimentoIndicadorBean();
        listaEmpreendimentos = eptBean.listarBean();

        //Indicador
        gerenIndicadores = new GerenciadorIndicadores();
        listaIndicadores = gerenIndicadores.listarIndicadores();
    }

    //METODOS
    public void liberaPainelIndicadores() {//Acontece ao selecionar um empreendimento na lista
        try {
            listaEptIndSalvar = bean.buscarListaPorCodigoBean(empreendimentoSelecionado);//Pega os inds para o ESS selecionado
            System.out.println("===============================");
            System.out.println("Tamanho é " + listaEptIndSalvar.size());
            for (int i = listaEptIndSalvar.size(); i < listaIndicadores.size(); i++) {
                EmpreendimentoIndicador indAux = new EmpreendimentoIndicador();
                EmpreendimentoIndicadorPK eptIndPk = new EmpreendimentoIndicadorPK();//guarda os IDs
                
                eptIndPk.setIdEmpreendimentoFk(empreendimentoSelecionado.getId());//SETA o empreendimento
                eptIndPk.setIdIndicadorFk(i + 1);//soma com 1 e seta o id
                indAux.setEmpreendimentoIndicadorPK(eptIndPk);//SETA a empreendimento e indicador
                
                listaEptIndSalvar.add(indAux);
            }
            System.out.println("Tamanho agora é: " + listaEptIndSalvar.size());
            System.out.println("===============================");
        } catch (NullPointerException ex) {
            System.out.println("Nenhum indicador para esse EES: " + ex);
        }
    }

    public void adicionaNota(int posicaoIndi) {
        EmpreendimentoIndicador eptInd = new EmpreendimentoIndicador();
        EmpreendimentoIndicadorPK eptIndPk = new EmpreendimentoIndicadorPK();//guarda os IDs

        eptIndPk.setIdEmpreendimentoFk(empreendimentoSelecionado.getId());//SETA o empreendimento
        eptIndPk.setIdIndicadorFk(posicaoIndi + 1);//passa a posição para INT, soma com 1 e seta

        eptInd.setEmpreendimentoIndicadorPK(eptIndPk);//SETA a empreendimento e indicador
        eptInd.setNota(listaEptIndSalvar.get(posicaoIndi).getNota());//Passa a nota para INT e seta

        for (int i = 0; i < listaEptIndSalvar.size(); i++) {
            if (eptIndPk.equals(listaEptIndSalvar.get(i).getEmpreendimentoIndicadorPK())) {
                listaEptIndSalvar.set(i, eptInd);
                System.out.println("===============================");
                System.out.println("Indicador " + (i+1) + " editado");
                System.out.println("TAMANHO: " + listaEptIndSalvar.size());
                System.out.println("===============================");
            }
        }
    }

    public void salvarView() {
        try {
            bean.salvarBean(listaEptIndSalvar);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");//fecha o panel de "formulario de indicadores"
            msgGrowSaveGeneric();
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

    //SETS GETS
    public Empreendimento getEmpreendimentoSelecionado() {
        return empreendimentoSelecionado;
    }

    public void setEmpreendimentoSelecionado(Empreendimento empreendimentoSelecionado) {
        this.empreendimentoSelecionado = empreendimentoSelecionado;
    }

    public List<Empreendimento> getListaEmpreendimentos() {
        return listaEmpreendimentos;
    }

    public void setListaEmpreendimentos(List<Empreendimento> listaEmpreendimentos) {
        this.listaEmpreendimentos = listaEmpreendimentos;
    }

    public List<Empreendimento> getListaEmpreendimentosFiltrados() {
        return listaEmpreendimentosFiltrados;
    }

    public void setListaEmpreendimentosFiltrados(List<Empreendimento> listaEmpreendimentosFiltrados) {
        this.listaEmpreendimentosFiltrados = listaEmpreendimentosFiltrados;
    }

    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List<EmpreendimentoIndicador> getListaEptIndSalvar() {
        return listaEptIndSalvar;
    }

    public void setListaEptIndSalvar(List<EmpreendimentoIndicador> listaEptIndSalvar) {
        this.listaEptIndSalvar = listaEptIndSalvar;
    }
}
