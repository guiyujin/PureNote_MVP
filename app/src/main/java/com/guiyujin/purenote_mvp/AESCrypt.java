package com.guiyujin.purenote_mvp;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ProjectName: test
 * @Package: com.guiyujin.encryptTest.sec
 * @ClassName: AES
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/10/5 11:58
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/10/5 11:58
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public final class AESCrypt {
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";

    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private static final String HASH_ALGORITHM = "SHA-256";


    private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    public static String encrypt(final String password, String message)
            throws GeneralSecurityException {

        try {
            final SecretKeySpec key = generateKey(password);
            byte[] cipherText = encrypt(key, ivBytes, message.getBytes(CHARSET));
            String encoded = Base64.encodeToString(cipherText, Base64.NO_WRAP);
            return encoded;
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }


    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = cipher.doFinal(message);
        return cipherText;
    }


    public static String decrypt(final String password, String base64EncodedCipherText)
            throws GeneralSecurityException {
        try {
            final SecretKeySpec key = generateKey(password);
            byte[] decodedCipherText = Base64.decode(base64EncodedCipherText, Base64.NO_WRAP);
            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);
            String message = new String(decryptedBytes, CHARSET);
            return message;
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }



    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
        return decryptedBytes;
    }
}
