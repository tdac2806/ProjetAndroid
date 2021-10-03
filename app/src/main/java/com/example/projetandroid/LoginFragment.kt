package com.example.projetandroid

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.projetandroid.databinding.FragmentLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.NonCancellable.cancel

class LoginFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loginButton.setOnClickListener {
            val loginInput = binding.loginInput.text.toString()

            if (loginInput.isNotEmpty() && loginInput.isNotBlank()) {
                viewModel.setLogin(loginInput)
                binding.loginDetails.visibility = View.INVISIBLE

                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                view.findNavController().navigate(action)
            } else {
                binding.loginDetails.text = "Veuillez entrer un pseudo avec au moins 1 caractère"
                binding.loginDetails.visibility = View.VISIBLE
            }
        }

        return view
    }

}