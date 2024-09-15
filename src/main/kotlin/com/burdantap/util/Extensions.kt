package com.burdantap.util

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