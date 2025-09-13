package com.example.hology.cache

import com.example.hology.domain.model.Category
import com.example.hology.domain.model.RecommendedActivity
import com.example.hology.R
import com.example.hology.domain.model.Conclusion

val conclusionList = listOf(
    Conclusion(
        id = "1",
        description = "Anak menunjukkan kecenderungan yang menonjol pada kategori [nama kategori], dengan respon ekspresi yang berada pada tingkat [sangat senang/senang/tidak senang/sangat tidak senang]. Hal ini menggambarkan bahwa anak memiliki hubungan emosional yang kuat terhadap aktivitas dalam kategori ini. Antusiasme yang muncul menjadi sinyal penting bahwa area ini dapat menjadi jalur utama pengembangan potensi anak ke depannya. Dengan dukungan stimulasi yang tepat, minat ini dapat berkembang lebih optimal dan memberikan kontribusi positif terhadap pembentukan keterampilan anak."
    ),
    Conclusion(
        id = "2",
        description = "Anak memperlihatkan potensi pada dua kategori utama, yaitu [kategori 1] dan [kategori 2]. Dari keduanya, tingkat ekspresi menunjukkan ketertarikan lebih tinggi pada kategori [kategori utama] dengan respon [sangat senang/senang/tidak senang/sangat tidak senang]. Hal ini menandakan bahwa kategori tersebut merupakan area yang paling kuat menarik perhatian dan emosi anak. Meski demikian, keberadaan kategori lainnya tetap menunjukkan fleksibilitas dan kemampuan adaptasi anak dalam merespons beragam stimulasi. Potensi ganda ini dapat menjadi kekuatan unik bila diarahkan dengan strategi pembelajaran yang menyenangkan dan konsisten."
    ),
    Conclusion(
        id = "3",
        description = "Anak memperlihatkan minat pada beberapa kategori. Dari keseluruhan grafik, tingkat ekspresi menunjukkan ketertarikan lebih tinggi pada kategori [kategori utama] dengan respon [sangat senang/senang/tidak senang/sangat tidak senang]. Hal ini menunjukkan bahwa meski anak memiliki rasa ingin tahu yang tinggi pada berbagai aktivitas, ada satu jalur yang lebih kuat mendorong motivasi dan antusiasme anak. Keberagaman minat yang ada tetap menjadi fondasi positif untuk pengembangan holistik, namun fokus pada kategori dominan dapat menjadi langkah awal yang tepat sebelum mengeksplorasi kategori lain. Dengan begitu, anak dapat memperoleh pengalaman belajar yang menyenangkan sekaligus membangun rasa percaya diri."
    )
)

val reportCategoryList = listOf(
    Category(
        id = "1",
        title = "Logika & Pola",
        description = "Anak dengan kekuatan logika dan pola memiliki kemampuan berpikir sistematis, analitis, dan cenderung mencari keteraturan dalam setiap hal. Mereka lebih nyaman ketika berhadapan dengan aktivitas yang terukur, memiliki aturan, dan dapat diprediksi. Anak dengan minat ini biasanya menikmati pemecahan masalah, menemukan hubungan antar pola, serta menyusun strategi untuk menyelesaikan tantangan.",
        recommendedActivity = listOf(
            RecommendedActivity(
                id = "1",
                title = "Pembelajaran matematika kreatif dengan pendekatan visual",
                description = "Memvisualisasikan konsep matematika agar anak lebih mudah menemukan pola dan memecahkan masalah.",
                image = R.drawable.image_1_1
            ),
            RecommendedActivity(
                id = "2",
                title = "Kegiatan coding anak berbasis blok (misalnya Scratch, Code.org)",
                description = "Melatih cara berpikir sistematis dan logis dengan menyusun blok perintah visual untuk membuat program.",
                image = R.drawable.image_1_2
            ),
            RecommendedActivity(
                id = "3",
                title = "Aktivitas robotik atau lego robotics",
                description = "Mengasah logika dan pemahaman sebab-akibat dengan merakit serta memprogram robot agar dapat bergerak.",
                image = R.drawable.image_1_3
            ),
            RecommendedActivity(
                id = "4",
                title = "Eksperimen sains sederhana",
                description = "Membangun kemampuan analisis dan berpikir kritis melalui pengamatan dan pengujian fenomena ilmiah.",
                image = R.drawable.image_1_4
            ),
            RecommendedActivity(
                id = "5",
                title = "Pendalaman logika dan pola melalui permainan strategi",
                description = "Mengajarkan anak menyusun strategi dan berpikir ke depan melalui permainan papan yang menantang.",
                image = R.drawable.image_1_5
            )
        )
    ),
    Category(
        id = "2",
        title = "Seni & Visual",
        description = "Anak dengan kecenderungan seni & visual biasanya memiliki imajinasi yang luas serta cara unik dalam mengekspresikan diri. Mereka mudah menangkap detail visual, menyukai aktivitas yang melibatkan kreativitas, dan sering kali lebih nyaman menyampaikan perasaan melalui gambar atau karya seni dibandingkan kata-kata. Potensi ini dapat membantu mereka berkembang menjadi pribadi yang ekspresif dan inovatif.",
        recommendedActivity = listOf(
            RecommendedActivity(
                id = "1",
                title = "Kegiatan menggambar atau melukis",
                description = "Memvisualisasikan konsep matematika agar anak lebih mudah menemukan pola dan memecahkan masalah",
                image = R.drawable.image_2_1
            ),
            RecommendedActivity(
                id = "2",
                title = "Pembelajaran desain grafis dasar dengan aplikasi ramah anak",
                description = "Memadukan kreativitas seni dengan keterampilan digital melalui pengenalan dasar desain grafis praktis",
                image = R.drawable.image_2_2
            ),
            RecommendedActivity(
                id = "3",
                title = "Eksplorasi seni digital dan fotografi anak",
                description = "Melatih kepekaan anak terhadap komposisi visual dan cara bercerita melalui media fotografi atau digital",
                image = R.drawable.image_2_3
            ),
            RecommendedActivity(
                id = "4",
                title = "Aktivitas kerajinan tangan (clay, origami, kriya)",
                description = "Mengasah ketelitian dan imajinasi spasial dengan mengubah ide menjadi sebuah karya tiga dimensi",
                image = R.drawable.image_2_4
            ),
            RecommendedActivity(
                id = "5",
                title = "Terapi seni untuk menyalurkan emosi melalui karya visual",
                description = "Membantu anak mengenali dan menyalurkan emosi secara aman melalui media seni yang ekspresif",
                image = R.drawable.image_2_5
            )
        )
    ),
    Category(
        id = "3",
        title = "Verbal",
        description = "Anak dengan kekuatan verbal memiliki kemampuan luar biasa dalam memahami, menggunakan, dan mengolah kata-kata. Mereka cepat menangkap informasi dari cerita, percakapan, maupun bacaan. Anak dengan minat ini senang bercerita, bertanya, atau bahkan berimajinasi lewat narasi. Potensi verbal yang kuat mendukung mereka dalam keterampilan komunikasi dan literasi.",
        recommendedActivity = listOf(
            RecommendedActivity(
                id = "1",
                title = "Aktivitas public speaking untuk anak",
                description = "Membangun kepercayaan diri dan melatih kemampuan menyampaikan ide secara terstruktur di depan umum",
                image = R.drawable.image_3_1
            ),
            RecommendedActivity(
                id = "2",
                title = "Pembelajaran storytelling atau drama",
                description = "Mengembangkan kemampuan bercerita yang menarik dengan melatih penggunaan intonasi, ekspresi, dan narasi",
                image = R.drawable.image_3_2
            ),
            RecommendedActivity(
                id = "3",
                title = "Pendalaman menulis kreatif (puisi, cerita pendek)",
                description = "Mengasah kemampuan menuangkan imajinasi ke dalam tulisan serta memperkaya kosakata dan gaya bahasa",
                image = R.drawable.image_3_3
            ),
            RecommendedActivity(
                id = "4",
                title = "Pembelajaran bahasa asing untuk memperluas kemampuan komunikasi",
                description = "Memperluas wawasan global dan melatih fleksibilitas kognitif dengan mempelajari cara komunikasi baru",
                image = R.drawable.image_3_4
            ),
            RecommendedActivity(
                id = "5",
                title = "Kegiatan membaca bersama dengan diskusi interaktif",
                description = "Meningkatkan pemahaman bacaan dan kemampuan berpikir kritis melalui diskusi interaktif tentang isi cerita",
                image = R.drawable.image_3_5
            )
        )
    ),
    Category(
        id = "4",
        title = "Sosial & Imajinasi",
        description = "Anak dengan kekuatan sosial dan imajinasi memiliki ketertarikan besar untuk berinteraksi dengan orang lain serta berimajinasi melalui permainan peran. Mereka menikmati kebersamaan, mudah membangun hubungan, dan suka berpikir kreatif tentang situasi atau cerita. Dengan pendampingan tepat, minat ini bisa mengembangkan empati, keterampilan kolaborasi, dan daya imajinasi yang kaya.",
        recommendedActivity = listOf(
            RecommendedActivity(
                id = "1",
                title = "Pembelajaran drama atau teater anak",
                description = "Membangun empati dan kerja sama tim dengan memerankan berbagai karakter dan sudut pandang",
                image = R.drawable.image_4_1
            ),
            RecommendedActivity(
                id = "2",
                title = "Aktivitas bermain peran (role play learning)",
                description = "Melatih keterampilan sosial dan pemecahan masalah dengan mensimulasikan berbagai situasi di kehidupan nyata",
                image = R.drawable.image_4_2
            ),
            RecommendedActivity(
                id = "3",
                title = "Kegiatan pengembangan soft skills (kerjasama tim, komunikasi)",
                description = "Mengajarkan pentingnya kolaborasi dan komunikasi yang efektif melalui permainan tim yang menantang",
                image = R.drawable.image_4_3
            ),
            RecommendedActivity(
                id = "4",
                title = "Storytelling kelompok untuk menumbuhkan interaksi",
                description = "Mendorong kreativitas dan interaksi sosial dengan membangun sebuah cerita secara kolaboratif",
                image = R.drawable.image_4_4
            ),
            RecommendedActivity(
                id = "5",
                title = "Simulasi profesi (dokter-dokteran, chef, pilot)",
                description = "Memperluas wawasan tentang dunia profesi sekaligus merangsang daya imajinasi melalui permainan peran",
                image = R.drawable.image_4_5
            )
        )
    ),
    Category(
        id = "5",
        title = "Musik & Auditori",
        description = "Anak dengan kekuatan musik & auditori peka terhadap bunyi, ritme, dan melodi. Mereka sering kali memiliki kemampuan menirukan nada, menikmati lagu, atau bahkan menunjukkan ketertarikan untuk menciptakan suara sendiri. Musik bukan hanya sarana hiburan, tapi juga media terapi yang membantu anak menenangkan diri sekaligus mengekspresikan emosi.",
        recommendedActivity = listOf(
            RecommendedActivity(
                id = "1",
                title = "Pembelajaran alat musik (piano, gitar, drum, atau alat musik tradisional)",
                description = "Melatih disiplin, koordinasi, dan kepekaan auditori dengan mempelajari sebuah alat musik secara terstruktur.",
                image = R.drawable.image_5_1
            ),
            RecommendedActivity(
                id = "2",
                title = "Aktivitas vokal atau paduan suara",
                description = "Mengembangkan kontrol vokal dan harmonisasi sambil membangun kepercayaan diri serta kerja sama tim",
                image = R.drawable.image_5_2
            ),
            RecommendedActivity(
                id = "3",
                title = "Musik terapi untuk regulasi emosi",
                description = "Menggunakan musik sebagai media yang menenangkan untuk membantu anak mengenali dan mengelola emosi",
                image = R.drawable.image_5_3
            ),
            RecommendedActivity(
                id = "4",
                title = "Latihan ritme dengan perkusif sederhana",
                description = "Membangun kepekaan terhadap ritme dan tempo melalui permainan alat musik perkusi yang menyenangkan",
                image = R.drawable.image_5_4
            ),
            RecommendedActivity(
                id = "5",
                title = "Eksplorasi musik digital dengan aplikasi ramah anak",
                description = "Mendorong kreativitas musikal dengan menciptakan komposisi dan ritme sendiri menggunakan aplikasi digital",
                image = R.drawable.image_5_5
            )
        )
    ),
    Category(
        id = "6",
        title = "Motorik & Gerak",
        description = "Anak dengan kekuatan motorik & gerak menunjukkan ekspresi terbaik mereka melalui aktivitas fisik. Mereka cenderung lebih fokus dan gembira ketika melakukan kegiatan yang melibatkan tubuh. Kekuatan ini dapat menjadi modal besar untuk mengembangkan kesehatan, disiplin, serta keterampilan koordinasi tubuh yang baik.",
        recommendedActivity = listOf(
            RecommendedActivity(
                id = "1",
                title = "Aktivitas menari (tradisional atau modern)",
                description = "Meningkatkan koordinasi tubuh dan kemampuan berekspresi melalui gerakan tari yang mengikuti irama",
                image = R.drawable.image_6_1
            ),
            RecommendedActivity(
                id = "2",
                title = "Olahraga anak (renang, senam, bela diri ringan)",
                description = "Menanamkan disiplin dan sportivitas sambil mengembangkan kekuatan fisik melalui olahraga terstruktur",
                image = R.drawable.image_6_2
            ),
            RecommendedActivity(
                id = "3",
                title = "Yoga anak untuk melatih konsentrasi dan relaksasi",
                description = "Melatih fokus, keseimbangan, dan relaksasi melalui pose serta teknik pernapasan yang menenangkan",
                image = R.drawable.image_6_3
            ),
            RecommendedActivity(
                id = "4",
                title = "Kegiatan outbound untuk melatih kerjasama tim",
                description = "Membangun kerja sama tim dan jiwa kepemimpinan melalui tantangan fisik yang seru di alam terbuka",
                image = R.drawable.image_6_4
            ),
            RecommendedActivity(
                id = "5",
                title = "Aktivitas motorik halus melalui prakarya dengan tangan",
                description = "Menguatkan otot jari dan koordinasi mata-tangan yang penting untuk keterampilan menulis dan kemandirian",
                image = R.drawable.image_6_5
            )
        )
    )
)