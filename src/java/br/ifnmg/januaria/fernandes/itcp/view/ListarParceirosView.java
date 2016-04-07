package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.dao.ParceiroDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Parceiro;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name="ListarParceirosView")
@ViewScoped
public class ListarParceirosView {
    private List<Parceiro> listaParceiros; //    
    
    public void listarTodosParceiros() {
        System.out.println("BEAN(ListarEmpreendimentosView): listarTodosEmpreendimentos: ");
        try {
            ParceiroDAO parceiroDAO = new ParceiroDAO();
            listaParceiros = parceiroDAO.listarTodosParceiros();
        } catch (RuntimeException ex) {
            //FacesUtil.adicionarMsgErro("Erro ao carregar pesquisa:" + ex.getMessage());
            System.out.println("VIEW(listarTodosParceiros): Erro ao Carregar lista de Parceiros: " + ex);
        }
    }

    public List<Parceiro> getListaParceiros() {
        return listaParceiros;
    }

    public void setListaParceiros(List<Parceiro> listaParceiros) {
        this.listaParceiros = listaParceiros;
    }
    
}
