package com.example.quizzzo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzzo.database.Model
import com.example.quizzzo.database.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val notFirstTime = intent.getStringExtra("notFirstTime").toString();

        //if(notFirstTime!="1")
        //{
            val db = baseContext.openOrCreateDatabase("quizzzo.db", Context.MODE_PRIVATE, null)
            val m = Model(this)
            m.onCreate(db)
            db.close();
        //}

        val btnSignUp: Button = findViewById(R.id.btnSignup)
        btnSignUp.setOnClickListener { view ->
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }

        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener { view ->
            var helper = Query(applicationContext)
            var db = helper.readableDatabase
            val edEmail: EditText = findViewById(R.id.edEmail)
            val edPassword: EditText = findViewById(R.id.edPassword)
            var args =
                listOf<String>(edEmail.text.toString(), edPassword.text.toString()).toTypedArray()
            var admin = db.rawQuery("select * from Admins where email = ? and password = ?", args)

            if (admin.moveToNext()) {
                val intent = Intent(this, AdminDashboard::class.java)
                startActivity(intent)
                }
            else {
                var user = db.rawQuery("select * from Users where email = ? and password = ?", args)
                if (user.moveToNext()) {
                    val intent = Intent(this, ChooseQuiz::class.java)
                    intent.putExtra(
                        "username",
                        user.getString(1).toString() + " " + user.getString(2).toString()
                    )
                    startActivity(intent)
                    db.close()
                }
                else
                {
                    Toast.makeText(applicationContext, "Sorry!, Incorrect email or password", Toast.LENGTH_LONG).show()
                }
            }
            db.close()
        }
    }

}