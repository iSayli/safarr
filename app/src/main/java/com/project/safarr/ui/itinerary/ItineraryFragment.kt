package com.project.safarr.ui.itinerary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.safarr.databinding.FragmentItineraryBinding

class ItineraryFragment : Fragment() {

    private lateinit var itineraryViewModel: ItineraryViewModel
    private var _binding: FragmentItineraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itineraryViewModel =
            ViewModelProvider(this).get(ItineraryViewModel::class.java)

        _binding = FragmentItineraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textItinerary
        itineraryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}