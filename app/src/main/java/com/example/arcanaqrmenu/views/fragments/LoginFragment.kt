package com.example.arcanaqrmenu.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arcanaqrmenu.R
import com.example.arcanaqrmenu.interfaces.ILogin


class LoginFragment : Fragment(), ILogin.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun startAlertLoading() {
        TODO("Not yet implemented")
    }

    override fun stopAlertLoading() {
        TODO("Not yet implemented")
    }

    override fun showMessage(stringId: Int) {
        TODO("Not yet implemented")
    }

    override fun clearFields() {
        TODO("Not yet implemented")
    }

    override fun startNextFragment() {
        TODO("Not yet implemented")
    }

}