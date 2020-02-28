package com.mengxyz.example.firebase_admin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.mengxyz.example.firebase_admin.R
import com.mengxyz.example.firebase_admin.util.thMessage
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
//    private val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this,
            QrGeneraterActivity::class.java)).also {
            finish()
        }

//        if(firebaseAuth.currentUser != null)
//            startActivity(Intent(this,
//                MainActivity::class.java)).also {
//                finish()
//            }
//        setContentView(R.layout.activity_login)
//
//        login.setOnClickListener {
//            val email = email.text.toString()
//            val password = password.text.toString()
//            firebaseAuth.signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener {
//                    if(it.isSuccessful){
//                        startActivity(Intent(this,
//                            MainActivity::class.java)).also {
//                            finish()
//                        }
//                    }else{
//                        val msg = (it.exception as FirebaseAuthException).thMessage
//                        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
//                    }
//                }
//        }
    }
}