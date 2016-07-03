package br.ifnmg.januaria.fernandes.itcp.converter;

import br.ifnmg.januaria.fernandes.itcp.bean.EmpreendimentoBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Empreendimento;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
 
@FacesConverter("empreendimentoConverter")
public class EmpreendimentoConverter<TipoClasse> implements Converter {
    
    private final EmpreendimentoBean bean = new EmpreendimentoBean();
    private Empreendimento ept = new Empreendimento();
 
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                ept.setIdEpt(Integer.parseInt(value));
                return bean.buscarPorCodigoBean(ept);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "O empreendimento é obrigatório."));
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object instanceof Empreendimento && object != null){  
            Empreendimento ept = (Empreendimento) object;  
            return ept.getIdEpt().toString();  
        }  
        else {
            return null;
        }
    }   
} 
