package com.multi.happytails.member.model.dao;


import com.multi.happytails.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {

    MemberDTO findMemberById(String memberId);
    }
