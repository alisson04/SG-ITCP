package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AcompanhamentoEptBean;
import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.UsuarioBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatorioAcompanhamentosView")
@ViewScoped
public class RelatorioAcompanhamentosView extends MensagensGenericas implements Serializable {

    //Acompanhamento VARS
    private AcompanhamentoEptBean bean;
    private List<AcompanhamentoEpt> listaAcom;

    //Datas de filtragem
    private Date dataFiltroInicio;
    private Date dataFiltroFim;

    //Relatorio PDF vars
    private List<String> listaDatasPdf;

    //CONSTRUTOR
    public RelatorioAcompanhamentosView() {
        try {
            bean = new AcompanhamentoEptBean();

            //Filtro de datas na listagem de atividades
            Calendar x = Calendar.getInstance();//Pega a data atual
            x.add((Calendar.DAY_OF_MONTH), -15);//subtrai 15 dias  a data atual
            dataFiltroInicio = x.getTime();//Seta a data atual
            x.add((Calendar.DAY_OF_MONTH), 30);//soma 15 dias a data atual
            dataFiltroFim = x.getTime();//Seta a data somada
            filtrarAcons();//Filtra as atividades
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    //METODOS
    public void filtrarAcons() {
        try {
            //Relatorio PDF vars
            listaDatasPdf = new ArrayList<>();
            
            listaAcom = bean.listarPorIntervaloBean(dataFiltroInicio, dataFiltroFim);
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void gerarRelatorio(String tipo) {//O tipo se refere a "download" ou na "tela"
        try {
            if (!listaAcom.isEmpty()) {                
                bean.gerarRelatorio(dataFiltroInicio, dataFiltroFim, listaDatasPdf, tipo);
            } else {
                msgGrowlErroCustomizavel(null, "Não ha empreendimentos para listar");
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public String converteData(AcompanhamentoEpt a) {//Necesário por não se aceitar "paterrn" no local
        try {
            if (a.getDataAcompanhamento() != null && a.getHoraInicio() != null && a.getHoraFim() != null) {
                SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
                String data = forma.format(a.getDataAcompanhamento());

                SimpleDateFormat forma2 = new SimpleDateFormat("HH:mm");
                String ini = forma2.format(a.getHoraInicio());
                String fim = forma2.format(a.getHoraFim());

                listaDatasPdf.add(data + " das " + ini + " até " + fim);
                return data + " | " + ini + " até " + fim;
            } else {
                listaDatasPdf.add("");
                return "";
            }
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public List<AcompanhamentoEpt> getListaAcom() {
        return listaAcom;
    }

    public void setListaAcom(List<AcompanhamentoEpt> listaAcom) {
        this.listaAcom = listaAcom;
    }

    //SETS E GETS
    public Date getDataFiltroInicio() {
        return dataFiltroInicio;
    }

    public void setDataFiltroInicio(Date dataFiltroInicio) {
        this.dataFiltroInicio = dataFiltroInicio;
    }

    public Date getDataFiltroFim() {
        return dataFiltroFim;
    }

    public void setDataFiltroFim(Date dataFiltroFim) {
        this.dataFiltroFim = dataFiltroFim;
    }
}
