package com.example.quizzzo.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Query(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "quizzzo.db"
        private const val COL1 = "Question_id"
        private const val COL2 = "Question_desc"

    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(TblName: String, contentValues: ContentValues): Long {
        val db = this.writableDatabase
        val success = db.insert(
            TblName,
            null,
            contentValues
        )
        db.close()
        return success
    }

    fun update(TblName: String, Columns: Array<String>, Values: Array<String>, Condition: String): Boolean {
        val db = this.writableDatabase
        val sql = "UPDATE $TblName SET ${Columns[0]}=${Values[0].toInt()}, ${Columns[1]}='${Values[1]}', ${Columns[2]}='${Values[2]}', ${Columns[3]}='${Values[3]}', ${Columns[4]}='${Values[4]}', ${Columns[5]}='${Values[5]}' WHERE $Condition;"
        val result : Unit = db.execSQL(sql)
        return result==Unit
    }

    fun delete(TblName: String, Condition: String): Boolean {
        val db = this.writableDatabase
        val sql = "DELETE FROM $TblName WHERE $Condition;"
        val result : Unit = db.execSQL(sql)
        return result==Unit
    }

    fun select(TblName: String, Columns: Array<String>, Condition: String): Cursor {
        val db = this.writableDatabase
        val sql = "SELECT ${Columns[0]}, ${Columns[1]} FROM $TblName WHERE $Condition;"
        val result : Cursor = db.rawQuery(sql, null)
        return result
    }

}