package project.arknightscharacterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.arknightscharacterslist.databinding.ItemRowCharBinding

class ListCharAdapter(private val listChar: ArrayList<Char>) : RecyclerView.Adapter<ListCharAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowCharBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photoChar, photoClass, photoSubclass) = listChar[position]
        Glide.with(holder.itemView.context)
            .load(photoChar)
            .into(holder.binding.imgCharPhoto)
        Glide.with(holder.itemView.context)
            .load(photoClass)
            .into(holder.binding.imgClassPhoto)
        Glide.with(holder.itemView.context)
            .load(photoSubclass)
            .into(holder.binding.imgSubclassPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listChar[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listChar.size

    class ListViewHolder(var binding: ItemRowCharBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Char)
    }
}