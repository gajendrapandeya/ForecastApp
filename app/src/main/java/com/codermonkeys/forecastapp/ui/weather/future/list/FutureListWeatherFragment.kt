package com.codermonkeys.forecastapp.ui.weather.future.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.codermonkeys.forecastapp.R
import com.codermonkeys.forecastapp.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.codermonkeys.forecastapp.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry
import com.codermonkeys.forecastapp.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.future_list_weather_fragment.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FutureListWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: FutureListWeatherViewModelFactory by instance()

    private lateinit var viewModel: FutureListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(FutureListWeatherViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch(Main) {

        val futureWeatherEntries = viewModel.weatherEntries.await()
        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(viewLifecycleOwner, Observer { location ->
            if (location == null)
                return@Observer
            updateLocation(location.name)
        })

        futureWeatherEntries.observe(viewLifecycleOwner, Observer { weatherEntries ->
            if (weatherEntries == null) return@Observer

            group_loading.visibility = View.GONE
            updateDateToNextWeek()
            initRecyclerView(weatherEntries.toFutureWeatherItems())
        })
    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToNextWeek() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Next Week"
    }

    private fun List<UnitSpecificSimpleFutureWeatherEntry>.toFutureWeatherItems(): List<FutureWeatherItem> {
        return this.map {
            FutureWeatherItem(it)
        }
    }

    private fun initRecyclerView(items: List<FutureWeatherItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FutureListWeatherFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(
                this@FutureListWeatherFragment.context,
                "Clicked $item",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}