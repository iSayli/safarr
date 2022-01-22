package com.project.safarr.ui.explore.adapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.safarr.placePackage.PlacesActivity
import com.project.safarr.R
import com.project.safarr.ui.explore.data.Places

class MyAdapter(private val context: Context , private val placesList: ArrayList<Places>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
/*
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_places,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem : Places = placesList[position]
        holder.town.text = currentItem.town
        holder.state.text = currentItem.state
        Glide.with(context).load(currentItem.img).into(holder.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlacesActivity::class.java)
            intent.putExtra("main_town",currentItem.town)
            holder.itemView.context.startActivity(intent)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val img : ImageView = itemView.findViewById(R.id.places_image)
        val state : TextView = itemView.findViewById(R.id.places_state)
        val town : TextView = itemView.findViewById(R.id.places_town)

/*
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
*/
    }
}