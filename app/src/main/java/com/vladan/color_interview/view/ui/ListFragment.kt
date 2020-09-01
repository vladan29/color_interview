package com.vladan.color_interview.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vladan.color_interview.R;
import com.vladan.color_interview.databinding.FragmentListIdsBinding;
import com.vladan.color_interview.model.ListIds;
import com.vladan.color_interview.view.ListIdsAdapter;
import com.vladan.color_interview.viewmodel.ListViewModel;

/**
 * Created by vladan on 8/29/2020
 */
public class ListFragment extends Fragment implements ListIdsAdapter.OnItemClickListener {
    private ListIdsAdapter mListIdsAdapter;
    private FragmentListIdsBinding mBinding;
    private ListViewModel mListViewModel;
    RecyclerView listIds;
    ViewModelProvider.Factory mFactory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ListViewModel(getActivity());
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListViewModel = new ViewModelProvider(this, mFactory).get(ListViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_ids, container, false);
        listIds = mBinding.recyclerListIds;
        return (View) mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListIdsAdapter = new ListIdsAdapter(mListViewModel, this);
        listIds.setAdapter(mListIdsAdapter);
        listIds.setLayoutManager(new LinearLayoutManager(getContext()));
        mListIdsAdapter.SetOnItemClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        observeViewModel(mListViewModel);
    }

    private void observeViewModel(ListViewModel listViewModel) {
        listViewModel.getListIdsLiveData().observe(getViewLifecycleOwner(), new Observer<ListIds>() {
            @Override
            public void onChanged(ListIds listIds) {
                mListIdsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position, String id) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).show(id);
        }
    }
}
