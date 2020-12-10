package com.azatberdimyradov.kotlincountres.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.azatberdimyradov.kotlincountres.R
import com.azatberdimyradov.kotlincountres.adapter.CountryAdapter
import com.azatberdimyradov.kotlincountres.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private var countryAdapter = CountryAdapter(arrayListOf())
    private var recyclerView: RecyclerView? = null
    private var errorTextView: TextView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.feed_fragment_recycler_view)
        errorTextView = view.findViewById(R.id.feed_fragment_text_view)
        progressBar = view.findViewById(R.id.feed_fragment_progress_bar)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = countryAdapter

        val refreshLayout: SwipeRefreshLayout = view.findViewById(R.id.feed_fragment_refresh_layout)

        refreshLayout.setOnRefreshListener {
                recyclerView?.visibility = View.INVISIBLE
                errorTextView?.visibility = View.INVISIBLE
                progressBar?.visibility = View.VISIBLE
                viewModel.refreshFromAPI()
                refreshLayout.isRefreshing = false
            }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
            countries?.let {
                recyclerView?.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if (it){
                    errorTextView?.visibility = View.VISIBLE
                }else{
                    errorTextView?.visibility = View.GONE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    progressBar?.visibility = View.VISIBLE
                    recyclerView?.visibility = View.GONE
                    errorTextView?.visibility = View.GONE
                } else {
                    progressBar?.visibility = View.GONE
                }
            }
        })
    }
}