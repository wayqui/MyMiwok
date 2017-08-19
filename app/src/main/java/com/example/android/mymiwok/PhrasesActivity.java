package com.example.android.mymiwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.mymiwok.adapters.WordAdapter;
import com.example.android.mymiwok.dataobject.Word;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private static final String TAG = "PhrasesActivity";

    private MediaPlayer mReproductor;

    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mListenerReproduccion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            limpiarRecursosAudio();
        }
    };

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.str_phr1), getString(R.string.str_phr1_miwok), -1, R.raw.phrase_where_are_you_going));
        words.add(new Word(getString(R.string.str_phr2), getString(R.string.str_phr2_miwok), -1, R.raw.phrase_what_is_your_name));
        words.add(new Word(getString(R.string.str_phr3), getString(R.string.str_phr3_miwok), -1, R.raw.phrase_my_name_is));
        words.add(new Word(getString(R.string.str_phr4), getString(R.string.str_phr4_miwok), -1, R.raw.phrase_how_are_you_feeling));
        words.add(new Word(getString(R.string.str_phr5), getString(R.string.str_phr5_miwok), -1, R.raw.phrase_im_feeling_good));
        words.add(new Word(getString(R.string.str_phr6), getString(R.string.str_phr6_miwok), -1, R.raw.phrase_are_you_coming));
        words.add(new Word(getString(R.string.str_phr7), getString(R.string.str_phr7_miwok), -1, R.raw.phrase_yes_im_coming));
        words.add(new Word(getString(R.string.str_phr8), getString(R.string.str_phr8_miwok), -1, R.raw.phrase_im_coming));
        words.add(new Word(getString(R.string.str_phr9), getString(R.string.str_phr9_miwok), -1, R.raw.phrase_lets_go));
        words.add(new Word(getString(R.string.str_phr10), getString(R.string.str_phr10_miwok), -1, R.raw.phrase_come_here));

        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView lista = (ListView) findViewById(R.id.list);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int result = audioManager.requestAudioFocus(audioManagerListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    limpiarRecursosAudio();
                    mReproductor = MediaPlayer.create(PhrasesActivity.this, words.get(position).getRelatedAudioId());
                    mReproductor.start();
                    mReproductor.setOnCompletionListener(mListenerReproduccion);
                }

            }
        });
    }

    private void limpiarRecursosAudio() {
        if (mReproductor != null) {
            mReproductor.release();
            mReproductor = null;
            audioManager.abandonAudioFocus(audioManagerListener);
        }
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "calling onStop() method");
        super.onStop();
        limpiarRecursosAudio();
    }
}
