package org.mazaady.com.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.mazaady.com.R
import org.mazaady.com.databinding.ActivityMainBinding
import org.mazaady.com.presentation.category.CategoriesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeObserver()

    }

    private fun subscribeObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->

//                    activity?.showProgressBar(uiState.isLoading)

                    uiState.categoriesData?.let {
//                        binding.detailConstraint.isVisible = true
//                        setMovieDetail(movieDetail = it)
                    }

                    if (uiState.errorMessage != null) {
//                        binding.detailConstraint.isVisible = false
//                        activity?.showRetry(true,uiState.errorMessage)
                    } else
//                        activity?.showRetry(false,null)
                }
            }
        }
    }

}