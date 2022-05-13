package com.example.studentrxjava.servicedto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddStudentRequest {
    private String studentName;
    private String studentCity;
}