package com.example.weaveon.data.model

object FormCategories {

    val list = listOf(
        FormCategory(
            categoryId = "komunikasi_bahasa",
            questionId = "2_3",
            question = "Kemampuan bicara anak_Anak sering menggunakan isyarat atau non-verbal",
            relatedKeywords = listOf(
                "bicara", "berbicara", "ngomong", "ngoceh", "ngomel", "bicara sendiri", "berkata", "ngobrol",
                "kata", "kalimat", "ucapan", "mengucap", "mengucapkan", "menyebut", "berdialog", "berkomunikasi",
                "komunikasi", "menyampaikan", "menuturkan", "ngobrol", "omong",
                "isyarat", "nonverbal", "bahasa tubuh", "gesture", "gerakan tangan", "gerak tubuh",
                "komunikasi nonverbal", "bahasa isyarat", "mime", "tanda", "gestur", "ekspresi wajah",
                "menunjuk", "mengekspresikan tanpa kata", "tubuh bicara"
            )
        ),

        FormCategory(
            categoryId = "gaya_belajar",
            questionId = "4",
            question = "Anak lebih tertarik pada",
            relatedKeywords = listOf(
                "visual", "melihat", "gambar", "warna", "bentuk", "mata", "ilustrasi", "diagram", "video", "tampilan", "pandangan",
                "auditori", "musik", "suara", "lagu", "mendengar", "audio", "melodi", "pendengaran", "irama", "nada", "bunyi",
                "kinestetik", "gerak", "fisik", "tangan", "kaki", "bergerak", "melakukan", "bermain", "aktif", "sentuhan", "ekspresi fisik"
            )
        ),

        FormCategory(
            categoryId = "respons_sensorik_dan_reaksi",
            questionId = "5_6",
            question = "Anak sensitif terhadap_Saat overstimulated, anak biasanya",
            relatedKeywords = listOf(
                "sensitif", "sensitivitas", "peka", "tidak peka", "tidak sensitif",
                "suara keras", "bising", "noise", "tekstur", "kasar", "halus", "cahaya", "terang", "lampu",
                "sensori", "sensorik", "stimulus", "stimulasi", "rangsangan", "rangsang",
                "menangis", "nangis", "tangisan", "sedih", "reaksi", "emosi", "emosional", "tutup telinga", "tutup mata",
                "menghindar", "menjauh", "panik", "tidak merespon", "tidak respons", "reaksi berlebihan",
                "overstimulated", "kewalahan", "stress", "ketakutan", "fobia", "gelisah", "marah"
            )
        ),

        FormCategory(
            categoryId = "perilaku_repetitif",
            questionId = "7",
            question = "Anak sering melakukan",
            relatedKeywords = listOf(
                "mengayun", "ayunan", "mengepak", "kepakan", "mengulang", "kata berulang", "pengulangan", "diulang-ulang",
                "menyusun", "menata", "merapikan", "bermain berulang", "ritual", "gerak berulang",
                "kebiasaan", "perilaku berulang", "repetitif", "kegiatan sama terus", "rutinitas", "kegiatan berulang",
                "pengulangan gerakan", "gerakan stereotip", "berulang"
            )
        ),

        FormCategory(
            categoryId = "interaksi_sosial",
            questionId = "8_9",
            question = "Anak menyukai kontak mata_Anak tertarik bermain dengan orang lain",
            relatedKeywords = listOf(
                "kontak mata", "tatapan mata", "melihat langsung", "eye contact", "menatap", "melirik",
                "interaksi sosial", "bertemu orang", "bermain dengan", "orang lain", "berteman", "bergaul",
                "bersosialisasi", "berkomunikasi dengan orang lain", "sosialisasi",
                "tertarik bermain", "main bersama", "bermain kelompok", "bermain sendiri",
                "menghindari orang", "tidak suka orang lain", "menjauh dari orang lain", "malu", "antisosial"
            )
        ),

        FormCategory(
            categoryId = "fokus_pengembangan",
            questionId = "10",
            question = "Area yang paling ingin dikembangkan saat ini",
            relatedKeywords = listOf(
                "komunikasi", "berbicara", "berdialog", "berinteraksi", "kemandirian", "mandiri",
                "emosi", "perasaan", "ekspresi", "mengekspresikan", "mengungkapkan", "sosialisasi", "berteman",
                "kreativitas", "berkreasi", "berimajinasi", "fokus", "perhatian", "konsentrasi",
                "belajar fokus", "area ingin dikembangkan", "pengembangan anak", "kemampuan anak",
                "ketrampilan", "skill", "bakat", "minat", "potensi"
            )
        )
    )
}
