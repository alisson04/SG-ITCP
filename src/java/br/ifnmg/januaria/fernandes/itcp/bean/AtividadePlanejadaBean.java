package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.AtividadePlanejadaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class AtividadePlanejadaBean implements Serializable {

    private AtividadePlanejadaDAO dao;

    public AtividadePlanejadaBean() {
        dao = new AtividadePlanejadaDAO();
    }

    //Salva uma atividade
    public AtividadePlanejada salvarBean(AtividadePlanejada obj) {
        if (obj.getId() == null) {
            obj.setStatus("Não iniciada");//Seta o status
        }

        return dao.salvarDao(obj);
    }

    //Lita todas as atividades
    public List<AtividadePlanejada> listarBean() {
        return dao.listarDao();
    }

    //Exclui uma atividade
    public void excluirBean(AtividadePlanejada obj) {
        dao.excluirDao(obj);
    }
    
    //lista as ativiades por um intervalo de datas
    public List<AtividadePlanejada> listarPorIntervaloBean(Date dataIni, Date dataFim) {
        return dao.listarPorIntervaloDao(dataIni, dataFim);
    }

    public List<AtividadePlanejada> buscarAtividadesPorMetaBean(Meta obj) {
        return dao.buscarAtividadesPorMetaDao(obj);
    }

    //Filtra atividade por Status
    public List<AtividadePlanejada> filtraAtividadesPorStatus(String status, List<AtividadePlanejada> listaAtv) {
        List<AtividadePlanejada> listaFiltrada;
        listaFiltrada = new ArrayList();

        for (int i = 0; i < listaAtv.size(); i++) {
            if (listaAtv.get(i).getStatus().equals(status)) {
                listaFiltrada.add(listaAtv.get(i));
            }
        }
        return listaFiltrada;
    }

    //Filtra metas por Status
    public List<Meta> filtraMetasPorStatus(String status, List<Meta> listaAtv) {
        List<Meta> listaFiltrada;
        listaFiltrada = new ArrayList();

        for (int i = 0; i < listaAtv.size(); i++) {
            if (listaAtv.get(i).getStatus().equals(status)) {
                listaFiltrada.add(listaAtv.get(i));
            }
        }
        return listaFiltrada;
    }

    public void gerarRelatorio(int totalMinutosGastos, String datas, List<AtividadePlanejada> lista, List<String> listaHorasPdf, 
            List<String> listaDatasPdf, Empreendimento eesSelecionado, Usuario userSelecionado, String tipo) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();
        //Total de horas gastos
        int horas = totalMinutosGastos / 60;
        int minutosRestantes = totalMinutosGastos % 60;

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
            listaParametros.put("paramTotal", (horas + ":0" + minutosRestantes));
        } else {
            listaParametros.put("paramTotal", (horas + ":" + minutosRestantes));
        }
        
        //Filtro de datas
        listaParametros.put("paramTempo", datas);
        
        //Lista de nomes e Status e EES
        List<String> listaNomes = new ArrayList<>();
        List<String> listaStatus = new ArrayList<>();

        if (eesSelecionado == null) {
            for (int i = 0; i < lista.size(); i++) {
                listaNomes.add(lista.get(i).getMeta().getPlanoAcao().getEmpreendimento().getSigla() + " - " + lista.get(i).getNome());
                listaStatus.add(lista.get(i).getStatus());
            }
            listaParametros.put("paramEes", "Sem filtro");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                listaNomes.add(lista.get(i).getNome());
                listaStatus.add(lista.get(i).getStatus());
            }
            listaParametros.put("paramEes", eesSelecionado.getSigla());
        }
        
        //USER
        if(userSelecionado == null){
            listaParametros.put("paramUser", "Sem filtro");
        }else{
            listaParametros.put("paramUser", userSelecionado.getNome());
        }

        listaParametros.put("listaStatus", listaStatus);
        listaParametros.put("listaNomes", listaNomes);
        listaParametros.put("listaHoras", listaHorasPdf);
        listaParametros.put("listaDatas", listaDatasPdf);

        RelatoriosManager m = new RelatoriosManager<Empreendimento>();
        m.gerarRelatorioGenerico(lista, listaParametros, "/iReport/relatorioAtividades.jasper",
                "Relatorio-de-Atividades.pdf", tipo);
    }
}
