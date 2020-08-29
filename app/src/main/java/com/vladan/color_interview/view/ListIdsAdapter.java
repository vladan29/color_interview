package com.vladan.color_interview.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.vladan.color_interview.R;
import com.vladan.color_interview.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladan on 8/29/2020
 */
public class ListIdsAdapter extends RecyclerView.Adapter<ListIdsAdapter.ListIdsViewHolder> {


    private List<String> mIdsList = new ArrayList<>();
    private OnItemClickListener itemClickListener;
    TextView personId;


    public ListIdsAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner) {

        viewModel.getListIdsLiveData().observe(lifecycleOwner, ListIds -> {
            mIdsList.clear();
            if (ListIds.data != null) {
                mIdsList.addAll(ListIds.data);
            }
        });
    }

    @NonNull
    @Override
    public ListIdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ids_item, parent, false);
        return new ListIdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListIdsViewHolder holder, int position) {
        personId.setText(mIdsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mIdsList.size();
    }

    public class ListIdsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ListIdsViewHolder(@NonNull View itemView) {
            super(itemView);
            personId=itemView.findViewById(R.id.personId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
