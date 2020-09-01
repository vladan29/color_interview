package com.vladan.color_interview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.vladan.color_interview.R
import com.vladan.color_interview.model.ListIds
import com.vladan.color_interview.view.ListIdsAdapter.ListIdsViewHolder
import com.vladan.color_interview.viewmodel.ListViewModel
import java.util.*

/**
 * Created by vladan on 8/29/2020
 */
class ListIdsAdapter(viewModel: ListViewModel, lifecycleOwner: LifecycleOwner?) :
    RecyclerView.Adapter<ListIdsViewHolder>() {
    private val mIdsList: MutableList<String> = ArrayList()
    private var itemClickListener: OnItemClickListener? = null
    var personId: TextView? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListIdsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_ids_item, parent, false)
        return ListIdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListIdsViewHolder, position: Int) {
        personId!!.text = mIdsList[position]
    }

    override fun getItemCount(): Int {
        return mIdsList.size
    }

    inner class ListIdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        override fun onClick(view: View) {
            if (itemClickListener != null) {
                itemClickListener!!.onItemClick(view, adapterPosition, mIdsList[adapterPosition])
            }
        }

        init {
            personId = itemView.findViewById(R.id.personId)
            itemView.setOnClickListener(this)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, id: String?)
    }

    fun SetOnItemClickListener(itemClickListener: OnItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    init {
        viewModel.listIdsLiveData.observe(lifecycleOwner!!, Observer { ListIds: ListIds? ->
            mIdsList.clear()
            if (ListIds?.data != null) {
                mIdsList.addAll(ListIds.data!!)
            }
        })
    }
}