package com.example.musicplayer.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.musicplayer.models.Playlist;
import com.example.musicplayer.repositories.PlaylistRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PlaylistService {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static final String TAG = "Read Data Activity";

    public static void getAllPlaylists(Context context, ServiceDone cb){
        db.collection("Playlist")
                .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int i = 0;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Playlist newPlaylist = new Playlist(document);
                                        PlaylistRepository.getInstance().addPlaylist(i, newPlaylist);
                                        i++;
                                    }
                                    if(cb != null){
                                        cb.onServiceDone();
                                    }
                                } else {
                                    Toast.makeText(context, "Something went wrong.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }
}
