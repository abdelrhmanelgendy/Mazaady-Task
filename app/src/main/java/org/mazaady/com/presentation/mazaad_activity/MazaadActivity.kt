package org.mazaady.com.presentation.mazaad_activity

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.mazaady.com.R
import org.mazaady.com.databinding.ActivityMainBinding
import org.mazaady.com.databinding.ActivityMazaadBinding
import org.mazaady.com.presentation.bottom_sheet_dialog.adapter.MazaadSimilarGoodsAdapter

class MazaadActivity : AppCompatActivity() {
    lateinit var binding: ActivityMazaadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMazaadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.similarItemsRec) {
            layoutManager=LinearLayoutManager(this@MazaadActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter=MazaadSimilarGoodsAdapter(this@MazaadActivity)
        }

    }
}