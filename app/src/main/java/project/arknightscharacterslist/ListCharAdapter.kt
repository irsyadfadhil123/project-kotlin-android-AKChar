package project.arknightscharacterslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListCharAdapter(private val listChar: ArrayList<Char>) : RecyclerView.Adapter<ListCharAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_char, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photoChar, photoClass, photoSubclass) = listChar[position]
        Glide.with(holder.itemView.context)
            .load(photoChar)
            .into(holder.imgPhotoChar)
        Glide.with(holder.itemView.context)
            .load(photoClass)
            .into(holder.imgPhotoClass)
        Glide.with(holder.itemView.context)
            .load(photoSubclass)
            .into(holder.imgPhotoSubclass)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listChar[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listChar.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhotoChar: ImageView = itemView.findViewById(R.id.img_char_photo)
        val imgPhotoClass: ImageView = itemView.findViewById(R.id.img_class_photo)
        val imgPhotoSubclass: ImageView = itemView.findViewById((R.id.img_subclass_photo))
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Char)
    }
}