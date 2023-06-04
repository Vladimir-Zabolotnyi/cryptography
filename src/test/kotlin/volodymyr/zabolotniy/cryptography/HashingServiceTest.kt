package volodymyr.zabolotniy.cryptography

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HashingServiceTest() {

    @Test
    fun hashTestWithSHA256() {
        // given
        val text = "Some text"
        val textCopy = "Some text"
        val textNotCorrectCopy = "Some texx"
        val hashingService = HashingService()

        // when
        val hashText = hashingService.hashTest("SHA-256", text)
        val hashTextCopy = hashingService.hashTest("SHA-256", textCopy)
        val hashTextNotCorrectCopy = hashingService.hashTest("SHA-256", textNotCorrectCopy)

        // then
        println(hashText)
        assertThat(hashText).isEqualTo(hashTextCopy)
        assertThat(hashText).isNotEqualTo(hashTextNotCorrectCopy)
        assertThat(hashText.length).isNotEqualTo(hashTextNotCorrectCopy.length)
    }
}
