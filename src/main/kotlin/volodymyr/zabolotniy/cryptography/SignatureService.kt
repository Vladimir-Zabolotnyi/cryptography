package volodymyr.zabolotniy.cryptography

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature

class SignatureService {

    fun generateKeyPair(keyPairGeneratorAlgorithm: String, keySize: Int): KeyPair {
        val keyGenerator = KeyPairGenerator.getInstance(keyPairGeneratorAlgorithm)
        keyGenerator.initialize(keySize)
        return keyGenerator.generateKeyPair()
    }

    fun getSignature(signatureAlgorithmName: String, privateKey: PrivateKey, data: ByteArray): ByteArray {
        val signatureAlgorithm = Signature.getInstance(signatureAlgorithmName)
        signatureAlgorithm.initSign(privateKey)
        signatureAlgorithm.update(data)
        return signatureAlgorithm.sign()
    }

    fun verifySignature(
        signatureAlgorithmName: String,
        publicKey: PublicKey,
        data: ByteArray,
        signature: ByteArray,
    ): Boolean {
        val verificationAlgorithm = Signature.getInstance(signatureAlgorithmName)
        verificationAlgorithm.initVerify(publicKey)
        verificationAlgorithm.update(data)
        return verificationAlgorithm.verify(signature)
    }
}
