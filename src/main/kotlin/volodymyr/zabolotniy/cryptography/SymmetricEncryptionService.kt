package volodymyr.zabolotniy.cryptography

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class SymmetricEncryptionService {

    fun symmetricEncrypt(key: SecretKey, cipherTransportation: String, text: String): ByteArray {
        val cipher = Cipher.getInstance(cipherTransportation)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(text.toByteArray())
    }

    fun symmetricEncrypt(key: SecretKey, cipherTransportation: String, text: String, ivSpec: IvParameterSpec): ByteArray {
        val cipher = Cipher.getInstance(cipherTransportation)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)
        return cipher.doFinal(text.toByteArray())
    }

    fun symmetricDecrypt(key: SecretKey, cipherTransportation: String, encryptedMessage: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(cipherTransportation)
        cipher.init(Cipher.DECRYPT_MODE, key)
        return cipher.doFinal(encryptedMessage)
    }

    fun symmetricDecrypt(key: SecretKey, cipherTransportation: String, encryptedMessage: ByteArray, ivSpec: IvParameterSpec): ByteArray {
        val cipher = Cipher.getInstance(cipherTransportation)
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
        return cipher.doFinal(encryptedMessage)
    }

    fun generateKey(keyGeneratorAlgorithm: String, keySize: Int): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(keyGeneratorAlgorithm)
        keyGenerator.init(keySize)
        return keyGenerator.generateKey()
    }

    fun generateSecureRandom(secureRandomGeneratorAlgorithm: String): IvParameterSpec {
        val secureRandom = SecureRandom.getInstance(secureRandomGeneratorAlgorithm)
        val random = ByteArray(16)
        secureRandom.nextBytes(random)
        return IvParameterSpec(random)
    }
}
