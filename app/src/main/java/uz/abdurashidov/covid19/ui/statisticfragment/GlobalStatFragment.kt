package uz.abdurashidov.covid19.ui.statisticfragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentGlobalStatBinding
import uz.abdurashidov.covid19.models.covidmodels.RawData
import uz.abdurashidov.covid19.ui.adapter.CountryAdapter
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext


@AndroidEntryPoint
class GlobalStatFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentGlobalStatBinding.inflate(layoutInflater) }
    private val networkViewModel: NetworkViewModel by viewModels()
    private lateinit var list: ArrayList<RawData>
    private lateinit var countryAdapter: CountryAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        list = ArrayList()
        countryAdapter = CountryAdapter(list)
        binding.myRv.adapter = countryAdapter
        launch(Dispatchers.Main) {
            networkViewModel.getData().collectLatest {
                if (it.data != null) {
                    binding.myProgress.visibility = View.GONE
                    list.addAll(it.data.rawData)
                    countryAdapter.notifyDataSetChanged()
                }
            }

        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}