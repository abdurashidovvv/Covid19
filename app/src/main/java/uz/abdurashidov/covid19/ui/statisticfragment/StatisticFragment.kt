package uz.abdurashidov.covid19.ui.statisticfragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentStatisticBinding
import uz.abdurashidov.covid19.utils.Status
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class StatisticFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentStatisticBinding.inflate(layoutInflater) }
    private val networkViewModel: NetworkViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val fragment = GlobalStatFragment()
        replaceFragment(fragment)

        launch {
            networkViewModel.getData().collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("@statisticsFragment", "onCreateView: ${it.data?.rawData}")
                    }

                    Status.ERROR -> {
                        Log.d("@statisticsFragment", "onCreateView: ${it.message}")
                    }

                    Status.LOADING -> {
                        Log.d("@statisticsFragment", "onCreateView: ${it.message}")
                    }
                }
            }
        }

        binding.global.setOnClickListener {
            val fragment = GlobalStatFragment()
            binding.global.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.uzb.setCardBackgroundColor(Color.parseColor("#353B45"))
            binding.globalTv.setTextColor(Color.parseColor("#353B45"))
            binding.uzbTv.setTextColor(Color.parseColor("#FFFFFF"))
            replaceFragment(fragment)
        }
        binding.uzb.setOnClickListener {
            binding.global.setCardBackgroundColor(Color.parseColor("#353B45"))
            binding.uzb.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.uzbTv.setTextColor(Color.parseColor("#353B45"))
            binding.globalTv.setTextColor(Color.parseColor("#FFFFFF"))
            val fragment = UzbStatFragment()
            replaceFragment(fragment)
        }

        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()


    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}