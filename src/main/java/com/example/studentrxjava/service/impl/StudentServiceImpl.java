package com.example.studentrxjava.service.impl;

import com.example.studentrxjava.servicedto.response.StudentResponse;
import com.example.studentrxjava.entity.Student;
import com.example.studentrxjava.repository.StudentRepository;
import com.example.studentrxjava.service.StudentService;
import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Single<List<StudentResponse>> getAllStudents(int limit, int page) {
        return findAllBooksInRepository(limit, page)
                .map(this::toStudentResponseLists);
    }

    @Override
    public List<Student> getAllStudentsSimple(int limit, int page) {
        return studentRepository.findAll(PageRequest.of(page,limit)).getContent();
    }

    private Single<List<Student>> findAllBooksInRepository(int limit, int page) {
        return Single.create(singleSubscriber -> {
            List<Student> students=studentRepository.findAll(PageRequest.of(page,limit)).getContent();
            singleSubscriber.onSuccess(students);
        });
    }

    private List<StudentResponse> toStudentResponseLists(List<Student> studentList) {
        return studentList
                .stream()
                .map(this::toStudentResponse)
                .collect(Collectors.toList());
    }

    private StudentResponse toStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(student, studentResponse);
        studentResponse.setStudentId(student.getId());
        studentResponse.setStudentName(student.getName());
        studentResponse.setStudentCity(student.getCity());
        return studentResponse;
    }
}
