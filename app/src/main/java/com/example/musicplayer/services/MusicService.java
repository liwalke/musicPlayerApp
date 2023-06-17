package com.example.musicplayer.services;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.musicplayer.models.Music;
import com.example.musicplayer.repositories.MusicRepository;
import com.example.musicplayer.repositories.PlaylistRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MusicService {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static final String TAG = "Read Data Activity";

    public static void getAllMusics(Context context, String playlistId, ServiceDone cb){

        List<String> musicIdList = PlaylistRepository.getInstance().getPlaylistById(playlistId).getIdMusicList();

        MusicRepository.getInstance().clearList();

        for (String musicId : musicIdList){
            db.collection("Music").document((String) musicId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();

                                Music newMusic = new Music(document);

                                MusicRepository.getInstance().addMusic(MusicRepository.getInstance().getSize(), newMusic);

                                if(MusicRepository.getInstance().getSize() == musicIdList.size()){
                                    if(cb != null){
                                        cb.onServiceDone();
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
