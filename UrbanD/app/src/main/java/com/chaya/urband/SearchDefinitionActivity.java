package com.chaya.urband;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import com.chaya.urband.databinding.ActivityMainBinding;
import com.chaya.urband.model.Definition;
import com.chaya.urband.viewmodel.SearchResultsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class SearchDefinitionActivity extends AppCompatActivity {
    private SearchResultsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        setupBindings(savedInstanceState);
        setupSearchView();
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupListUpdate("Whats up");

    }

    private void setupListUpdate(String searchTerm) {
        viewModel.loading.set(View.VISIBLE);
        viewModel.fetchList(searchTerm);
        viewModel.getDefinitionsResult().observe(this, new Observer<List<Definition>>() {
            @Override
            public void onChanged(List<Definition> DefinitionList) {
                viewModel.loading.set(View.GONE);
                if (DefinitionList.size() == 0) {
                    viewModel.showEmpty.set(View.VISIBLE);
                } else {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setDefinitionsInAdapter(DefinitionList);
                }
            }
        });
    }

    public void setupSearchView() {
        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setupListUpdate(query);
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//
//        final MenuItem searchActionItem = menu.findItem(R.id.action_search);
//
//        SearchManager searchManager = (SearchManager) SearchDefinitionActivity.this.getSystemService(Context.SEARCH_SERVICE);
//
//        SearchView searchView = (SearchView) searchActionItem.getActionView();
//        final SearchView finalSearchView = searchView;
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
