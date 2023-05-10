package uz.abdurashidov.covid19.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abdurashidov.covid19.databinding.PrevRvItemBinding
import uz.abdurashidov.covid19.models.PrevModel

class PreventationAdapter(val list: ArrayList<PrevModel>) :
    RecyclerView.Adapter<PreventationAdapter.Vh>() {

    inner class Vh(val prevRvItemBinding: PrevRvItemBinding) :
        RecyclerView.ViewHolder(prevRvItemBinding.root) {
        fun onBind(prevModel: PrevModel) {
            prevRvItemBinding.image.setImageResource(prevModel.prevImage)
            prevRvItemBinding.title.text = prevModel.prevTitle
            prevRvItemBinding.info.text = prevModel.prevInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(PrevRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}