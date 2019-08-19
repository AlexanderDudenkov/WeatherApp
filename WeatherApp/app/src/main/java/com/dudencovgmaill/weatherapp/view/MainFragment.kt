package com.dudencovgmaill.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.dudencovgmaill.weatherapp.R
import com.dudencovgmaill.weatherapp.databinding.FragmentMainBinding
import com.dudencovgmaill.weatherapp.di.components.DaggerIMainFragmentComponent
import com.dudencovgmaill.weatherapp.di.injector
import com.dudencovgmaill.weatherapp.di.modules.MainFragmentModule
import com.dudencovgmaill.weatherapp.tools.inflate
import com.dudencovgmaill.weatherapp.view.adapters.MainAdapter
import com.dudencovgmaill.weatherapp.view.adapters.MainViewHolder
import com.dudencovgmaill.weatherapp.view.base.ABaseFragment
import com.dudencovgmaill.weatherapp.viewmodel.base.AMainFragmentVm
import com.dudencovgmaill.weatherapp.viewmodel.MainFragmentVm
import javax.inject.Inject

open class MainFragment : ABaseFragment() {

    @Inject
    lateinit var lm: RecyclerView.LayoutManager

    @Inject
    lateinit var adapter: RecyclerView.Adapter<MainViewHolder>

    protected open lateinit var binding: FragmentMainBinding

    protected open val vm: AMainFragmentVm by lazy {
        ViewModelProviders.of(this, activity?.injector?.mainViewModelFactory()).get(MainFragmentVm::class.java)
    }

    protected open var component: Any? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.bind(container!!.inflate(R.layout.fragment_main))
        binding.vm = vm as MainFragmentVm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.onViewCreated()
        initDI()
        initRv()
        updateAdapter()
        startMapFragment()
        getListAddress()

        (adapter as MainAdapter).clickedListener = { vm.clickOnItem(it) }
    }

    protected open fun initRv() {
        binding.rvFrMain.run {
            layoutManager = lm
            this.adapter = this@MainFragment.adapter
        }
    }

    protected open fun initDI() {
        component = DaggerIMainFragmentComponent.builder()
            .iAppComponent((activity as MainActivity).injector)
            .mainFragmentModule(MainFragmentModule(activity as MainActivity))
            .build()
            .apply { inject(this@MainFragment) }
    }

    protected open fun updateAdapter() {
        vm.list.observe(this, Observer {
            (adapter as MainAdapter).setList(it)
        })
    }

    protected open fun startMapFragment() {
        vm.startMapFragment.observe(this, Observer { id ->
            if (id != null)
                navigate(R.id.mapFragment, MainFragmentDirections.actionMainFragmentToMapFragment().also { it.id = id })
        })
    }

    protected open fun getListAddress() {
        CityLocationListener.getLocation().observe(this, Observer { vm.setLocation(it) })
    }
}