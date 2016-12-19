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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;

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

    //Gráfico Vars
    private LineChartModel lineModel1;
    private String categoriaSelecionada;//Para receber o valor que representa a categoria selecionada
    private List<EmpreendimentoIndicador> listaEptIndGrafico;

    private TabView tabview;

    //Construtor
    public IndicadoresMaturidadeView() {
        try {
            listaEptIndSalvar = new ArrayList();
            eptBean = new EmpreendimentoBean();
            bean = new EmpreendimentoIndicadorBean();
            listaEmpreendimentos = eptBean.listarBean();

            //Indicador
            gerenIndicadores = new GerenciadorIndicadores();
            listaIndicadores = gerenIndicadores.listarIndicadores();

            //Gráfico
            listaEptIndGrafico = new ArrayList();
            categoriaSelecionada = "";//Necessário, não retirar
            tabview = new TabView();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS    
    public void criaGrafico() {//Configurações do gráfico - Acontece ao selecionar uma categoria
        try {
            Calendar calendar = Calendar.getInstance();//Pega a data atual
            calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
            Date dataAtual = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada

            lineModel1 = preencheGrafico();
            lineModel1.setTitle("Evolução dos indicadores por categoria");//Titulo do gráfico
            lineModel1.setLegendPosition("e");
            lineModel1.setAnimate(true);

            lineModel1.getAxis(AxisType.Y).setLabel("Nota");//Texto do eixo Y do gráfico
            DateAxis axis = new DateAxis("Data de modificação do indicador");//Texto do eixo X do gráfico
            axis.setTickAngle(-50);
            System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
            axis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
            axis.setTickFormat("%#d %b, %y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

            lineModel1.getAxes().put(AxisType.X, axis);

            configuraCategoria();//SETA a Tab que deve estar ativa de acordo com categoria selecionada

            if (listaEptIndGrafico.isEmpty()) {
                msgGrowlErroCustomizavel("", "Não há indicadores preenchidos para essa categoria.");
            } else {
                RequestContext.getCurrentInstance().update("frmComponentesGenerico");//Atualiza para retirar a msg anterior
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    private LineChartModel preencheGrafico() {//Preenche as informações do gráfico - É chamado pelo método "Cria Gráfico"
        try {
            listaEptIndGrafico = bean.listarEesIndisPorcategoriaBean(empreendimentoSelecionado, categoriaSelecionada);
            return bean.preencheGraficoBean(categoriaSelecionada, listaEptIndGrafico);
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    private void configuraCategoria() {//SETA a Tab que deve estar ativa de acordo com categoria selecionada
        try {
            if (categoriaSelecionada.equals(listaIndicadores.get(0).getCategoria())) {
                tabview.setActiveIndex(0);//Ativa a TAB de sua categoria
                System.out.println("0 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(6).getCategoria())) {
                tabview.setActiveIndex(1);//Ativa a TAB de sua categoria
                System.out.println("1 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(10).getCategoria())) {
                tabview.setActiveIndex(2);//Ativa a TAB de sua categoria
                System.out.println("2 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(16).getCategoria())) {
                tabview.setActiveIndex(3);//Ativa a TAB de sua categoria
                System.out.println("3 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(24).getCategoria())) {
                tabview.setActiveIndex(4);//Ativa a TAB de sua categoria
                System.out.println("4 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(31).getCategoria())) {
                tabview.setActiveIndex(5);//Ativa a TAB de sua categoria
                System.out.println("5 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(36).getCategoria())) {
                tabview.setActiveIndex(6);//Ativa a TAB de sua categoria
                System.out.println("6 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(42).getCategoria())) {
                tabview.setActiveIndex(7);//Ativa a TAB de sua categoria
                System.out.println("7 Tab setada");
            } else if (categoriaSelecionada.equals(listaIndicadores.get(46).getCategoria())) {
                tabview.setActiveIndex(8);//Ativa a TAB de sua categoria
                System.out.println("8 Tab setada");
            } else {
                msgPanelErroInesperadoGeneric();
                System.out.println("Estranho, nenhuma tab setada");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void liberaPainelIndicadores() {//Acontece ao selecionar um empreendimento na lista
        try {
            if (empreendimentoSelecionado != null) {
                Date dataAtual = new Date();
                listaEptIndSalvar = bean.buscarListaPorCodigoBean(empreendimentoSelecionado);//Pega os inds para o ESS selecionado
                List<EmpreendimentoIndicador> listaEptIndAux = new ArrayList();//Lista auxiliar

                System.out.println("===============================");
                System.out.println("Quantidade de inds Cadastrados para esse EES: " + listaEptIndSalvar.size());

                for (int i = 0; i < listaIndicadores.size(); i++) {//Roda o total de indicaroes possiveis
                    EmpreendimentoIndicador indAux = new EmpreendimentoIndicador();
                    EmpreendimentoIndicadorPK EptIndPK = new EmpreendimentoIndicadorPK();
                    EptIndPK.setDataNota(dataAtual);//SETA a data
                    EptIndPK.setIdEmpreendimentoFk(empreendimentoSelecionado.getId());//SETA o empreendimento
                    EptIndPK.setIdIndicador(i + 1);//SETA o indicador

                    indAux.setEmpreendimentoIndicadorPK(EptIndPK);//SETA as chaves
                    listaEptIndAux.add(indAux);//Adiciona o obj a lista p ser usado na tela e talvez salvo
                }
                System.out.println("Lista aux: " + listaEptIndAux.size());

                for (int i = 0; i < listaIndicadores.size(); i++) {//Roda o total de indicaroes possiveis
                    for (int x = 0; x < listaEptIndSalvar.size(); x++) {//Roda os inds Pegos do BD
                        if (listaEptIndSalvar.get(x).getEmpreendimentoIndicadorPK().getIdIndicador() == i + 1) {
                            listaEptIndAux.set(i, listaEptIndSalvar.get(x));
                        }
                    }
                }
                System.out.println("Lista Salvar oringin: " + listaEptIndSalvar.size());
                System.out.println("Lista aux: " + listaEptIndAux.size());

                listaEptIndSalvar = listaEptIndAux;
                categoriaSelecionada = "";//Limpa a categoria
                listaEptIndGrafico = new ArrayList();//Limpa o gráfico
                tabview.setActiveIndex(0);//Volta para a primeira TAB do formulario da tela
                msgGrowlInfoCustomizavel("", "Agora selecione uma categoria de indicadores.");
            } else {
                System.out.println("ERRO CRITICO NA PASSAGEM DE EES");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void adicionaNota(int posicaoIndi) {
        try {
            EmpreendimentoIndicador eptIndAux;//Cria este para guardar a nota
            EmpreendimentoIndicadorPK EptIndPK;//Criar esta para receber a data nova
            Date x = new Date();//Cria a nova data
            EptIndPK = listaEptIndSalvar.get(posicaoIndi).getEmpreendimentoIndicadorPK();//SETA a chave
            eptIndAux = listaEptIndSalvar.get(posicaoIndi);
            EptIndPK.setDataNota(x);//SETA a data atual
            eptIndAux.setEmpreendimentoIndicadorPK(EptIndPK);
            listaEptIndSalvar.set(posicaoIndi, eptIndAux);//Coloca na lista com informações atualizadas
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void salvarView() {
        try {
            bean.salvarBean(listaEptIndSalvar);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wVarEditarDialog').hide()");//fecha o panel de "formulario de indicadores"
            if (!categoriaSelecionada.equals("")) {//Caso algum gráfico esteja sendo mostrado na tela durante o salvamento
                criaGrafico();//Chama o gráfico para atualização
            }
            msgGrowSaveGeneric();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String conveteData(Date data) {
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

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public String getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(String categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public List<EmpreendimentoIndicador> getListaEptIndGrafico() {
        return listaEptIndGrafico;
    }

    public void setListaEptIndGrafico(List<EmpreendimentoIndicador> listaEptIndGrafico) {
        this.listaEptIndGrafico = listaEptIndGrafico;
    }

    public TabView getTabview() {
        return tabview;
    }

    public void setTabview(TabView tabview) {
        this.tabview = tabview;
    }
}
