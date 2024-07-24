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

/**
 * packageName    : com.multi.happytails.member.controller
 * fileName       : MemberController
 * author         : EUN SOO
 * date           : 2024-07-24
 * description    : 회원, 명예의 전당, SNS 공유 관련 기능 처리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-24        EUN SOO       최초 생성
 */

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UploadService uploadService;

    /**
     * methodName : login
     * author : Eunsoo Lee
     * description : 로그인
     */
    @RequestMapping("/login")
    public void login() {
    }

    /**
     * methodName : logout
     * author : Eunsoo Lee
     * description : 로그아웃
     *
     * @return string
     */
    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/member/login";
    }

    /**
     * Gets current user.
     *
     * @param customUser the custom user
     * @return the current user
     */
    @GetMapping("/getCurrentUser")
    @ResponseBody
    public ResponseEntity<MemberDTO> getCurrentUser(@AuthenticationPrincipal CustomUser customUser) {
        MemberDTO member = memberService.findMemberById(customUser.getId());
        return ResponseEntity.ok(member);
    }

    /**
     * methodName : findUserIdForm
     * author : Eunsoo Lee
     * description :
     *
     * @return string
     */
    @GetMapping("/findUserIdForm")
    public String findUserIdForm() {
        return "member/findUserId";
    }

    /**
     * methodName : findUserId
     * author : Eunsoo Lee
     * description :
     *
     * @param memberDTO dto
     * @return response entity
     */
    @PostMapping("/findUserId")
    public ResponseEntity<?> findUserId(@RequestBody MemberDTO memberDTO) {
        MemberDTO member = memberService.findUserByDetails(memberDTO);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    /**
     * methodName : findUserPwdForm
     * author : Eunsoo Lee
     * description :
     *
     * @return string
     */
    @GetMapping("/findUserPwdForm")
    public String findUserPwdForm() {
        return "member/findUserPwd";
    }

    /**
     * methodName : changePassword
     * author : Eunsoo Lee
     * description :
     *
     * @param payload
     * @return response entity
     */
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

    /**
     * methodName : signup
     * author : Eunsoo Lee
     * description :
     */
    @GetMapping("/signup")
    public void signup() {

    }

    /**
     * methodName : handleFileUpload
     * author : Eunsoo Lee
     * description :
     *
     * @param file
     * @param id
     * @param password
     * @param name
     * @param nickname
     * @param age
     * @param gender
     * @param tel
     * @return model and view
     */
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

    /**
     * methodName : checkIdDuplicate
     * author : Eunsoo Lee
     * description :
     *
     * @param id
     * @return response entity
     */
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

    /**
     * methodName : mypage
     * author : Eunsoo Lee
     * description :
     */
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