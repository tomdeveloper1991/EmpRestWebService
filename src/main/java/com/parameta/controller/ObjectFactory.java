
package com.parameta.controller;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.parameta.controller package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AlmacenarEmpleado_QNAME = new QName("http://controller.parameta.com/", "almacenarEmpleado");
    private final static QName _AlmacenarEmpleadoResponse_QNAME = new QName("http://controller.parameta.com/", "almacenarEmpleadoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.parameta.controller
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AlmacenarEmpleado }
     * 
     */
    public AlmacenarEmpleado createAlmacenarEmpleado() {
        return new AlmacenarEmpleado();
    }

    /**
     * Create an instance of {@link AlmacenarEmpleadoResponse }
     * 
     */
    public AlmacenarEmpleadoResponse createAlmacenarEmpleadoResponse() {
        return new AlmacenarEmpleadoResponse();
    }

    /**
     * Create an instance of {@link Empleado }
     * 
     */
    public Empleado createEmpleado() {
        return new Empleado();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlmacenarEmpleado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controller.parameta.com/", name = "almacenarEmpleado")
    public JAXBElement<AlmacenarEmpleado> createAlmacenarEmpleado(AlmacenarEmpleado value) {
        return new JAXBElement<AlmacenarEmpleado>(_AlmacenarEmpleado_QNAME, AlmacenarEmpleado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlmacenarEmpleadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controller.parameta.com/", name = "almacenarEmpleadoResponse")
    public JAXBElement<AlmacenarEmpleadoResponse> createAlmacenarEmpleadoResponse(AlmacenarEmpleadoResponse value) {
        return new JAXBElement<AlmacenarEmpleadoResponse>(_AlmacenarEmpleadoResponse_QNAME, AlmacenarEmpleadoResponse.class, null, value);
    }

}
