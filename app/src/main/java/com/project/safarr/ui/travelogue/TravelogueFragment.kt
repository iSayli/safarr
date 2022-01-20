package com.project.safarr.ui.travelogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.safarr.databinding.FragmentTravelogueBinding

class TravelogueFragment : Fragment() {

    private lateinit var travelogueViewModel: TravelogueViewModel
    private var _binding: FragmentTravelogueBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        travelogueViewModel =
            ViewModelProvider(this).get(TravelogueViewModel::class.java)

        _binding = FragmentTravelogueBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTravelogue
        travelogueViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}