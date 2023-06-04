package volodymyr.zabolotniy.cryptography

import java.security.MessageDigest

class HashingService {

    fun hashTest(hashingAlgorithm: String, text: String): String {
        val messageDigest = MessageDigest.getInstance(hashingAlgorithm)
        return String(messageDigest.digest(text.toByteArray()))
    }
}
