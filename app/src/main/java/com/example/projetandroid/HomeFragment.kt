package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Retrieves data from datasource
        val playerNameList = Datasource(requireContext()).getPlayerNameList()
        val playerScoreList = Datasource(requireContext()).getPlayerScoreList()

        var recyclerView: RecyclerView = view.findViewById(R.id.rvPlayers);
        recyclerView.setHasFixedSize(true);
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PlayerAdapter(playerNameList, playerScoreList)

            return view
        }

        binding.textButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAdminFragment()
            view.findNavController().navigate(action)
        }

        binding.homePlayButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGameFragment()
            view.findNavController().navigate(action)
        }
    }
}