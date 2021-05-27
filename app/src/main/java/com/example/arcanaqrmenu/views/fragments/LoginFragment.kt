package com.example.arcanaqrmenu.views.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.arcanaqrmenu.R
import com.example.arcanaqrmenu.interfaces.ILogin
import com.example.arcanaqrmenu.models.User
import com.example.arcanaqrmenu.presenters.LoginPresenter
import com.example.arcanaqrmenu.presenters.RegistrationPresenter
import com.example.arcanaqrmenu.views.alerts.LoadingAlert
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registration.*


class LoginFragment : Fragment(), ILogin.View {

    private lateinit var prefs: SharedPreferences
    private lateinit var presenter: LoginPresenter
    private lateinit var alert: LoadingAlert
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LoginPresenter(this)
        alert = LoadingAlert(requireActivity())
        prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        setUpListeners()
    }

    private fun setUpListeners() {
        login_button.setOnClickListener {
            if (login_email_edit_text.text.toString().isNotEmpty() &&
                login_password_edit_text.text.toString().isNotEmpty()
            ) {
                presenter.checkLogin(
                    login_email_edit_text.text.toString(),
                    login_password_edit_text.text.toString()
                )
            }
        }
        goRegister.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment2)
        }
    }

    override fun startAlertLoading() = alert.start()

    override fun stopAlertLoading() = alert.stop()

    override fun showMessage(stringId: Int) {
        Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show()
    }

    override fun clearFields() {
        login_email_edit_text.setText("")
        login_password_edit_text.setText("")
    }

    override fun startNextFragment(user: User) {
        prefs.edit().putString("user_uid", user.id.toString()).apply()
        findNavController().navigate(R.id.qrFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanUp()
    }

}