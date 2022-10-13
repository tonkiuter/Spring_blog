package com.okaru.springboot.controller;

import com.okaru.springboot.bean.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {


    @GetMapping
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Okaru", "Tsukero");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-cache");
        MultiValueMap value = new LinkedMultiValueMap<String, String>();
        value.add("X-add-count", "2");
        return new ResponseEntity<Student>(student,value, HttpStatus.OK);
//        return ResponseEntity.ok().
//                header("Cache", "yes").
//                body(student);
    }

    @GetMapping("list")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "Okaru", "Tsukero"));
        students.add(new Student(2, "Nanji Kimiko", "Oboro no Aria"));
        students.add(new Student(3, "Enma Rauri", "Arianimasu"));

        return students;
    }

    //Spring Boot Rest API with Path Variable
    //http://localhost:8080/student/1/okaru/tsukero

    @GetMapping("{id}/{first-name}/{last-name}")
    public Student studentPathVarible(@PathVariable("id") int studentId,
                                      @PathVariable("first-name") String firstName,
                                      @PathVariable("last-name") String lastName) {

        return new Student(studentId, firstName, lastName);
    }


    //Spring Boot Rest Api with Request Param
    // http://localhost:8080/students/query?id=1&firstName=okaru&lastName=Tsukero

    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }

    //Spring boot handles POST request
    //@PostMapping and @RequestBody

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.toString());

        return student;
    }


    //Spring Boot REst Api that handles PUT Request

    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.toString());
        return student;
    }


}
