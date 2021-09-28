class ValidationException(val errorCode: Array<ErrorCode>) :
    RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Недопустимая длина"),
    INVALID_NUMBER(102, "Недопустимое контрольное число"),
    NULL_OR_EMPTY(103, "Пустая строка")
}