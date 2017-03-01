package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.AcompanhamentoEptBean;
import br.ifnmg.januaria.fernandes.itcp.domain.AcompanhamentoEpt;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.util.MensagensGenericas;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "RelatoriosGeraisView")
@ViewScoped
public class RelatoriosGeraisView extends MensagensGenericas implements Serializable {

    //Datas de filtragem
    private Date dataFiltroInicio;
    private Date dataFiltroFim;

    private String nomeRelatorioGenerico;
    private String caminhoRelatorioGenerico;
    private int op;

    //CONSTRUTOR
    public RelatoriosGeraisView() {
        try {
            //Filtro de datas na listagem de atividades
            Calendar x = Calendar.getInstance();//Pega a data atual
            x.add((Calendar.DAY_OF_MONTH), -15);//subtrai 15 dias  a data atual
            dataFiltroInicio = x.getTime();//Seta a data atual
            x.add((Calendar.DAY_OF_MONTH), 30);//soma 15 dias a data atual
            dataFiltroFim = x.getTime();//Seta a data somada
            filtrarAcompaPorData();//Filtra as atividades
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void setaCaminhoNome() {
        if (op == 1) {
            nomeRelatorioGenerico = "Contatos-dos-usuarios.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioContatosUsers.jasper";
        } else if (op == 2) {
            nomeRelatorioGenerico = "Contatos-dos-ees.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioContatosEes.jasper";
        } else if (op == 3) {
            nomeRelatorioGenerico = "Contatos-dos-membros-dos-ees.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioContatosMembrosEes.jasper";
        } else if (op == 4) {
            nomeRelatorioGenerico = "Contatos-dos-parceiros.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioContatosParceiros.jasper";
        } else if (op == 5) {
            nomeRelatorioGenerico = "Lista-de-usuarios.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioUsuarios.jasper";
        } else if (op == 6) {
            nomeRelatorioGenerico = "Horarios-de-trabalho.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioHorariosTrabalho.jasper";
        } else if (op == 7) {
            nomeRelatorioGenerico = "Lista-de-empreendimentos.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioEmpreendimentos.jasper";
        } else if (op == 8) {
            nomeRelatorioGenerico = "Lista-de-membros-de-ees.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioMembrosEes.jasper";
        } else if (op == 9) {
            nomeRelatorioGenerico = "Lista-de-parceiros.pdf";
            caminhoRelatorioGenerico = "/iReport/relatorioParceiros.jasper";
        } else {
            nomeRelatorioGenerico = "";
            caminhoRelatorioGenerico = "";
        }

    }

    //METODOS
    public void filtrarAcompaPorData() {//Filtra as atividades
        AcompanhamentoEptBean bean = new AcompanhamentoEptBean();
        List<AcompanhamentoEpt> listaEes = bean.listarPorIntervaloBean(dataFiltroInicio, dataFiltroFim);

    }

    public void gerarRelatorioAcompa(String caminho, String nome, String tipo) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();

        listaParametros.put("dataInicio", dataFiltroInicio);
        listaParametros.put("dataFim", dataFiltroFim);

        RelatoriosManager m = new RelatoriosManager<Empreendimento>();
        m.gerarRelatorioGenericoSemDataSource(listaParametros, caminho,
                nome, tipo);
    }

    public void gerarRelatorioGenerico(String caminho, String nome, String tipo) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();

        RelatoriosManager m = new RelatoriosManager<Empreendimento>();
        m.gerarRelatorioGenericoSemDataSource(listaParametros, caminho,
                nome, tipo);
    }

    //SETS GETS
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

    public String getNomeRelatorioGenerico() {
        return nomeRelatorioGenerico;
    }

    public void setNomeRelatorioGenerico(String nomeRelatorioGenerico) {
        this.nomeRelatorioGenerico = nomeRelatorioGenerico;
    }

    public String getCaminhoRelatorioGenerico() {
        return caminhoRelatorioGenerico;
    }

    public void setCaminhoRelatorioGenerico(String caminhoRelatorioGenerico) {
        this.caminhoRelatorioGenerico = caminhoRelatorioGenerico;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
}
