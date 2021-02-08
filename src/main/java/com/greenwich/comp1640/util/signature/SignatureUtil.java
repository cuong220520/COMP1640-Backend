package com.greenwich.comp1640.util.signature;

import java.security.*;
import java.security.spec.InvalidKeySpecException;

public interface SignatureUtil {
    KeyPair generateKeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException;

    String sign(String privateKeyStr, String message) throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, InvalidKeySpecException;

    String getPrivateKey(KeyPair keyPair);

    String getPublicKey(KeyPair keyPair);

    boolean verify(String message, String publicKeyStr, String sign) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, SignatureException;
}
