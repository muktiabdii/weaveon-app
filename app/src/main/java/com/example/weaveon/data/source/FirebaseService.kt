package com.example.weaveon.data.source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseService {

    // inisiasi firebase auth
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    //inisiasi firebase realtime database
    val db: FirebaseDatabase by lazy{
        FirebaseDatabase.getInstance("https://weaveon-8c4cc-default-rtdb.asia-southeast1.firebasedatabase.app/") }
}