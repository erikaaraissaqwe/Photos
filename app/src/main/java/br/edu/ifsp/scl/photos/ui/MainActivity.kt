package br.edu.ifsp.scl.photos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.photos.R
import br.edu.ifsp.scl.photos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding  by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activityMainBinding.root)

        setSupportActionBar(activityMainBinding.mainTb.apply {
            title = getString(R.string.app_name)
        })
    }
}