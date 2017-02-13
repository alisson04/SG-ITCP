package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AtividadePlanejadaBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoIndicadorBean;
import br.ifnmg.januaria.fernandes.itcp.bean.HorasTrabalhadasBean;
import br.ifnmg.januaria.fernandes.itcp.bean.NotaMaturidadeBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicadorPK;
import br.ifnmg.januaria.fernandes.itcp.domain.HorasTrabalhadas;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.domain.NotaMaturidade;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
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
@ManagedBean(name = "RelatorioAtividadesView")
@ViewScoped
public class RelatorioAtividadesView extends MensagensGenericas implements Serializable {

    private AtividadePlanejadaBean bean;
    private List<AtividadePlanejada> listaAtv;
    private List<AtividadePlanejada> listaAtvFiltradas;

    //HorasTrabalhadas Vars
    private List<HorasTrabalhadas> listaHorasTrab;
    private HorasTrabalhadasBean horasTrabBean;

    //Relatorio PDF vars
    private List<String> listaHorasPdf;
    private List<String> listaDatasPdf;

    //Filtros Vars
    private List<Usuario> listaUser;
    private UsuarioBean userlBean;
    private Usuario userSelecionado;
    private Empreendimento eesSelecionado;
    private EmpreendimentoBean eesBean;
    private List<Empreendimento> listaEes;

    //CONSTRUTOR
    public RelatorioAtividadesView() {
        try {
            bean = new AtividadePlanejadaBean();
            listaAtv = bean.listarBean();

            //HorasTrabalhadas Vars
            horasTrabBean = new HorasTrabalhadasBean();
            listaHorasTrab = horasTrabBean.listarBean();

            //Relatorio PDF vars
            listaHorasPdf = new ArrayList<>();
            listaDatasPdf = new ArrayList<>();

            //Filtros Vars
            userlBean = new UsuarioBean();
            listaUser = userlBean.listarBean();
            eesBean = new EmpreendimentoBean();
            listaEes = eesBean.listarBean();
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS    
    public void filtrarAtividades() {
        listaAtv = bean.listarBean();

        if (eesSelecionado != null) {//Se a EES selecionado
            List<AtividadePlanejada> listaAux = new ArrayList<>();

            for (int i = 0; i < listaAtv.size(); i++) {
                if (listaAtv.get(i).getMeta().getPlanoAcao().getEmpreendimento().equals(eesSelecionado)) {
                    listaAux.add(listaAtv.get(i));
                }
            }

            listaAtv = listaAux;
        }

        if (userSelecionado != null) {//Se a USER selecionado
            List<AtividadePlanejada> listaAux = new ArrayList<>();
            boolean userParticipa = false;

            for (int i = 0; i < listaAtv.size(); i++) {
                userParticipa = false;
                
                for (int x = 0; x < listaAtv.get(i).getHorasTrabalhadasList().size(); x++) {
                    if (listaAtv.get(i).getHorasTrabalhadasList().get(x).getUsuario().equals(userSelecionado)) {
                        userParticipa = true;

                        System.out.println("Setou TRUE");
                    }
                }

                if (userParticipa) {
                    listaAux.add(listaAtv.get(i));
                    System.out.println("TRUE");
                }
            }
            listaAtv = listaAux;
        }
    }

    public String converteData(AtividadePlanejada atv) {

        Date data;
        String dataRetrun;
        try {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");

            data = atv.getDataInicio();
            dataRetrun = forma.format(data);

            data = atv.getDataFim();
            dataRetrun = dataRetrun + " - " + forma.format(data);

            listaDatasPdf.add(dataRetrun);//Adiciona a lista para gerar o PDF
            return dataRetrun;
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void gerarRelatorio(String tipo) {//O tipo se refere a "download" ou na "tela"
        try {
            if (!listaAtv.isEmpty()) {
                bean.gerarRelatorio(listaAtv, listaHorasPdf, listaDatasPdf, eesSelecionado, userSelecionado, tipo);
            } else {
                msgGrowlErroCustomizavel(null, "Não ha empreendimentos para listar");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String geraHorasTrabPorAtv(AtividadePlanejada atv) {
        try {
            if (!listaHorasTrab.isEmpty()) {//Se não esta vazio
                int segundos = 0;

                HorasTrabalhadas obj;
                for (int i = 0; i < listaHorasTrab.size(); i++) {
                    if (listaHorasTrab.get(i).getAtividadePlanejada().equals(atv)) {
                        obj = listaHorasTrab.get(i);
                        segundos += (int) Math.floor(segundos + (((int) (long) (obj.getHorasFim().getTime() - obj.getHorasInicio().getTime())) / 1000));
                    }
                }

                int minutos = (segundos / 60);
                int horas = minutos / 60;
                int minutosRestantes = minutos % 60;

                if (minutosRestantes == 0
                        || minutosRestantes == 1
                        || minutosRestantes == 2
                        || minutosRestantes == 3
                        || minutosRestantes == 4
                        || minutosRestantes == 5
                        || minutosRestantes == 6
                        || minutosRestantes == 7
                        || minutosRestantes == 8
                        || minutosRestantes == 9) {
                    listaHorasPdf.add(horas + ":0" + minutosRestantes);//Adiciona para ser exportado como PDF
                    return (horas + ":0" + minutosRestantes);
                } else {
                    listaHorasPdf.add(horas + ":" + minutosRestantes);//Adiciona para ser exportado como PDF
                    return (horas + ":" + minutosRestantes);
                }
            } else {
                listaHorasPdf.add("0");//Adiciona para ser exportado como PDF
                return "0";
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //SETS GETS
    public List<AtividadePlanejada> getListaAtv() {
        return listaAtv;
    }

    public void setListaAtv(List<AtividadePlanejada> listaAtv) {
        this.listaAtv = listaAtv;
    }

    public List<AtividadePlanejada> getListaAtvFiltradas() {
        return listaAtvFiltradas;
    }

    public void setListaAtvFiltradas(List<AtividadePlanejada> listaAtvFiltradas) {
        this.listaAtvFiltradas = listaAtvFiltradas;
    }

    public Usuario getUserSelecionado() {
        return userSelecionado;
    }

    public void setUserSelecionado(Usuario userSelecionado) {
        this.userSelecionado = userSelecionado;
    }

    public Empreendimento getEesSelecionado() {
        return eesSelecionado;
    }

    public void setEesSelecionado(Empreendimento eesSelecionado) {
        this.eesSelecionado = eesSelecionado;
    }

    public List<Usuario> getListaUser() {
        return listaUser;
    }

    public void setListaUser(List<Usuario> listaUser) {
        this.listaUser = listaUser;
    }

    public List<Empreendimento> getListaEes() {
        return listaEes;
    }

    public void setListaEes(List<Empreendimento> listaEes) {
        this.listaEes = listaEes;
    }
}
