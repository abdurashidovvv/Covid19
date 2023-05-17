package uz.abdurashidov.covid19.ui.statisticfragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.databinding.FragmentUzbStatBinding
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class UzbStatFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentUzbStatBinding.inflate(layoutInflater) }
    private val networkViewModel: NetworkViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        launch(Dispatchers.Main) {
            networkViewModel.getData().collectLatest {
                if (it.data != null) {
                    Log.d("@uzbStatFragment", "onCreateView: ${it.data.rawData}")
                    it.data.rawData.forEach { country ->
                        if (country.Country_Region == "Uzbekistan") {
                            Log.d("@uzbStatFragment", "onCreateView: $country")
                            val s1 = Segment("${country.Confirmed}", country.Confirmed.toInt())
                            val s3 = Segment("${country.Deaths}", country.Deaths.toInt())

                            val sf1 = SegmentFormatter(Color.BLUE)
                            val sf3 = SegmentFormatter(Color.CYAN)


                            binding.pieChart.addSegment(s1, sf1)
                            binding.pieChart.addSegment(s3, sf3)

                            binding.deathNumber.text = country.Deaths
                            binding.caseNumber.text = country.Confirmed

                            binding.progressBar.visibility = View.GONE
                            binding.pieChart.invalidate()
                        }
                    }
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}