package com.example.cv.ui.notifications

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cv.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.sendButton.setOnClickListener(){
            val email = "tdnet59@gmail.com"
            val subject = binding.subjectInput.text.toString()
            val message = binding.messageInput.text.toString()

            val adresses = email.split(",".toRegex()).toTypedArray()

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL,adresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, message)
            }

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                //error
            }
        }

        val button : Button = binding.socialBtn
        val linearLayout : LinearLayout = binding.linearLayout
        val lnBtn : Button  = binding.linkedinBtn
        val gtBtn : Button  = binding.githubBtn

        button.setOnClickListener {
            if(lnBtn.isVisible && gtBtn.isVisible) {
                lnBtn.visibility = View.GONE
                gtBtn.visibility = View.GONE
                linearLayout.layoutParams.height = 0
                linearLayout.visibility = View.INVISIBLE
                button.setText("Réseaux sociaux \uD83D\uDD3D")
            } else {
                lnBtn.visibility = View.VISIBLE
                gtBtn.visibility = View.VISIBLE
                linearLayout.visibility = View.VISIBLE
                linearLayout.layoutParams.height = 260
                button.setText("Réseaux sociaux \uD83D\uDD3C")
            }
        }

        lnBtn.setOnClickListener() {
                val viewIntent = Intent(
                    "android.intent.action.VIEW",
                    Uri.parse("http://www.linkedin.com/in/thibautdusautoir/")
                )
                startActivity(viewIntent)
        }

        gtBtn.setOnClickListener() {
            val viewIntent = Intent(
                "android.intent.action.VIEW",
                Uri.parse("http://www.github.com/tdusautoir")
            )
            startActivity(viewIntent)
        }
//
//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}