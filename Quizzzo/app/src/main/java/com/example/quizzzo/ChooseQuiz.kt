package com.example.quizzzo

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class ChooseQuiz : AppCompatActivity() {

    var username = "";
    var categoryId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_quiz)

        username = intent.getStringExtra("username").toString();

        var btn1:Button = Button(this);
        var btn2:Button = Button(this);
        var btn3:Button = Button(this);
        btn1 = findViewById<Button>(R.id.button1)
        btn2 = findViewById<Button>(R.id.button2)
        btn3 = findViewById<Button>(R.id.button3)

        val db = baseContext.openOrCreateDatabase("quizzzo.db", Context.MODE_PRIVATE,null);

        var ourContext = this
        var categories : ArrayList<Category> = ArrayList();

        val cursor: Cursor = db.rawQuery("select * from Category", null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                val id = cursor.getInt(cursor.getColumnIndex("Category_id").toInt())
                val desc = cursor.getString(cursor.getColumnIndex("Category_desc").toInt())
                categories.add(Category(id,desc))
                cursor.moveToNext()
            }
        }

        categoryMapButton(btn1,categories[0]);
        categoryMapButton(btn2,categories[1]);
        categoryMapButton(btn3,categories[2]);
        db.close();
    }

    private fun goToQuiz(button : Button)
    {
        val intent = Intent(this, Quiz::class.java);
        intent.putExtra("categoryId",button.id.toString())
        intent.putExtra("username",username)

        startActivity(intent);
    }

    private fun categoryMapButton(button:Button,category:Category)
    {
        button.text = category.categoryDesc
        button.setOnClickListener(View.OnClickListener {
            categoryId = category.categoryId
            button.id = categoryId
            goToQuiz(button);
        })
        //button.setBackgroundColor(Color.GREEN)
        //button.setTextColor(Color.RED)
    }
}