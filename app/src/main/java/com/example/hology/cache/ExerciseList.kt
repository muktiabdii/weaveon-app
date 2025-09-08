package com.example.hology.cache

import com.example.hology.domain.model.Exercise
import com.example.hology.domain.model.ExerciseActivity
import com.example.hology.R

val exerciseList = listOf(
    Exercise(
        id = "1",
        title = "Komunikasi",
        image = R.drawable.foto_komunikasi,
        description = "Latihan komunikasi membantu anak belajar mengekspresikan diri, memahami ucapan, dan merespons orang lain.",
        activities = listOf(
            ExerciseActivity(
                id = "C1",
                title = "Permainan Meniru Suara Hewan",
                image = R.drawable.foto_permainan_meniru_suara_hewan,
                description = "Orangtua mengajak anak bermain dengan mainan hewan (misal boneka atau mobil binatang) dan menirukan...",
                goal = "Kegiatan ini bisa mendorong anak mengeluarkan suara dan membangun kontak dua arah",
                tips = "Mulai dari hewan favorit anak, beri pujian saat anak berhasil meniru suara atau menempelkan kartu hewan setelah menirukannya."
            ),
            ExerciseActivity(
                id = "C2",
                title = "Kata Penting 'Lebih'/'Tolong'",
                image = R.drawable.foto_kata_penting_lebihtolong,
                description = "Saat anak ingin sesuatu, arahkan ia untuk mengucapkan kata sederhana seperti “lebih” atau “tolong”. Orangtua dapat memberi contoh terlebih dahulu sebelum anak mengulanginya.",
                goal = "Kegiatan ini membiasakan anak menggunakan kata sederhana untuk kebutuhan sehari-hari, sekaligus melatih keberanian berbicara.",
                tips = "Gunakan situasi nyata, misalnya saat anak ingin minum atau minta mainan. Jangan lupa beri apresiasi setelah anak berhasil mengucapkannya."
            ),
            ExerciseActivity(
                id = "C3",
                title = "Permainan Pilihan Favorit",
                image = R.drawable.foto_permainan_pilihan_favorit,
                description = "Tawarkan dua benda/aktivitas favorit anak (misalnya bola atau balon). Anak diminta menunjuk atau menyebutkan pilihannya.",
                goal = "Kegiatan ini melatih anak untuk mengambil keputusan, mengomunikasikan keinginannya, dan mengekspresikan pilihan.",
                tips = "Mulai dengan pilihan yang jelas dan terbatas (2–3 benda). Ulangi beberapa kali agar anak terbiasa mengutarakan pilihannya."
            )
        ),
    ),
    Exercise(
        id = "2",
        title = "Interaksi Sosial",
        image = R.drawable.foto_interaksi_sosial,
        description = "Latihan interaksi sosial membantu anak belajar berhubungan, memahami aturan sosial, dan membangun rasa percaya diri",
        activities = listOf(
            ExerciseActivity(
                id = "C1",
                title = "Permainan Giliran (Bola Estafet)",
                image = R.drawable.foto_permainan_giliran_bola,
                description = "Bermain lempar tangkap bola atau rolling bola secara bergantian. Katakan \"Sekarang giliran Mama\" saat melempar, dan \"Sekarang giliran Adik\" saat anak mendapat bola. Tambahkan hitungan atau lagu pendek untuk membuat lebih menarik.",
                goal = "Kegiatan ini mengajarkan konsep berbagi, menunggu giliran, dan interaksi timbal balik. Membangun kemampuan sosial dasar yang penting untuk bermain dengan teman sebaya.",
                tips = "Mulai dengan bola yang mudah ditangkap seperti bola foam. Berikan pujian besar saat anak menunggu giliran. Buat aturan sederhana dan konsisten."
            ),
            ExerciseActivity(
                id = "C2",
                title = "Permainan \"Aku Melihat...\"",
                image = R.drawable.foto_permainan_aku_melihat,
                description = "Bermain tebak-tebakan dengan mengamati benda di sekitar. Mulai dengan \"Aku melihat sesuatu yang berwarna merah...\" atau \"Aku melihat sesuatu yang bulat...\". Bantu anak menebak, lalu minta mereka bergantian memberikan clue.",
                goal = "Kegiatan ini melatih kemampuan anak memperhatikan lingkungan, meningkatkan kosakata, dan membangun interaksi.",
                tips = "Gunakan benda nyata di sekitar anak. Jika anak kesulitan, arahkan dengan petunjuk tambahan seperti “yang ada di meja” atau “yang bisa dimakan”."
            ),
            ExerciseActivity(
                id = "C3",
                title = "Visual Cerita Sosial",
                image = R.drawable.foto_visual_cerita_sosial,
                description = "Buat buku cerita bergambar sederhana tentang situasi sosial seperti \"Cara menyapa teman\" atau \"Bermain bersama di taman\". Baca bersama anak sambil menunjuk gambar dan mendiskusikan apa yang terjadi dalam cerita.",
                goal = "Kegiatan ini membantu anak memahami aturan sosial, melatih urutan berpikir, dan mengenal interaksi sehari-hari.",
                tips = "Mulai dengan cerita singkat (2–3 gambar), lalu tambah kompleksitas secara bertahap."
            )
        ),
    ),
    Exercise(
        id = "3",
        title = "Perilaku Berulang",
        image = R.drawable.foto_perilaku_berulang,
        description = "Latihan ini bertujuan mengurangi perilaku repetitif dengan mengalihkannya ke aktivitas terstruktur.",
        activities = listOf(
            ExerciseActivity(
                id = "C1",
                title = "Transisi dengan Timer Visual",
                image = R.drawable.foto_transisi_dengan_timer_visual,
                description = "Gunakan timer visual (bisa app di HP atau timer fisik) sebelum berganti aktivitas. Set timer 5-10 menit sebelum transisi sambil berkata \"Setelah timer habis, kita akan mandi ya.\" Biarkan anak melihat waktu yang tersisa.",
                goal = "Kegiatan ini membantu anak mengurangi stres saat transisi dengan tanda visual yang jelas.",
                tips = "Mulai dengan warning 10 menit, lalu 5 menit, lalu 2 menit. Gunakan timer yang memiliki visual menarik. Konsisten dengan rutine ini setiap hari."
            ),
            ExerciseActivity(
                id = "C2",
                title = "Jadwal Aktivitas Bergambar",
                image = R.drawable.foto_jadwal_aktivitas_bergambar,
                description = "Buat papan jadwal harian dengan gambar atau foto untuk setiap aktivitas: \n" +
                        "bangun tidur → mandi → sarapan → bermain → tidur siang\n" +
                        "Tempel di tempat yang mudah dilihat anak dan review bersama setiap pagi.",
                goal = "Kegiatan ini mengurangi kecemasan dan mengalihkan perilaku berulang ke aktivitas terstruktur.",
                tips = "Tempel jadwal di tempat yang mudah terlihat. Coret/beri tanda setelah aktivitas selesai. "
            ),
            ExerciseActivity(
                id = "C3",
                title = "Puzzle Gambar",
                image = R.drawable.foto_puzzle_gambar,
                description = "Sediakan puzzle dengan tingkat kesulitan yang sesuai (mulai 4-6 pieces). Bantu anak menyelesaikan puzzle sambil memberikan petunjuk verbal seperti \"Coba cari yang warnanya sama\" atau \"Piece ini cocoknya di pojok\".",
                goal = "Kegiatan ini mengalihkan perilaku berulang ke aktivitas konstruktif, melatih fokus dan koordinasi mata-tangan.",
                tips = "Mulai dengan puzzle 2–4 potong. Tambah tingkat kesulitan secara bertahap."
            )
        ),
    ),
    Exercise(
        id = "4",
        title = "Sensorik & Motorik",
        description = "Latihan sensorik & motorik  melatih koordinasi tubuh, sensitivitas sensorik, dan kekuatan motorik.",
        image = R.drawable.foto_sensorik_motorik,
        activities = listOf(
            ExerciseActivity(
                id = "C1",
                title = "Mencetak Tangan Dengan Cat Air",
                image = R.drawable.foto_mencetak_tangan_dengan_cat_air,
                description = "Siapkan cat air non-toxic dan kertas besar. Bantu anak mencelupkan tangan ke cat dan mencetak di kertas. Variasikan dengan menggunakan jari untuk membuat titik-titik, atau menggunakan kuas dengan berbagai tekstur.",
                goal = "Kegiatan ini memberikan stimulasi dan melatih toleransi terhadap tekstur basah.",
                tips = "Gunakan cat aman non-toxic. Biarkan anak memilih warna kesukaannya."
            ),
            ExerciseActivity(
                id = "C2",
                title = "Aktivitas Playdough/Clay",
                image = R.drawable.foto_aktivitas_playdough,
                description = "Berikan playdough atau clay dan ajak anak meremas, menggulung, memotong dengan alat plastik, atau membentuk objek sederhana. Variasikan dengan menambahkan alat seperti rolling pin kecil atau cetakan.",
                goal = "Kegiatan ini melatih kekuatan otot tangan, koordinasi bilateral (menggunakan kedua tangan), dan memberikan input proprioseptif yang menenangkan sistem saraf.",
                tips = "Mulai dengan playdough yang lembut dan mudah dibentuk. Biarkan anak mengeksplorasi tanpa tekanan untuk membuat sesuatu yang spesifik."
            ),
            ExerciseActivity(
                id = "C3",
                title = "Memindahkan Air dengan Sendok",
                image = R.drawable.foto_memindahkan_air_dengan_sendok,
                description = "Siapkan dua mangkuk dan sendok. Minta anak memindahkan air dari mangkuk satu ke mangkuk lainnya menggunakan sendok. Variasikan dengan menggunakan spons, pipet besar, atau cangkir kecil.",
                goal = "Kegiatan ini melatih konsentrasi, koordinasi mata-tangan, dan motorik halus.",
                tips = "Gunakan air hangat untuk sensasi yang nyaman. Biarkan anak bereksplorasi dengan air sambil tetap fokus pada tugas pemindahan."
            )
        ),
    ),
    Exercise(
        id = "5",
        title = "Kognitif",
        image = R.drawable.foto_kognitif,
        description = "Latihan kognitif membantu anak melatih daya ingat, perhatian, dan kemampuan berpikir sederhana.",
        activities = listOf(
            ExerciseActivity(
                id = "C1",
                title = "Urutkan Kegiatan Harian",
                image = R.drawable.foto_urutkan_kegiatan_harian,
                description = "Siapkan kartu bergambar aktivitas harian (bangun tidur, sikat gigi, sarapan, main, makan siang, tidur siang). Minta anak mengurutkan kartu sesuai dengan urutan yang benar dalam sehari.",
                goal = "Kegiatan ini mengembangkan pemahaman sequence dan logical thinking, melatih memori tentang rutine harian, dan membangun konsep waktu dan cause-effect.",
                tips = "Mulai dengan 3-4 kartu saja. Gunakan gambar yang jelas dan familiar untuk anak."
            ),
            ExerciseActivity(
                id = "C2",
                title = "Tebak Benda Tertutup",
                image = R.drawable.foto_tebak_benda_tertutup,
                description = "Masukkan benda familiar ke dalam tas atau kotak tertutup. Biarkan anak meraba dan menebak apa benda tersebut sebelum melihatnya. Mulai dengan benda-benda yang sangat berbeda tekstur dan bentuknya.",
                goal = "Kegiatan ini mengasah kemampuan analisis dan mengenali benda lewat sentuhan.",
                tips = "Pilih benda yang aman dan familiar untuk anak. Berikan clue verbal jika anak kesulitan. Rayakan setiap tebakan, baik benar maupun salah, untuk memotivasi anak terus mencoba."
            ),
            ExerciseActivity(
                id = "C3",
                title = "Ikuti Pola Suara",
                image = R.drawable.foto_ikuti_pola_suara,
                description = "Buat pola suara sederhana dengan mengetuk meja, bertepuk tangan, atau menggunakan instrumen sederhana. Mulai dengan pola 2-3 beat, minta anak mengulangi pola yang sama.",
                goal = "Kegiatan ini mengembangkan auditory processing, memori sekuensial, dan kemampuan meniru pola. Juga melatih attention dan listening skills.",
                tips = "Gunakan visual cues seperti mengangkat tangan untuk membantu anak mengikuti rhythm. Berikan model yang jelas sebelum meminta anak mengulangi."
            )
        ),
    ),
    Exercise(
        id = "6",
        title = "Regulasi Emosi",
        image = R.drawable.foto_regulasi_emosi,
        description = "Latihan regulasi emosi membantu anak mengenali, mengendalikan, dan menyalurkan emosi dengan cara sehat.",
        activities = listOf(
            ExerciseActivity(
                id = "C1",
                title = "Teknik Pernapasan Balon",
                image = R.drawable.foto_teknik_pernapasan_balon,
                description = "Ajarkan anak bernapas dalam dengan visualisasi balon. \"Hirup udara melalui hidung seolah meniup balon besar di perut, tahan sebentar, lalu hembuskan perlahan melalui mulut seperti balon yang mengempis.\" Letakkan tangan di perut anak untuk membantunya merasakan gerakan napas.",
                goal = "Kegiatan ini mengajarkan teknik relaksasi mandiri, menenangkan sistem saraf, dan meningkatkan kesadaran anak terhadap tubuhnya saat merasa cemas atau marah.",
                tips = "Jadikan ini permainan yang menyenangkan. Latih teknik ini saat anak dalam keadaan tenang, sehingga ia terbiasa melakukannya saat dibutuhkan. Gunakan balon sungguhan sebagai contoh di awal."
            ),
            ExerciseActivity(
                id = "C2",
                title = "Zona Regulasi Dengan Warna",
                image = R.drawable.foto_zona_regulasi_dengan_warna,
                description = "Kenalkan konsep emosi menggunakan warna. Misalnya: Zona Hijau (tenang, senang, siap belajar), Zona Kuning (cemas, mulai marah, gelisah), dan Zona Merah (sangat marah, panik, hilang kendali). Diskusikan perasaan apa yang masuk ke setiap zona.",
                goal = "Kegiatan ini membantu anak mengidentifikasi, memahami, dan mengomunikasikan tingkat emosinya dengan bahasa yang sederhana.",
                tips = "Buat poster visual \"Zona Regulasi\" bersama anak. Bicarakan tentang zona emosi Anda sendiri (\"Bunda sekarang di Zona Kuning karena...\") untuk memberi contoh. Hindari menghakimi saat anak berada di Zona Kuning atau Merah."
            ),
            ExerciseActivity(
                id = "C3",
                title = "Sudut Tenang (Bantal/Bean Bag)",
                image = R.drawable.foto_sudut_tenang,
                description = "Siapkan sebuah area kecil yang nyaman di rumah dengan bantal empuk, selimut, atau beberapa mainan sensorik yang menenangkan (misalnya squishy toy atau buku). Ajak anak ke sudut ini saat ia mulai merasa kewalahan oleh emosinya.",
                goal = "Kegiatan ini memberikan ruang yang aman bagi anak untuk memproses emosi besar tanpa merasa dihakimi. Mengajarkan bahwa tidak apa-apa untuk mengambil waktu menenangkan diri.",
                tips = "Ajak anak ikut mendesain sudut tenangnya sendiri. Tekankan bahwa sudut tenang bukanlah tempat hukuman, melainkan tempat khusus untuk merasa lebih baik."
            )
        ),
    )
)