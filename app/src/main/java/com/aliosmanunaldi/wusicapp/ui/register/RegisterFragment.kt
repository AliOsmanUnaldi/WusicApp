package com.aliosmanunaldi.wusicapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aliosmanunaldi.wusicapp.UserRegister
import com.aliosmanunaldi.wusicapp.data.register.RegisterRepository
import com.aliosmanunaldi.wusicapp.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    val viewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(
            repository = RegisterRepository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            val user = UserRegister(
                username = binding.userNameRegisterEditText.text.toString(),
                password = binding.passwordRegisterEditText.text.toString(),
                email = binding.emailRegisterEditText.text.toString()
            )
            viewModel.setUserRegister(user)
        }
        setUpViewModel()
    }

    private fun setUpViewModel() {

        with(viewModel) {
            getPageLiveData().observe(viewLifecycleOwner) {
                renderPageViewState(it)
            }
        }
    }

    private fun renderPageViewState(viewState: RegisterPageViewState) {

        if (viewState.result?.success == true) {
            navigateLoginFragment(viewState)
        }
        Snackbar.make(
            binding.registerLayout,
            viewState.result?.message.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun navigateLoginFragment(viewState: RegisterPageViewState) {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }

}