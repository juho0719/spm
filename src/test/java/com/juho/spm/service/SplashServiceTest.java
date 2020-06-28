package com.juho.spm.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class SplashServiceTest {

    private SplashService splashService;
    // 금액, 인원을 파라미터로 받음
    // 토큰 발급 후 응답으로 내려줌
    // 뿌릴 금액 인원수에 맞게 분배 저장
    // 토큰은 3자리 문자열

    @BeforeAll
    public void setUp() {
        splashService = new SplashService();
    }


    // 토큰 발급 (3자리문자열)
    @Test
    public void createTokenTest() {
        //given
        Pattern pattern = Pattern.compile("[a-zA-Z]{3}");

        //when
        String token = splashService.createToken();
        Matcher matcher = pattern.matcher(token);

        //then
        assertEquals(matcher.find(), true);     // 영문자 3자리 체크
    }

    // 금액 분배
    @Test
    public void devideMoneyTest() {
        // given
        long money = 10000L;
        int count = 3;
        long sumMoney;

        // when
        List<Long> devidedMoneyList = splashService.devideMoney(money, count);
        sumMoney = devidedMoneyList.stream().mapToLong(devidedMoney -> devidedMoney).sum();

        // then
        assertEquals(money, sumMoney);
    }

    // 뿌리기 저장
    @Test
    public void storeSplashMoneyTest() {
        // given
        int clientId = 1;
        String roomId = "testRoom";
        long splashMoney = 3000;

        // when
        String token = splashService.storeSplashMoney(clientId, roomId, splashMoney);

    }
}