package dev.passerby.seven_winds_test.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.passerby.seven_winds_test.databinding.FragmentLoginBinding
import dev.passerby.seven_winds_test.domain.models.AuthUserDataModel
import dev.passerby.seven_winds_test.presentation.viewmodels.AuthViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding is null")

    private val viewModel by lazy { ViewModelProvider(this)[AuthViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loginMainButton.setOnClickListener {
                val login = loginEmailEditText.text?.trim().toString()
                val password = loginPasswordEditText.text?.trim().toString()
                viewModel.loginUser(AuthUserDataModel(login, password))
            }
            loginRegisterButton.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}