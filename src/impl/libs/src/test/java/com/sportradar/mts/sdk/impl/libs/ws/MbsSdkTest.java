package com.sportradar.mts.sdk.impl.libs.ws;

import org.junit.Test;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public class MbsSdkTest {

//    @Test
//    public void test() {
//        URI wsServer = URI.create("wss://wss.dataplane-nonprod.sportradar.dev");
//        URI authServer = URI.create("https://auth.sportradar.com/oauth/token");
//        String authClientId = "vIzVKcVxbaND3CnLbLcHNhfCJ3zlOSm6";
//        String authClientSecret = "L8k1uLhfL92tGyftuvj53n83atIAIaNIaTifzbrP0fnY5eteETvac-JK_Mkp6drq";
//        String authAudience = "mbs-dp-non-prod-wss";
//        long operatorId = 1234L;
//
//        MbsSdkConfig config = new MbsSdkConfig(wsServer, authServer, authClientId, authClientSecret, authAudience, operatorId);
//
//        try(MbsSdk mbsSdk = new MbsSdk(config)) {
//            mbsSdk.connect();
////            TicketRequest request = new TicketRequest();
////            String request = "{}";
//            String request = "{\"content\":{},\"operation\":\"ticket-placement\",\"correlationId\":\"asdf1234qwer5678_1\",\"operatorId\":1234,\"version\":\"3.0\",\"timestampUtc\":1234567890}";
//            String ticketResponse = mbsSdk.getTicketProtocol().sendString(request);
//            System.out.println(ticketResponse);
//        } catch (ExecutionException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
