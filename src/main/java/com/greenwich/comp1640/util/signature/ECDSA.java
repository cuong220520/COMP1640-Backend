package com.greenwich.comp1640.util.signature;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;

@Getter
@NoArgsConstructor
@Component
public class ECDSA implements SignatureUtil {
    private static final String SPEC = "secp256k1";
    private static final String SIGNING_ALGO = "EC";
    private static final String HASHING_ALGO = "SHA256withECDSA";

    @Override
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);
        KeyPairGenerator g = KeyPairGenerator.getInstance(SIGNING_ALGO);
        g.initialize(ecSpec, new SecureRandom());
        return g.generateKeyPair();
    }

    @Override
    public String sign(String privateKeyStr, String message) throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, InvalidKeySpecException {
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(SIGNING_ALGO);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        Signature ecdsaSign = Signature.getInstance(HASHING_ALGO);
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] signature = ecdsaSign.sign();
        return Base64.encodeBase64String(signature);
    }

    @Override
    public boolean verify(String message, String publicKeyStr, String sign) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, SignatureException {
        Signature ecdsaVerify = Signature.getInstance(HASHING_ALGO);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(SIGNING_ALGO);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message.getBytes(StandardCharsets.UTF_8));
        return ecdsaVerify.verify(Base64.decodeBase64(sign));
    }

    @Override
    public String getPrivateKey(KeyPair keyPair) {
        return Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
    }

    @Override
    public String getPublicKey(KeyPair keyPair) {
        return Base64.encodeBase64String(keyPair.getPublic().getEncoded());
    }

    public PrivateKey convertPrivKeyStrToKey(String privKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = java.util.Base64.getDecoder().decode(privKeyStr.trim().getBytes());

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance(SIGNING_ALGO);

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    public PublicKey convertPubKeyStrToPubKey(String pubKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyByte = java.util.Base64.getDecoder().decode(pubKeyStr.getBytes());

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyByte);

        KeyFactory keyFactory = KeyFactory.getInstance(SIGNING_ALGO);

        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;
    }
}
