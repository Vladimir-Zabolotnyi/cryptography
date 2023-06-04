package volodymyr.zabolotniy.cryptography

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SymmetricEncryptionServiceTest {

    @Test
    fun symmetricECBEncrypt() {
        // given
        val text = "Devoxx!!"
        val textRepeated = "Devoxx!!".repeat(16)
        val symmetricEncryptionService = SymmetricEncryptionService()
        val key = symmetricEncryptionService.generateKey("AES", 192)

        // when
        val encryptedMessage = symmetricEncryptionService.symmetricEncrypt(key, "AES/ECB/PKCS5Padding", text)
        val decryptedMessage =
            symmetricEncryptionService.symmetricDecrypt(key, "AES/ECB/PKCS5Padding", encryptedMessage)

        // and
        val encryptedMessageRepeated =
            symmetricEncryptionService.symmetricEncrypt(key, "AES/ECB/PKCS5Padding", textRepeated)

        // then
        assertThat(text).isEqualTo(String(decryptedMessage))
        println(String(encryptedMessageRepeated)) // repeated values
    }

    @Test
    fun symmetricCBCEncrypt() {
        // given
        val text = "Devoxx!!"
        val textRepeated = "Devoxx!!".repeat(16)
        val symmetricEncryptionService = SymmetricEncryptionService()
        val key = symmetricEncryptionService.generateKey("AES", 192)
        val ivSpec = symmetricEncryptionService.generateSecureRandom("SHA1PRNG")

        // when
        val encryptedMessage = symmetricEncryptionService.symmetricEncrypt(key, "AES/CBC/PKCS5Padding", text, ivSpec)
        val decryptedMessage =
            symmetricEncryptionService.symmetricDecrypt(key, "AES/CBC/PKCS5Padding", encryptedMessage, ivSpec)

        // and
        val encryptedMessageRepeated =
            symmetricEncryptionService.symmetricEncrypt(key, "AES/CBC/PKCS5Padding", textRepeated)

        // then
        assertThat(text).isEqualTo(String(decryptedMessage))
        println(String(encryptedMessageRepeated)) // not repeated values
    }
}
