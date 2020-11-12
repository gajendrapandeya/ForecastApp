package com.codermonkeys.forecastapp.ui.weather.current

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.codermonkeys.forecastapp.R
import com.codermonkeys.forecastapp.data.network.ApixuWeatherApiService
import com.codermonkeys.forecastapp.data.network.ConnectivityInterceptorImpl
import com.codermonkeys.forecastapp.data.network.WeatherNetworkDataSource
import com.codermonkeys.forecastapp.data.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.requireContext()))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            textView.text = it.toString()
        })
        GlobalScope.launch(Main) {
            weatherNetworkDataSource.fetchCurrentWeather("Dhangadhi", "en")
        }
    }

}