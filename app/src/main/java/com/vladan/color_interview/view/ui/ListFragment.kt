package com.vladan.color_interview.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vladan.color_interview.R
import com.vladan.color_interview.databinding.FragmentListIdsBinding
import com.vladan.color_interview.view.ListIdsAdapter
import com.vladan.color_interview.viewmodel.ListViewModel

/**
 * Created by vladan on 8/29/2020
 */
class ListFragment : Fragment(), ListIdsAdapter.OnItemClickListener {
    private var mListIdsAdapter: ListIdsAdapter? = null
    private lateinit var listIdsBinding: FragmentListIdsBinding
    private var mListViewModel: ListViewModel? = null
    private var listIds: RecyclerView? = null
    private var mFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListViewModel(activity) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mListViewModel = ViewModelProvider(this, mFactory).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        listIdsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list_ids, container, false)
        listIds = listIdsBinding.recyclerListIds
        return listIdsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mListIdsAdapter = ListIdsAdapter(mListViewModel!!, this)
        listIds!!.adapter = mListIdsAdapter
        listIds!!.layoutManager = LinearLayoutManager(context)
        mListIdsAdapter!!.SetOnItemClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel(mListViewModel)
    }

    private fun observeViewModel(listViewModel: ListViewModel?) {
        listViewModel!!.listIdsLiveData.observe(
            viewLifecycleOwner, { mListIdsAdapter!!.notifyDataSetChanged() })
    }

    override fun onItemClick(view: View?, position: Int, id: String?) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            (activity as MainActivity?)!!.show(id)
        }
    }
}