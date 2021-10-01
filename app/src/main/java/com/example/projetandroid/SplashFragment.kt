package com.example.projetandroid

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.findNavController
import com.example.projetandroid.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding?=null
    private val binding get() = _binding!!
    private val NumberOfTurn = 5L
    private val LoadingDuration : Long = 600*NumberOfTurn

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

        binding.imageView.animate().rotationBy((360*NumberOfTurn).toFloat()).setDuration(LoadingDuration).start()

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