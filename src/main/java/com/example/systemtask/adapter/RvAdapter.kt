package com.example.systemtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.systemtask.R
import com.example.systemtask.repo.pojo.Data
import com.example.systemtask.utils.gone
import com.example.systemtask.utils.invisble
import com.example.systemtask.utils.visible
import kotlinx.android.synthetic.main.rv_item_layout.view.*

//using Primary constructor
class RvAdapter(var list: ArrayList<Data>, var from:String, private val itemClickListener: (Data) -> Unit) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, factsAdapter: RvAdapter, function: (Data) -> Unit) = with(itemView) {
            tvSub_title.text = resources.getString(data.title)
            tvSub_text.text = resources.getString(data.desc)
            tvCount.text = data.count.toString()

            if (data.type=="2"){
                tvTypeOne.visible()
                tvTypeTwo.visible()
            }else{
                tvTypeTwo.invisble()
            }

            if (data.count == 0) {
                llAdd.visible()
                llCount.gone()
            } else {
                llAdd.gone()
                llCount.visible()
            }

            llAdd.setOnClickListener {
                data.count = 1
                function(data)
                factsAdapter.notifyItemChanged(adapterPosition)
            }

            ivAdd.setOnClickListener {
                if (data.count != 20) {
                    data.count = data.count + 1
                    function(data)
                    factsAdapter.notifyItemChanged(adapterPosition)
                }
            }

            ivMinus.setOnClickListener {
                data.count = data.count - 1
                function(data)
                factsAdapter.notifyItemChanged(adapterPosition)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.rv_item_layout, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return if(from.isEmpty())
            list.size
        else
            2
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], this) {
            itemClickListener(list[position])
        }
    }

    fun showMore(){
        from = ""
        notifyDataSetChanged()
    }
}

