package com.example.studentrxjava.webdto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentWebResponse {
    int studentId;
    String studentName;
    String studentCity;
}
