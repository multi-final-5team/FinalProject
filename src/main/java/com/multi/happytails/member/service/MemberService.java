package com.multi.happytails.member.service;


import com.multi.happytails.member.model.dao.MemberDAO;
import com.multi.happytails.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MemberDTO findMemberById(String id) {
        return memberDAO.findMemberById(id);
    }

    public MemberDTO findUserByDetails(MemberDTO memberDTO) {
        System.out.println(memberDTO);
        MemberDTO memberDTO2 = memberDAO.findUserByDetails(memberDTO);
        System.out.println(memberDTO2);
        return memberDTO2;
    }

    public MemberDTO findUserByDetails2(MemberDTO memberDTO) {
        return memberDAO.findUserByDetails2(memberDTO.getName(), memberDTO.getTel());
    }

    public void changePassword(String id, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        memberDAO.updatePassword(id, encodedPassword);
    }

    public void insertMember(MemberDTO memberDTO) {
        // 비밀번호 암호화
        memberDTO.setPwd(passwordEncoder.encode(memberDTO.getPwd()));
        memberDAO.insertMember(memberDTO);
    }

    public boolean isIdDuplicate(String id) {
        return memberDAO.findMemberById(id) != null;
    }

//    public void deleteMember(MemberDTO memberDTO){
//
//    }
}
