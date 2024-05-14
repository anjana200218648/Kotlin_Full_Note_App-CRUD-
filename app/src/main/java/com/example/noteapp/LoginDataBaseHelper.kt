package com.example.noteapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoginDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "LOGIN.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "logindata"
        private const val LOGIN_ID = "  loginid"
        private const val FULL_NAME = "name"
        private const val AGE = "age"
        private const val PHONE_NUMBER = "phonenumber"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTableQuery = "CREATE TABLE $TABLE_NAME " +
                "($LOGIN_ID INTEGER PRIMARY KEY,$FULL_NAME TEXT, $AGE TEXT, $PHONE_NUMBER TEXT, $EMAIL TEXT , $PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertDetails(userDetails:  UserDetails) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(FULL_NAME, userDetails.fullName)
        values.put(AGE, userDetails.age)
        values.put(PHONE_NUMBER, userDetails.phoneNumber)
        values.put(EMAIL, userDetails.email)
        values.put(PASSWORD, userDetails.password)

        // Inserting Row
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    @SuppressLint("Range")
    fun getUserDetails(fullName: String): UserDetails? {
        val db = this.readableDatabase
        val columns = arrayOf(LOGIN_ID, FULL_NAME, AGE, PHONE_NUMBER, EMAIL, PASSWORD)
        val selection = "$FULL_NAME = ?"
        val selectionArgs = arrayOf(fullName)

        val cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null)
        var userDetails: UserDetails? = null

        if (cursor.moveToFirst()) {
            userDetails = UserDetails(
                cursor.getString(cursor.getColumnIndex(FULL_NAME)),
                cursor.getString(cursor.getColumnIndex(AGE)),
                cursor.getString(cursor.getColumnIndex(PHONE_NUMBER)),
                cursor.getString(cursor.getColumnIndex(EMAIL)),
                cursor.getString(cursor.getColumnIndex(PASSWORD))
            )
        }

        cursor.close()
        return userDetails
    }

}
