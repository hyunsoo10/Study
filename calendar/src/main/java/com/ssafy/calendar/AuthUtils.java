package com.ssafy.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import java.util.List;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Component
public class AuthUtils {

    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private final static JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Arrays.asList("email", "profile", "https://www.googleapis.com/auth/calendar.readonly");

    public static Credential getCredentials(HttpTransport httpTransport, String clientId, String clientSecret) throws IOException {
        InputStream in = AuthUtils.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build();

        TokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
            httpTransport, JSON_FACTORY, clientId, clientSecret, "authorization_code")
            .setRedirectUri("http://localhost:8080/login/oauth2/code/google")
            .execute();

        return flow.createAndStoreCredential(tokenResponse, null);
    }
}
