package com.dudencovgmaill.weatherapp.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dudencovgmaill.weatherapp.R
import com.dudencovgmaill.weatherapp.databinding.RvItemBinding
import com.dudencovgmaill.weatherapp.repo.local.models.City
import com.dudencovgmaill.weatherapp.tools.inflate
import com.dudencovgmaill.weatherapp.tools.inflater
import kotlinx.android.synthetic.main.fragment_main.view.*

open class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var clickedListener: ((Int) -> Unit)? = null

    protected open val list: ArrayList<City> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        /*val binding = RvItemBinding.inflate(parent.inflater(), parent, false)*/
        val binding = RvItemBinding.bind(parent.inflate(R.layout.rv_item))
        return MainViewHolder(binding, clickedListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<City>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}

open class MainViewHolder(private val binding: RvItemBinding, clickedListener: ((Int) -> Unit)? = null) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { clickedListener?.invoke(adapterPosition) }
    }

    fun bindData(city: City) {
        binding.run {
            this.city = city
            executePendingBindings()
        }
    }
}

