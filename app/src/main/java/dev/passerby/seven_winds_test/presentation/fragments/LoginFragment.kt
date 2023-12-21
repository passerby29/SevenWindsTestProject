package dev.passerby.seven_winds_test.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        observeViewModel()
        addTextChangeListeners()
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

    private fun observeViewModel() {
        viewModel.apply {
            with(binding) {
                isLoginSuccessful.observe(viewLifecycleOwner) {
                    if (it) {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCoffeeHousesFragment())
                    }
                }
                isLoginFieldValid.observe(viewLifecycleOwner) {
                    loginEmailContainer.error = if (it) {
                        null
                    } else {
                        "Неправильный email"
                    }
                }
                isPasswordFieldValid.observe(viewLifecycleOwner) {
                    loginPasswordContainer.error = if (it) {
                        null
                    } else {
                        "Неправильный пароль"
                    }
                }
            }
        }
    }

    private fun addTextChangeListeners() {
        binding.loginEmailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetEmailField()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.loginPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetPasswordField()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}