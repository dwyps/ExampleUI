package com.frangrgec.exampleui.ui.phoneVideos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.frangrgec.exampleui.R

class PhoneVideosFragment : Fragment() {

    private lateinit var phoneVideosViewModel: PhoneVideosViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        phoneVideosViewModel =
                ViewModelProviders.of(this).get(PhoneVideosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_phone_videos, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        phoneVideosViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
