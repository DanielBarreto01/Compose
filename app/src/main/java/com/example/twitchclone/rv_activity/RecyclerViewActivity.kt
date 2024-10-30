package com.example.twitchclone.rv_activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitchclone.R
import com.example.twitchclone.rv_activity.adapters.RVAdapterPosts
import com.example.twitchclone.utils.showToast

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var rvPosts: RecyclerView
    private lateinit var viewModel: TransmissionViewModel
    private lateinit var loadingIndicator: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        viewModel = ViewModelProvider(this).get(TransmissionViewModel::class.java)
        initializeView()
        observeViewModel()
    }

    private fun initializeView() {
        rvPosts = findViewById(R.id.rv_posts)
        loadingIndicator = findViewById(R.id.loading_indicator)
        rvPosts.layoutManager = GridLayoutManager(this, 3)
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { uiState ->
            if (uiState.isLoading) {
                showToast("Loading...")
            } else {
                if (uiState.transmissions.isEmpty()) {
                    showToast("No transmissions available.")
                } else {
                    rvPosts.adapter = RVAdapterPosts(uiState.transmissions, this@RecyclerViewActivity)
                    showToast("Data loaded successfully!")
                }
            }
            if (!uiState.isListActive) {
                showToast("List deactivated for ${uiState.deactivationTime} seconds")
            } else {
                showToast("Active list")
            }
        }
    }

    fun disableListForSeconds(seconds: Int) {
        viewModel.setListActive(false, seconds)
        rvPosts.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            rvPosts.visibility = View.VISIBLE
            loadingIndicator.visibility = View.GONE
            viewModel.setListActive(true, 0)
        }, (seconds * 1000).toLong())
    }
}