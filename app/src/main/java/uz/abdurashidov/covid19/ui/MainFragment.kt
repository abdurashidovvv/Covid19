package uz.abdurashidov.covid19.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentMainBinding
import uz.abdurashidov.covid19.ui.news.NewsFragment
import uz.abdurashidov.covid19.ui.statisticfragment.StatisticFragment

class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding.navView.setNavigationItemSelectedListener(this)

        val fragment=HomeFragment()
        replaceFragment(fragment)

        binding.menu.setOnClickListener {
            binding.drawer.open()
        }

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                val fragment=HomeFragment()
                replaceFragment(fragment)
                return true
            }
            R.id.statistics -> {
                val fragment=StatisticFragment()
                replaceFragment(fragment)
                return true
            }
            R.id.symptoms -> {
                val fragment=SymptomFragment()
                replaceFragment(fragment)
                return true
            }
            R.id.preventation -> Toast.makeText(context, "Preventation !", Toast.LENGTH_SHORT).show()
            R.id.article -> {
                val fragment=ArticleFragment()
                replaceFragment(fragment)
                return true
            }
            R.id.news -> {
                val fragment= NewsFragment()
                replaceFragment(fragment)
                return true
            }
            R.id.help -> {
                val fragment=HelpFragment()
                replaceFragment(fragment)
                return true
            }
        }
        return false
    }


    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}