package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.projetandroid.databinding.FragmentAdminBinding

class AdminFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()

    private var _binding: FragmentAdminBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.login.observe(viewLifecycleOwner,
            { newvalue ->  binding.adminTitle.text = newvalue
            }
        )

        binding.adminBackArrow.setOnClickListener {
            val action = AdminFragmentDirections.actionAdminFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}