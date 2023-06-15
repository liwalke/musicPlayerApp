package com.example.musicplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer.R;
import com.example.musicplayer.models.Playlist;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistsViewHolder> {
    private List<Playlist> playlistList;
    private Context context;
    private OnPlaylistListener mOnPlaylistListener;

    public PlaylistAdapter(List<Playlist> playlistList, Context context, OnPlaylistListener onPlaylistListener){
        this.playlistList = playlistList;
        this.context = context;
        this.mOnPlaylistListener = onPlaylistListener;
    }

    @NonNull
    @Override
    public PlaylistAdapter.PlaylistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_playlists, parent, false);

        return new PlaylistsViewHolder(view, mOnPlaylistListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.PlaylistsViewHolder holder, int position){
        Playlist playlist = playlistList.get(position);
        holder.title.setText(playlist.getName());
        Glide.with(context).load(playlist.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount(){return playlistList.size();}

    public class PlaylistsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private ImageView image;
        private OnPlaylistListener onPlaylistListener;

        public PlaylistsViewHolder (@NonNull View itemView, OnPlaylistListener onPlaylistListener){
            super(itemView);

            this.title = itemView.findViewById(R.id.vhPlaylistTextViewTittle);
            this.image = itemView.findViewById(R.id.vhPlaylistImageViewCoverImage);
            this.onPlaylistListener = onPlaylistListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){ onPlaylistListener.onPlaylistClick(getAdapterPosition()); }
    }

    public interface OnPlaylistListener{
        void onPlaylistClick(int position);
    }
}
