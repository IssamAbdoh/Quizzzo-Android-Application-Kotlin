package com.example.quizzzo.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class Model(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "quizzzo.db"
        private const val TblUsers = "Users"
        private const val TblAdmins = "Admins"
        private const val TblQuestions = "Questions"
        private const val TblCategory = "Category"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        //onUpgrade(db,db!!.version,db!!.version+1);

        val createTblUsers =
            ("CREATE TABLE IF NOT EXISTS Users ( Email text primary key not null, FName text, LName text, Password text );")
        val createTblTblAdmins =
            ("Create table if not exists Admins (Email text primary key not null, FName text, LName text, Password text );")
        val createTblTblCategory =
            ("Create table if not exists Category (Category_id integer primary key not null, Category_desc text);")
        val createTblQuestions =
            ("Create table if not exists Questions (Question_id integer primary key not null, Category_id integer, Question_desc text, ans1 text, ans2 text, ans3 text, ans4 text);")
        db?.execSQL(createTblUsers)
        db?.execSQL(createTblTblAdmins)
        db?.execSQL(createTblTblCategory)
        db?.execSQL(createTblQuestions)
        try {
            var s = "INSERT INTO ADMINS VALUES(?,?,?,?)"
            var args =
                listOf<String>("admin1@gmail.com", "Samer", "Al-Zoubi", "12345").toTypedArray()
            db?.execSQL(s, args)
            args[0] = "admin2@gmail.com"
            args[1] = "Essam"
            args[2] = "Abdo"
            args[3] = "12345"
            db?.execSQL(s, args)
            args[0] = "admin3@gmail.com"
            args[1] = "Omar"
            args[2] = "Rahwanji"
            args[3] = "12345"
            db?.execSQL(s, args)

            s = "INSERT INTO CATEGORY VALUES(0,'Math'); "
            db?.execSQL(s)
            s = "INSERT INTO CATEGORY VALUES(1,'Java');"
            db?.execSQL(s)
            s = "INSERT INTO CATEGORY VALUES(2,'Machine Learning');"
            db?.execSQL(s)


            /////////Start Math Quotations//////////////////////////////////////
            var args2: ArrayList<String> = ArrayList()
            s = "INSERT INTO Questions VALUES(?,?,?,?,?,?,?)"
            args2.add("0")
            args2.add("0")
            args2.add("What is three fifth of 100?")
            args2.add("60")
            args2.add("20")
            args2.add("5")
            args2.add("3")
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "1"
            args2[1] = "0"
            args2[2] = "If David’s age is 27 years old in 2011. What was his age in 2003?"
            args2[3] = "19 years"
            args2[4] = "37 years"
            args2[5] = "20 years"
            args2[6] = "17 years"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "2"
            args2[1] = "0"
            args2[2] = "What is the remainder of 21 divided by 7?"
            args2[3] = "None of these"
            args2[4] = "21"
            args2[5] = "7"
            args2[6] = "1"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "3"
            args2[1] = "0"
            args2[2] = "What is 7% equal to?"
            args2[3] = "0.07"
            args2[4] = "7"
            args2[5] = "0.7"
            args2[6] = "0.007"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "4"
            args2[1] = "0"
            args2[2] = "I am a number. I have 7 in the ones place. I am less than 80 but greater than 70. What is my number?"
            args2[3] = "77"
            args2[4] = "75"
            args2[5] = "73"
            args2[6] = "71"
            db?.execSQL(s, args2.toTypedArray())
            /////////End Math Quotations//////////////////////////////////////

            /////////Start Java Quotations//////////////////////////////////////
            args2[0] = "5"
            args2[1] = "1"
            args2[2] = "Which of the following option leads to the portability and security of Java?"
            args2[3] = "Bytecode is executed by JVM"
            args2[4] = "The applet makes the Java code secure and portable"
            args2[5] = "Use of exception handling"
            args2[6] = "Dynamic binding between objects"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "6"
            args2[1] = "1"
            args2[2] = "What is the return type of the hashCode() method in the Object class?"
            args2[3] = "int"
            args2[4] = "Object"
            args2[5] = "long"
            args2[6] = "void"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "7"
            args2[1] = "1"
            args2[2] = "Which of the following is not a Java features?"
            args2[3] = "Use of pointers  "
            args2[4] = "Dynamic"
            args2[5] = "Architecture Neutral"
            args2[6] = "Object-oriented"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "8"
            args2[1] = "1"
            args2[2] = "Which of the following is a valid declaration of a char?"
            args2[3] = "char ch = '\\utea';"
            args2[4] = "char ca = 'tea';"
            args2[5] = "char cr = \\u0223;"
            args2[6] = "char cc = '\\itea';"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "9"
            args2[1] = "1"
            args2[2] = "_____ is used to find and fix bugs in the Java programs."
            args2[3] = "JDB"
            args2[4] = "JVM"
            args2[5] = "JRE"
            args2[6] = "JDK"
            db?.execSQL(s, args2.toTypedArray())

            /////////End Java Quotations//////////////////////////////////////

            /////////Start Machine Learning Quotations//////////////////////////////////////
            args2[0] = "10"
            args2[1] = "2"
            args2[2] = "What is true about Machine Learning?"
            args2[3] = "All the answers"
            args2[4] = "Machine Learning (ML) is that field of computer science"
            args2[5] = "ML is a type of artificial intelligence that extract patterns out of raw data by using an  algorithm or method."
            args2[6] = "The main focus of ML is to allow computer systems learn from experience without being explicitly programmed or human intervention."
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "11"
            args2[1] = "2"
            args2[2] = "The action _______ of a robot arm specify to Place block A on block B."
            args2[3] = "STACK(A,B)"
            args2[4] = "LIST(A,B)"
            args2[5] = "QUEUE(A,B)"
            args2[6] = "ARRAY(A,B)"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "12"
            args2[1] = "2"
            args2[2] = "A model of language consists of the categories which does not include ________."
            args2[3] = "structural units."
            args2[4] = "System Unit"
            args2[5] = "data units"
            args2[6] = "empirical units"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "13"
            args2[1] = "2"
            args2[2] = "Different learning methods does not include?"
            args2[3] = "Introduction"
            args2[4] = "Analogy"
            args2[5] = "Deduction"
            args2[6] = "Memorization"
            db?.execSQL(s, args2.toTypedArray())

            args2[0] = "14"
            args2[1] = "2"
            args2[2] = "Which of the following are ML methods?"
            args2[3] = "based on human supervision"
            args2[4] = "supervised Learning"
            args2[5] = "semi-reinforcement Learning"
            args2[6] = "All the answers"
            db?.execSQL(s, args2.toTypedArray())

            /////////End Machine Learning Quotations//////////////////////////////////////

//            /////////Start English Grammar Quotations//////////////////////////////////////
//            args2[0] = "10"
//            args2[1] = "2"
//            args2[2] = ""
//            args2[3] = ""
//            args2[4] = ""
//            args2[5] = ""
//            args2[6] = ""
//            db?.execSQL(s, args2.toTypedArray())
//
//            args2[0] = "11"
//            args2[1] = "2"
//            args2[2] = ""
//            args2[3] = ""
//            args2[4] = ""
//            args2[5] = ""
//            args2[6] = ""
//            db?.execSQL(s, args2.toTypedArray())
//
//            args2[0] = "12"
//            args2[1] = "2"
//            args2[2] = ""
//            args2[3] = ""
//            args2[4] = ""
//            args2[5] = ""
//            args2[6] = ""
//            db?.execSQL(s, args2.toTypedArray())
//
//            args2[0] = "13"
//            args2[1] = "2"
//            args2[2] = ""
//            args2[3] = ""
//            args2[4] = ""
//            args2[5] = ""
//            args2[6] = ""
//            db?.execSQL(s, args2.toTypedArray())
//
//            args2[0] = "14"
//            args2[1] = "2"
//            args2[2] = ""
//            args2[3] = ""
//            args2[4] = ""
//            args2[5] = ""
//            args2[6] = ""
//            db?.execSQL(s, args2.toTypedArray())
//
//            /////////End English Grammar Quotations//////////////////////////////////////
        } catch (exception: Exception) {
            println("" + exception.message.toString())
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${TblUsers}")
        db?.execSQL("DROP TABLE IF EXISTS ${TblAdmins}")
        db?.execSQL("DROP TABLE IF EXISTS ${TblQuestions}")
        db?.execSQL("DROP TABLE IF EXISTS ${TblCategory}")
        onCreate(db)
    }

}