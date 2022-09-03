package io.study.security_basic.auth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;

    public FacebookUserInfo(Map<String, Object> attriburesMap) {
        this.attributes = attriburesMap;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}
