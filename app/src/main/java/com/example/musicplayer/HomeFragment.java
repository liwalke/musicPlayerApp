package com.example.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.musicplayer.adapters.PlaylistAdapter;
import com.example.musicplayer.models.Playlist;
import com.example.musicplayer.presenters.HomePresenter;
import com.example.musicplayer.presenters.HomePresenterContract;
import com.example.musicplayer.repositories.PlaylistRepository;

import java.util.List;

public class HomeFragment extends Fragment implements HomePresenterContract.View, PlaylistAdapter.OnPlaylistListener{
    private View view;
    private Context mContext;
    private HomePresenterContract.Presenter presenter;
    private List<Playlist> playlistList;
    private RecyclerView rvFeatured, rvRecentlyPlayed, rvDiscover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = view.getContext();
        presenter = new HomePresenter(this, mContext);
        rvFeatured = (RecyclerView) view.findViewById(R.id.recyclerViewFeatured);
        rvRecentlyPlayed = (RecyclerView) view.findViewById(R.id.recyclerViewRecentlyPlayed);
        rvDiscover = (RecyclerView) view.findViewById(R.id.recyclerViewDiscover);

        if (PlaylistRepository.getInstance().getSize() == 0) {
            presenter.getAllPlaylists();
        } else {
            inflateRecyclerViews();
        }

        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void inflateRecyclerViews() {
        playlistList = PlaylistRepository.getInstance().getPlaylists();

        PlaylistAdapter featuredAdapter = new PlaylistAdapter(playlistList, mContext, this);
        PlaylistAdapter recentlyPlayedAdapter = new PlaylistAdapter(playlistList, mContext, this);
        PlaylistAdapter discoverAdapter = new PlaylistAdapter(playlistList, mContext, this);

        LinearLayoutManager llm1 = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager llm2 = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager llm3 = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);

        rvFeatured.setLayoutManager(llm1);
        rvFeatured.setAdapter(featuredAdapter);

        rvRecentlyPlayed.setLayoutManager(llm2);
        rvRecentlyPlayed.setAdapter(recentlyPlayedAdapter);

        rvDiscover.setLayoutManager(llm3);
        rvDiscover.setAdapter(discoverAdapter);
    }

    @Override
    public void onPlaylistClick(int position) {
        Playlist playlist = playlistList.get(position);
        Intent intent = new Intent(mContext, PlaylistActivity.class);
        intent.putExtra("playlistId", playlist.getId());
        startActivity(intent);
    }
}