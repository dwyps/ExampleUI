package com.frangrgec.exampleui.ui.settings

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.frangrgec.exampleui.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("Goggle Name", Context.MODE_PRIVATE)

        if (sharedPref.contains("Goggle Name")) {
            goggles_name.text =
                Editable.Factory.getInstance()
                    .newEditable(sharedPref.getString("Goggle Name", "GOGGLE NAME"))
        }


        goggles_name.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sharedPref.edit {
                    Log.e("text", s.toString())
                    putString("Goggle Name", s.toString())
                    commit()
                }
            }

        })

        layout.setOnClickListener {
            val inputService: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputService.hideSoftInputFromWindow(goggles_name.windowToken, 0)
            inputService.hideSoftInputFromInputMethod(goggles_name.windowToken, 0)
        }
    }
}
