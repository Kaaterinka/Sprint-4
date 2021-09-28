package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество не указано",
    val age: Int = 18,
    val averageRate: Double,
    val city: String = "Город не указан",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Информация о предыдущем образовании не указана",
)
