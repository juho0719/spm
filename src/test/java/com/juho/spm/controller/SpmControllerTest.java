package com.juho.spm.controller;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpmControllerTest {

    // 뿌리기 API
    // 금액, 인원을 파라미터로 받음
    // 토큰 발급 후 응답으로 내려줌
    // 뿌릴 금액 인원수에 맞게 분배 저장
    // 토큰은 3자리 문자열

    // 받기 API
    // 토큰을 요청값으로
    // 할당되지 않은 금액 할당하여 응답으로 내려줌
    // 한 사용자당 한번만 가능
    // 같은 대화방내 사람만 가능
    // 뿌린건 유효시간 10분, 이후에는 실패응답


    // 조회 API
    // 토큰 요청값으로
    // 뿌린건의 대한 현재 상태 응답값으로
        // 뿌린시간, 금액, 받기 완료금액, 받기 완료 정보([받은 금액, 사용자아이]리스트)
    // 뿌린사람만 조회 가능
    // 유효기간 7일
}
