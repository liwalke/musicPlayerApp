package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.adapters.PlaylistAdapter;
import com.example.musicplayer.models.Playlist;
import com.example.musicplayer.presenters.ExplorePresenter;
import com.example.musicplayer.presenters.ExplorePresenterContract;
import com.example.musicplayer.repositories.PlaylistRepository;

import java.util.List;

public class ExploreFragment extends Fragment implements ExplorePresenterContract.View, PlaylistAdapter.OnPlaylistListener {
    private View view;
    private Context mContext;
    private ExplorePresenterContract.Presenter presenter;
    private List<Playlist> playlistList;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);
        mContext = view.getContext();
        presenter = new ExplorePresenter(this, mContext);
        rv = (RecyclerView) view.findViewById(R.id.recyclerViewDoJucafy);

        if (PlaylistRepository.getInstance().getSize() == 0) {
            presenter.getAllPlaylists();
        } else {
            inflateRecyclerView();
        }

        return view;
    }
    @Override
    public void inflateRecyclerView(){
        playlistList = PlaylistRepository.getInstance().getPlaylists();

        PlaylistAdapter exploreAdapter = new PlaylistAdapter(playlistList, mContext, this);
        RecyclerView.LayoutManager lm = new GridLayoutManager(mContext, 2);
        rv.setLayoutManager(lm);
        rv.setAdapter(exploreAdapter);
    }

    @Override
    public void onPlaylistClick(int position) {
        Playlist playlist = playlistList.get(position);

        SharedPreferences prefs = getActivity().getSharedPreferences("generalKey-Xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("playlistId", playlist.getId());
        editor.commit();

        Intent intent = new Intent(mContext, PlaylistActivity.class);
        startActivity(intent);
    }
}