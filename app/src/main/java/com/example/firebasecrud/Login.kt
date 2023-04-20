package com.example.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        loginBTNLogin.setOnClickListener {
            if(emailETLogin.text.toString()!="" && passETLogin.text.toString()!="")
            {
                firebaseAuth.signInWithEmailAndPassword(emailETLogin.text.toString(), passETLogin.text.toString()).addOnCompleteListener {

                    if(it.isSuccessful)
                    { startActivity(Intent(this, MainActivity::class.java))}
                    else
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                }

            }
            else
            {
                Toast.makeText(this,"Enter all Credentials",Toast.LENGTH_LONG).show()
            }


        }
    }
}