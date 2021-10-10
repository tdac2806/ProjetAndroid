package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.projetandroid.databinding.FragmentAddCatBinding
import com.example.projetandroid.db.Cat
import com.example.projetandroid.db.GameDatabase

class AddCatFragment : Fragment() {

    private var _binding: FragmentAddCatBinding? = null
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
        _binding = FragmentAddCatBinding.inflate(inflater, container, false)
        val view = binding.root
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()


        //All bindings
        binding.addCatNumber.text = db.catDao().countCat().toString()
        binding.addCatBackArrow.setOnClickListener {
            val action = AddCatFragmentDirections.actionAddCatFragmentToAdminFragment()
            view.findNavController().navigate(action)
        }

        binding.addCatButton.setOnClickListener {
            val catName = binding.addCatInput.text.toString()
            if (catName.isNotEmpty() && catName.isNotBlank()) {
                var currentCat: Cat = Cat(catName)
                if (db.catDao().catExists(currentCat.name) == 0) {
                    db.catDao().insert(currentCat)
                }
            }
            binding.addCatNumber.text = db.catDao().countCat().toString()
            binding.addCatInput.setText("")

        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}