package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.MembroEmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
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
public class MembroEmpreendimentoBean implements Serializable{

    MembroEmpreendimentoDAO dao = new MembroEmpreendimentoDAO();
    List<MembroEmpreendimento> listaMembroEmpreendimentos;
    
    
    public MembroEmpreendimentoBean() {
    }
    
    public void salvarMembroEpt(MembroEmpreendimento membroEpt){
        dao.salvarMembroEpt(membroEpt);
    }
    
    public void excluirMembroBean(MembroEmpreendimento membroEpt){
        dao.excluirMembroDao(membroEpt);
    }
    
    public List<MembroEmpreendimento> listarTodosMembrosEpts() {
        System.out.println("BEAN(listarTodosMembrosEmpreendimentos): listarTodosMembrosEmpreendimentos: ");
        listaMembroEmpreendimentos = dao.listarTodosMembrosEmpreendimentos();
        return listaMembroEmpreendimentos;
    }
}
