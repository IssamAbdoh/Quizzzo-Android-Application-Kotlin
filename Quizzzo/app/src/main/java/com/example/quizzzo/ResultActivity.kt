package com.example.quizzzo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private lateinit var tv_result: TextView
    private lateinit var iv_trophy: ImageView
    private lateinit var tv_congratulations: TextView
    private lateinit var tv_name: TextView
    private lateinit var tv_score: TextView
    private lateinit var btn_finish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var username = intent.getStringExtra("username")
        var score = intent.getIntExtra("score",0)
        var mxScore = intent.getIntExtra("mxScore",0)

        initView()

        tv_name.text = username
        tv_score.text = "Your Score is $score out of $mxScore."

        btn_finish.setOnClickListener {
            //startActivity(Intent(this@ResultActivity, ChooseQuiz::class.java))
            finish()
        }
    }

    private fun initView()
    {
        tv_result = findViewById<TextView>(R.id.tv_result)
        iv_trophy = findViewById<ImageView>(R.id.iv_trophy)
        tv_congratulations = findViewById<TextView>(R.id.tv_congratulations)
        tv_name = findViewById<TextView>(R.id.tv_name)
        tv_score = findViewById<TextView>(R.id.tv_score)
        btn_finish = findViewById<Button>(R.id.btn_finish)
    }

}