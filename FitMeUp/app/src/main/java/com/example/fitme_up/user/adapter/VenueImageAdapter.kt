package com.example.fitme_up.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.fitme_up.R
import com.example.fitme_up.user.dataset.VenueImageData

class VenueImageAdapter(private val imageList: List<VenueImageData>) :
    RecyclerView.Adapter<VenueImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.venueImageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pager_venue_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = imageList[position]

        Glide.with(holder.itemView.context)
            .load(image.url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)



    }
    override fun getItemCount() = imageList.size
}