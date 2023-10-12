package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TimeStampDTO {
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    private LocalDateTime timeStamp;

}
