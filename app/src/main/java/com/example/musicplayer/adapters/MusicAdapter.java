package com.example.musicplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.models.Music;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<Music> musicList;
    private OnMusicListener mOnMusicListener;

    public MusicAdapter(List<Music> musicList, OnMusicListener onMusicListener){
        this.musicList = musicList;
        this.mOnMusicListener = onMusicListener;
    }

    @NonNull
    @Override
    public MusicAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_musics, parent, false);

        return new MusicViewHolder(view, mOnMusicListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.MusicViewHolder holder, int position){
        Music music = musicList.get(position);
        holder.name.setText(music.getName());
        holder.artist.setText(music.getArtist());
    }

    @Override
    public int getItemCount(){return musicList.size();}

    public class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView artist;
        private OnMusicListener onMusicListener;

        public MusicViewHolder (@NonNull View itemView, OnMusicListener onMusicListener){
            super(itemView);

            this.name = itemView.findViewById(R.id.vhMusicTextViewName);
            this.artist = itemView.findViewById(R.id.vhMusicTextViewArtist);
            this.onMusicListener = onMusicListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){ onMusicListener.onMusicClick(getAdapterPosition()); }
    }

    public interface OnMusicListener{
        void onMusicClick(int position);
    }
}
