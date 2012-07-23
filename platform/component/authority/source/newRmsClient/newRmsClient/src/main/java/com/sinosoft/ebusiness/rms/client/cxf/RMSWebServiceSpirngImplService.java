
package com.sinosoft.ebusiness.rms.client.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "RMSWebServiceSpirngImplService", targetNamespace = "http://spring.webService.service.rms.ebusiness.sinosoft.com/", wsdlLocation = "http://localhost:9001/mis/services/RMSWebService?wsdl")
public class RMSWebServiceSpirngImplService
    extends Service
{

    private final static URL RMSWEBSERVICESPIRNGIMPLSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9001/mis/services/RMSWebService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RMSWEBSERVICESPIRNGIMPLSERVICE_WSDL_LOCATION = url;
    }

    public RMSWebServiceSpirngImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RMSWebServiceSpirngImplService() {
        super(RMSWEBSERVICESPIRNGIMPLSERVICE_WSDL_LOCATION, new QName("http://spring.webService.service.rms.ebusiness.sinosoft.com/", "RMSWebServiceSpirngImplService"));
    }

    /**
     * 
     * @return
     *     returns RMSWebService
     */
    @WebEndpoint(name = "RMSWebServiceSpirngImplPort")
    public RMSWebService getRMSWebServiceSpirngImplPort() {
        return (RMSWebService)super.getPort(new QName("http://spring.webService.service.rms.ebusiness.sinosoft.com/", "RMSWebServiceSpirngImplPort"), RMSWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RMSWebService
     */
    @WebEndpoint(name = "RMSWebServiceSpirngImplPort")
    public RMSWebService getRMSWebServiceSpirngImplPort(WebServiceFeature... features) {
        return (RMSWebService)super.getPort(new QName("http://spring.webService.service.rms.ebusiness.sinosoft.com/", "RMSWebServiceSpirngImplPort"), RMSWebService.class, features);
    }

}
