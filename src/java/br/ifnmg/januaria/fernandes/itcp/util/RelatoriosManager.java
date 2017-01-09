package br.ifnmg.januaria.fernandes.itcp.util;

import br.ifnmg.januaria.fernandes.itcp.bean.IncubadoraBean;
import br.ifnmg.januaria.fernandes.itcp.domain.Incubadora;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author alisson
 * @param <TipoClasse>
 */
public class RelatoriosManager<TipoClasse> {

    //Incubadora VARS
    private Incubadora inc;
    private IncubadoraBean incBean;

    //Jasper VARS
    private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;

    //Construtor
    public RelatoriosManager() {
        //Incubadora CONSTRU
        incBean = new IncubadoraBean();
        //Não a necessidade de verificar a existencia de INC, pois essa pagina só pode ser ascessado quando exite uma
        inc = incBean.listarBean().get(0);

        context = FacesContext.getCurrentInstance();
        response = (HttpServletResponse) context.getExternalContext().getResponse();
    }

    public void gerarRelatorioGenerico(List<TipoClasse> lista, Map<String, Object> params, String caminhoRelatorio, String nomeRelatorio) throws Exception {
        stream = this.getClass().getResourceAsStream(caminhoRelatorio);
        baos = new ByteArrayOutputStream();
        String cam = getRealPath() + "image/imagemTopoRelatorio/" + inc.getFotoTopoRelatorio();
        params.put("paramImage", cam);
        JasperReport report = (JasperReport) JRLoader.loadObject(stream);
        JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(lista));
        JasperExportManager.exportReportToPdfStream(print, baos);
        response.reset();
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        response.setHeader("Content-disposition", "inline; filename=" + nomeRelatorio);//attachment para download
        response.getOutputStream().write(baos.toByteArray());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        context.responseComplete();
    }

    public String getRealPath() {//Gera o caminho do projeto no computador que esta instalado
        ExternalContext externalContext
                = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response
                = (HttpServletResponse) externalContext.getResponse();

        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context
                = (ServletContext) aFacesContext.getExternalContext().getContext();

        return context.getRealPath("/");
    }
}
