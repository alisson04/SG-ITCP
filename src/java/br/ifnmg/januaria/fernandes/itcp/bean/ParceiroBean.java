package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.ParceiroDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import br.ifnmg.januaria.fernandes.itcp.util.RelatoriosManager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author alisson
 */
@SessionScoped
@Named
public class ParceiroBean implements Serializable {

    private ParceiroDAO dao;

    //CONSTRUTOR
    public ParceiroBean() {
        dao = new ParceiroDAO();
    }

    //METODOS
    public void gerarRelatorio(List<Parceiro> lista) throws Exception {
        Map<String, Object> listaParametros = new HashMap<String, Object>();
        RelatoriosManager m = new RelatoriosManager<Parceiro>();//O relatorio desde estar dentro de um pacote do projeto
        m.gerarRelatorioGenerico(lista, listaParametros, "/iReport/relatorioParceiros.jasper", "Relatorio-de-Parceiros.pdf");
    }

    public void salvarBean(Parceiro parceiroSalvar) {
        dao.salvarEpt(parceiroSalvar);
    }

    public List<Parceiro> listarBean() {
        return dao.listarTodosParceiros();
    }

    public void excluirBean(Parceiro parceiro) {
        dao.excluirParceiroDao(parceiro);
    }
}
