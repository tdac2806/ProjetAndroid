package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.projetandroid.databinding.FragmentAdminBinding
import com.example.projetandroid.db.Cat
import com.example.projetandroid.db.GameDatabase

class AdminFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()

    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!
    lateinit var db: GameDatabase

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
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()

        binding.adminCatNumber.text = db.catDao().countCat().toString()
        binding.adminBackArrow.setOnClickListener {
            val action = AdminFragmentDirections.actionAdminFragmentToHomeFragment()
            view.findNavController().navigate(action)
        }

        binding.adminAddCatButton.setOnClickListener {
            val action = AdminFragmentDirections.actionAdminFragmentToAddCatFragment()
            view.findNavController().navigate(action)
        }

        binding.adminRemoveCatButton.setOnClickListener {
            val action = AdminFragmentDirections.actionAdminFragmentToRemoveCatFragment()
            view.findNavController().navigate(action)
        }
        return view
    }

    private suspend fun addCat() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}