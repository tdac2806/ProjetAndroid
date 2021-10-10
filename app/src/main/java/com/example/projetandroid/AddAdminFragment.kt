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
import com.example.projetandroid.databinding.FragmentAddAdminBinding
import com.example.projetandroid.db.GameDatabase

class AddAdminFragment : Fragment() {

    private var _binding: FragmentAddAdminBinding? = null
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
        _binding = FragmentAddAdminBinding.inflate(inflater, container, false)
        val view = binding.root
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()

        binding.addAdminNumber.text = db.userDao().countAdmin().toString()
        binding.addAdminBackArrow.setOnClickListener {
            val action = AddAdminFragmentDirections.actionAddAdminFragmentToAdminFragment()
            view.findNavController().navigate(action)
        }

        binding.addAdminButton.setOnClickListener {
            val userName = binding.addAdminInput.text.toString()
            if (userName.isNotEmpty() && userName.isNotBlank()) {
                if(db.userDao().userExists(userName)) {
                    db.userDao().setUserAdmin(userName)
                }
            }
            binding.addAdminNumber.text = db.userDao().countAdmin().toString()
            binding.addAdminInput.setText("")
            var recyclerView: RecyclerView = view.findViewById(R.id.rvUsers);
            recyclerView.apply {
                val updateUsersList = db.userDao().getAllUsers()
                var UserNameList: MutableList<String> = mutableListOf()
                for(user in updateUsersList){
                    UserNameList.add(user.username)
                }
                adapter = UserAdapter(UserNameList.toTypedArray())
            }
        }

        val usersList = db.userDao().getAllUsers()
        var UserNameList: MutableList<String> = mutableListOf()
        for(user in usersList){
            UserNameList.add(user.username)
        }
        var recyclerView: RecyclerView = view.findViewById(R.id.rvUsers);
        recyclerView.setHasFixedSize(true);
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = UserAdapter(UserNameList.toTypedArray())

            return view
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}