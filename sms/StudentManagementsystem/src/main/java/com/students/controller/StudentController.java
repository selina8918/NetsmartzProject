package com.students.controller;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.students.entity.Student;
import com.students.exception.StudentNotFoundException;
import com.students.repository.StudentRepository;
import com.students.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/api")
@Api(tags = {"Student Services Developed By selina"})
@SwaggerDefinition(tags = {
	    @Tag(name = "Student Services", description = "Data of Students")})
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;

	@PostMapping
	
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentRepository.save(student));
	}

	@GetMapping
	public ResponseEntity<List<Student>> getStudentList() {
		return ResponseEntity.ok(studentRepository.findAll());
	}
	
	@GetMapping("/{id}")
	
	public  ResponseEntity<Student> findStudent(@PathVariable Integer id ){
		return ResponseEntity.ok(studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("give data is not found")));
	
	}
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String>handleStudentNotFoundException(){
		return ResponseEntity.ok("give data is not found");
	}
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		Student s = studentRepository.findById(student.getId()).get();
		s.setName(student.getName());
		s.setRollno(s.getRollno());
		s.setEmail(s.getEmail());
		
		s =  studentRepository.save(s);
		return ResponseEntity.ok(s);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteStudent(@PathVariable Integer id){
		studentRepository.deleteById(id);
		return ResponseEntity.ok("Delete success!");
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<List<Student>>getStudentPagination(@RequestParam int page,@RequestParam int size){
		Pageable pageable =PageRequest.of(page, size);
	List<Student> list	=studentRepository.findAll(pageable).getContent();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> downloadFile() throws IOException {
		File file =studentService .getFile();
		byte[] data = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		ByteArrayResource byteData = new ByteArrayResource(data);
		MediaType type = MediaType.parseMediaType("appication/txt");
		return (ResponseEntity<ByteArrayResource>) ResponseEntity.ok()
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
						"attachment;filename=" + file.getName())
				.contentType(type).contentLength((int) file.length()).body(byteData);
			
		
	}

}

