package com.example.arcanaqrmenu.dataBase

import com.example.arcanaqrmenu.models.User
import com.google.firebase.auth.FirebaseAuth

class DBManager {
    private lateinit var mAuth: FirebaseAuth

    companion object {
        var CURRENT_USER: User? = null
    }


    //возможно стоит поменять тип возвращаемых данных с Bolean на FirebaseUser
    fun checkLogin(email: String, password: String): Boolean {
        try {
            mAuth.signInWithEmailAndPassword(email.trim(),password.trim()).addOnCompleteListener { task ->

                if (task.isSuccessful){
                    CURRENT_USER = User(mAuth.currentUser!!.uid,mAuth.currentUser!!.email.toString().trim(),password.trim())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return CURRENT_USER != null
    }

    fun checkEmailIsUnique(email: String): Boolean {
        var check = false
        mAuth.fetchSignInMethodsForEmail(email.trim()).addOnCompleteListener { task ->
            if (task.result?.signInMethods?.size == 0){
                check = true
            }
        }
        return check
    }

    fun addUser(email: String, password: String) {
        if (checkEmailIsUnique(email)) {
            mAuth.createUserWithEmailAndPassword(email.trim(), password.trim())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                    } else {

                    }
                }
        }
    }
}