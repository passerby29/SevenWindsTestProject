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
import dev.passerby.seven_winds_test.databinding.FragmentRegisterBinding
import dev.passerby.seven_winds_test.domain.models.AuthUserDataModel
import dev.passerby.seven_winds_test.presentation.viewmodels.AuthViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = _binding ?: throw RuntimeException("FragmentRegisterBinding is null")

    private val viewModel by lazy { ViewModelProvider(this)[AuthViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        addTextChangeListeners()

        binding.apply {
            registerMainButton.setOnClickListener {
                val login = binding.registerEmailEditText.text?.trim().toString()
                val password = binding.registerPasswordEditText.text?.trim().toString()
                val passwordConf = binding.registerPasswordConfEditText.text?.trim().toString()
                if (password == passwordConf) {
                    viewModel.registerUser(AuthUserDataModel(login, password))
                } else {
                    registerPasswordContainer.error = "Пароли не совпадают"
                    registerPasswordConfContainer.error = "Пароли не совпадают"
                }
            }
            registerLoginButton.setOnClickListener {
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            with(binding) {
                isRegisterSuccessful.observe(viewLifecycleOwner) {
                    if (it) {
                        findNavController().navigate(
                            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        )
                    }
                }
                isLoginFieldValid.observe(viewLifecycleOwner) {
                    registerEmailContainer.error = if (it) {
                        null
                    } else {
                        "Пользователь с такой почтой уже сущетсвует"
                    }
                }
            }
        }
    }

    private fun addTextChangeListeners() {
        binding.registerEmailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetEmailField()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.registerPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetPasswordField()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.registerPasswordConfEditText.addTextChangedListener(object : TextWatcher {
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