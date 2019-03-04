package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Slf4j
public class MemberVO {
    private int memberSeq;
    private String memberId;
    private String memberPw;
    private String memberName;

    public String toString(MemberVO memberVO) {
        return String.format("\nMEMBER INFO \n" +
                "memberId : %s\n" +
                "memberPw : %s\n" +
                "memberName : %s",
                memberVO.getMemberId(), memberVO.getMemberPw(), memberVO.getMemberName());
    }
}
