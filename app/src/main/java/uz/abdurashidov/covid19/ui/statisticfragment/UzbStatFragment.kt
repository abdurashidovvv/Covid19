package uz.abdurashidov.covid19.ui.statisticfragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentUzbStatBinding

class UzbStatFragment : Fragment() {

    private val binding by lazy { FragmentUzbStatBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        val s1 = Segment("S1", 23)
        val s3 = Segment("S1", 55)

        val sf1 = SegmentFormatter(Color.BLUE)
        val sf3 = SegmentFormatter(Color.CYAN)

        binding.pieChart.addSegment(s1,sf1)
        binding.pieChart.addSegment(s3,sf3)

        return binding.root
    }
}