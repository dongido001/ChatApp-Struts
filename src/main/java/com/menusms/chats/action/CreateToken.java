package com.menusms.chats.action;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;

class CreateToken {

    static String token(String identity) {
        String twilioAccountSid = System.getenv("TWILIO_ACCOUNT_SID");
        String twilioApiKey = System.getenv("TWILIO_API_KEY");
        String twilioApiSecret = System.getenv("TWILIO_API_SECRET");

        String serviceSid = System.getenv("TWILIO_IPM_SERVICE_SID");
        String deviceId = "browser";
        String appName = "chatX";
        String endpointId = appName + ":" + identity + ":" + deviceId;

        ChatGrant grant = new ChatGrant();
        grant.setEndpointId(endpointId);
        grant.setServiceSid(serviceSid);

        AccessToken token = new AccessToken.Builder(twilioAccountSid, twilioApiKey, twilioApiSecret)
                .identity(identity).grant(grant).build();

        return token.toJwt();
    }

}