package ru.sspo.focussignup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sspo.focussignup.R
import ru.sspo.focussignup.databinding.FragmentSignUpBinding
import ru.sspo.focussignup.ui.viewmodel.SignUpViewModel

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSaveButton()
        initObservers()
    }

    private fun initObservers() {
        viewModel.signUpScreenState.onEach { screenState ->
            clearFocus()
            when (screenState) {
                is SignUpScreenState.Error -> {
                    Toast.makeText(activity, screenState.error, Toast.LENGTH_SHORT).show()
                }

                SignUpScreenState.Success -> {
                    Toast.makeText(
                        activity,
                        getString(R.string.registration_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                    clearFields()
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun initSaveButton() {
        binding.buttonSave.setOnClickListener {
            val username = binding.editTextName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            viewModel.handleSignUp(username, email, password, confirmPassword)
        }
    }

    private fun clearFocus() {
        binding.editTextName.clearFocus()
        binding.editTextEmail.clearFocus()
        binding.editTextPassword.clearFocus()
        binding.editTextConfirmPassword.clearFocus()

        val inputMethodManager =
            ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        inputMethodManager?.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun clearFields() {
        binding.editTextName.text?.clear()
        binding.editTextEmail.text?.clear()
        binding.editTextPassword.text?.clear()
        binding.editTextConfirmPassword.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}