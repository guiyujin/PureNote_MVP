package com.guiyujin.purenote_mvp.activity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.guiyujin.purenote_mvp.MyAdapter;
import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.Util;
import com.guiyujin.purenote_mvp.base.BaseActivity;
import com.guiyujin.purenote_mvp.base.BaseModel;
import com.guiyujin.purenote_mvp.base.BasePresenter;
import com.guiyujin.purenote_mvp.base.BaseView;
import com.guiyujin.purenote_mvp.model.main.MainModelConstract;
import com.guiyujin.purenote_mvp.model.main.MainModelImpl;
import com.guiyujin.purenote_mvp.model.main.MainPresenter;
import com.guiyujin.purenote_mvp.room.DBengine;
import com.guiyujin.purenote_mvp.room.Note;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter, MainModelImpl>implements MainModelConstract.View {
    private MyAdapter myAdapter;
    private DBengine dBengine;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attach(this,model);
        Log.i("ONRESULTSSSS", "ONCREATE " + myAdapter.getItemCount());
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected MainModelImpl initModel() {
        return new MainModelImpl();
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {

        mSearchView = find(R.id.search_view);

        dBengine = new DBengine(this);
        myAdapter = new MyAdapter();

        recyclerView = find(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = find(R.id.add);
        initToolBar(R.id.toolbar_main, R.menu.menu_main);
    }

    @Override
    public void initListener() {
        fab.setOnClickListener(this);
        myAdapter.setOnItemClickListener((v, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("content", dBengine.getAllNotes().get(position).getContent());
            bundle.putInt("_id", dBengine.getAllNotes().get(position).getId());
            startActivity(DetailActivity.class, bundle);
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.search(dBengine.getAllNotes(), newText,MainActivity.this);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.setAllNotes(dBengine.getAllNotes());
        recyclerView.setAdapter(myAdapter);
    }


    @Override
    public void widgetClick(int id) {
        switch (id){
            case R.id.add:
                startActivity(AddContentActivity.class);
                break;
            case R.id.action_settings:
                startActivity(SettingsActivity.class);
                break;
            default:
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void checkPermission() {

    }

    @Override
    public void onSearchSuccess(List<Note> note) {
        myAdapter.setAllNotes(note);
        recyclerView.setAdapter(myAdapter);
    }


    @Override
    public void onFailed() {

    }

}