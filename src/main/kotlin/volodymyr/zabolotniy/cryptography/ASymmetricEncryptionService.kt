package volodymyr.zabolotniy.cryptography

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher

class ASymmetricEncryptionService {

    fun aSymmetricEncrypt(key: PrivateKey, cipherTransportation: String, text: String): ByteArray {
        val cipher = Cipher.getInstance(cipherTransportation)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(text.toByteArray())
    }

    fun aSymmetricDecrypt(key: PublicKey, cipherTransportation: String, encryptedMessage: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(cipherTransportation)
        cipher.init(Cipher.DECRYPT_MODE, key)
        return cipher.doFinal(encryptedMessage)
    }

    fun generateKeyPair(keyPairGeneratorAlgorithm: String, keySize: Int): KeyPair {
        val keyGenerator = KeyPairGenerator.getInstance(keyPairGeneratorAlgorithm)
        keyGenerator.initialize(keySize)
        return keyGenerator.generateKeyPair()
    }
}
