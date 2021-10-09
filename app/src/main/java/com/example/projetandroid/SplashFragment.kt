package com.example.projetandroid

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.projetandroid.databinding.FragmentSplashBinding
import com.example.projetandroid.db.Cat
import com.example.projetandroid.db.GameDatabase
import com.example.projetandroid.db.User

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val NumberOfTurn = 5L
    private val LoadingDuration: Long = 600 * NumberOfTurn
    lateinit var db: GameDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()
        binding.imageView.animate().rotationBy((360 * NumberOfTurn).toFloat())
            .setDuration(LoadingDuration).start()

        if (db.catDao().countCat() == 0) {
            db.catDao().insert(
                Cat("Siamois"),
                Cat("Persan"),
                Cat("Angora"),
                Cat("Main coon"),
                Cat("sacré de Birmanie"),
                Cat("Sphinx"),
                Cat("Bleu")
            )
        }
        if (db.userDao().countUser() == 0) {
            db.userDao().insert(User("Admin", "Admin", true))
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }, LoadingDuration)


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}