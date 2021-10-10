package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.projetandroid.databinding.FragmentLoginBinding
import com.example.projetandroid.db.GameDatabase
import com.example.projetandroid.db.User

class LoginFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var db: GameDatabase
    private var connected: Boolean = false
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
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()

        binding.loginClose.setOnClickListener {
            activity?.finish()
        }

        binding.loginButton.setOnClickListener {
            val loginInput = binding.loginInput.text.toString()
            val passwordInput = binding.passwordInput.text.toString()
            if (loginInput.isNotEmpty() && loginInput.isNotBlank() &&
                passwordInput.isNotEmpty() && passwordInput.isNotBlank()) {
                connected = db.userDao().userExists(loginInput)
                if (!connected) {
                    db.userDao().insert(User(loginInput, passwordInput, false))
                    startHomeFragment(loginInput, view)
                } else {
                    if (db.userDao().userPasswordOk(loginInput, passwordInput)) {
                        startHomeFragment(loginInput, view)
                    }
                    else{
                        binding.loginDetails.text = "Mot de passe invalide"
                        binding.loginDetails.visibility = View.VISIBLE
                    }
                }


            } else {
                binding.loginDetails.text = "Veuillez entrer un pseudo avec au moins 1 caractère"
                binding.loginDetails.visibility = View.VISIBLE
            }
        }

        return view
    }

    private fun startHomeFragment(loginInput: String, view: View) {
        viewModel.setLogin(loginInput)
        binding.loginDetails.visibility = View.INVISIBLE
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        view.findNavController().navigate(action)
    }
}