import com.example.orderapp.domain.kotlin.Kot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NameTest {
    @Test
    fun testFullNameConcatenation() {
        val firstName = "길동"
        val lastName = "홍"
        val expectedFullName = "길동 홍"

        val fullName = "$firstName $lastName"

        assertEquals(expectedFullName, fullName)
    }
}