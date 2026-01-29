package com.parking.system.exception;

public class SlotInUseException extends RuntimeException {
    public SlotInUseException(String message) {
        super(message);
    }
}
