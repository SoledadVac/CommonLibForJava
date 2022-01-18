package com.common.lib.demo.bitcoincopycat;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/11/20
 * \* Time: 8:09 下午
 * \* Description:钱包
 * \
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements Serializable {
    private static final long serialVersionUID = -7848786859278946662L;
    private String address;//短地址
    private String publicKey; //公钥
    private String privateKey; //私钥

    public static Wallet generateNewWallet() {
        // 基于RSA算法生成对象
        KeyPairGenerator keyPairGen;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (Exception e) {
            return null;
        }
        //初始化密钥对生成器，密钥大小为1024位
        keyPairGen.initialize(512);  // 最小值是512，一般设置值为1024，该值越大加密信息越安全，对计算机消耗也越大
        //生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //得到私钥 RSAPrivateKey
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //得到公钥 RSAPublicKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        String address = Hashing.md5().newHasher().putString(publicKeyStr, Charsets.UTF_8).hash().toString();
        return new Wallet(address, publicKeyStr, privateKeyStr);
    }


}
