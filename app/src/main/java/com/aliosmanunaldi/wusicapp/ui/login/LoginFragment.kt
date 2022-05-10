package com.aliosmanunaldi.wusicapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.aliosmanunaldi.wusicapp.User
import com.aliosmanunaldi.wusicapp.data.login.LoginRepository
import com.aliosmanunaldi.wusicapp.databinding.FragmentLoginBinding

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

        if (viewState.result?.data != null){
            navigateHomeFragment(viewState)
        }
    }

    private fun navigateHomeFragment(viewState: LoginPageViewState) {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment(viewState.result?.data!!))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}