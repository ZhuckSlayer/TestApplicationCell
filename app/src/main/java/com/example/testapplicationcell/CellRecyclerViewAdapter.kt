package com.example.testapplicationcell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplicationcell.databinding.CellBinding

class CellRecyclerViewAdapter :
    ListAdapter<Cell, CellRecyclerViewAdapter.CellInfoViewHolder>(CellUtilCallback()) {

    class CellInfoViewHolder(val binding: CellBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellInfoViewHolder {
        val binding = CellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CellInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CellInfoViewHolder, position: Int) {
        val binding=holder.binding
        val cell=getItem(position)
        binding.tilte1.text=cell.title
        binding.title2.text=cell.titleSecond

        when(cell.title){
            "Живая"->{
                binding.cvImg.setBackgroundResource(R.drawable.borned_bg)
            }
            "Мёртвая"->{
                binding.cvImg.setBackgroundResource(R.drawable.dead_bg)

            }
            "Жизнь"->{
                binding.cvImg.setBackgroundResource(R.drawable.live_bg)

            }
        }


        Glide.with(holder.itemView.context).load(cell.image).into(binding.cvImg)
    }
}