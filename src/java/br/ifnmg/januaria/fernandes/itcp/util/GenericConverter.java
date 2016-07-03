package br.ifnmg.januaria.fernandes.itcp.util;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.ifnmg.januaria.fernandes.itcp.domain.EntityConverter;


//Fonte: AndersonLira; link: http://respostas.guj.com.br/22937-jsf---erro-de-conversao-ao-definir-o-valor--para-null-converter
@FacesConverter("generic")
public class GenericConverter implements Converter,Serializable{
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null
                && !"".equals(value)) {

            EntityConverter entity = (EntityConverter) value;

            // adiciona item como atributo do componente
            this.addAttribute(component, entity);

            Integer codigo = entity.getIdConverter();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
    }

    protected void addAttribute(UIComponent component, EntityConverter o) {
        String key = o.getIdConverter().toString(); // codigo da empresa como chave neste caso
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}
