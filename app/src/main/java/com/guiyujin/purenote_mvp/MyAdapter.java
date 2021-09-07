package com.guiyujin.purenote_mvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.guiyujin.purenote_mvp.room.Note;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Note> allNotes = new ArrayList<>();

    public MyAdapter.onItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private onItemClickListener onItemClickListener;

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
        holder.tv_content.setText(note.getContent());
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
    }

    @Override
    public int getItemCount() {

        return allNotes.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_content, tv_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.rv_content);
            tv_time = itemView.findViewById(R.id.rv_time);
        }
    }

    public interface onItemClickListener{
        void onClick(View v, int position);
    }


}
