package com.example.hology.cache

import androidx.compose.ui.graphics.Color
import com.example.hology.domain.model.WevyResult
import com.example.hology.R

val wevyResultList = listOf(
    WevyResult(
        label = "Sangat Senang",
        color = Color(0xFF33A136),
        activeIcon = R.drawable.ic_very_happy,
        inactiveIcon = R.drawable.ic_very_happy_gray,
        description = "Anak menunjukkan ekspresi sangat senang sepanjang kegiatan dengan energi dan semangat yang konsisten. Rasa antusias tampak jelas sejak awal hingga akhir, disertai fokus yang stabil dan kegembiraan ketika berhasil menyelesaikan tantangan kecil. Hasil ini menandakan bahwa kegiatan ini memberikan pengalaman positif yang kuat dan berpotensi menjadi stimulasi penting bagi perkembangan emosional dan kognitif anak."
    ),
    WevyResult(
        label = "Cukup Senang",
        color = Color(0xFF77BF3A),
        activeIcon = R.drawable.ic_happy,
        inactiveIcon = R.drawable.ic_happy_gray,
        description = "Anak menunjukkan ekspresi senang dengan antusiasme yang cukup tinggi sejak awal kegiatan. Fokus dan ketertarikan terlihat jelas, meskipun sempat menurun di pertengahan, namun kembali meningkat setelah anak berhasil mencapai keberhasilan kecil. Pada akhir kegiatan, anak menutup dengan ekspresi puas dan positif, menandakan bahwa aktivitas ini memberikan pengalaman menyenangkan serta mampu menstimulasi konsentrasi dan keterampilan berpikir anak."
    ),
    WevyResult(
        label = "Kurang Senang",
        color = Color(0xFFFCB506),
        activeIcon = R.drawable.ic_unhappy,
        inactiveIcon = R.drawable.ic_unhappy_gray,
        description = "Anak lebih banyak menunjukkan ekspresi tidak senang selama kegiatan berlangsung. Konsentrasi terlihat kurang stabil, dengan penurunan minat yang cukup cepat ketika menemui kesulitan. Meski ada momen singkat ketika anak mencoba terlibat kembali, ekspresi kurang nyaman tetap mendominasi. Hal ini menandakan perlunya pendekatan yang lebih lembut dan penyesuaian tingkat kesulitan agar anak merasa lebih didukung."
    ),
    WevyResult(
        label = "Sangat Tidak Senang",
        color = Color(0xFFF67812),
        activeIcon = R.drawable.ic_very_unhappy,
        inactiveIcon = R.drawable.ic_very_unhappy_grey,
        description = "Anak memperlihatkan ekspresi sangat tidak senang hampir di seluruh proses kegiatan. Respon yang muncul cenderung menunjukkan penolakan, kurang fokus, dan minim ketertarikan. Meskipun terdapat sedikit upaya untuk mencoba, rasa frustrasi tampak lebih dominan. Hasil ini menunjukkan bahwa kegiatan belum sesuai dengan kondisi emosional anak saat itu, sehingga disarankan untuk memberi jeda, memberikan dukungan emosional, atau mengganti dengan aktivitas yang lebih ringan dan menyenangkan."
    ),
)