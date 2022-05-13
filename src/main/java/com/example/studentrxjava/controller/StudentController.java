package com.example.studentrxjava.controller;

import com.example.studentrxjava.entity.Student;
import com.example.studentrxjava.servicedto.response.BaseWebResponse;
import com.example.studentrxjava.servicedto.response.StudentResponse;
import com.example.studentrxjava.service.StudentService;
import com.example.studentrxjava.webdto.response.StudentWebResponse;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse<List<StudentWebResponse>>>> getAllStudent(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                                                                        @RequestParam(value = "page", defaultValue = "0") int page) {
        return studentService.getAllStudents(limit, page)
                .subscribeOn(Schedulers.io())
                .map(studentResponses -> ResponseEntity.ok(BaseWebResponse.successWithData(toStudentResponseList(studentResponses))));
    }
    @GetMapping(value = "/simple",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Student>> getAllStudentSimple(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                                       @RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(studentService.getAllStudentsSimple(limit,page));
    }
    private List<StudentWebResponse> toStudentResponseList(List<StudentResponse> studentResponseList) {
        return studentResponseList
                .stream()
                .map(this::toStudentWebResponse)
                .collect(Collectors.toList());
    }

    private StudentWebResponse toStudentWebResponse(StudentResponse studentResponse) {
        StudentWebResponse studentWebResponse = new StudentWebResponse();
        BeanUtils.copyProperties(studentResponse, studentWebResponse);
        return studentWebResponse;
    }
}
