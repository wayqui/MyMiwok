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
public class NumbersFragment extends Fragment {

    private static final String TAG = "NumbersActivity";

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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View numbersView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.str_one), getString(R.string.str_one_miwok), R.drawable.number_one, R.raw.number_one));
        words.add(new Word(getString(R.string.str_two), getString(R.string.str_two_miwok), R.drawable.number_two, R.raw.number_two));
        words.add(new Word(getString(R.string.str_three), getString(R.string.str_three_miwok), R.drawable.number_three, R.raw.number_three));
        words.add(new Word(getString(R.string.str_four), getString(R.string.str_four_miwok), R.drawable.number_four, R.raw.number_four));
        words.add(new Word(getString(R.string.str_five), getString(R.string.str_five_miwok), R.drawable.number_five, R.raw.number_five));
        words.add(new Word(getString(R.string.str_six), getString(R.string.str_six_miwok), R.drawable.number_six, R.raw.number_six));
        words.add(new Word(getString(R.string.str_seven), getString(R.string.str_seven_miwok), R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word(getString(R.string.str_eight), getString(R.string.str_eight_miwok), R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word(getString(R.string.str_nine), getString(R.string.str_nine_miwok), R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word(getString(R.string.str_ten), getString(R.string.str_ten_miwok), R.drawable.number_ten, R.raw.number_ten));

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        WordAdapter itemsAdapter =
                new WordAdapter(getActivity(), words, R.color.category_numbers);

        ListView listView = (ListView) numbersView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                limpiarRecursosAudio();
                mReproductor = MediaPlayer.create(getActivity(), words.get(position).getRelatedAudioId());
                mReproductor.start();
                mReproductor.setOnCompletionListener(mListenerReproduccion);
            }
        });

        return numbersView;
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
