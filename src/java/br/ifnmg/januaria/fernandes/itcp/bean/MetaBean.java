package br.ifnmg.januaria.fernandes.itcp.bean;

import br.ifnmg.januaria.fernandes.itcp.dao.MetaDAO;
import br.ifnmg.januaria.fernandes.itcp.domain.AtividadePlanejada;
import br.ifnmg.januaria.fernandes.itcp.domain.Meta;
import br.ifnmg.januaria.fernandes.itcp.domain.PlanoAcao;
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
public class MetaBean implements Serializable {

    private MetaDAO dao;
    private AtividadePlanejadaBean atvBean;

    public MetaBean() {
        dao = new MetaDAO();
        atvBean = new AtividadePlanejadaBean();
    }

    //Salva uma meta
    public Meta salvarBean(Meta metaSalvar) {
        return dao.salvarDao(metaSalvar);
    }

    //Lita todas a metas
    public List<Meta> listarBean() {
        return dao.listarDao();
    }

    //Exclui uma meta
    public void excluirBean(Meta meta) {
        dao.excluirDao(meta);
    }

    //Busca metas de um plano
    public List<Meta> buscarMetasPorPlanoBean(PlanoAcao p) {
        return dao.buscarMetasPorPlanoDao(p);
    }

    ///Muda o status da meta dependendo da mudança do status de alguma atividade modificada
    public void atualizaStatusMeta(Meta meta) {
        List<AtividadePlanejada> listaAtvMeta;
        listaAtvMeta = atvBean.buscarAtividadesPorMetaBean(meta);

        if (!listaAtvMeta.isEmpty()) {
            int quantidadeNaoIniciada = 0;
            int quantidadeIniciada = 0;
            int finalizada = 0;

            for (int i = 0; i < listaAtvMeta.size(); i++) {
                if (listaAtvMeta.get(i).getStatus().equals("Não iniciada")) {
                    quantidadeNaoIniciada++;
                } else if (listaAtvMeta.get(i).getStatus().equals("Iniciada")) {
                    quantidadeIniciada++;
                } else {
                    finalizada++;
                }
            }

            if (quantidadeIniciada == 0 && finalizada == 0) {//Caso a meta esteja não iniciada
                meta.setSituacao("Não iniciada");
            }else if(quantidadeNaoIniciada == 0 && quantidadeIniciada == 0){
                meta.setSituacao("Finalizada");
            }else{
                meta.setSituacao("Iniciada");
            }
            
            salvarBean(meta);
        }else{
            
        }
    }
}
