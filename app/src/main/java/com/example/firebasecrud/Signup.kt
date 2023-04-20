package com.example.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {

    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

    firebaseAuth =FirebaseAuth.getInstance()
        signupBTNSu.setOnClickListener {
            if(emailETSu.text.toString() !="" && passETSu.text.toString() !="" && pass2ETSu.text.toString()!="" && passETSu.text.toString()==pass2ETSu.text.toString())
            {
            firebaseAuth.createUserWithEmailAndPassword(emailETSu.text.toString(),passETSu.text.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"User Created",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,Login::class.java))
            }
                else
            {
                Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
            }
}
            }
        else{
                Toast.makeText(this,"Enter Proper Credentials match pass too",Toast.LENGTH_LONG).show()
        }



        }
        loginBTNSu.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }
}