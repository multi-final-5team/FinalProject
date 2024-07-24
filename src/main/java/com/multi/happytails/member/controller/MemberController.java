package com.multi.happytails.member.controller;

import com.multi.happytails.authentication.model.dto.CustomUser;
import com.multi.happytails.member.model.dto.MemberDTO;
import com.multi.happytails.member.service.MemberService;
import com.multi.happytails.upload.model.dto.UploadDto;
import com.multi.happytails.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/login")
    public void login() {
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/member/login";
    }

    @GetMapping("/getCurrentUser")
    @ResponseBody
    public ResponseEntity<MemberDTO> getCurrentUser(@AuthenticationPrincipal CustomUser customUser) {
        MemberDTO member = memberService.findMemberById(customUser.getId());
        return ResponseEntity.ok(member);
    }

    @GetMapping("/findUserIdForm")
    public String findUserIdForm() {
        return "member/findUserId";
    }

    @PostMapping("/findUserId")
    public ResponseEntity<?> findUserId(@RequestBody MemberDTO memberDTO) {
        MemberDTO member = memberService.findUserByDetails(memberDTO);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/findUserPwdForm")
    public String findUserPwdForm() {
        return "member/findUserPwd";
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> payload) {
        String id = payload.get("id");
        String name = payload.get("name");
        String tel = payload.get("tel");
        String newPassword = payload.get("newPassword");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setName(name);
        memberDTO.setTel(tel);

        MemberDTO member = memberService.findUserByDetails(memberDTO);
        if (member != null) {
            memberService.changePassword(id, newPassword);
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/signup")
    public void signup() {

    }

    @PostMapping("/signup")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, // MultipartFile file은 프로필 이미지 파일을 받기
                                         @RequestParam("id") String id,
                                         @RequestParam("password") String password,
                                         @RequestParam("name") String name,
                                         @RequestParam("nickname") String nickname,
                                         @RequestParam("age") int age,
                                         @RequestParam("gender") String gender,
                                         @RequestParam("tel") String tel) {

        // MemberDTO 객체를 생성 -> 받아온 회원 정보를 설정
        // 데이터를 하나의 객체로 묶어 관리하기 위함
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setPwd(password);
        memberDTO.setName(name);
        memberDTO.setNickname(nickname);
        memberDTO.setAge(age);
        memberDTO.setGender(gender);
        memberDTO.setTel(tel);

        System.out.println(memberDTO);

//            // 회원 정보를 db에 저장
            memberService.insertMember(memberDTO);

            // 회원 번호 이름, 전화번호 받아옴(저장한 회원 정보를 다시 조회)
            // 이는 db에서 자동 생성된 회원 번호(no)를 얻기 위함
            MemberDTO memberDTO2 = new MemberDTO();
            memberDTO2 = memberService.findUserByDetails(memberDTO);
            // 업로드 dto 객체 생성
            UploadDto uploadDto = new UploadDto();
            uploadDto.setFile(file);
            uploadDto.setCategoryCode("P"); // P : 프로필 이미지
            uploadDto.setForeignNo(memberDTO2.getNo());

            uploadService.uploadInsert(uploadDto); // 회원 정보와 프로필 이미지를 연결

            return new ModelAndView("redirect:/member/login");

    }

    @PostMapping("/checkIdDuplicate")
    @ResponseBody
    public ResponseEntity<String> checkIdDuplicate(@RequestParam("id") String id) {
        boolean isDuplicate = memberService.isIdDuplicate(id);
        if (isDuplicate) {
            return ResponseEntity.ok("중복된 아이디입니다.");
        }
        else {
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }

    @GetMapping("/mypage")
    public void mypage(){

    }



//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteAccount() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUser customUser = (CustomUser) authentication.getPrincipal();
//        memberService.deleteMember(customUser.getId());
//        return ResponseEntity.ok("Account deleted successfully.");
//    }
}