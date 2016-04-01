package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class EmpreendimentoBean implements Serializable {

    public EmpreendimentoBean() {
    }

    public void salvarEptBd(Empreendimento ept) {
        System.out.println(ept.getCnpjEpt());
        System.out.println(ept.getDataCriacaoEpt());
        System.out.println(ept.getDataIncubacaoEpt());
        System.out.println(ept.getEmailEpt());
        System.out.println(ept.getEnderecoEpt());
        System.out.println(ept.getFaturamentoMedioAnualEmp());
        System.out.println(ept.getFaturamentoMedioMensalEmp());
        System.out.println(ept.getTipoEpt());
        System.out.println(ept.getNomeFantasiaEpt());
        System.out.println(ept.getTelefoneEpt());
        System.out.println(ept.getTelefoneAlternativoEpt());
        System.out.println(ept.getSituacaoEpt());
        System.out.println(ept.getNomeEpt());
        System.out.println(ept.getSiteEpt());
        System.out.println(ept.getAtividadeExercidaEpt());
        
        /*ept.setCnpjEpt("asd");
        ept.setDataCriacaoEpt("asd");
        ept.setDataIncubacaoEpt("asd");
        ept.setEmailEpt("asd");
        ept.setEnderecoEpt("asd");
        ept.setNomeEpt("asd");
        ept.setSituacaoEpt("asd");
        ept.setTelefoneAlternativoEpt("asd");
        ept.setTelefoneEpt("asd");
        ept.setNomeFantasiaEpt("dasd");
        ept.setTipoEpt("dasdas");
        ept.setFaturamentoMedioAnualEmp("asd");
        ept.setSiteEpt("asd");
        ept.setAtividadeExercidaEpt("asd");*/
        System.out.println("__________BEAN(salvarEptBd): INÍCIO");
        EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
        empreendimentoDAO.salvarEpt(ept);
    }
}
