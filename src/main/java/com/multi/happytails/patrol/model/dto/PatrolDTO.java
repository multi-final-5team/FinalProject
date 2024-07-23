package com.multi.happytails.patrol.model.dto;


import lombok.Data;

@Data
public class PatrolDTO {
    private int patrolNo;
    private int userNo;
    private String name;
    private int age;
    private String breed;
    private String location;
}
