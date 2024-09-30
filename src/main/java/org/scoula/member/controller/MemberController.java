package org.scoula.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.common.UploadFiles;
import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;
import org.scoula.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    final MemberService service;

    @GetMapping("/checkusername/{user_no}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String user_no) { // unsername = sangyeop0715
        return ResponseEntity.ok().body(service.findById(user_no)); // sangyeop0715
    }

    @PostMapping("")
    //   데이터를 받아먹는 다고 맹목적으로 @RequestBody을 달아버리면 안된다. 이 경우에는 json이 아니라 멀티파트+폼 데이터이기 때문
    // 그리고 클라이언트에서 멀티파트폼 데이터로 제대로 보내줘야, 받아먹을 수 있다. axios.post 요청 날리는 부분 참고
    public ResponseEntity<MemberDTO> join(MemberJoinDTO member) {
        MemberDTO memberDTo = service.join(member);
        return ResponseEntity.ok(memberDTo);
//        return ResponseEntity.ok(service.join(member));
    }

    @GetMapping("/{user_no}/avatar")
    public void getAvatar(@PathVariable String user_no, HttpServletResponse response) {
        String avatarPath = "c:/upload/avatar/" + user_no + ".png";
        File file = new File(avatarPath);
        if (!file.exists()) { // 아바타 등록이 없는 경우, 디폴트 아바타 이미지 사용
            file = new File("C:/upload/avatar/unknown.png");
        }
        UploadFiles.downloadImage(response, file);
    }

    @PutMapping("/{user_no}")
    public ResponseEntity<MemberDTO> updateProfileOrPassword(
            @PathVariable String user_no,
            @ModelAttribute MemberUpdateDTO member) {

        // 비밀번호가 변경되는 경우 처리
        if (member.getNewPassword() != null && !member.getNewPassword().isEmpty()) {
            ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
            changePasswordDTO.setUser_no(user_no);
            changePasswordDTO.setOldPassword(member.getOldPassword());
            changePasswordDTO.setNewPassword(member.getNewPassword());
            service.changePassword(changePasswordDTO);
        }

        // 프로필 변경 (비밀번호 변경 외의 정보 업데이트)
        MemberDTO updatedMember = service.update(member);

        return ResponseEntity.ok(updatedMember);
    }



}