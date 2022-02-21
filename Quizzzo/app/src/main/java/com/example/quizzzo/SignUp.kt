package com.example.quizzzo

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzzo.database.Query

class SignUp : AppCompatActivity() {
    private lateinit var edFName: EditText
    private lateinit var edLName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var edCPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()
        btnLogin.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("notFirstTime","1");
            startActivity(intent);
            finish();
        }

        btnSignup.setOnClickListener { addUser() }

    }

    fun addUser() {
        val Fname = edFName.text.toString()
        val Lname = edLName.text.toString()
        val Email = edEmail.text.toString()
        val Password = edPassword.text.toString()
        val CPassword = edCPassword.text.toString()

        if (Fname.isEmpty() || Lname.isEmpty() || Email.isEmpty() || Password.isEmpty() || CPassword.isEmpty()) {
            Toast.makeText(this, "Please Enter Required Field", Toast.LENGTH_SHORT).show()
        } else {
            if (Password == CPassword) {
                val contentValues = ContentValues()
                contentValues.put("Email", Email)
                contentValues.put("Fname", Fname)
                contentValues.put("Lname", Lname)
                contentValues.put("Password", Password)
                val status = Query(this).insert("Users", contentValues)
                if (status > -1) {
                    Toast.makeText(this, "Register Successfully :)", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Unsuccessful Registration :(", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initView() {
        edFName = findViewById(R.id.edFName)
        edLName = findViewById(R.id.edLName)
        edEmail = findViewById(R.id.edEmail)
        edPassword = findViewById(R.id.edPassword)
        edCPassword = findViewById(R.id.edCPassword)
        btnSignup = findViewById(R.id.btnSignup)
        btnLogin = findViewById(R.id.btnLogin)
    }
}