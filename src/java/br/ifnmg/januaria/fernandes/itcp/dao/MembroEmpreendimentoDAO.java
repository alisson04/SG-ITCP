package br.ifnmg.januaria.fernandes.itcp.dao;

import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author alisson
 */
public class MembroEmpreendimentoDAO extends DaoGenerico<MembroEmpreendimento>{
    public void TestaAtividade(){
        MembroEmpreendimento at = new MembroEmpreendimento();
        List<Empreendimento> listaObjs;
        listaObjs = new ArrayList<>();
        
        EmpreendimentoDAO eee = new EmpreendimentoDAO();
        listaObjs = eee.listarTodosEmpreendimentos();
        System.out.println("EMAIL EMPT: " + listaObjs.get(2).getEmailEpt());
        
        at.setEmpreendimento(listaObjs.get(2));
        at.setApelidoMembroEmpreendimento("asdasd");
        at.setDataNascimentoMembroEmpreendimento("asdd");
        at.setEnderecoMembroEmpreendimento("asdas");
        at.setNisMembroEmpreendimento("asdd");
        at.setNomeMaeMembroEmpreendimento("asdsad");
        at.setNomeMembroEmpreendimento("asdas");
        at.setRgMembroEmpreendimento("asdsa");
        at.setSexoMembroEmpreendimento("asdd");
        at.setTelefoneAlternativoMembroEmpreendimento("asdd");
        at.setTelefoneMembroEmpreendimento("asdk");
        salvarGenerico(at);
    }
    
    public void salvarMembroEpt(MembroEmpreendimento membroEpt){
        salvarGenerico(membroEpt);
    } 
    
    public List<MembroEmpreendimento> listarTodosEmpreendimentos() {
        return listarObjsGenerico("MembroEmpreendimento");
    }
}
