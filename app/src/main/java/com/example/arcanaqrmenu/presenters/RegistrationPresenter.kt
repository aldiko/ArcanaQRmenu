package com.example.arcanaqrmenu.presenters

import android.util.Log
import com.example.arcanaqrmenu.R
import com.example.arcanaqrmenu.dataBase.DBManager
import com.example.arcanaqrmenu.interfaces.IRegistration
import kotlinx.coroutines.*

class RegistrationPresenter(private val view: IRegistration.View): IRegistration.Presenter {

    private val dbManager = DBManager()
    private var job: Job? = null


    override fun register(email: String, password: String, confirmPassword: String) {
        view.startAlertLoading()
        job = GlobalScope.launch(Dispatchers.IO) {
            if (emailValidation(email)) {
                if (passwordValidation(password,confirmPassword)) {
                    dbManager.checkEmailIsUnique(email,object : DBManager.UserExistCallback {
                        override fun isUserExist(exist: Boolean) {
                            view.stopAlertLoading()
                            if(exist) {
                                dbManager.addUser(email,password)
                                view.startNextFragment()
                            }else {
                                view.showMessage(R.string.email_already_exists)
                                view.clearFields()
                            }

                        }
                    })
                } else {
                    withContext(Dispatchers.Main) {
                        view.stopAlertLoading()
                        view.clearFields()
                        view.showMessage(R.string.invalid_password)
                    }
                }
            }  else {
                withContext(Dispatchers.Main) {
                    view.stopAlertLoading()
                    view.clearFields()
                    view.showMessage(R.string.invalid_email)
                }
            }
        }
    }

    private fun emailValidation(email: String): Boolean = email.contains("@") && email.contains(".")

    private fun passwordValidation(password: String, confirmPassword: String): Boolean = password.length > 7 && password == confirmPassword

    override fun cleanUp() {
        job?.cancel()
    }

}