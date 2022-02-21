package com.example.quizzzo.database

import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

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

        val createTblUsers =
            ("CREATE TABLE IF NOT EXISTS Users ( Email text primary key not null, FName text, LName text, Password text );")
        val createTblTblAdmins =
            ("Create table if not exists Admins (Email text primary key not null, FName text, LName text, Password text );")
        val createTblTblCategory =
            ("Create table if not exists Categories (Category_id integer primary key not null, Category_desc text);")
        val createTblQuestions =
            ("Create table if not exists Questions (Question_id integer not null, Category_id integer, Question_desc text, ans1 text, ans2 text, ans3 text, ans4 text, primary key(Question_id, Category_id));")
        db?.execSQL(createTblUsers)
        db?.execSQL(createTblTblAdmins)
        db?.execSQL(createTblTblCategory)
        db?.execSQL(createTblQuestions)
        Log.d(TAG,"KOKOKOKOKOKOKOKKOKOKOKOKO $createTblQuestions");
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${TblUsers}")
        db?.execSQL("DROP TABLE IF EXISTS ${TblAdmins}")
        db?.execSQL("DROP TABLE IF EXISTS ${TblQuestions}")
        db?.execSQL("DROP TABLE IF EXISTS ${TblCategory}")
        onCreate(db)
    }

}