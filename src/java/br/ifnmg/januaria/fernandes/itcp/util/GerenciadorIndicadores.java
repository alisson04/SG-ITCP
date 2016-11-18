package br.ifnmg.januaria.fernandes.itcp.util;

import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alisson
 */
public class GerenciadorIndicadores {

    public GerenciadorIndicadores() {
    }
    
    public List<Indicador> listarIndicadoresPorCategoria(String categoria){
        List<Indicador> listaIndicadores =listarIndicadores();
        List<Indicador> listaFiltrada = new ArrayList();
        int quantidadeIndicadores = listaIndicadores.size();//Variavel é necessário visto q se mexe na lista dentro do FOR
        
        for(int i=0; i< quantidadeIndicadores; i++){
            if(listaIndicadores.get(i).getCategoria().equals(categoria)){
                listaFiltrada.add(listaIndicadores.get(i));
            }
        }
        System.out.println("listarIndicadoresPorCategoria-GerenciadorIndicadores");
        return listaFiltrada;
    }
    
    public List<Indicador> listarIndicadores(){
        Indicador obj = new Indicador();
        List<Indicador> listaIndicadores = new ArrayList();
        
        Integer i = 0;//ID dos indicadores
        
        //CATEGORIAS
        String empreendimento = "Empreendimento";
        String infraestrutura = "Infraestrutura";
        String organizacao = "Organização";
        String participacao = "Participação";
        String remuneracao = "Remuneração";
        String comercializacao = "Comercialização";
        String redes = "Redes";
        String apoio = "Apoio";
        String producao = "Produção";

        //EIXOS
        String social = "Social";
        String economico = "Econômico";

        
        //Categoria 1 - Empreendimento================
        
        //Indicador 1
        i = i+1;
        obj.setId(i);
        obj.setNome("Tempo de existência do EES");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        obj.setNotaMax(2);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 2
        i = i+1;
        obj.setId(i);
        obj.setNome("Número de membros");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 3
        i = i+1;
        obj.setId(i);
        obj.setNome("Gênese do empreendimento");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        obj.setNotaMax(2);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 4
        i = i+1;
        obj.setId(i);
        obj.setNome("Trabalhadores não-sócios");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 5
        i = i+1;
        obj.setId(i);
        obj.setNome("Rotatividade nos cargos da coordenação");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Categoria 2 - Infraestrutura================
        
        //Indicador 6
        i = i+1;
        obj.setId(i);
        obj.setNome("Sede");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 7
        i = i+1;
        obj.setId(i);
        obj.setNome("Equipamentos");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        obj.setNotaMax(2);
        listaIndicadores.add(obj);
        
        //Indicador 8
        i = i+1;
        obj.setId(i);
        obj.setNome("Espaços de comercialização");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        obj.setNotaMax(2);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 9
        i = i+1;
        obj.setId(i);
        obj.setNome("Captação de crédito");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Categoria 3 - Organização (dividir soma por dois)================
        
        //Indicador 10
        i = i+1;
        obj.setId(i);
        obj.setNome("Estatuto");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 11
        i = i+1;
        obj.setId(i);
        obj.setNome("Ata");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 12
        i = i+1;
        obj.setId(i);
        obj.setNome("Regimento interno");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 13
        i = i+1;
        obj.setId(i);
        obj.setNome("Controle de caixa");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 14
        i = i+1;
        obj.setId(i);
        obj.setNome("Planejamentos ou reuniões internas");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 15
        i = i+1;
        obj.setId(i);
        obj.setNome("Registros/licenças");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Categoria 4 - Participação================
        
        //Indicador 16
        i = i+1;
        obj.setId(i);
        obj.setNome("Coletivização do trabalho/produção");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 17
        i = i+1;
        obj.setId(i);
        obj.setNome("Boa convivência");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 18
        i = i+1;
        obj.setId(i);
        obj.setNome("Decisão coletiva");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(2);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 19
        i = i+1;
        obj.setId(i);
        obj.setNome("Gestão de contas é transparente");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 20
        i = i+1;
        obj.setId(i);
        obj.setNome("Divulgação interna");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 21
        i = i+1;
        obj.setId(i);
        obj.setNome("Princípios da ecosol");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 22
        i = i+1;
        obj.setId(i);
        obj.setNome("Participação cotidiana na gestão");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 23
        i = i+1;
        obj.setId(i);
        obj.setNome("Ações de preservação/sustentabilidade");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Categoria 5 - Remuneração================
        
        //Indicador 24
        i = i+1;
        obj.setId(i);
        obj.setNome("Única fonte de renda");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 25
        i = i+1;
        obj.setId(i);
        obj.setNome("Distribuição justa das sobras");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 26
        i = i+1;
        obj.setId(i);
        obj.setNome("Benefício, INSS");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 27
        i = i+1;
        obj.setId(i);
        obj.setNome("Todos são remunerados");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 28
        i = i+1;
        obj.setId(i);
        obj.setNome("Férias");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 29
        i = i+1;
        obj.setId(i);
        obj.setNome("Constituem fundo");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 30
        i = i+1;
        obj.setId(i);
        obj.setNome("Todos trabalhadores conseguem 1 salário mínimo/mês");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Categoria 6 - Comercialização================
        
        //Indicador 31
        i = i+1;
        obj.setId(i);
        obj.setNome("Número/variedade de produtos");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 32
        i = i+1;
        obj.setId(i);
        obj.setNome("Comercialização preocupada com os consumidores");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 33
        i = i+1;
        obj.setId(i);
        obj.setNome("Divulgação/propaganda adequada");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 34
        i = i+1;
        obj.setId(i);
        obj.setNome("Visão estratégica");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 35
        i = i+1;
        obj.setId(i);
        obj.setNome("Dificuldades na comercialização");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        obj.setNotaMax(2);
        listaIndicadores.add(obj);
        obj = new Indicador();

        //Categoria 7 - Redes================
        
        //Indicador 36
        i = i+1;
        obj.setId(i);
        obj.setNome("Participação em espaços de diálogo");
        obj.setCategoria(redes);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 37
        i = i+1;
        obj.setId(i);
        obj.setNome("Participação em redes de comercialização");
        obj.setCategoria(redes);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 38
        i = i+1;
        obj.setId(i);
        obj.setNome("Participação em fóruns ou conselhos de ECOSOL");
        obj.setCategoria(redes);
        obj.setEixo(social);
        obj.setNotaMax(2);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 39
        i = i+1;
        obj.setId(i);
        obj.setNome("Participação em qualquer movimento social");
        obj.setCategoria(redes);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 40
        i = i+1;
        obj.setId(i);
        obj.setNome("Participação em ações sociais/comunitárias");
        obj.setCategoria(redes);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 41
        i = i+1;
        obj.setId(i);
        obj.setNome("Compra ou venda de insumos para outro EES");
        obj.setCategoria(redes);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Categoria 8 - Apoio================
        
        //Indicador 42
        i = i+1;
        obj.setId(i);
        obj.setNome("Apoio de outras entidades");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 43
        i = i+1;
        obj.setId(i);
        obj.setNome("Educação formal");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 44
        i = i+1;
        obj.setId(i);
        obj.setNome("Capacitação técnica");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 45
        i = i+1;
        obj.setId(i);
        obj.setNome("Inseridos em projetos");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Categoria 9 - Produção================
        
        //Indicador 46
        i = i+1;
        obj.setId(i);
        obj.setNome("Preocupação com a qualidade de vida dos trabalhadores");
        obj.setCategoria(producao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 47
        i = i+1;
        obj.setId(i);
        obj.setNome("Produção regular");
        obj.setCategoria(producao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        obj = new Indicador();
        
        //Indicador 48
        i = i+1;
        obj.setId(i);
        obj.setNome("Segue as normas vigentes");
        obj.setCategoria(producao);
        obj.setEixo(economico);
        obj.setNotaMax(1);
        listaIndicadores.add(obj);
        return listaIndicadores;
    }
}
