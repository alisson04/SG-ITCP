package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.bean.NotaMaturidadeBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.apache.commons.codec.binary.Base64;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatorioIndicadoresMaturidadeView")
@ViewScoped
public class RelatorioIndicadoresMaturidadeView extends MensagensGenericas implements Serializable {

    //Empreendimento Vars
    private Empreendimento empreendimentoSelecionado;
    private List<Empreendimento> listaEmpreendimentos;
    private EmpreendimentoBean eptBean;

    //EmpreendimentoIndicador Vars
    private EmpreendimentoIndicadorBean bean;

    //Indicador Vars
    private GerenciadorIndicadores gerenIndicadores;//Para gerar os indicadores
    private List<Indicador> listaIndicadores;//Para guardar os indicadores que serão usados na tela
    private List<LineChartModel> listaGraficoInds;//Lista de gráficos dos inds

    //Gráfico Inds
    List<Indicador> listaTodosInds;
    List<Indicador> listaIndsSelecionados;//Lista de inds que terão gráficos

    //Gráfico Vars
    private String categoriaSelecionada;//Para receber o valor que representa a categoria selecionada

    //Variáveis nota de maturidade
    private NotaMaturidadeBean notaBean;
    private List<NotaMaturidade> listaNotas;

    //CheckBox Indicadores
    private boolean selecionaTodosInds;//Variavel que define que todos os indicadores do check box serão selecionados

    private List<EmpreendimentoIndicador> listaEptIndGrafico;

    //Relatorio PDF vars
    private List<String> listaUsuariosPdf;
    private List<String> listaImagensBase64;
    private List<String> listaWidgetVars;

    //CONSTRUTOR
    public RelatorioIndicadoresMaturidadeView() {
        try {
            bean = new EmpreendimentoIndicadorBean();

            selecionaTodosInds = false;

            //Indicador
            gerenIndicadores = new GerenciadorIndicadores();
            listaIndicadores = gerenIndicadores.listarIndicadores();

            //Gráfico Inds
            listaIndsSelecionados = new ArrayList();
            listaTodosInds = gerenIndicadores.listarIndicadores();//Para ser filtrada na tela - precisa ser exclusiva

            //Gráfico
            listaEptIndGrafico = new ArrayList();
            categoriaSelecionada = "";//Necessário, não retirar

            eptBean = new EmpreendimentoBean();
            listaEmpreendimentos = eptBean.listarBean();

            //Notas de maturidade
            notaBean = new NotaMaturidadeBean();
            listaNotas = notaBean.listarUltimasNotasBean(listaEmpreendimentos);

            //Relatorio PDF vars
            listaUsuariosPdf = new ArrayList<>();
            listaImagensBase64 = new ArrayList<>();
            listaWidgetVars = new ArrayList<>();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public void gerarRelatorio(String tipo) {//O tipo se refere a "download" ou na "tela"
        try {
            if (!listaTodosInds.isEmpty()) {
                if (!listaImagensBase64.isEmpty()) {
                    modeloGeralView m = new modeloGeralView();
                    List<InputStream> listaTeste = new ArrayList<>();

                    boolean aux;//Variável auxiliar que determina se á uma imagem para determina posição da lista de imagens
                    String base64Aux;//Varialvel auxiliar q recebe a correção da base64
                    for (int i = 0; i < listaTodosInds.size(); i++) {//Corrige as bases64 e mantem o objetos vazios

                        for (int x = 0; x < listaWidgetVars.size(); x++) {//Roda alista de WidgetVars
                            if (corrigeWidgetVar(listaWidgetVars.get(x)).equals(i + "")) {//EES com gráfico preenchido
                                base64Aux = m.corrigeImagemBase64(listaImagensBase64.get(i));//Recebe a base corrigida
                                listaImagensBase64.set(i, base64Aux);//Seta a base corrigida
                                listaTeste.add(new ByteArrayInputStream(Base64.decodeBase64(listaImagensBase64.get(i).getBytes())));
                                System.out.println("Adicionou gráfico na posição: " + i);
                            }
                        }
                    }

                    System.out.println("=======================EES: " + listaTodosInds.size());
                    System.out.println("=======================GRA: " + listaTeste.size());

                    bean.gerarRelatorio(empreendimentoSelecionado.getSigla(), categoriaSelecionada, listaTeste, tipo);
                } else {
                    msgGrowlErroCustomizavel(null, "Nenhum lançamento em gráfico para imprimir!");
                }
            } else {
                msgGrowlErroCustomizavel(null, "Nenhum indicador selecionado!");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void selecionaEptTela() {
        selecionaTodosInds = false;//Diz que nenhum ind estaselecionados
    }

    public void selecionaTodosIndsView() {
        listaIndsSelecionados = listaTodosInds;
        criaGraficoInd();
    }

    // Metodos de tela
    public void selecionaCategoriaTela() {
        //Limpa vars
        listaGraficoInds = new ArrayList();//Limpa a lista de gráficos
        listaIndicadores = gerenIndicadores.listarIndicadores();//Pega todos os inds
        listaIndsSelecionados = new ArrayList();//Limpa os inds selecionados
        selecionaTodosInds = false;//Diz que todos os inds não estão selecionados

        //Filtra
        filtraIndsPorCategoria();
    }

    public void filtraIndsPorCategoria() {
        listaTodosInds = gerenIndicadores.listarIndicadoresPorCategoria(categoriaSelecionada);
    }

    //Cria uma lista com os gráficos dos inds selecionados
    public void criaGraficoInd() {
        try {
            listaImagensBase64 = new ArrayList<>();//Limpa lista antes de usar
            listaWidgetVars = new ArrayList<>();//Limpa lista antes de usar

            if (listaTodosInds.size() == listaIndsSelecionados.size()) {
                selecionaTodosInds = true;
            } else {
                selecionaTodosInds = false;
            }
            Calendar calendar = Calendar.getInstance();//Pega a data atual
            calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
            Date dataAtual = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada

            listaGraficoInds = preencheGraficoInd();//Preenche a lista com os gráficos dos inds

            for (int i = 0; i < listaGraficoInds.size(); i++) {
                LineChartModel graficoAux = listaGraficoInds.get(i);
                graficoAux.setLegendPosition("e");
                graficoAux.setAnimate(true);

                graficoAux.getAxis(AxisType.Y).setLabel("Nota");//Texto do eixo Y do gráfico
                DateAxis axis = new DateAxis("");//Texto do eixo X do gráfico
                axis.setTickAngle(-50);
                System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
                axis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
                axis.setTickFormat("%#d, %m, 20%y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

                graficoAux.getAxes().put(AxisType.X, axis);

                listaGraficoInds.set(i, graficoAux);
                System.out.println("QUANTI: " + listaIndsSelecionados.size());
            }

            //AS condições abaixo servem para mostrar uma mensagem caso não aja lançamento para os inds selecionados
            if(listaIndsSelecionados.isEmpty()){
                System.out.println("Nenhum ind selecionado!");
            }else if (listaGraficoInds.isEmpty()) {
                msgPanelErroCustomizavel("Impossível gerar gráficos", "Nenhum desses indicadores tem lançamentos!");
            }

            geraWidgetVars();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    private void geraWidgetVars() {
        for (int i = 0; i < listaIndsSelecionados.size(); i++) {//Roda a quantidade de indicadores da categoria

            int verificador = 0;
            for (int x = 0; x < listaEptIndGrafico.size(); x++) {
                if (listaIndsSelecionados.get(i).getId().equals(
                        listaEptIndGrafico.get(x).getEmpreendimentoIndicadorPK().getIdIndicador())) {
                    verificador = 1;
                }
            }

            if (verificador != 0) {
                listaWidgetVars.add("WidgetVar" + i);
                listaImagensBase64.add("");//Para cada EES existente, uma widgetVar é criada e adicionada
                System.out.println("Criou a WV " + i);
            } else {
                listaImagensBase64.add("");//Para cada EES existente, uma widgetVar é criada e adicionada
                System.out.println("Não adicionou o indicador " + listaIndsSelecionados.get(i).getNome() + " ao gráfico!");
            }
        }
    }

    public void geraImagemBase64() {//Gera e seta imagens nas variaveis
        for (int i = 0; i < listaWidgetVars.size(); i++) {
            //1º parametro é o gráfico - 2º é o receptor da base64
            RequestContext.getCurrentInstance().execute("exportImageToPdfGeneric('" + listaWidgetVars.get(i)
                    + "', '#frmrelatorios\\\\:gridInIndicadoresMaturidade\\\\:"
                    + corrigeWidgetVar(listaWidgetVars.get(i)) + "\\\\:in');");
        }
    }

    private String corrigeWidgetVar(String widgetVar) {
        int i = widgetVar.indexOf("r");//Utiliza a letra r pq é a ultima do nome que é dado a widgetVar dos gráficos
        return widgetVar.substring(i + 1);
    }

    //Preenche uma lista com os gráficos dos inds selecionados
    private List<LineChartModel> preencheGraficoInd() {//Preenche as informações do gráfico - É chamado pelo método "Cria Gráfico"
        try {
            System.out.println("QUANTI: " + listaIndsSelecionados.size());
            listaEptIndGrafico = bean.listarEesIndisPorcategoriaBean(empreendimentoSelecionado, categoriaSelecionada);
            return bean.preencheGraficoIndBean(listaIndsSelecionados, listaEptIndGrafico);
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

    public String getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(String categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List<Indicador> getListaIndsSelecionados() {
        return listaIndsSelecionados;
    }

    public void setListaIndsSelecionados(List<Indicador> listaIndsSelecionados) {
        this.listaIndsSelecionados = listaIndsSelecionados;
    }

    public List<Indicador> getListaTodosInds() {
        return listaTodosInds;
    }

    public void setListaTodosInds(List<Indicador> listaTodosInds) {
        this.listaTodosInds = listaTodosInds;
    }

    public List<NotaMaturidade> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<NotaMaturidade> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public List<LineChartModel> getListaGraficoInds() {
        return listaGraficoInds;
    }

    public void setListaGraficoInds(List<LineChartModel> listaGraficoInds) {
        this.listaGraficoInds = listaGraficoInds;
    }

    public boolean isSelecionaTodosInds() {
        return selecionaTodosInds;
    }

    public void setSelecionaTodosInds(boolean selecionaTodosInds) {
        this.selecionaTodosInds = selecionaTodosInds;
    }

    public List<EmpreendimentoIndicador> getListaEptIndGrafico() {
        return listaEptIndGrafico;
    }

    public void setListaEptIndGrafico(List<EmpreendimentoIndicador> listaEptIndGrafico) {
        this.listaEptIndGrafico = listaEptIndGrafico;
    }

    public List<String> getListaImagensBase64() {
        return listaImagensBase64;
    }

    public void setListaImagensBase64(List<String> listaImagensBase64) {
        this.listaImagensBase64 = listaImagensBase64;
    }

    public List<String> getListaWidgetVars() {
        return listaWidgetVars;
    }

    public void setListaWidgetVars(List<String> listaWidgetVars) {
        this.listaWidgetVars = listaWidgetVars;
    }
}
