package org.mazaady.com.presentation.mazaad_result_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import org.mazaady.com.R
import org.mazaady.com.databinding.ActivityMainBinding
import org.mazaady.com.databinding.ActivityMazaadResultBinding
import org.mazaady.com.presentation.bottom_sheet_dialog.adapter.MazaadDataViewerRecyclerViewAdapter
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadDataModel


class MazaadResultActivity : AppCompatActivity() {
    lateinit  var binding:ActivityMazaadResultBinding
    lateinit  var mazaadDaataViewerAdapter: MazaadDataViewerRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMazaadResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mazaadHeader.appBarTitle.setText(R.string.mazaad_data)
        binding.mazaadHeader.appBarIcon.setOnClickListener {
            finish()
        }
        mazaadDaataViewerAdapter=MazaadDataViewerRecyclerViewAdapter(this)
        val mazaadDataModel = intent.extras?.getSerializable("data") as MazaadDataModel
        setUpRecyclerViewData(mazaadDataModel)
    }

    private fun setUpRecyclerViewData(mazaadDataModel: MazaadDataModel) {
        mazaadDaataViewerAdapter.setData(mazaadDataModel.data)
        with(binding.mazzadRcData) {
            layoutManager=LinearLayoutManager(this@MazaadResultActivity)
            adapter=mazaadDaataViewerAdapter
        }
    }
}