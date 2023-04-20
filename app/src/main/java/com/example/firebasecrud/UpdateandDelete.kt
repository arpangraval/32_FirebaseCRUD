package com.example.firebasecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_updateand_delete.*

class UpdateandDelete : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateand_delete)

        deleteBTNDu.setOnClickListener {
            if(nameETDU.text.toString()!="")
            {

                if(nameETDU.text.toString()!="")
                {
                    var name = nameETDU.text.toString()
                    deleteData(name)
                }
                else
                {
                    Toast.makeText(this,"Enter Name to delete or update",Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this,"Enter Name to delete or update",Toast.LENGTH_LONG).show()
            }

        }
        updateBTNDu.setOnClickListener {
            nameETDup.isEnabled = true
            ageETDu.isEnabled = true
            salaryETDu.isEnabled = true

            if(nameETDU.text.toString()!="")
            {
            var name = nameETDU.text.toString()
            readData(name)
            }
            else
            {
                Toast.makeText(this,"Enter Name to delete or update",Toast.LENGTH_LONG).show()
            }

        }
        updateRecordBTNDu.setOnClickListener {
            if(nameETDup.text.toString()!="" && ageETDu.text.toString()!="" && salaryETDu.text.toString()!="")
            {
                var nm = nameETDup.text.toString()
                var ag = ageETDu.text.toString()
                var sl = salaryETDu.text.toString()
                updateData(nm,ag,sl)
            }

        }
    }

    private fun updateData(nm: String, ag: String, sl: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Employees")
        val updata = mapOf<String,String>(
            "ename" to nm,
            "eage" to ag,
            "esal" to sl)
        databaseReference.child(nm).updateChildren(updata).addOnSuccessListener {
            Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show()
            nameETDup.setText("")
            ageETDu.setText("")
            salaryETDu.setText("")

        }
    }

    private fun deleteData(name: String) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Employees")
        databaseReference.child(name).removeValue().addOnSuccessListener {
            Toast.makeText(this,"Data Deleted",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Data does not exist",Toast.LENGTH_LONG).show()
        }
    }

    private fun readData(name: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Employees")
        databaseReference.child(name).get().addOnSuccessListener {
            if(it.exists()){
                nameETDup.setText(it.child("ename").value.toString())
                ageETDu.setText(it.child("eage").value.toString())
                salaryETDu.setText(it.child("esal").value.toString())
            }
            else
            {
                Toast.makeText(this,"Record Not Found",Toast.LENGTH_LONG).show()
            }
        }
    }
}