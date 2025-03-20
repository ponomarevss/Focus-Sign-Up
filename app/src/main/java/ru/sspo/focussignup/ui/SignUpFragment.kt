package ru.sspo.focussignup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.sspo.focussignup.databinding.FragmentSignUpBinding
import ru.sspo.focussignup.viewmodel.SignUpViewModel

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

        viewModel.signUpResult.observe(
            viewLifecycleOwner
        ) { result -> Toast.makeText(activity, result, Toast.LENGTH_SHORT).show() }

        initSaveButton()
    }

    private fun initSaveButton() {
        binding.buttonSave.setOnClickListener {
            val username = binding.editTextName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            viewModel.onSignUpButtonClicked(username, email, password, confirmPassword)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}