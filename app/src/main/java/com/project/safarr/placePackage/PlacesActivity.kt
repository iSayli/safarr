package com.project.safarr.placePackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore

import com.project.safarr.R
import com.project.safarr.placePackage.adapter.ViewPageAdapter

class PlacesActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        val mainTown : TextView = findViewById(R.id.main_town)
        val mainState : TextView = findViewById(R.id.main_state)

        val bundle : Bundle?= intent.extras
        val town = bundle!!.get("main_town")
        mainTown.text = town.toString()

        db = FirebaseFirestore.getInstance()

        db.collection("Place").whereEqualTo("town", town)
            .addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("Listen failed.", e)
                return@addSnapshotListener
            }

            for (doc in snapshot!!){
                mainState.text = doc.getString("state")
            }
        }

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)

        val adapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="Overview"
                }
                1->{
                    tab.text="Safety"
                }
                2->{
                    tab.text="Travelogue"
                }
            }
        }.attach()


    }
}