package com.chaya.urband.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chaya.urband.model.Definition;
import com.chaya.urband.viewmodel.SearchResultsViewModel;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.chaya.urband.BR;

import java.util.List;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsAdapter.DefinitionViewHolder> {

    private int layoutId;
    private List<Definition> definitionList;
    private SearchResultsViewModel viewModel;

    public DefinitionsAdapter(@LayoutRes int layoutId, SearchResultsViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new DefinitionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemCount() {
        return definitionList == null ? 0 : definitionList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setDefinitionList(List<Definition> definitionList) {
        this.definitionList = definitionList;
    }

    class DefinitionViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        DefinitionViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SearchResultsViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}
