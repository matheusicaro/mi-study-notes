package project.name.test; //NOSONAR

import project.name.Encryption;
import project.name.EncryptionException;
import project.name.impl.DefaultEncryption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class DefaultEncryptionTest {

    @BeforeEach()
    void setUp() {
        Encryption.INSTANCE.setDefaultSecret("@secret:ENCRYPTION_SECRET");
    }

    @Test
    void encrypt_and_decrypt_should_return_the_expected_values() {
        String value = "valueHere";
        String encrypted = Encryption.INSTANCE.encrypt(value);
        Assertions.assertNotEquals(encrypted, value);
        Assertions.assertEquals(value, Encryption.INSTANCE.decrypt(encrypted));
    }

    @Test
    void should_return_if_values_is_encrypted() {
        String notEncrypted = "value not encrypted";
        String encrypted = Encryption.INSTANCE.encrypt("value to be encrypted");
        Assertions.assertTrue(Encryption.INSTANCE.isEncrypt(encrypted));
        Assertions.assertFalse(Encryption.INSTANCE.isEncrypt(notEncrypted));
    }

    @Test
    void decrypt_should_return_expected_error_if_value_informed_is_not_encrypted() {
        try {
            Assertions.assertNull(Encryption.INSTANCE.decrypt("value not encrypted"));
        } catch (Exception exception) {
            Assertions.assertTrue(exception instanceof EncryptionException);
            Assertions.assertEquals("Encryption error (Operation: 'value is unknown or is not encrypted'), input: value not encrypted",
                exception.getMessage());
        }
    }

    @Test
    void decrypt_should_return_expected_error_if_something_is_not_going_well() {
        try {
            Encryption.INSTANCE.decrypt("value");
            Assertions.fail();
        } catch (Exception exception) {
            Assertions.assertTrue(exception instanceof EncryptionException);
            Assertions.assertEquals("Encryption error (Operation: 'value is unknown or is not encrypted'), input: value", exception.getMessage());
        }
    }

    @Test
    void encrypt_should_return_expected_exception_if_environment_variable_was_not_informed() {
        try {
            Encryption.INSTANCE.setDefaultSecret(null);
            Encryption.INSTANCE.encrypt("VALUE");
            Assertions.fail();
        } catch (Exception exception) {
            Assertions.assertTrue(exception instanceof EncryptionException);
            Assertions.assertEquals("The secret is invalid! It is necessary to set a secret through the method [setDefaultSecret]", exception.getMessage());
        }
    }

    @Test
    void encrypt_should_return_expected_exception_and_message_detail_if_secret_dont_start_with_the_required_prefix() {
        try {
            Encryption.INSTANCE.setDefaultSecret("SECRET");
            Encryption.INSTANCE.encrypt("VALUE");
            Assertions.fail();
        } catch (Exception exception) {
            Assertions.assertTrue(exception instanceof EncryptionException);
            Assertions.assertEquals("The secret should be started with the prefix: \"@secret:\"", exception.getMessage());
        }
    }
}