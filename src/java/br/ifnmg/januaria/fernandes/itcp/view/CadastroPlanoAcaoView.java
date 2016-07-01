package br.ifnmg.januaria.fernandes.itcp.view;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.bean.MensagensBean;
import br.ifnmg.januaria.fernandes.itcp.bean.PlanoAcaoBean;
import br.ifnmg.januaria.fernandes.itcp.dao.EmpreendimentoDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private PlanoAcao planoAcaoSalvar = new PlanoAcao();
    private MensagensBean mensagensBean = new MensagensBean();
    private PlanoAcaoBean bean = new PlanoAcaoBean();
    private boolean planoAcaoSendoVisualizado;
    private List<Empreendimento> listaEpts;
    private EmpreendimentoBean empreendimentoBean = new EmpreendimentoBean();
    private String[] listaNomeEpts;
    private String empreedimentoSelecionado;
    private Date dataInicio;
    
    public CadastroPlanoAcaoView(){
        listaEpts = empreendimentoBean.listarTodosEptsBean();
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
        }
        bean.salvarPlanoBean(planoAcaoSalvar);
        planoAcaoSendoVisualizado = true;
    }
    
    public String conveteData(Date data) {
        if (data != null) {
            SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
            return forma.format(data);
        } else {
            return "";
        }
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
}
