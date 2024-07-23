package com.multi.happytails.authentication.model.service;


import com.multi.happytails.authentication.model.dto.CustomUser;
import com.multi.happytails.member.model.dao.MemberDAO;
import com.multi.happytails.member.model.dto.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final MemberDAO memberDAO;

    public AuthenticationServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("호출되었음 username은 " + username);

        MemberDTO memberDTO = memberDAO.findMemberById(username);
        System.out.println(memberDTO);

        if(memberDTO == null){
            throw new UsernameNotFoundException("회원정보가 존재하지않음");
        }
//        List<MemberDTO> memberRoleList = memberDTO.getMemberRoleList();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        memberRoleList.forEach(memberRole -> authorities.add(new SimpleGrantedAuthority(memberRole.getRole())));
        String role = memberDTO.getRole();
        List<GrantedAuthority> authorities = new ArrayList<>();

        // role 변수를 이용해 권한을 추가합니다.
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role));
        }


        System.out.println(authorities);

        return new CustomUser(memberDTO, authorities);
    }


    @Override
    public Map<String, List<String>> getPermitListMap() {
        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new ArrayList<>();
        List<String> memberPermitList = new ArrayList<>();

        adminPermitList.add("/admin/dashboard");
        memberPermitList.add("/order/regist");

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("memberPermitList", memberPermitList);

        return permitListMap;

    }
}
