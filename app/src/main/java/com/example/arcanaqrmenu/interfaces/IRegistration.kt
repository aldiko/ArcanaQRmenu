package com.example.arcanaqrmenu.interfaces

interface IRegistration {

    interface View {
        fun startAlertLoading()
        fun stopAlertLoading()
        fun showMessage(stringId: Int)
        fun clearFields()
        fun startNextFragment()
    }

    interface Presenter {
        fun register(email: String, password: String, confirmPassword: String)
        fun cleanUp()
    }


}