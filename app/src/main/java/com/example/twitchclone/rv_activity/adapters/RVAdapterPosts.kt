package com.example.twitchclone.rv_activity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitchclone.R
import com.example.twitchclone.databinding.ItemTransmissionBinding
import com.example.twitchclone.data.Transmission
import com.example.twitchclone.rv_activity.RecyclerViewActivity
import com.example.twitchclone.utils.showToast

class RVAdapterPosts(
    private val posts: List<Transmission>,
    private val activity: RecyclerViewActivity
) : RecyclerView.Adapter<RVAdapterPosts.PostViewHolder>() {

    inner class PostViewHolder(private val binding: ItemTransmissionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transmission: Transmission) {
            binding.textView.text = transmission.title
            binding.imageView.setImageResource(transmission.imageResId)
            binding.imageButtonChannel.setOnClickListener {
                val message = it.context.getString(R.string.channel_button_clicked, transmission.title)
                it.context.showToast(message)
            }
            binding.buttonClose.setOnClickListener {
                activity.disableListForSeconds(3)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemTransmissionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size
}