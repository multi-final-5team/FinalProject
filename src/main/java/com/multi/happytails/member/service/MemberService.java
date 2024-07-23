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
}
