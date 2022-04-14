package com.penguin.neverexpire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.security.MessageDigest
import java.util.Base64
import javax.crypto.Cipher
class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val register = findViewById<Button>(R.id.Register)
        val email = findViewById<EditText>(R.id.Email_Register)
        val username = findViewById<EditText>(R.id.Username_Register)
        val password = findViewById<EditText>(R.id.Password_Register)
        val md = MessageDigest.getInstance("SHA-256")

        register.setOnClickListener {
            write("email",email.text.toString())
            write("hash",Base64.getUrlEncoder().encodeToString(md.digest((username.text.toString()+password.text.toString()).toByteArray(Charsets.UTF_8))))
        }
    }
    private fun write(ref:String, value:String){
        val database = Firebase.database
        val myRef = database.getReference(ref)
        myRef.setValue(value)

    }
}