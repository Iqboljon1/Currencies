package com.iraimjanov.currencies.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iraimjanov.currencies.R
import com.iraimjanov.currencies.databinding.ItemMoneyBinding
import com.iraimjanov.currencies.model.Money

class RvAdapter(
    private val listMoney: ArrayList<Money>,
) :
    RecyclerView.Adapter<RvAdapter.VH>() {

    inner class VH(private val itemRV: ItemMoneyBinding) : RecyclerView.ViewHolder(itemRV.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(money: Money) {

            itemRV.tvName.text = money.CcyNm_RU
            itemRV.tvPrice.text = "${money.Rate} сум"
            itemRV.tvTrending.text = money.Diff
            if (money.Diff.toDouble() == 0.0) {
                itemRV.tvTrending.setTextColor(Color.BLACK)
                itemRV.imageTrending.setImageResource(R.drawable.ic_trending_flat)
            }
            if (money.Diff.toDouble() > 0.0) {
                itemRV.tvTrending.setTextColor(Color.parseColor("#009688"))
                itemRV.tvTrending.text = "+${itemRV.tvTrending.text}"
                itemRV.imageTrending.setImageResource(R.drawable.ic_trending_up)
            }
            if (money.Diff.toDouble() < 0.0) {
                itemRV.tvTrending.setTextColor(Color.parseColor("#F44336"))
                itemRV.imageTrending.setImageResource(R.drawable.ic_trending_down)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMoneyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(listMoney[position])
    }

    override fun getItemCount(): Int = listMoney.size

}