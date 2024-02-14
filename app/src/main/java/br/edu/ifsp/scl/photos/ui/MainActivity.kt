package br.edu.ifsp.scl.photos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import br.edu.ifsp.scl.photos.R
import br.edu.ifsp.scl.photos.adapter.PhotosAdapter
import br.edu.ifsp.scl.photos.databinding.ActivityMainBinding
import br.edu.ifsp.scl.photos.model.PhotosItem
import br.edu.ifsp.scl.photos.service.PhotosJsonAPI

class MainActivity : AppCompatActivity() {
    private val activityMainBinding  by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val photosList: MutableList<PhotosItem> = mutableListOf()

    private val photosAdapter: PhotosAdapter by lazy {
        PhotosAdapter(this, photosList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activityMainBinding.root)

        setSupportActionBar(activityMainBinding.mainTb.apply {
            title = getString(R.string.app_name)
        })

        activityMainBinding.titlesSp.apply {
            adapter = photosAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // preencher imagens 1 e 2 aqui
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Nothing
                }

            }
        }

        retrievePhotosList()
    }

    private fun retrievePhotosList() {
        PhotosJsonAPI.PhotosListRequest({ photosList ->
            photosList.also{
                photosAdapter.addAll(it)
            }
        },{
            Toast.makeText(this, getString(R.string.request_problem), Toast.LENGTH_SHORT).show()
        }).also {
            PhotosJsonAPI.getInstance(this).addToRequestQueue(it)
        }
    }
}