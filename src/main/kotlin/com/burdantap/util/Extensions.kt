package com.burdantap.util

import kotlin.random.Random

fun String.toSlug(): String {
    // Başındaki ve sonundaki boşlukları kaldır
    // Azerice karakterleri İngilizce eşdeğerleriyle değiştir
    // Map ile dönüştürülen karakterleri tekrar string haline getir
    // Küçük harfe dönüştür
    // Özel karakterleri ve boşlukları "-" ile değiştir
    // Birden fazla "-" karakterini tek "-" karakteri ile değiştir
    // Başındaki ve sonundaki "-" karakterlerini kaldır
    return this
        .trim()
        .toEnglish()
        .lowercase()
        .replace(Regex("[^a-z0-9]+"), "-")
        .replace(Regex("-+"), "-")
        .trim('-')
}

fun String.toEnglish(): String {
    val turkishChars = mapOf(
        'ç' to 'c', 'ə' to 'e', 'ğ' to 'g', 'ı' to 'i', 'ö' to 'o', 'ş' to 's', 'ü' to 'u',
        'Ç' to 'C', 'Ə' to 'E', 'Ğ' to 'G', 'I' to 'I', 'Ö' to 'O', 'Ş' to 'S', 'Ü' to 'U'
    )
    return this.map { turkishChars[it] ?: it }.joinToString("")
}

fun String.normalizeProductTitle(): String {
    // Harf ve rakam dışındaki karakterleri kaldırır
    // Baş ve sondaki boşlukları kaldırır
    // Aradaki fazla boşlukları tek bir boşlukla değiştirir
    return this.toEnglish()
        .replace(Regex("[^\\p{L}\\p{Nd}\\s]"), "")
        .trim()
        .replace(Regex("\\s+"), " ")
}

fun generateNineDigitNumber(): Int {
    // 9 haneli bir sayı 100_000_000 ile 999_999_999 arasında olmalıdır
    return Random.nextInt(100_000_000, 1_000_000_000)
}