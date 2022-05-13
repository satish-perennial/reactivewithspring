package com.example.studentrxjava.service;

import com.example.studentrxjava.entity.Student;
import com.example.studentrxjava.servicedto.response.StudentResponse;
import io.reactivex.Single;

import java.util.List;

public interface StudentService {

    Single<List<StudentResponse>> getAllStudents(int limit, int page);

    List<Student> getAllStudentsSimple(int limit, int page);
}
