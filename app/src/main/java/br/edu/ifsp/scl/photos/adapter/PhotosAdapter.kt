package br.edu.ifsp.scl.photos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.photos.model.PhotosItem

class PhotosAdapter (
    private val activityContext: Context,
    private val photosList: MutableList<PhotosItem>
): ArrayAdapter<PhotosItem>(activityContext, android.R.layout.simple_expandable_list_item_1, photosList ) {

    private data class PhotosHolder(val photoTitleTv: TextView)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val productView =
            convertView ?: LayoutInflater.from(activityContext)
                .inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
                .apply {
                    tag = PhotosHolder(findViewById(android.R.id.text1))
                }

        (productView.tag as PhotosHolder).photoTitleTv.text = photosList[position].title

        return productView
    }
}