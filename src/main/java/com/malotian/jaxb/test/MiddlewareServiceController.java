package com.malotian.jaxb.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBResult;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MiddlewareServiceController {

	@RequestMapping(value = "/xml-test-class-room", produces = MediaType.APPLICATION_XML_VALUE)
	public ClassRoomModified xmlTestClassRoom() throws JAXBException, TransformerException {
		return testClassRoom();
	}

	@RequestMapping(value = "/json-test-class-room", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClassRoomModified jsonTestClassRoom() throws JAXBException, TransformerException {
		return testClassRoom();
	}

	public ClassRoomModified testClassRoom() throws JAXBException, TransformerException {
		RestTemplate restTemplate = new RestTemplate();
		
		String xmlClassRoom = "http://localhost:8080/json-class-room";
		ResponseEntity<ClassRoom> response = restTemplate.getForEntity(xmlClassRoom, ClassRoom.class);
		
		
		Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "HTTP Failure");
		ClassRoomModified classRoomModified = transformModel(response.getBody(), new StreamSource("src/main/resources/transformation.xsl"), ClassRoomModified.class);
		return (ClassRoomModified) classRoomModified;
	}

	@SuppressWarnings("unchecked")
	public static <T, U> U transformModel(final T sourceModelObject, final Source xsltSrc, final Class<U> targetModel) throws JAXBException, TransformerException {
		final JAXBSource jxSrc = new JAXBSource(JAXBContext.newInstance(sourceModelObject.getClass()), sourceModelObject);
		final TransformerFactory tf = TransformerFactory.newInstance();
		final Transformer t = tf.newTransformer(xsltSrc);
		final JAXBResult jxRes = new JAXBResult(JAXBContext.newInstance(targetModel));
		t.transform(jxSrc, jxRes);
		return (U) jxRes.getResult();
	}
}