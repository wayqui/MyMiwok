package com.example.android.mymiwok.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.mymiwok.R;
import com.example.android.mymiwok.adapters.WordAdapter;
import com.example.android.mymiwok.dataobject.Word;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private static final String TAG = "FamilyFragment";

    private MediaPlayer mReproductor;

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener audioManagerListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mReproductor.pause();
                mReproductor.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                limpiarRecursosAudio();
                audioManager.abandonAudioFocus(audioManagerListener);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mReproductor.start();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mListenerReproduccion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            limpiarRecursosAudio();
        }
    };
    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View familyView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.str_father), getString(R.string.str_father_miwok), R.drawable.family_father, R.raw.family_father));
        words.add(new Word(getString(R.string.str_mother), getString(R.string.str_mother_miwok), R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(getString(R.string.str_son), getString(R.string.str_son_miwok), R.drawable.family_son, R.raw.family_son));
        words.add(new Word(getString(R.string.str_daughter), getString(R.string.str_daughter_miwok), R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(getString(R.string.str_oldbro), getString(R.string.str_oldbro_miwok), R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(getString(R.string.str_younbro), getString(R.string.str_younbro_miwok), R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(getString(R.string.str_oldsis), getString(R.string.str_oldsis_miwok), R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(getString(R.string.str_younsis), getString(R.string.str_younsis_miwok), R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(getString(R.string.str_grandmother), getString(R.string.str_grandmother_miwok), R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(getString(R.string.str_grandfather), getString(R.string.str_grandfather_miwok), R.drawable.family_grandfather, R.raw.family_grandfather));

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_family);
        ListView lista = (ListView)familyView.findViewById(R.id.list);
        lista.setAdapter(wordAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mReproductor = MediaPlayer.create(getActivity(), words.get(position).getRelatedAudioId());
                mReproductor.start();
                mReproductor.setOnCompletionListener(mListenerReproduccion);
            }
        });

        return familyView;
    }

    private void limpiarRecursosAudio() {
        if (mReproductor != null) {
            mReproductor.release();
            mReproductor = null;
        }
    }

    @Override
    public void onStop() {
        Log.v(TAG, "calling onStop() method");
        super.onStop();
        limpiarRecursosAudio();
    }

}
