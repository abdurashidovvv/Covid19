package uz.abdurashidov.covid19.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentNewsBinding
import uz.abdurashidov.covid19.models.ViewPagerItem
import uz.abdurashidov.covid19.ui.adapter.StateAdapters


class NewsFragment : Fragment() {

    private val binding by lazy { FragmentNewsBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<ViewPagerItem>
    private lateinit var stateAdapters: StateAdapters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = ArrayList()
        list.addAll(arrayOf(ViewPagerItem(type = "news"), ViewPagerItem(type = "saved")))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        stateAdapters = StateAdapters(list, this)
        binding.myViewpager.adapter = stateAdapters


        TabLayoutMediator(binding.myTab, binding.myViewpager) { tab, position ->
            tab.text = list[position].type
        }.attach()
        return binding.root
    }
}