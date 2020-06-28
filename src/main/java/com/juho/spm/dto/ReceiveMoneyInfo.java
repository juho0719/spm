package com.juho.spm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter @Getter
public class ReceiveMoneyInfo implements Serializable {
    private long receiveMoney;
    private boolean isReceive;
    private int clientId;
}
