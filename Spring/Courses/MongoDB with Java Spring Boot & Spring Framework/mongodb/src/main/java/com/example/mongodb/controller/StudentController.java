package com.example.mongodb.controller;

import java.util.List;

import com.example.mongodb.model.Student;
import com.example.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    // Field injection with @Autowired is discouraged
    // Using constructor injection instead of field injection (@Autowired)
    // for better testability, immutability, and clearer dependency management.
    // Field injection is discouraged because it hides dependencies and complicates unit testing
    private final StudentService studentService;

    @Autowired // Optional if there's only one constructor, mandatory if there is more. Still a good practice to keep it
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PostMapping("/createbulk")
    public ResponseEntity<List<Student>> createStudentsInBulk(@RequestBody List<Student> students) {
        List<Student> saved = studentService.createStudentsInBulk(students);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/id/{id}")
    public String deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/get/id/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/get/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/get/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/get/name/native/{name}")
    public ResponseEntity<List<Student>> getStudentByNameNativeWay(@PathVariable String name) {
        List<Student> retrieved = studentService.getStudentByNameNativeWay(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(retrieved);
    }

    // Tested with http://localhost:8080/api/student/get/name/startswith?word=Elon
    @GetMapping("/get/name/startswith")
    public ResponseEntity<List<Student>> getStudentsNameStartsWith(@RequestParam String word) {
        List<Student> retrieved = studentService.getStudentsNameStartsWith(word);
        return ResponseEntity.status(HttpStatus.FOUND).body(retrieved);
    }

    // Tested in postman with http://localhost:8080/api/student/get/email/like?like=gmail
    @GetMapping("/get/email/like")
    public ResponseEntity<List<Student>> getStudentsByEmailLike(@RequestParam String like) {
        List<Student> retrieved = studentService.getStudentsByEmailLike(like);
        return ResponseEntity.status(HttpStatus.FOUND).body(retrieved);
    }

    @GetMapping("/get/department/name")
    public ResponseEntity<List<Student>> getStudentsByDepartmentName(@RequestParam String name) {
        List<Student> retrieved = studentService.getStudentsByDepartmentName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(retrieved);
    }

    @GetMapping("/get/subjects/name")
    public ResponseEntity<List<Student>> getStudentsBySubjectName(@RequestParam String name) {
        List<Student> retrieved = studentService.getStudentsBySubjectName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(retrieved);
    }

    // Tested in postman with http://localhost:8080/api/student/get/nameandemail?name=Peter&email=peter@test.com
    @GetMapping("/get/nameandemail")
    public List<Student> findByNameAndEmail(@RequestParam String name, @RequestParam String email) {
        return studentService.findByNameAndEmail(name, email);
    }

    /* Tested with & in postman, since there is no OR character, just need to change to @GetMapping("/get/nameoremail")
    * logic is controlled here.
    * By the way, wilcard can be used for email /api/student/get/nameoremail?name=Peter&email=*@test.com
    * in postman and will return without issues. But using wildcard with the name and email combined didn't work
    */
    @GetMapping("/get/nameoremail")
    public List<Student> findByNameOrEmail(@RequestParam String name, @RequestParam String email) {
        return studentService.findByNameOrEmail(name, email);
    }

    // Tested successfully with http://localhost:8080/api/student/get/paginated?pageNo=1&pageSize=5
    @GetMapping("/get/paginated")
    public ResponseEntity<List<Student>> getStudentsPaginated(@RequestParam int pageNo, @RequestParam int pageSize ) {
        List<Student> retrieved = studentService.getStudentsPaginated(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.FOUND).body(retrieved);
    }

    @GetMapping("/get/sorted/name")
    public ResponseEntity<List<Student>> getStudentsSortedByName() {
        List<Student> retrieved = studentService.getStudentsSortedByName();
        return ResponseEntity.status(HttpStatus.OK).body(retrieved);
    }
}
