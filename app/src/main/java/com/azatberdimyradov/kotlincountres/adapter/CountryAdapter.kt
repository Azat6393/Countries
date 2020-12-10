package com.azatberdimyradov.kotlincountres.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.azatberdimyradov.kotlincountres.R
import com.azatberdimyradov.kotlincountres.databinding.ItemRowRecyclerViewBinding
import com.azatberdimyradov.kotlincountres.model.Country
import com.azatberdimyradov.kotlincountres.util.downloadFromUrl
import com.azatberdimyradov.kotlincountres.util.placeHolderProgressBar
import com.azatberdimyradov.kotlincountres.view.FeedFragmentDirections

class CountryAdapter(var countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.PostHolder>(), CountryClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.PostHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_row_recycler_view,parent,false)
        val view = DataBindingUtil.inflate<ItemRowRecyclerViewBinding>(inflater,R.layout.item_row_recycler_view,parent,false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: CountryAdapter.PostHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this

       /* holder.countryNameTextView?.text = countryList[position].capitalName
        holder.countryInfoTextView?.text = countryList[position].countryRegion
        holder.imageView?.downloadFromUrl(countryList[position].imageUrl!!, placeHolderProgressBar(holder.view.context))

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return countryList.size
   }
    class PostHolder(var view: ItemRowRecyclerViewBinding): RecyclerView.ViewHolder(view.root){

        /*var imageView: ImageView? = null
        var countryNameTextView: TextView? = null
        var countryInfoTextView: TextView? = null
        init {
            imageView = view.findViewById(R.id.recycler_view_image_view)
            countryNameTextView = view.findViewById(R.id.recycler_view_country_name)
            countryInfoTextView = view.findViewById(R.id.recycler_view_country_info)
        }*/
    }

    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClick(view: View) {
        val uuid = view.findViewById<TextView>(R.id.countryUuidText).text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}