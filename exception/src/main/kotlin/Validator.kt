abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class FirstLastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "[А-Яа-я]+".toRegex()
        if (value.isNullOrEmpty())
            return listOf(ErrorCode.NULL_OR_EMPTY)
        else if (value.length > 16)
            return listOf(ErrorCode.INVALID_LENGTH)
        else if (!value.matches(pattern))
            return listOf(ErrorCode.INVALID_CHARACTER)
        return emptyList()
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "(7|8)[0-9]+".toRegex()
        if (value.isNullOrEmpty())
            return listOf(ErrorCode.NULL_OR_EMPTY)
        else if (value.length != 11)
            return listOf(ErrorCode.INVALID_LENGTH)
        else if (!value.matches(pattern))
            return listOf(ErrorCode.INVALID_CHARACTER)
        return emptyList()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "[a-zA-z]+@[a-zA-z]+.(ru|com)".toRegex()
        if (value.isNullOrEmpty())
            return listOf(ErrorCode.NULL_OR_EMPTY)
        else if (value.length > 32)
            return listOf(ErrorCode.INVALID_LENGTH)
        else if (!value.matches(pattern))
            return listOf(ErrorCode.INVALID_CHARACTER)
        return emptyList()
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "[0-9]+".toRegex()
        if (value.isNullOrEmpty())
            return listOf(ErrorCode.NULL_OR_EMPTY)
        else if (value.length > 11)
            return listOf(ErrorCode.INVALID_LENGTH)
        else if (!value.matches(pattern))
            return listOf(ErrorCode.INVALID_CHARACTER)
        else if (countNumber(value) != value.substring(9, 11).toInt())
            return listOf(ErrorCode.INVALID_NUMBER)
        return emptyList()
    }

    private fun countNumber(value: String): Int {
        var result = 0
        for (i in 9 downTo 1) {
            result += value[9 - i].digitToInt() * i
        }
        if (result == 100)
            return 0
        else if (result < 100)
            return result
        else {
            val remain = result % 101
            if (remain == 100)
                return 0
            else
                return remain
        }
    }
}
