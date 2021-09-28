import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation phone length error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_length.json")
        val exception=assertThrows<ValidationException>("Неверная длина номера телефона") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_LENGTH, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation phone start error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_start.json")
        val exception=assertThrows<ValidationException>("Номер телефона начинается не с 7 или 8") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation name length error`() {
        val client = getClientFromJson("/fail/user_with_bad_name.json")
        val exception=assertThrows<ValidationException>("Недопустимые символы имени/фамилии") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[1])
    }

    @Test
    fun `fail save client - validation email error`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception=assertThrows<ValidationException>("Недопустимые символы email") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation snils error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception=assertThrows<ValidationException>("Недопустимые символы snils") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_NUMBER, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_LENGTH, exception.errorCode[0])
        assertEquals(ErrorCode.NULL_OR_EMPTY, exception.errorCode[1])
        assertEquals(ErrorCode.INVALID_LENGTH, exception.errorCode[2])
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[3])
        assertEquals(ErrorCode.INVALID_NUMBER, exception.errorCode[4])
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}