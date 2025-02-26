package com.sportradar.mts.sdk.ws.exceptions;

/**
 * The ExceptionCode class represents the error codes used in the SDK exceptions.
 * Each code corresponds to a specific type of error.
 */
public class ExceptionCode {
    public static final int AuthTokenFailure = 1;
    public static final int WebSocketFailure = 2;
    public static final int SdkNotConnected = 3;
    public static final int ServerErrorResponse = 4;
    public static final int ProtocolMessageTooBig = 5;
    public static final int ProtocolSendBufferFull = 6;
    public static final int ProtocolSendFailed = 7;
    public static final int ProtocolInvalidResponse = 8;
    public static final int ProtocolInvalidRequest = 9;
}
