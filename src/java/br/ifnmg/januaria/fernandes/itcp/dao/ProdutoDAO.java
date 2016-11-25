package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Produto;
import java.util.List;

/**
 *
 * @author alisson
 */
public class ProdutoDAO extends DaoGenerico<Produto>{

    public void salvar(Produto produto) {
        salvarGenerico(produto);
    }

    public List<Produto> listar() {
        return listarObjsGenerico("Produto");
    }
}
