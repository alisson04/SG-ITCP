package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
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
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatoriosView")
@ViewScoped
public class RelatoriosView extends MensagensGenericas implements Serializable {

    //Empreendimento Vars
    private Empreendimento empreendimentoSelecionado;
    private List<Empreendimento> listaEmpreendimentos;
    private EmpreendimentoBean eptBean;

    //Gráfico Vars
    private LineChartModel graficoAtividades;
    private String categoriaSelecionada;//Para receber o valor que representa a categoria selecionada
    private List<EmpreendimentoIndicador> listaEptIndGrafico;

    //EmpreendimentoIndicador Vars
    private EmpreendimentoIndicadorBean EesIndBean;

    //Indicador Vars
    private GerenciadorIndicadores gerenIndicadores;//Para gerar os indicadores
    private List<Indicador> listaIndicadores;//Para guardar os indicadores que serão usados na tela

    //Atividade Vars
    private List<AtividadePlanejada> listaAtividades;
    private List<AtividadePlanejada> listaAtividadesFiltradas;
    private AtividadePlanejadaBean atividadeBean;

    //Plano de ação
    private List<PlanoAcao> listaPlanos;
    private PlanoAcaoBean planoBean;
    private PlanoAcao planoSelecionado;

    //Usuários
    private List<Usuario> listaUser;
    private UsuarioBean userlBean;
    private Usuario userSelecionado;

    private BarChartModel barModel;

    private PieChartModel graficoAtividadesPlano;
    private PieChartModel graficoMetasPlano;

    private List<Meta> listaMetas;

    //Maturidade vars
    private PieChartModel graficoMaturidade;

    //Construtor
    public RelatoriosView() {
        try {
            InicializaVariaveis();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void InicializaVariaveis() {//Foi separado para ser chamado na tela também
        try {
            eptBean = new EmpreendimentoBean();
            EesIndBean = new EmpreendimentoIndicadorBean();
            listaEmpreendimentos = eptBean.listarBean();

            //Gráfico
            listaEptIndGrafico = new ArrayList();
            categoriaSelecionada = "";//Necessário, não retirar

            //Indicador
            gerenIndicadores = new GerenciadorIndicadores();
            listaIndicadores = gerenIndicadores.listarIndicadores();

            //Atividade Vars
            atividadeBean = new AtividadePlanejadaBean();
            listaAtividades = atividadeBean.listarBean();

            //Plano de ação
            planoBean = new PlanoAcaoBean();
            listaPlanos = planoBean.listarBean();

            //User
            userlBean = new UsuarioBean();
            listaUser = userlBean.listarBean();

            //Meta
            listaMetas = new ArrayList();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS de filtragem de atividadesAtividades
    public void filtraAtividadesPorUser() {
        try {
            listaAtividades = atividadeBean.listarBean();
            List<AtividadePlanejada> listaAux = new ArrayList();

            if (userSelecionado != null) {
                for (int i = 0; i < listaAtividades.size(); i++) {//Roda todas as atividades
                    boolean userIgual = false;
                    for (int x = 0; x < listaAtividades.get(i).getUsuarioList().size(); x++) {//Roda users de cada atividade
                        if (listaAtividades.get(i).getUsuarioList().get(x).equals(userSelecionado)) {
                            userIgual = true;
                        }
                    }
                    if (userIgual == true) {
                        listaAux.add(listaAtividades.get(i));
                    }
                }
                listaAtividades = listaAux;
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void filtraAtividadesPorEes() {
        try {
            //Filtra os planos
            List<PlanoAcao> listaPlanoAux = new ArrayList();
            listaPlanos = planoBean.listarBean();
            for (int i = 0; i < listaPlanos.size(); i++) {
                if (listaPlanos.get(i).getEmpreendimento().equals(empreendimentoSelecionado)) {
                    listaPlanoAux.add(listaPlanos.get(i));
                }
            }
            listaPlanos = listaPlanoAux;

            //Filtra as atividades
            List<AtividadePlanejada> listaAux = new ArrayList();
            if (empreendimentoSelecionado != null) {
                listaAtividades = atividadeBean.listarBean();
                for (int i = 0; i < listaAtividades.size(); i++) {
                    if (listaAtividades.get(i).getMeta().getPlanoAcao().getEmpreendimento().equals(empreendimentoSelecionado)) {
                        listaAux.add(listaAtividades.get(i));
                    }
                }
                listaAtividades = listaAux;
            } else {
                listaAtividades = atividadeBean.listarBean();
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void filtraAtividadesPorPlano() {
        try {
            List<AtividadePlanejada> listaAux = new ArrayList();

            if (planoSelecionado != null) {
                for (int i = 0; i < listaAtividades.size(); i++) {
                    if (listaAtividades.get(i).getMeta().getPlanoAcao().equals(planoSelecionado)) {
                        listaAux.add(listaAtividades.get(i));
                    }
                }
                listaAtividades = listaAux;
            } else {
                listaAtividades = atividadeBean.listarBean();
                filtraAtividadesPorEes();
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS Indicadores
    public void criaGrafico() {//Configurações do gráfico - Acontece ao selecionar uma categoria da aba de indicadores
        try {
            Calendar calendar = Calendar.getInstance();//Pega a data atual
            calendar.add(Calendar.DAY_OF_MONTH, 3);//Adiciona 3 dias - P ficar + bonito no gráfico
            Date dataAtual = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//Cria o formato q data sera mostrada

            graficoAtividades = preencheGraficoLinhas();
            graficoAtividades.setTitle("Evolução dos indicadores da categoria " + categoriaSelecionada);//Titulo do gráfico
            graficoAtividades.setLegendPosition("e");
            graficoAtividades.setAnimate(true);
            graficoAtividades.getAxis(AxisType.Y).setLabel("Nota");//Texto do eixo Y do gráfico
            DateAxis axis = new DateAxis("Data de modificação do indicador");//Texto do eixo X do gráfico
            axis.setTickAngle(-50);
            System.out.println("DATA ATUAL: " + dateFormat.format(dataAtual));
            axis.setMax(dateFormat.format(dataAtual));//Seta a data máxima para ser mostrada no gráfico
            axis.setTickFormat("%#d, %m, 20%y");//Define a ordem dia, mes, ano q é mostrada na parte baixo do gráfico

            graficoAtividades.getAxes().put(AxisType.X, axis);

            if (listaEptIndGrafico.isEmpty()) {
                msgGrowlErroCustomizavel("", "Não há indicadores preenchidos para essa categoria.");
            } else {
                RequestContext.getCurrentInstance().update("frmMsgGenerico");//Atualiza para retirar a msg anterior
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    private LineChartModel preencheGraficoLinhas() {//Preenche as informações do gráfico - É chamado pelo método "Cria Gráfico"
        try {
            listaEptIndGrafico = EesIndBean.listarEesIndisPorcategoriaBean(empreendimentoSelecionado, categoriaSelecionada);
            return EesIndBean.preencheGraficoBean(categoriaSelecionada, listaEptIndGrafico);
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void limpaPainelIndicadores() {//Acontece ao selecionar um empreendimento na lista
        try {
            categoriaSelecionada = "";//Limpa a categoria
            if (empreendimentoSelecionado != null) {
                msgGrowlInfoCustomizavel("", "Agora selecione uma categoria de indicadores.");
            } else {
                msgGrowlInfoCustomizavel("", "Selecione um empreendimento.");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS PIECHART
    public void selecionaEes() {
        try {
            filtraAtividadesPorEes();
            graficoAtividadesPlano = null; //Limpa o gráfico
            graficoAtividadesPlano = null; //Limpa o gráfico
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void criaGraficoPieChatAtiviMetas() {
        try {
            filtraAtividadesPorPlano();
            listaMetas = planoSelecionado.getMetaList();

            if (!listaMetas.isEmpty()) {//Verifica se a metas para o plano
                graficoMetasPlano = new PieChartModel();
                graficoMetasPlano.set("Não iniciadas",
                        (atividadeBean.filtraMetasPorStatus("Não iniciada", listaMetas)).size());
                graficoMetasPlano.set("Iniciadas",
                        (atividadeBean.filtraMetasPorStatus("Iniciada", listaMetas)).size());
                graficoMetasPlano.set("Finalizadas",
                        (atividadeBean.filtraMetasPorStatus("Finalizada", listaMetas)).size());

                graficoMetasPlano.setTitle("Situação atual das metas  do plano de ação");
                graficoMetasPlano.setLegendPosition("e");
                graficoMetasPlano.setFill(false);
                graficoMetasPlano.setShowDataLabels(true);
                graficoMetasPlano.setDiameter(150);
                if (!listaAtividades.isEmpty()) {//Verifica se a atividades para o plano
                    graficoAtividadesPlano = new PieChartModel();
                    graficoAtividadesPlano.set("Não iniciadas",
                            (atividadeBean.filtraAtividadesPorStatus("Não iniciada", listaAtividades)).size());
                    graficoAtividadesPlano.set("Iniciadas",
                            (atividadeBean.filtraAtividadesPorStatus("Iniciada", listaAtividades)).size());
                    graficoAtividadesPlano.set("Finalizadas",
                            (atividadeBean.filtraAtividadesPorStatus("Finalizada", listaAtividades)).size());

                    graficoAtividadesPlano.setTitle("Situação atual das atividades  do plano de ação");
                    graficoAtividadesPlano.setLegendPosition("e");
                    graficoAtividadesPlano.setFill(false);
                    graficoAtividadesPlano.setShowDataLabels(true);
                    graficoAtividadesPlano.setDiameter(150);

                } else {
                    msgGrowlInfoCustomizavel("", "Não há atividades para esse Plano de ação!");
                    graficoAtividadesPlano = null; //Recebe null, pois não a atividades para esse plano
                }
            } else {
                msgGrowlInfoCustomizavel("", "Não há metas cadastradas para esse Plano de ação!");
                graficoMetasPlano = null; //Recebe null, pois não a metas para esse plano
                graficoAtividadesPlano = null; //Recebe null, pois não a atividades para esse plano
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //Metodos maturidade
    public void criaGraficoPieChatMaturidade() {
        try {
            filtraAtividadesPorPlano();
            listaMetas = planoSelecionado.getMetaList();

            if (!listaMetas.isEmpty()) {//Verifica se a metas para o plano
                graficoMetasPlano = new PieChartModel();
                graficoMetasPlano.set("Não iniciadas", 10);
                graficoMetasPlano.set("Iniciadas", 10);
                graficoMetasPlano.set("Finalizadas", 10);

                graficoMetasPlano.setTitle("Situação atual das metas  do plano de ação");
                graficoMetasPlano.setLegendPosition("e");
                graficoMetasPlano.setFill(false);
                graficoMetasPlano.setShowDataLabels(true);
                graficoMetasPlano.setDiameter(150);
            } else {
                msgGrowlInfoCustomizavel("", "Não há metas cadastradas para esse Plano de ação!");
                graficoMetasPlano = null; //Recebe null, pois não a metas para esse plano
                graficoAtividadesPlano = null; //Recebe null, pois não a atividades para esse plano
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS E GETS
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

    public LineChartModel getGraficoAtividades() {
        return graficoAtividades;
    }

    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List<AtividadePlanejada> getListaAtividades() {
        return listaAtividades;
    }

    public void setListaAtividades(List<AtividadePlanejada> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    public List<AtividadePlanejada> getListaAtividadesFiltradas() {
        return listaAtividadesFiltradas;
    }

    public void setListaAtividadesFiltradas(List<AtividadePlanejada> listaAtividadesFiltradas) {
        this.listaAtividadesFiltradas = listaAtividadesFiltradas;
    }

    public PlanoAcao getPlanoSelecionado() {
        return planoSelecionado;
    }

    public void setPlanoSelecionado(PlanoAcao planoSelecionado) {
        this.planoSelecionado = planoSelecionado;
    }

    public List<PlanoAcao> getListaPlanos() {
        return listaPlanos;
    }

    public void setListaPlanos(List<PlanoAcao> listaPlanos) {
        this.listaPlanos = listaPlanos;
    }

    public List<Usuario> getListaUser() {
        return listaUser;
    }

    public void setListaUser(List<Usuario> listaUser) {
        this.listaUser = listaUser;
    }

    public Usuario getUserSelecionado() {
        return userSelecionado;
    }

    public void setUserSelecionado(Usuario userSelecionado) {
        this.userSelecionado = userSelecionado;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getGraficoAtividadesPlano() {
        return graficoAtividadesPlano;
    }

    public PieChartModel getGraficoMetasPlano() {
        return graficoMetasPlano;
    }

    public List<Meta> getListaMetas() {
        return listaMetas;
    }

    public PieChartModel getGraficoMaturidade() {
        return graficoMaturidade;
    }

    public void setGraficoMaturidade(PieChartModel graficoMaturidade) {
        this.graficoMaturidade = graficoMaturidade;
    }
}
