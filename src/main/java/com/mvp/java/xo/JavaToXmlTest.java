package com.mvp.java.xo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: hems-dev
 * Date: 4/19/17
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaToXmlTest {

    public static void main1(String[] args) throws JAXBException {

        COMPENSATIONPLANType  compPlan = new COMPENSATIONPLANType();
        compPlan.setCOMPENSATIONPLANID(1002);

        COMPENSATIONRATEType ratePlan = new COMPENSATIONRATEType();
        ratePlan.setCUSTOMERTYPEID(1);
        compPlan.getCOMPENSATIONRATE().add(ratePlan);

        JAXBContext jaxbContext = JAXBContext.newInstance(COMPENSATIONPLANType.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter result = new StringWriter();
        jaxbMarshaller.marshal(compPlan, result);
        System.out.println(result.toString());
    }
}
