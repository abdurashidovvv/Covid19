package uz.abdurashidov.covid19.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentSaveViewpagerItemBinding

class SaveViewpagerItemFragment : Fragment() {

    private val binding by lazy { FragmentSaveViewpagerItemBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        return binding.root
    }
}