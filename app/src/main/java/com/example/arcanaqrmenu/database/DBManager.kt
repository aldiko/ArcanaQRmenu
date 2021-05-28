package com.example.arcanaqrmenu.dataBase

import android.util.Log
import com.example.arcanaqrmenu.models.User
import com.google.firebase.auth.FirebaseAuth


class DBManager {

    companion object {
        var mAuth = FirebaseAuth.getInstance()
        var CURRENT_USER: User? = null
    }

    interface UserCallback {
        fun isLoginSuccess(exist: User?)
    }


    interface UserExistCallback {
        fun isUserExist(exist: Boolean)
    }

    //возможно стоит поменять тип возвращаемых данных с Bolean на FirebaseUser
    fun checkLogin(email: String, password: String, check: UserCallback) {
        try {
            mAuth.signInWithEmailAndPassword(email.trim(), password.trim())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        check.isLoginSuccess(User(mAuth.currentUser!!.uid, email, password))
                    } else {
                        check.isLoginSuccess(null)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkEmailIsUnique(email: String, check: UserExistCallback) {
        mAuth.fetchSignInMethodsForEmail(email.trim()).addOnCompleteListener { task ->
            val add = task.result?.signInMethods?.size == 0
            check.isUserExist(add)
        }
    }


    fun addUser(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email.trim(), password.trim())
            .addOnCompleteListener { task ->
            }
    }


}