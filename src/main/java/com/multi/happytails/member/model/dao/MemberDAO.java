package com.multi.happytails.member.model.dao;


import com.multi.happytails.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDAO {

    MemberDTO findMemberById(String memberId);

    MemberDTO findUserByDetails(MemberDTO memberDTO);

    MemberDTO findUserByDetails2(String userName, String userTel);

    void updatePassword(@Param("id") String id, @Param("newPassword") String newPassword);

    void insertMember(MemberDTO member);

    }
