package com.example.cv.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionManager;
import androidx.transition.Fade
import androidx.transition.Transition
import com.example.cv.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val button : Button = binding.button
        val linearLayout : LinearLayout = binding.linearLayout
        val info : TextView  = binding.info
        val interest : TextView  = binding.interest

        button.setOnClickListener {
            if(info.isVisible && interest.isVisible) {
                info.visibility = View.GONE
                interest.visibility = View.GONE
                button.setText("PROFIL \uD83D\uDD3D")
            } else {
                interest.visibility = View.VISIBLE
                info.visibility = View.VISIBLE
                button.setText("PROFIL \uD83D\uDD3C")
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}