package com.azatberdimyradov.kotlincountres.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.azatberdimyradov.kotlincountres.R
import com.azatberdimyradov.kotlincountres.databinding.FragmentCountreBinding
import com.azatberdimyradov.kotlincountres.util.downloadFromUrl
import com.azatberdimyradov.kotlincountres.util.placeHolderProgressBar
import com.azatberdimyradov.kotlincountres.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    private var countryUUID = 0
    private lateinit var viewModel: CountryViewModel
    private lateinit var dataBinding: FragmentCountreBinding

    /*private var nameTextView: TextView? = null
    private var regionTextView: TextView? = null
    private var languageTextView: TextView? = null
    private var currencyTextView: TextView? = null
    private var capitalTextView: TextView? = null
    private var imageView: ImageView? = null*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_countre,container,false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*nameTextView = view.findViewById(R.id.country_fragment_name)
        regionTextView = view.findViewById(R.id.country_fragment_region)
        languageTextView = view.findViewById(R.id.country_fragment_language)
        currencyTextView = view.findViewById(R.id.country_fragment_currency)
        capitalTextView = view.findViewById(R.id.country_fragment_capital)
        imageView = view.findViewById(R.id.country_fragment_image_view)*/

        arguments?.let {
            countryUUID = CountryFragmentArgs.fromBundle(it).countryUUID
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUUID)


        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = country
                /*ameTextView?.text = country.countryName
                regionTextView?.text = country.countryRegion
                languageTextView?.text = country.countryLanguage
                currencyTextView?.text = country.countryCurrency
                capitalTextView?.text = country.capitalName
                context?.let {
                    imageView?.downloadFromUrl(country.imageUrl!!, placeHolderProgressBar(it))
                }*/
            }
        })
    }
}