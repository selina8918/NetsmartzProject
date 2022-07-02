package com.students.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int rollno;
	
	public Student() {
		super();
		
	}

	private String email;
	
	public Student(int id, String name, int rollno, String email) {
		super();
		this.id = id;
		this.name = name;
		this.rollno = rollno;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		 return "Student[ id=" +id + ",name=" + name +", rollno=" + rollno+",email="+email+"]";
	 }
	

	

}
