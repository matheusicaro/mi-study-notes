package project.name.encryption.impl; //NOSONAR

import project.name.encryption.Encryption;
import project.name.encryption.EncryptionException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultEncryption implements Encryption {

    /**
     * This prefix is so important to maintain the truly secret desired. Also, it avoids a wrong automatic secret was being set.
     */
    private static final String PREFIX_OF_EXPECTED_SECRET_KEY = "@secret:";
    private String SECRET;
    private SecretKeySpec secretKey;

    private static final String PREFIX_OF_ENCRYPTED_VALUE = "enc_";
    private static final String CIPHER_TRANSFORMATION_ECB = "AES/ECB/PKCS5PADDING";
    private static final String SECRET_KEY_SPEC = "AES";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * The method is responsible to encrypt value
     *
     * @return <String>
     * @throws EncryptionException when some problem occurs during decryption
     */
    @Override
    public String encrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_ECB); //NOSONAR
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            String valueEncrypted = Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes(UTF_8)));
            return addPrefix(valueEncrypted);
        } catch (EncryptionException e) {
            throw e;
        } catch (Exception e) {
            throw new EncryptionException("Encryption error (Operation: 'encrypt key/value'), input: " + value, e);
        }
    }

    /**
     * The method is responsible to decrypt value
     *
     * @return <String>
     * @throws EncryptionException when some problem occurs during decryption
     */
    @Override
    public String decrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_ECB); //NOSONAR
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            return new String(cipher.doFinal(Base64.getDecoder().decode(removePrefix(value))));
        } catch (EncryptionException e) {
            throw e;
        } catch (IndexOutOfBoundsException e) {
            throw new EncryptionException("Encryption error (Operation: 'value is unknown or is not encrypted'), input: " + value, e);
        } catch (Exception e) {
            throw new EncryptionException("Encryption error (Operation: 'decrypt key/value'), input: " + value, e);
        }
    }

    /**
     * The method is responsible to verify if value is encrypt through the default prefix
     *
     * @return <Boolean>
     */
    @Override
    public Boolean isEncrypt(String value) {
        Objects.requireNonNull(value);
        return value.startsWith(PREFIX_OF_ENCRYPTED_VALUE);
    }

    @Override
    public void setDefaultSecret(String newSecret) {
        this.SECRET = newSecret;
        this.secretKey = null;
    }

    private String addPrefix(String value) {
        return PREFIX_OF_ENCRYPTED_VALUE.concat(value);
    }

    private String removePrefix(String value) {
        return value.split(PREFIX_OF_ENCRYPTED_VALUE)[1];
    }

    private SecretKeySpec getSecretKey() throws NoSuchAlgorithmException {

        if (this.secretKey == null) {

            if (this.SECRET == null) {
                throw new EncryptionException(
                    "The secret is invalid! It is necessary to set a secret through the method [setDefaultSecret]");
            }

            if (!this.SECRET.startsWith(PREFIX_OF_EXPECTED_SECRET_KEY)) {
                throw new EncryptionException("The secret should be started with the prefix: \"" + PREFIX_OF_EXPECTED_SECRET_KEY + "\"");
            }

            String secret = this.SECRET.split(PREFIX_OF_EXPECTED_SECRET_KEY)[1];

            byte[] key = secret.getBytes(UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, SECRET_KEY_SPEC);
        }

        return this.secretKey;
    }
}
