package com.example.apigateway;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

// this class is to make it possible for the API gateway to access the Car Service
@Component
public class UserFeignClientInterceptor implements RequestInterceptor { // RequestInterceptor is from feign
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";
    private final OAuth2AuthorizedClientService clientService;

    public UserFeignClientInterceptor(OAuth2AuthorizedClientService clientService) { // takes in OAuth2AuthorizedClientService as dependency
        this.clientService = clientService;
    }

    @Override
    public void apply(RequestTemplate template) {
        // getting oauth token from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        // loads okta client
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName());
        // gets access token that's already been set for the client and passes it as authorization header
        OAuth2AccessToken accessToken = client.getAccessToken();
        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, accessToken.getTokenValue()));
    }
}
