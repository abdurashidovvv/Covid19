package uz.abdurashidov.covid19.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abdurashidov.covid19.databinding.CountryRvItemBinding
import uz.abdurashidov.covid19.models.covidmodels.RawData

class CountryAdapter(private val list: ArrayList<RawData>) :
    RecyclerView.Adapter<CountryAdapter.Vh>() {

    inner class Vh(private val rvItem: CountryRvItemBinding) :
        RecyclerView.ViewHolder(rvItem.root) {
        fun onBind(rawData: RawData) {
            rvItem.countryName.text = rawData.Country_Region
            rvItem.death.text = rawData.Deaths
            rvItem.totalCase.text = rawData.Confirmed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(CountryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}