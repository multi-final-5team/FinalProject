package com.multi.happytails.member.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class MemberDTO {

    private int no;
    private String id;
    private String pwd;
    private String name;
    private String nickname;
    private int age;
    private String gender;
    private String tel;
    private Timestamp signupDate;
    private Timestamp deleteDate;
    private char deleteAccountFlag;
    private String role;

    private List<MemberDTO> memberRoleList;

    public List<MemberDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

}
