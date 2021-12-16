package com.student.control.controller;

import com.student.control.Constants;
import com.student.control.model.Student;
import com.student.control.services.StudentService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentResource {

    @Autowired
    StudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>>getAllCategories(HttpServletRequest request){
     List<Student>categories = studentService.fetchAllCategories();
     return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<Student>getCategoryById(HttpServletRequest request,
                                                  @PathVariable("studentId")Integer studentId){
        Student student = studentService.fetchCategoryById(studentId);
        return  new ResponseEntity<>(student,HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>>loginStudent (@RequestBody Map<String,Object> studentMap){
        String email = (String) studentMap.get("email");
        String password = (String) studentMap.get("password");
        Student student = studentService.validateStudent(email,password);
        return  new ResponseEntity<>(generateJWTToken(student),HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity <Map<String,String>>registerStudent (@RequestBody Map<String,Object> studentMap){
        String firstName =  (String) studentMap.get("firstName");
        String lastName = (String) studentMap.get("lastName");
        String email = (String) studentMap.get("email");
        String password = (String) studentMap.get("password");
        Student student = studentService.registerStudent(firstName,lastName,email,password);
        return  new ResponseEntity<>(generateJWTToken(student),HttpStatus.OK);
    }

    private void generateJWTToken(Student student){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.API_SECRET_KEY))
                .claim("studentId", student.getStudentId())
                .claim("email", student.getEmail())
                .clain("firstName",student.getFirstName())
                .clain("lastName",student.getLastName());
    }

    @PostMapping("")
    public ResponseEntity<Student>addCategory(HttpServletRequest request,
                                              @RequestBody Map<String,Object> studentMap){
        String first_name = (String) studentMap.get("first_name");
        String last_name = (String) studentMap.get("last_name") ;
        Integer age = (Integer) studentMap.get("age");
        Integer course = (Integer) studentMap.get("course");
        Boolean status = (Boolean) studentMap.get("status");
        Student category = studentService.addCategory(first_name,last_name,age,course,status);
        return new ResponseEntity<>(category,HttpStatus.CREATED);

        }
    @PatchMapping("/{studentId}")
    public ResponseEntity<Map<String,Boolean>> updateCategory(HttpServletRequest request,
                                                           @PathVariable("studentId")Integer studentId,@RequestBody Map<String,Object>studentMap){
        String first_name = (String) studentMap.get("first_name");
        String last_name = (String) studentMap.get("last_name") ;
        Integer age = (Integer) studentMap.get("age");
        Integer course = (Integer) studentMap.get("course");
        Boolean status = (Boolean) studentMap.get("status");
        studentService.updateCategory(studentId, first_name,last_name,age,course,status);
        Map<String,Boolean>map = new HashMap<>();
        map.put("success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("/{studentId}")
    public  ResponseEntity<Map<String,Boolean>> deleteCategory(HttpServletRequest request,
                                                              @PathVariable("studentId") Integer categoryId ){
        studentService.removeCustomerById(categoryId);
        Map<String,Boolean> map = new HashMap<>();
        map.put("success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
