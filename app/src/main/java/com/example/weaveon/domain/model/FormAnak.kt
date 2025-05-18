package com.example.weaveon.domain.model

sealed class QuestionType {
    object Radio : QuestionType()
    object Checkbox : QuestionType()
}

data class Question(
    val id: Int,
    val title: String,
    val type: QuestionType,
    val options: List<String>
)

val formQuestions = listOf(
    Question(
        id = 1,
        title = "Status Diagnosa Autisme",
        type = QuestionType.Radio,
        options = listOf(
            "Sudah didiagnosis profesional",
            "Belum didiagnosis profesional",
            "Dalam proses evaluasi"
        )
    ),
    Question(
        id = 2,
        title = "Bagaimana kemampuan bicara anak saat ini?",
        type = QuestionType.Radio,
        options = listOf(
            "Belum bisa Bicara",
            "Bicara satu-dua kata",
            "Bicara sederhana",
            "Lancar berbicara"
        )
    ),
    Question(
        id = 3,
        title = "Apakah anak sering menggunakan isyarat atau non-verbal untuk berkomunikasi?",
        type = QuestionType.Radio,
        options = listOf(
            "Ya",
            "Tidak",
            "Kadang-kadang"
        )
    ),
    Question(
        id = 4,
        title = "Berdasarkan pengamatan Anda, anak lebih tertarik pada",
        type = QuestionType.Checkbox,
        options = listOf(
            "Gambar / Warna / bentuk (Visual)",
            "Musik / suara / lagu (Auditori)",
            "Gerakan / bermain fisik (Kinestetik)"
        )
    ),
    Question(
        id = 5,
        title = "Anak sensitif terhadap",
        type = QuestionType.Checkbox,
        options = listOf(
            "Suara keras",
            "Tekstur tertentu",
            "Cahaya",
            "Tidak sensitif sama sekali"
        )
    ),
    Question(
        id = 6,
        title = "Saat overstimulated, anak biasanya",
        type = QuestionType.Checkbox,
        options = listOf(
            "Menangis",
            "Menutup telinga/mata",
            "Menghindar",
            "Tidak merespons"
        )
    ),
    Question(
        id = 7,
        title = "Anak sering melakukan",
        type = QuestionType.Checkbox,
        options = listOf(
            "Mengayun tubuh",
            "Mengepak tangan",
            "Mengulang kata",
            "Menyusun objek"
        )
    ),
    Question(
        id = 8,
        title = "Apakah anak menyukai kontak mata?",
        type = QuestionType.Radio,
        options = listOf(
            "Ya",
            "Tidak",
            "Kadang-kadang"
        )
    ),
    Question(
        id = 9,
        title = "Apakah anak tertarik bermain dengan orang lain?",
        type = QuestionType.Radio,
        options = listOf(
            "Ya",
            "Tidak",
            "Hanya dengan orang tertentu"
        )
    ),
    Question(
        id = 10,
        title = "Area yang paling ingin dikembangkan saat ini?",
        type = QuestionType.Checkbox,
        options = listOf(
            "Komunikasi",
            "Kemandirian",
            "Emosi dan ekspresi",
            "Sosialisasi",
            "Kretivitas",
            "Fokus dan perhatian"
        )
    )
)

