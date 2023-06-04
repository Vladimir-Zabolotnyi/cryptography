package volodymyr.zabolotniy.cryptography

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SignatureServiceTest {

    @Test
    fun signVerification() {
        // given
        val text = "Devoxx!!"
        val signatureService = SignatureService()
        val keyPair = signatureService.generateKeyPair("RSA", 1024)

        // when
        val signature = signatureService.getSignature("SHA256WithRSA", keyPair.private, text.toByteArray())
        val verifySignature =
            signatureService.verifySignature("SHA256WithRSA", keyPair.public, text.toByteArray(), signature)

        assertThat(verifySignature).isTrue()
    }
}
