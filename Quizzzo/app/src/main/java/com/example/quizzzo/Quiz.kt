package com.example.quizzzo

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class Quiz : AppCompatActivity() ,View.OnClickListener{

    var currentPosition = 1;
    var questionsArr:ArrayList<Question>? = null;

    private lateinit var   selectedOption:TextView

    private  lateinit var  progressBar:ProgressBar
    private lateinit var  tvQuestion:TextView
    private lateinit var  tvProgress:TextView
    private lateinit var  option1:TextView
    private lateinit var  option2:TextView
    private lateinit var  option3:TextView
    private lateinit  var  option4:TextView
    private lateinit var  btnSubmit:Button

    var correctAnswers : Int =0;
    var username :String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val categoryId = intent.getStringExtra("categoryId").toString();
        username = intent.getStringExtra("username").toString();

        initView()

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)

        btnSubmit.setOnClickListener(this)

        questionsArr = getQuestions(categoryId);
        progressBar.max = questionsArr!!.size;
        progressBar.progress=0;

        selectedOption = tvQuestion;
        updateProgress()

        postQuestion()
    }

    private fun updateProgress()
    {
        tvProgress.text = progressBar.progress.toString() + " / " + progressBar.max.toString()
    }

    private fun initView()
    {
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        tvQuestion = findViewById<TextView>(R.id.tv_question)
        tvProgress = findViewById<TextView>(R.id.tv_progress)
        option1 = findViewById<TextView>(R.id.option1)
        option2 = findViewById<TextView>(R.id.option2)
        option3 = findViewById<TextView>(R.id.option3)
        option4 = findViewById<TextView>(R.id.option4)
        btnSubmit = findViewById<Button>(R.id.btn_submit)
    }

    private fun getQuestions(cId:String) : ArrayList<Question>
    {
        var questionsArr = ArrayList<Question>();
        //("Create table if not exists Questions (Question_id integer not null, Category_id integer, Question_desc text, ans1 text, ans2 text, ans3 text, ans4 text, primary key(Question_id, Category_id));")
        val db = baseContext.openOrCreateDatabase("quizzzo.db", Context.MODE_PRIVATE,null)

        val sqlStatement = "SELECT * " +
                            "FROM Questions " +
                            "WHERE Category_id = " + cId + " ;";

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        //("Create table if not exists Questions (Question_id integer primary key not null, Category_id integer, Question_desc text, ans1 text, ans2 text, ans3 text, ans4 text);")

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                val questionId = cursor.getInt(cursor.getColumnIndex("Question_id").toInt())
                val categoryId = cursor.getInt(cursor.getColumnIndex("Category_id").toInt())
                val questionDesc = cursor.getString(cursor.getColumnIndex("Question_desc").toInt())
                val ans1 = cursor.getString(cursor.getColumnIndex("ans1").toInt())
                val ans2 = cursor.getString(cursor.getColumnIndex("ans2").toInt())
                val ans3 = cursor.getString(cursor.getColumnIndex("ans3").toInt())
                val ans4 = cursor.getString(cursor.getColumnIndex("ans4").toInt())

                questionsArr.add(Question(questionId,categoryId,questionDesc,ans1,ans2,ans3,ans4,ans1))

                cursor.moveToNext()
            }
        }
        db.close();
        return questionsArr;
    }

    private fun <T> shuffle(list: ArrayList<T>) {
        list.shuffle()
    }

    private fun postQuestion()
    {
        var question:Question? = questionsArr!![currentPosition-1];

        defaultOptions();

        if(currentPosition == questionsArr!!.size)
        {
            btnSubmit.text = "FINISH"
        }
        else
        {
            btnSubmit.text = "SUBMIT"
        }

        progressBar.progress = currentPosition
        updateProgress();
        tvQuestion.text = question!!.questionDesc
        var arrAns : ArrayList<String> = ArrayList();
        arrAns.add(question!!.ans1);
        arrAns.add(question!!.ans2);
        arrAns.add(question!!.ans3);
        arrAns.add(question!!.ans4);
        shuffle(arrAns);
        option1.text = arrAns[0]
        option2.text = arrAns[1]
        option3.text = arrAns[2]
        option4.text = arrAns[3]
    }

    private fun defaultOptions()
    {
        var options = ArrayList<TextView>();
        options.add(0,option1)
        options.add(1,option2)
        options.add(2,option3)
        options.add(3,option4)

        for(option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"));
            option.typeface= Typeface.DEFAULT;
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg);
        }
    }

    override fun onClick(v: View?)
    {
        when(v?.id)
        {
            R.id.option1 ->
            {
                selectOption(option1);
            }
            R.id.option2 ->
            {
                selectOption(option2);
            }
            R.id.option3 ->
            {
                selectOption(option3);
            }
            R.id.option4 ->
            {
                selectOption(option4);
            }
            R.id.btn_submit ->
            {
                if(selectedOption == tvQuestion)
                {
                    currentPosition++;
                    when
                    {
                        currentPosition <= questionsArr!!.size ->
                        {
                            postQuestion()
                        }
                        else ->
                        {
                            Toast.makeText(this,"You have successfully completed the quiz", Toast.LENGTH_LONG).show()
                            var intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra("username",username);
                            intent.putExtra("score",correctAnswers);
                            intent.putExtra("mxScore",progressBar.max);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                else
                {
                    var q = questionsArr?.get(currentPosition-1);
                    if(q!!.correctAns!=selectedOption!!.text)
                    {
                        answerView(selectedOption,R.drawable.wrong_option_border_bg)
                    }
                    else//if it was not incorrect , we increase the correctAnswers by one
                    {
                        correctAnswers++;
                    }
                    if(option1.text==q.correctAns)
                    {
                        answerView(option1,R.drawable.correct_option_border_bg)
                    }
                    if(option2.text==q.correctAns)
                    {
                        answerView(option2,R.drawable.correct_option_border_bg)
                    }
                    if(option3.text==q.correctAns)
                    {
                        answerView(option3,R.drawable.correct_option_border_bg)
                    }
                    if(option4.text==q.correctAns)
                    {
                        answerView(option4,R.drawable.correct_option_border_bg)
                    }
                    if(currentPosition ==questionsArr!!.size)
                    {
                        btnSubmit.text = "FINISH"
                    }
                    else
                    {
                        btnSubmit.text = "GO TO THE NEXT QUESTION"
                    }
                    selectedOption=tvQuestion;
                }
            }
        }
    }

    private fun selectOption(tv:TextView)
    {
        defaultOptions();
        selectedOption=tv;

        tv.setTextColor(Color.parseColor("#363A43"));
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg);
    }

    private fun answerView(answer:TextView,drawableView: Int)
    {
        answer.background = ContextCompat.getDrawable(
            this,drawableView
        )
        /*
        when(answer)
        {
            option1 ->
            {
                option1.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2 ->
            {
                option2.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3 ->
            {
                option3.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4 ->
            {
                option4.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
        */
    }
}