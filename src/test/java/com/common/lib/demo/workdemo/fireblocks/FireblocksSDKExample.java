package com.common.lib.demo.workdemo.fireblocks;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FireblocksSDKExample {
    public FireblocksSDKExample() throws JSONException {
        StringBuilder secretBuilder = new StringBuilder();
        String API_SECRET_PATH = "<API-SECRET-KEY-PATH>";
        try(Stream<String> stream = Files.lines(Paths.get(API_SECRET_PATH), StandardCharsets.UTF_8)){
            stream.forEach(s -> secretBuilder.append(s).append("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String API_KEY = "<API-KEY>";
        String BASE_URL = "https://sandbox-api.fireblocks.io"; // Choose the right api url for your workspace type

        FireblocksHttpClient httpClient = new FireblocksHttpClient(API_KEY, secretBuilder.toString(), BASE_URL);

        JSONObject getVaultsResponse = httpClient.get("/v1/vault/accounts_paged");
        System.out.println(getVaultsResponse.toString(4));

        JSONObject newVaultRequest = new JSONObject().put("name", "QuickStart_Vault3");
        JSONObject newVaultResponse = httpClient.post("/v1/vault/accounts", newVaultRequest);
        System.out.println(newVaultResponse.toString(4));

        getVaultsResponse = httpClient.get("/v1/vault/accounts_paged");
        System.out.println(getVaultsResponse.toString(4));
    }


}
