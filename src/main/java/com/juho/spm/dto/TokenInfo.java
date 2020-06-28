package com.juho.spm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenInfo {
    private int clientId;
    private String roomId;
    private int count;
    private long splashMoney;
    private String splashMoneyKey;
    private Date splashTime;
}
