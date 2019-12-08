package com.android.mobiusmvisample

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ImagesAdaper(var data: List<Bitmap>, val context: Context) :
    RecyclerView.Adapter<ImagesAdaper.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdaper.ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.image.setImageBitmap(data.get(position))
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.image
    }
}