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
import com.example.projetandroid.databinding.FragmentHomeBinding
import com.example.projetandroid.db.GameDatabase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var db: GameDatabase
    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var username: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()
        viewModel.login.observe(viewLifecycleOwner,
            { newvalue ->
                username = newvalue.toString()
            }
        )
        binding.textButton.setOnClickListener {
            if (db.userDao().isUserAdmin(username)) {
                val action = HomeFragmentDirections.actionHomeFragmentToAdminFragment()
                view.findNavController().navigate(action)
            }

        }

        binding.homePlayButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGameFragment()
            view.findNavController().navigate(action)
        }
        val UsersList = db.userDao().getAllUsers()
        var UsersNames: MutableList<String> = arrayListOf()
        var UsersScores: MutableList<String> = arrayListOf()
        for(user in UsersList){
            UsersNames.add(user.username)
            UsersScores.add(user.maxscore.toString())
        }

        var recyclerView: RecyclerView = view.findViewById(R.id.rvPlayers);
        recyclerView.setHasFixedSize(true);
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PlayerAdapter(UsersNames.toTypedArray(), UsersScores.toTypedArray())

            return view
        }
    }
}