package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alisson
 */
@ManagedBean(name = "CadastroPlanoAcaoView")
@ViewScoped
public class CadastroPlanoAcaoView implements Serializable{
    
    private PlanoAcao planoAcaoSalvar;
    private MensagensBean mensagensBean;
    private PlanoAcaoBean planoAcaoBean;
    private boolean planoAcaoSendoVisualizado;
    private List<Empreendimento> listaEpts;
    EmpreendimentoDAO empreendimentoDAO;
    private String[] listaNomeEpts;
    private String empreedimentoSelecionado;
    
    public CadastroPlanoAcaoView(){
        empreendimentoDAO = new EmpreendimentoDAO();
        planoAcaoSalvar = new PlanoAcao();
        mensagensBean = new MensagensBean();
        planoAcaoBean = new PlanoAcaoBean();
        listaEpts = empreendimentoDAO.listarTodosEmpreendimentos();
        listaNomeEpts = new String[listaEpts.size()];
        for (int i = 0; i < listaEpts.size(); i++) {
            listaNomeEpts[i] = listaEpts.get(i).getNomeEpt();
        }
    }
    
    public void salvarPlanoAcao() {
        for (int i = 0; i < listaEpts.size(); i++) {
            if (listaEpts.get(i).getNomeEpt().equals(empreedimentoSelecionado)) {
                planoAcaoSalvar.setEmpreendimento(listaEpts.get(i));
            }
            // else{
            //    mensagensBean.messagemCaixa("ERROR", "Erro no E-mail", "Este E-mail já esta cadastrado no sistema");
            //}
        }
        planoAcaoBean.salvarParceiroBd(planoAcaoSalvar);
        planoAcaoSendoVisualizado = true;
    }

    //SETS E GETS
    public PlanoAcao getPlanoAcaoSalvar() {
        return planoAcaoSalvar;
    }

    public void setPlanoAcaoSalvar(PlanoAcao planoAcaoSalvar) {
        this.planoAcaoSalvar = planoAcaoSalvar;
    }

    public boolean isPlanoAcaoSendoVisualizado() {
        return planoAcaoSendoVisualizado;
    }

    public void setPlanoAcaoSendoVisualizado(boolean planoAcaoSendoVisualizado) {
        this.planoAcaoSendoVisualizado = planoAcaoSendoVisualizado;
    }

    public List<Empreendimento> getListaEpts() {
        return listaEpts;
    }

    public void setListaEpts(List<Empreendimento> listaEpts) {
        this.listaEpts = listaEpts;
    }

    public String[] getListaNomeEpts() {
        return listaNomeEpts;
    }

    public void setListaNomeEpts(String[] listaNomeEpts) {
        this.listaNomeEpts = listaNomeEpts;
    }

    public String getEmpreedimentoSelecionado() {
        return empreedimentoSelecionado;
    }

    public void setEmpreedimentoSelecionado(String empreedimentoSelecionado) {
        this.empreedimentoSelecionado = empreedimentoSelecionado;
    }
    
    
}
