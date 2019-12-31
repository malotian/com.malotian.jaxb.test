package com.malotian.jaxb.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "classroom")
public class ClassRoomModified implements Serializable {

	public List<StudentModified> getStudents() {
		return students;
	}

	public void setStudents(List<StudentModified> students) {
		this.students = students;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String id;
	private List<StudentModified> students = new ArrayList<StudentModified>();

}
