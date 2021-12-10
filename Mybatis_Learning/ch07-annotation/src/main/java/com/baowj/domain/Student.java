package com.baowj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
}