package com.students.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.students.entity.Student;
import com.students.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

		
	@Autowired
	private  StudentRepository studentRepository;

	@Override
	public File getFile() {
		File file =new File("file.txt");
		try {
			List<Student> studentList =(List<Student>) studentRepository.findAll();
			try(FileOutputStream fos = new FileOutputStream(file); PrintWriter pw =new PrintWriter(fos)){
				studentList.forEach(student -> pw.println(student));
				pw.flush();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
}
