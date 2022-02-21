package com.example.quizzzo

data class Category
(
    //("Create table if not exists Category (Category_id integer primary key not null, Category_desc text);")
    val categoryId :Int,
    val categoryDesc:String
)