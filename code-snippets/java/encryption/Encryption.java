package project.name.encryption; //NOSONAR

import project.name.encryption.impl.DefaultEncryption;


/**
 * Interface to provide the contract to use the encryption methods:
 *
 * @apiNote use example:
 *
 * <p> - Encryption.INSTANCE.encrypt(value)
 * <p> - Encryption.INSTANCE.decrypt(value)
 * <p> - Encryption.INSTANCE.isEncrypt(value)
 */
public interface Encryption {

    Encryption INSTANCE = new DefaultEncryption();

    /**
     * Method to encrypt value
     *
     * @throws EncryptionException when some problem occurs during encryption
     */
    String encrypt(String value);

    /**
     * Method to decrypt value
     *
     * @throws EncryptionException when some problem occurs during decryption
     */
    String decrypt(String value);

    /**
     * Method to return if value is encrypt
     *
     * @return decrypted content
     */
    Boolean isEncrypt(String value);

    /**
     * Method to set new secret to the default secret for the encryption instance
     */
    void setDefaultSecret(String newSecret);
}
