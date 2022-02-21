package com.example.quizzzo

data class Question(
    //("Create table if not exists Questions (Question_id integer not null, Category_id integer, Question_desc text, ans1 text, ans2 text, ans3 text, ans4 text, primary key(Question_id, Category_id));")
    val questionId :Int,
    val categoryId:Int,
    val questionDesc:String,
    val ans1:String,
    val ans2:String,
    val ans3:String,
    val ans4:String,
    val correctAns:String
)
