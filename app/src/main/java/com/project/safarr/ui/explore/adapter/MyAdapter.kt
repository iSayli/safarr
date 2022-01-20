package com.project.safarr.ui.explore.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.safarr.R
import com.project.safarr.ui.explore.data.Places

class MyAdapter(private val placesList: ArrayList<Places>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_places,parent,false)
        return MyViewHolder(itemView as ViewGroup)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = placesList[position]
        holder.town.text = currentItem.town
        holder.state.text = currentItem.state
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val state : TextView = itemView.findViewById(R.id.places_state)
        val town : TextView = itemView.findViewById(R.id.places_town)
    }
}