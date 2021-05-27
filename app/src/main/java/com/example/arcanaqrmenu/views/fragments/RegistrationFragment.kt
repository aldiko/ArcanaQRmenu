package com.example.arcanaqrmenu.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.arcanaqrmenu.R
import com.example.arcanaqrmenu.databinding.FragmentRegistrationBinding
import com.example.arcanaqrmenu.interfaces.IRegistration
import com.example.arcanaqrmenu.presenters.RegistrationPresenter
import com.example.arcanaqrmenu.views.alerts.LoadingAlert
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.single__activity.*

class RegistrationFragment : Fragment(), IRegistration.View {
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var alert: LoadingAlert
    private lateinit var presenter: RegistrationPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RegistrationPresenter(this)
        alert = LoadingAlert(requireActivity())
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.registerButton.setOnClickListener {
            if (binding.registerEmailEditText.text.toString().trim().isNotEmpty() &&
                binding.registerPasswordEditText.text.toString().trim().isNotEmpty() &&
                binding.registerRepeatPasswordEditText.text.toString().trim().isNotEmpty()
            ) {
                presenter.register(
                    email = binding.registerEmailEditText.text.toString().trim(),
                    password = binding.registerPasswordEditText.text.toString().trim(),
                    confirmPassword = binding.registerRepeatPasswordEditText.text.toString().trim()
                )
            }
        }
        goLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment2)
        }
    }

    override fun startAlertLoading() = alert.start()

    override fun stopAlertLoading() = alert.stop()

    override fun showMessage(stringId: Int) {
        binding.registerErrorMessage.visibility = View.VISIBLE
        binding.registerErrorMessage.text = getString(stringId)
    }

    override fun clearFields() {
        binding.registerEmailEditText.setText("")
        binding.registerPasswordEditText.setText("")
        binding.registerRepeatPasswordEditText.setText("")
    }

    override fun startNextFragment() {
        findNavController().navigate(R.id.loginFragment2)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanUp()
    }
}