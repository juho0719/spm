package com.juho.spm.service;

import com.juho.spm.dto.TokenInfo;
import com.juho.spm.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
    private final Random random = new Random();
    private final TokenRepository tokenRepository;

    String generateToken() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++) {
            sb.append((char)(random.nextInt(26) + 65));
        }

        return sb.toString();
    }

    public TokenInfo getToken(int clientId, String roomId, int count, long splashMoney, Date currentTime) {
        String token = generateToken();

        while(tokenRepository.get(token).isPresent()) {
            token = generateToken();
        }

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setClientId(clientId);
        tokenInfo.setRoomId(roomId);
        tokenInfo.setCount(count);
        tokenInfo.setSplashMoney(splashMoney);
        tokenInfo.setSplashTime(currentTime);
        tokenInfo.setSplashMoneyKey(Arrays.asList(String.valueOf(clientId),roomId,token).stream()
                                        .collect(Collectors.joining()));
        tokenRepository.add(token, tokenInfo);

        return tokenInfo;
    }
}
