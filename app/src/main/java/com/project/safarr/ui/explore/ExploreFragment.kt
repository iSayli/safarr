package com.project.safarr.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.project.safarr.R
import com.project.safarr.databinding.FragmentExploreBinding
import com.project.safarr.ui.explore.adapter.ItemAdapter
import com.project.safarr.ui.explore.adapter.MyAdapter
import com.project.safarr.ui.explore.data.Datasource
import com.project.safarr.ui.explore.data.Places


class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel
    private var _binding: FragmentExploreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private lateinit var dbref : DatabaseReference
    private lateinit var placesRecyclerView: RecyclerView
    private lateinit var placesArrayList : ArrayList<Places>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        val myDataset = Datasource().loadAffirmations()
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL ,false)
        recyclerView.adapter = ItemAdapter(this, myDataset)

        val root: View = binding.root
        placesRecyclerView = view.findViewById(R.id.placesRecycler)
        placesRecyclerView.layoutManager = LinearLayoutManager(activity)
        placesRecyclerView.setHasFixedSize(true)
        placesArrayList = arrayListOf<Places>()

        dbref = FirebaseDatabase.getInstance().getReference("Places")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(placesSnapshot in snapshot.children)
                    {
                        val place = placesSnapshot.getValue(Places::class.java)
                        placesArrayList.add(place!!)
                    }
                    placesRecyclerView.adapter =  MyAdapter(placesArrayList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("not connected");
            }
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}