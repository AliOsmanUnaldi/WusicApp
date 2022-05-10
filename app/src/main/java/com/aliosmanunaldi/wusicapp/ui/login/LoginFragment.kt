package com.aliosmanunaldi.wusicapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aliosmanunaldi.wusicapp.User
import com.aliosmanunaldi.wusicapp.data.login.LoginRepository
import com.aliosmanunaldi.wusicapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            repository = LoginRepository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            val user = User(
                username = binding.userNameLoginEditText.text.toString(),
                password = binding.passwordLoginEditText.text.toString()
            )
            viewModel.setUserLogin(user)
        }
        binding.goToRegisterButton.setOnClickListener {
            navigateRegisterFragment()
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

    private fun renderPageViewState(viewState: LoginPageViewState) {

        if (viewState.result?.data != null) {
            navigateHomeFragment(viewState)
        }
        Snackbar.make(
            binding.loginLayout,
            viewState.result?.message.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun navigateHomeFragment(viewState: LoginPageViewState) {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                viewState.result?.data!!
            )
        )
    }


    private fun navigateRegisterFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}