package com.guiyujin.purenote_mvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.guiyujin.purenote_mvp.room.Note;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Note> allNotes = new ArrayList<>();

    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private onItemClickListener onItemClickListener;

    public void setOnItemLongClickListener(MyAdapter.onItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private onItemLongClickListener onItemLongClickListener;

    public void setAllNotes(List<Note> allNotes) {
        this.allNotes = allNotes;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.note_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        Note note = allNotes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_time.setText(note.getTime());

        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onClick(holder.itemView, position);
                }
            });
        }
        if (onItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemLongClickListener.onLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return allNotes.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title, tv_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.rv_title);
            tv_time = itemView.findViewById(R.id.rv_time);
        }
    }

    public interface onItemClickListener{
        void onClick(View v, int position);
    }

    public interface onItemLongClickListener{
        void onLongClick(View v, int position);
    }
}
