package com.example.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveBTN.setOnClickListener {
        databaseReference = FirebaseDatabase.getInstance().getReference("Employees")
       if(nameET.text.toString()!="" && ageET.text.toString()!="" && salaryET.text.toString()!="")
       {
        var ename = nameET.text.toString()
        var eage = ageET.text.toString()
        var sal = salaryET.text.toString()
        var emp = Employees(ename,eage,sal)

            databaseReference.child(ename).setValue(emp).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Data Not Saved",Toast.LENGTH_LONG).show()
            }
        }
        }
            else
       {
            Toast.makeText(this,"Enter all field information",Toast.LENGTH_LONG).show()
       }
        }
        updateDeleteBTN.setOnClickListener {
            startActivity(Intent(this,UpdateandDelete::class.java))
        }


    }
}