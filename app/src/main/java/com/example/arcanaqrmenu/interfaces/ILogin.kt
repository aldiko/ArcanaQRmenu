package com.example.arcanaqrmenu.interfaces

interface ILogin {

    interface View {
        fun startAlertLoading()
        fun stopAlertLoading()
        fun showMessage(stringId: Int)
        fun clearFields()
        fun startNextFragment()
    }

    interface Presenter {
        fun checkLogin(email: String, password: String)
        fun cleanUp()
    }
}