package com.students;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import com.students.entity.Student;
import com.students.repository.StudentRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTestCase {
	@Autowired
	StudentRepository sRepo;

	//  Test Case For  CREATE Operation

	@Test
	@Order(1)
	public void testCreate() {
		Student s = new Student();
		s.setId(8);
		s.setName("solomi");
		s.setRollno(12);
		s.setEmail("sol@gmail.com");
		sRepo.save(s);
		assertNotNull(sRepo.findById(8).get());
	}

	//  Test Case For READ Operation Operation

	@Test
	@Order(2)
	public void testReadAll() {
		List<Student> list = (List<Student>) sRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	//  Test Case For READ BY ID 

	@Test
	@Order(3)
	public void testSingleProduct() {
		Student student = sRepo.findById(2).get();
		assertEquals("deepika", student.getName());
	}

	// Test Case For UPDATE Operation

	@Test
	@Order(4)
	public void testUpdate() {
		Student s = sRepo.findById(5).get();
		s.setName("sinchan");
		sRepo.save(s);
		assertNotEquals("swkrity", sRepo.findById(5).get().getName());
	}

	//  Test Case For DELETE Operation

	@Test
	@Order(5)
	public void testDelete() {
		sRepo.deleteById(6);
		assertThat(sRepo.existsById(6)).isFalse();
	}


}
