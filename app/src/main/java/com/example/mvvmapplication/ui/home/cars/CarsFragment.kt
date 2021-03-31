package com.example.mvvmapplication.ui.home.cars

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.db.entities.Car
import com.example.mvvmapplication.util.Coroutines
import com.example.mvvmapplication.util.hide
import com.example.mvvmapplication.util.show
import com.example.mvvmapplication.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.cars_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CarsFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private lateinit var viewModel: CarsViewModel
    private val factory: CarsViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cars_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(CarsViewModel::class.java)
        bindUI()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.cars.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toCarItem())
        })
    }

    private fun initRecyclerView(carItem: List<CarItem>) {
        val myAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(carItem)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }
    }

    private fun List<Car>.toCarItem() : List<CarItem> {
        return this.map {
            CarItem(it)
        }
    }
}