package uz.abdurashidov.covid19.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.abdurashidov.covid19.models.ViewPagerItem
import uz.abdurashidov.covid19.ui.ViewPagerItemFragment

class StateAdapters(val list:ArrayList<ViewPagerItem>, fragment: Fragment)
    : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerItemFragment.newInstance(list[position].type,"")
    }
}