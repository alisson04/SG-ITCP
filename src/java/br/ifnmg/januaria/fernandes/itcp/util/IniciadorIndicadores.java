package br.ifnmg.januaria.fernandes.itcp.util;

import br.ifnmg.januaria.fernandes.itcp.domain.Indicador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alisson
 */
public class IniciadorIndicadores {

    public IniciadorIndicadores() {
    }
    
    public List<Indicador> gerarIndicadores(){
        Indicador obj = new Indicador();
        List<Indicador> listaIndicadores = new ArrayList();
        
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
        obj.setNome("Tempo de existência do EES");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 2
        obj.setNome("Número de membros");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 3
        obj.setNome("Gênese do empreendimento");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 4
        obj.setNome("Trabalhadores não-sócios");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 5
        obj.setNome("Rotatividade nos cargos da coordenação");
        obj.setCategoria(empreendimento);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Categoria 2 - Infraestrutura================
        
        //Indicador 6
        obj.setNome("Sede");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 7
        obj.setNome("Equipamentos");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 8
        obj.setNome("Espaços de comercialização");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 9
        obj.setNome("Captação de crédito");
        obj.setCategoria(infraestrutura);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Categoria 3 - Organização (dividir soma por dois)================
        
        //Indicador 10
        obj.setNome("Estatuto");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 11
        obj.setNome("Ata");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 12
        obj.setNome("Regimento interno");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 13
        obj.setNome("Controle de caixa");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 14
        obj.setNome("Planejamentos ou reuniões internas");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 15
        obj.setNome("Registros/licenças");
        obj.setCategoria(organizacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Categoria 4 - Participação================
        
        //Indicador 16
        obj.setNome("Coletivização do trabalho/produção");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 17
        obj.setNome("Boa convivência");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 18
        obj.setNome("Decisão coletiva");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 19
        obj.setNome("Gestão de contas é transparente");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 20
        obj.setNome("Divulgação interna");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 21
        obj.setNome("Princípios da ecosol");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 22
        obj.setNome("Participação cotidiana na gestão");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 23
        obj.setNome("Ações de preservação/sustentabilidade");
        obj.setCategoria(participacao);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Categoria 5 - Remuneração================
        
        //Indicador 23
        obj.setNome("Única fonte de renda");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 24
        obj.setNome("Distribuição justa das sobras");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 25
        obj.setNome("Benefício, INSS");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 26
        obj.setNome("Todos são remunerados");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 27
        obj.setNome("Férias");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 28
        obj.setNome("Constituem fundo");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 29
        obj.setNome("Todos trabalhadores conseguem 1 salário mínimo/mês");
        obj.setCategoria(remuneracao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Categoria 6 - Comercialização================
        
        //Indicador 30
        obj.setNome("Número/variedade de produtos");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 31
        obj.setNome("Comercialização preocupada com os consumidores");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 32
        obj.setNome("Divulgação/propaganda adequada");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 33
        obj.setNome("Visão estratégica");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 34
        obj.setNome("Dificuldades na comercialização");
        obj.setCategoria(comercializacao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);

        //Categoria 7 - Redes================
        
        //Indicador 35
        obj.setNome("Participação em espaços de diálogo");
        obj.setCategoria(redes);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 36
        obj.setNome("Participação em redes de comercialização");
        obj.setCategoria(redes);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 37
        obj.setNome("Participação em fóruns ou conselhos de ECOSOL");
        obj.setCategoria(redes);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 38
        obj.setNome("Participação em qualquer movimento social");
        obj.setCategoria(redes);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 39
        obj.setNome("Participação em ações sociais/comunitárias");
        obj.setCategoria(redes);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 40
        obj.setNome("Compra ou venda de insumos para outro EES");
        obj.setCategoria(redes);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Categoria 8 - Apoio================
        
        //Indicador 41
        obj.setNome("Apoio de outras entidades");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 42
        obj.setNome("Educação formal");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 43
        obj.setNome("Capacitação técnica");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Indicador 44
        obj.setNome("Inseridos em projetos");
        obj.setCategoria(apoio);
        obj.setEixo(social);
        listaIndicadores.add(obj);
        
        //Categoria 9 - Produção================
        
        //Indicador 45
        obj.setNome("Preocupação com a qualidade de vida dos trabalhadores");
        obj.setCategoria(producao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 46
        obj.setNome("Produção regular");
        obj.setCategoria(producao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        //Indicador 47
        obj.setNome("Segue as normas vigentes");
        obj.setCategoria(producao);
        obj.setEixo(economico);
        listaIndicadores.add(obj);
        
        return listaIndicadores;
    }
}
