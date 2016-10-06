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
    private String[] notas;//Nota que virá da tela e será setada no EmpreendimentoIndicador

    //Construtor
    public IndicadoresMaturidadeView() {
        listaEptIndSalvar = new ArrayList();
        eptBean = new EmpreendimentoBean();
        bean = new EmpreendimentoIndicadorBean();
        listaEmpreendimentos = eptBean.listarBean();

        //Indicador
        gerenIndicadores = new GerenciadorIndicadores();
        listaIndicadores = gerenIndicadores.listarIndicadores();

        notas = new String[48];//Lista de indicadores para a tela
    }

    public void liberaPainelIndicadores() {
        try {
            //Pega a lista de inds para o EPT selecionado
            listaEptIndSalvar = bean.buscarListaPorCodigoBean(empreendimentoSelecionado);
            System.out.println("===============================");
            for (int i = 0; i < listaEptIndSalvar.size(); i++) {
                notas[i] = (String.valueOf(listaEptIndSalvar.get(i).getNota()));
                System.out.println("ID EES: " + listaEptIndSalvar.get(i).getEmpreendimentoIndicadorPK().getIdEmpreendimentoFk());
                System.out.println("ID IND: " + listaEptIndSalvar.get(i).getEmpreendimentoIndicadorPK().getIdIndicadorFk());
            }
            System.out.println("===============================");
        } catch (NullPointerException ex) {
            System.out.println("Nenhum indicador para esse EES: " + ex);
        }
    }

    //METODOS
    public void adicionaNota(String posicaoIndi) {
        EmpreendimentoIndicador eptInd = new EmpreendimentoIndicador();
        EmpreendimentoIndicadorPK eptIndPk = new EmpreendimentoIndicadorPK();

        eptIndPk.setIdEmpreendimentoFk(empreendimentoSelecionado.getId());//SETA o empreendimento
        eptIndPk.setIdIndicadorFk(Integer.parseInt(posicaoIndi) + 1);//passa a posição para INT, soma com 1 e seta

        eptInd.setEmpreendimentoIndicadorPK(eptIndPk);//SETA a empreendimento e indicador
        eptInd.setNota(Integer.parseInt(notas[Integer.parseInt(posicaoIndi)]));//Passa a nota para INT e seta

        boolean editando = false;
        for (int i = 0; i < listaEptIndSalvar.size(); i++) {
            if (eptIndPk.equals(listaEptIndSalvar.get(i).getEmpreendimentoIndicadorPK())) {
                listaEptIndSalvar.set(i, eptInd);
                editando = true;
                System.out.println("===============================");
                System.out.println("1 EDITADO");
                System.out.println("TAMANHO: " + listaEptIndSalvar.size());
                System.out.println("===============================");
            }
        }

        if (!editando) {//
            listaEptIndSalvar.add(eptInd);//ADICIONA o empreendimentoIndicador a lista para ser salvo depois
            System.out.println("===============================");
            System.out.println("1 ADICIONADO");
            System.out.println("TAMANHO: " + listaEptIndSalvar.size());
            System.out.println("===============================");
        }
    }

    public void salvarView() {
        try {
            bean.salvarBean(listaEptIndSalvar);

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");
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

    public String[] getNotas() {
        return notas;
    }

    public void setNotas(String[] notas) {
        this.notas = notas;
    }

    public List<EmpreendimentoIndicador> getListaEptIndSalvar() {
        return listaEptIndSalvar;
    }

    public void setListaEptIndSalvar(List<EmpreendimentoIndicador> listaEptIndSalvar) {
        this.listaEptIndSalvar = listaEptIndSalvar;
    }
}
