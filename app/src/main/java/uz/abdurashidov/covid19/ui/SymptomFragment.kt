package uz.abdurashidov.covid19.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentSymptomBinding

class SymptomFragment : Fragment() {

    private val binding by lazy { FragmentSymptomBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        return binding.root
    }
}