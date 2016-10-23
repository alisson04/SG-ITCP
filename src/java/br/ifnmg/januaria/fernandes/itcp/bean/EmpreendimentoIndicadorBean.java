package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoIndicadorDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.EmpreendimentoIndicador;
import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import br.ifnmg.januaria.fernandes.itcp.util.GerenciadorIndicadores;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class EmpreendimentoIndicadorBean implements Serializable {

    private EmpreendimentoIndicadorDAO dao;
    private GerenciadorIndicadores gerenIndicadores;

    public EmpreendimentoIndicadorBean() {
        dao = new EmpreendimentoIndicadorDAO();
        gerenIndicadores = new GerenciadorIndicadores();
    }

    public void salvarBean(List<EmpreendimentoIndicador> listaEptInd) {
        List<EmpreendimentoIndicador> listaRetiradoNulls;
        listaRetiradoNulls = new ArrayList();

        //Lembresse q uma nova lista é necessária pois utilizar a lista q será salva como parametro p o FOR acarretará erro
        for (int i = 0; i < listaEptInd.size(); i++) {
            if (listaEptInd.get(i).getNota() != null) {
                listaRetiradoNulls.add(listaEptInd.get(i));
            }
        }

        if (!listaRetiradoNulls.isEmpty()) {
            dao.salvarListaDAO(listaRetiradoNulls);
        }else{
            System.out.println("Não salvou nada");
        }
    }
    
    //Retorna os EmprendimentoIndicadores por uma categoria
    public List<EmpreendimentoIndicador> listarEesIndisPorcategoriaBean(Empreendimento ees, String categoria){
        return dao.listarEesIndisPorcategoriaDAO(ees, listarIndicadoresPorCategoriaBean(categoria));
    }
    
    public List<Indicador> listarIndicadoresPorCategoriaBean(String categoria){//Retorna os indicadores por uma categoria
        return gerenIndicadores.listarIndicadoresPorCategoria(categoria);
    }

    public void excluirBean(EmpreendimentoIndicador ept) {
        dao.excluirDao(ept);
    }

    public List<EmpreendimentoIndicador> listarBean() {
        return dao.listarTodosDAO();
    }

    public long contarLinhasBean() {
        return dao.contarLinhasDAO();
    }

    public List<EmpreendimentoIndicador> buscarListaPorCodigoBean(Empreendimento obj) {
        return dao.buscarListaPorCodigo(obj);
    }
}
