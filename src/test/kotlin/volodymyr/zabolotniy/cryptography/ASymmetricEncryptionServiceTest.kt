package volodymyr.zabolotniy.cryptography

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.crypto.IllegalBlockSizeException

class ASymmetricEncryptionServiceTest {

    @Test
    fun asymmetricEncrypt() {
        // given
        val text = "Devoxx!!"
        val aSymmetricEncryptionService = ASymmetricEncryptionService()
        val key = aSymmetricEncryptionService.generateKeyPair("RSA", 1024)

        // when
        val encryptedMessage = aSymmetricEncryptionService.aSymmetricEncrypt(key.private, "RSA", text)
        val decryptedMessage = aSymmetricEncryptionService.aSymmetricDecrypt(key.public, "RSA", encryptedMessage)

        // then
        assertThat(text).isEqualTo(String(decryptedMessage))
    }

    @Test
    fun asymmetricEncryptOfLargeText() {
        // given
        val text = "Devoxx!!".repeat(1000)
        val aSymmetricEncryptionService = ASymmetricEncryptionService()
        val key = aSymmetricEncryptionService.generateKeyPair("RSA", 1024)

        // when
        val exception = assertThrows<IllegalBlockSizeException> {
            aSymmetricEncryptionService.aSymmetricEncrypt(key.private, "RSA", text)
        }

        // then
        assertThat(exception.message).isEqualTo("Data must not be longer than 117 bytes")
    }
}
