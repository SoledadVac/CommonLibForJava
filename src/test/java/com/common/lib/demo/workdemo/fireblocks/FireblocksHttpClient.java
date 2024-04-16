package com.common.lib.demo.workdemo.fireblocks;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.jersey.core.util.Base64;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class FireblocksHttpClient {

    private String baseUrl = "https://api.fireblocks.io";
    private final String apiKey;

    private final PrivateKey privateKey;
    private final OkHttpClient client;

    public FireblocksHttpClient(String apiKey, String apiSecret) {
        this(apiKey, apiSecret, "https://api.fireblocks.io");
    }

    public FireblocksHttpClient(String apiKey, String apiSecret, String baseUrl) {
        Objects.requireNonNull(apiKey);
        Objects.requireNonNull(apiSecret);
        if (baseUrl != null && !this.baseUrl.equals(baseUrl)) {
            this.baseUrl = baseUrl;
        }
        this.apiKey = apiKey;
        this.client = new OkHttpClient();
        try {
            byte[] keyBytes = Base64.decode(apiSecret.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replace("\n", ""));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            this.privateKey = factory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject get(String path) {
        Request req = new Request.Builder()
                .url(this.baseUrl + path)
                .addHeader("X-API-Key", this.apiKey)
                .addHeader("Authorization", "Bearer " + this.signJwt(path))
                .build();
        try (Response resp = this.client.newCall(req).execute()) {
            ResponseBody body = resp.body();
            if (body == null) {
                return new JSONObject();
            }
            return new JSONObject(body.string());
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject post(String path, JSONObject data) {

        Request req = new Request.Builder()
                .url(this.baseUrl + path)
                .addHeader("X-API-Key", this.apiKey)
                .addHeader("Authorization", "Bearer " + this.signJwt(path, data.toString()))
                .post(RequestBody.create(data.toString().getBytes(), MediaType.parse("application/json; charset=utf-8")))
                .build();
        try (Response resp = this.client.newCall(req).execute()) {
            ResponseBody body = resp.body();
            if (body == null) {
                return new JSONObject();
            }
            return new JSONObject(body.string());
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String signJwt(String path) {
        return this.signJwt(path, "");
    }

    private String signJwt(String path, String dataJSONString) {
        String bodyHash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(dataJSONString.getBytes());
            BigInteger number = new BigInteger(1, digest.digest());
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 64) {
                hexString.insert(0, '0');
            }
            bodyHash = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Algorithm algo = Algorithm.RSA256((RSAKey) this.privateKey);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 55);
        return JWT.create()
                .withSubject(this.apiKey)
                .withIssuedAt(new Date())
                .withExpiresAt(cal.getTime())
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("uri", path)
                .withClaim("bodyHash", bodyHash)
                .sign(algo);
    }


    public static void main(String[] args) throws JSONException {
        new FireblocksSDKExample();
    }
}
