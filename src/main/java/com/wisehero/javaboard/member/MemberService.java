package com.wisehero.javaboard.member;

import com.wisehero.javaboard.support.error.CoreException;
import com.wisehero.javaboard.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberRepository;

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND, "회원이 존재하지 않습니다. ID: " + memberId));
    }
}
