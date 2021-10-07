package com.guiyujin.purenote_mvp.activity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.guiyujin.purenote_mvp.MyAdapter;
import com.guiyujin.purenote_mvp.OperationDialog;
import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.Util;
import com.guiyujin.purenote_mvp.base.mvp.BaseMVPActivity;
import com.guiyujin.purenote_mvp.model.main.MainModelConstract;
import com.guiyujin.purenote_mvp.model.main.MainModelImpl;
import com.guiyujin.purenote_mvp.model.main.MainPresenter;
import com.guiyujin.purenote_mvp.room.DBengine;
import com.guiyujin.purenote_mvp.room.Note;

import java.util.List;

public class MainActivity extends BaseMVPActivity<MainPresenter, MainModelImpl> implements MainModelConstract.View {
    private MyAdapter myAdapter;
    private DBengine dBengine;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SearchView mSearchView;
    private boolean isSearch = false;
    private List<Note> note;
    private boolean isMenuOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        note = dBengine.getAllNotes();
        presenter.attach(this,model);
        Log.i("ONRESULTSSSS", "ONCREATE " + myAdapter.getItemCount());
    }

    @Override
    protected void initParams(Bundle params) {

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
            edit(position);
        });

        myAdapter.setOnItemLongClickListener((v, position) -> {
            OperationDialog operationDialog = new OperationDialog(this);
            operationDialog.showDialog(R.layout.operation_dialog_center, Gravity.CENTER);
            operationDialog.setOperationDialogListener(new OperationDialog.OperationDialogListener() {
                @Override
                public void edit() {
                    MainActivity.this.edit(position);
                }

                @Override
                public void delete() {
                    Note note = new Note(dBengine.getAllNotes().get(position).getTitle(),
                            dBengine.getAllNotes().get(position).getContent(), Util.getTime());
                    note.setId(dBengine.getAllNotes().get(position).getId());
                    presenter.delete(note,MainActivity.this);
                }
            });
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                isSearch = true;
                presenter.search(dBengine.getAllNotes(), newText,MainActivity.this);
                return false;
            }
        });

//        mSearchView.setOnCloseListener( () -> {
//            isSearch = false;
//            return false;
//        });
    }

    @Override
    protected void initData() {

    }

    private void edit(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("title", dBengine.getAllNotes().get(position).getTitle());
        bundle.putString("content", dBengine.getAllNotes().get(position).getContent());
        bundle.putInt("_id", dBengine.getAllNotes().get(position).getId());
        startActivity(DetailActivity.class, bundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isSearch){
            myAdapter.setAllNotes(dBengine.getAllNotes());
            recyclerView.setAdapter(myAdapter);
        }else {
            myAdapter.setAllNotes(note);
            recyclerView.setAdapter(myAdapter);
        }

    }


    @Override
    public void widgetClick(int id) {
        switch (id){
            case R.id.add:
//                changeStatus();
//                rotateFloatingButton();
                startActivity(AddContentActivity.class);
                break;
            case R.id.action_settings:
                startActivity(SettingsActivity.class);
                break;
            default:
        }
    }

    private void rotateFloatingButton(){
        ObjectAnimator animator = isMenuOpen ? ObjectAnimator.ofFloat(fab
                , "rotation", 45F, 0f) : ObjectAnimator.ofFloat(fab, "rotation", 0f, 45f);
        animator.setDuration(150);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    private void changeStatus(){
        if(isMenuOpen){
            isMenuOpen = false;
        }else{
            isMenuOpen = true;
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onSearchSuccess(List<Note> note) {
        this.note = note;
        myAdapter.setAllNotes(note);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onDelete() {
        myAdapter.setAllNotes(dBengine.getAllNotes());
        recyclerView.setAdapter(myAdapter);
    }


    @Override
    public void onFailed() {
    }

}