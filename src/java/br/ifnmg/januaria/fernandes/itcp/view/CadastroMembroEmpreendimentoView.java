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
    private MensagensBean mensagensBean = new MensagensBean();
    private MembroEmpreendimentoBean membroEmpreendimentoBean = new MembroEmpreendimentoBean();
    private boolean membroEptSendoVisualizado;
    private List<Empreendimento> listaEpts;
    private String[] listaNomeEpts;
    EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    private String empreedimentoSelecionado;
    
    public CadastroMembroEmpreendimentoView() {
        listaEpts = empreendimentoBean.listarTodosEptsBean();
        listaNomeEpts = new String[listaEpts.size()];
        for (int i = 0; i < listaEpts.size(); i++) {
            listaNomeEpts[i] = listaEpts.get(i).getNomeEpt();
        }
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
        
        for (int i = 0; i < listaEpts.size(); i++) {
            if (listaEpts.get(i).getNomeEpt().equals(empreedimentoSelecionado)) {
                membroEptCadastrado.setEmpreendimento(listaEpts.get(i));
            }
            // else{
            //    mensagensBean.messagemCaixa("ERROR", "Erro no E-mail", "Este E-mail já esta cadastrado no sistema");
            //}
        }

        membroEmpreendimentoBean.salvarMembroEpt(membroEptCadastrado);
        membroEptSendoVisualizado = true;
    }

    //SETS e GETS
    public void setEmpreedimentoSelecionado(String empreedimentoSelecionado) {
        this.empreedimentoSelecionado = empreedimentoSelecionado;
    }

    public String getEmpreedimentoSelecionado() {
        return empreedimentoSelecionado;
    }

    public String[] getListaNomeEpts() {
        return listaNomeEpts;
    }

    public void setListaNomeEpts(String[] listaNomeEpts) {
        this.listaNomeEpts = listaNomeEpts;
    }

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
