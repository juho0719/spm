package com.juho.spm.service;

import com.juho.spm.dto.ReceiveMoneyInfo;
import com.juho.spm.dto.TokenInfo;
import com.juho.spm.repository.SplashRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SplashService {

    private final TokenService tokenService;
    private final SplashRepository splashRepository;

    public List<Long> devideMoney(long money, int count) {
        List<Long> moneyList = new ArrayList<>();
        long remainMoney = money;
        for(int i=0; i<count; i++) {
            if(i == count-1) {
                moneyList.add(remainMoney);
                break;
            }

            long devidedMoney = money / count;
            moneyList.add(devidedMoney);

            remainMoney -= devidedMoney;
        }

        return moneyList;
    }

    public TokenInfo storeSplashMoney(int clientId, String roomId, int count, long splashMoney) {
        // 현재 시간 구하기
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();

        // 토큰 생성
        TokenInfo tokenInfo = tokenService.getToken(clientId, roomId, count, splashMoney, currentTime);

        // 유효 시간 구하기
        calendar.add(Calendar.MINUTE, 10);
        Date validReceiveTime = calendar.getTime();

        // 분배금 가져오기
        List<Long> devideMoneyList = devideMoney(splashMoney, count);

        // 뿌리기 데이터 세팅
        devideMoneyList.forEach(devideMoney -> {
            ReceiveMoneyInfo receiveMoneyInfo = new ReceiveMoneyInfo();
            receiveMoneyInfo.setClientId(0);
            receiveMoneyInfo.setReceive(false);
            receiveMoneyInfo.setReceiveMoney(devideMoney);
            splashRepository.add(tokenInfo.getSplashMoneyKey(), receiveMoneyInfo);
        });

        return tokenInfo;
    }
}
