package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MembroEmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.MembroEmpreendimento;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroMembroEmpreendimentoView")
@ViewScoped
public class CadastroMembroEmpreendimentoView implements Serializable {

    private MembroEmpreendimento membroEptCadastrado = new MembroEmpreendimento();
    private MembroEmpreendimentoBean membroEmpreendimentoBean = new MembroEmpreendimentoBean();
    private boolean membroEptSendoVisualizado;
    private List<Empreendimento> listaEpts;
    private EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    
    
    public CadastroMembroEmpreendimentoView() {
        listaEpts = empreendimentoBean.listarTodosEptsBean();        
    }
    
    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
    }

    //Métodos
    public void salvarMembroEpt() {
        membroEmpreendimentoBean.salvarMembroEpt(membroEptCadastrado);
        membroEptSendoVisualizado = true;
    }

    //SETS e GETS
    public List<Empreendimento> getListaEpts() {
        return listaEpts;
    }

    public void setListaEpts(List<Empreendimento> listaEpts) {
        this.listaEpts = listaEpts;
    }

    public MembroEmpreendimento getMembroEptCadastrado() {
        return membroEptCadastrado;
    }

    public void setMembroEptCadastrado(MembroEmpreendimento membroEptCadastrado) {
        this.membroEptCadastrado = membroEptCadastrado;
    }

    public boolean isMembroEptSendoVisualizado() {
        return membroEptSendoVisualizado;
    }

    public void setMembroEptSendoVisualizado(boolean membroEptSendoVisualizado) {
        this.membroEptSendoVisualizado = membroEptSendoVisualizado;
    }
}
