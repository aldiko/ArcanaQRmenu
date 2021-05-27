package com.example.arcanaqrmenu.presenters

import android.util.Log
import com.example.arcanaqrmenu.R
import com.example.arcanaqrmenu.dataBase.DBManager
import com.example.arcanaqrmenu.interfaces.ILogin
import com.example.arcanaqrmenu.interfaces.IRegistration
import com.example.arcanaqrmenu.models.User
import kotlinx.coroutines.*

class LoginPresenter(private val view: ILogin.View) : ILogin.Presenter {

    private val dbManager = DBManager()
    private var job: Job? = null

    override fun checkLogin(email: String, password: String) {
        view.startAlertLoading()
        job = GlobalScope.launch(Dispatchers.IO) {
            dbManager.checkLogin(email, password, object : DBManager.UserCallback {
                override fun isLoginSuccess(exist: User?) {
                    view.stopAlertLoading()
                    if (exist != null) {
                        view.startNextFragment(exist)
                    } else {
                        view.showMessage(R.string.wrong_email_or_password)
                        view.clearFields()
                    }
                }
            })
        }
    }

    override fun cleanUp() {
        job?.cancel()
    }

}