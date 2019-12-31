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
public class BuisnessServiceController {

	@RequestMapping(value = "/json-class-room", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClassRoom jsonClassRoom() {
		return classRoom();
	}

	public ClassRoom classRoom() {
		ClassRoom cr = new ClassRoom();
		cr.setName("Computers");
		Student s1 = new Student();
		s1.setAge(25);
		s1.setName("Aaron");
		cr.getStudents().add(s1);

		Student s2 = new Student();
		s2.setAge(28);
		s2.setName("Christina");
		cr.getStudents().add(s2);

		return cr;
	}

	@RequestMapping(value = "/xml-class-room", produces = MediaType.APPLICATION_XML_VALUE)
	public ClassRoom xmlsClassRoom() {
		return classRoom();
	}

}