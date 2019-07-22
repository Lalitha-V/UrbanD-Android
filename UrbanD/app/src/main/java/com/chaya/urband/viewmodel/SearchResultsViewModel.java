package com.chaya.urband.viewmodel;

import android.view.View;

import com.chaya.urband.R;
import com.chaya.urband.adapter.DefinitionsAdapter;
import com.chaya.urband.model.Definition;
import com.chaya.urband.model.DefinitionsResult;

import java.util.List;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchResultsViewModel extends ViewModel {

    private DefinitionsResult definitionsResult;
    private DefinitionsAdapter adapter;
    public MutableLiveData<Definition> selected;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init() {
        definitionsResult = new DefinitionsResult();
        selected = new MutableLiveData<>();
        adapter = new DefinitionsAdapter(R.layout.view_definition, this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public void fetchList(String term) {
        definitionsResult.fetchList(term);
    }

    public MutableLiveData<List<Definition>> getDefinitionsResult() {
        return definitionsResult.getDefinitions();
    }

    public DefinitionsAdapter getAdapter() {
        return adapter;
    }

    public void setDefinitionsInAdapter(List<Definition> definitionList) {
        this.adapter.setDefinitionList(definitionList);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<Definition> getSelected() {
        return selected;
    }

    public void onItemClick(Integer index) {
        Definition db = getDefinitionAt(index);
        selected.setValue(db);
    }

    public Definition getDefinitionAt(Integer index) {
        if (definitionsResult.getDefinitions().getValue() != null &&
                index != null &&
                definitionsResult.getDefinitions().getValue().size() > index) {
            return definitionsResult.getDefinitions().getValue().get(index);
        }
        return null;
    }




}
