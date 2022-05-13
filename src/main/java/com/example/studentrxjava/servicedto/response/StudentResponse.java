package com.example.studentrxjava.servicedto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponse {
    int studentId;
    String studentName;
    String studentCity;
}
