package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.projetandroid.databinding.FragmentRemoveCatBinding
import com.example.projetandroid.db.Cat
import com.example.projetandroid.db.GameDatabase

class RemoveCatFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()

    private var _binding: FragmentRemoveCatBinding? = null
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
        _binding = FragmentRemoveCatBinding.inflate(inflater, container, false)
        val view = binding.root
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()

        binding.removeCatNumber.text = db.catDao().countCat().toString()
        binding.removeCatBackArrow.setOnClickListener {
            val action = RemoveCatFragmentDirections.actionRemoveCatFragmentToAdminFragment()
            view.findNavController().navigate(action)
        }

        binding.removeCatButton.setOnClickListener {
            val catName = binding.removeCatInput.text.toString()
            if (catName.isNotEmpty() && catName.isNotBlank()) {
                var currentCat: Cat = Cat(catName)
                if (db.catDao().catExists(currentCat.name) == 1) {
                    db.catDao().delete(currentCat.name)
                }
            }
            binding.removeCatNumber.text = db.catDao().countCat().toString()
            binding.removeCatInput.setText("")
            var recyclerView: RecyclerView = view.findViewById(R.id.rvCats);
            recyclerView.apply {
                val updateCatsList = db.catDao().getAllCat()
                adapter = CatAdapter(updateCatsList.toTypedArray())
            }
        }

        val catsList = db.catDao().getAllCat()
        var recyclerView: RecyclerView = view.findViewById(R.id.rvCats);
        recyclerView.setHasFixedSize(true);
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CatAdapter(catsList.toTypedArray())

            return view
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