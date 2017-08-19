package com.example.android.mymiwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.mymiwok.adapters.WordAdapter;
import com.example.android.mymiwok.dataobject.Word;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private static final String TAG = "ColorsActivity";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.str_red), getString(R.string.str_red_miwok), R.drawable.color_red, R.raw.color_red));
        words.add(new Word(getString(R.string.str_green), getString(R.string.str_green_miwok), R.drawable.color_green, R.raw.color_green));
        words.add(new Word(getString(R.string.str_brown), getString(R.string.str_brown_miwok), R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word(getString(R.string.str_gray), getString(R.string.str_gray_miwok), R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word(getString(R.string.str_black), getString(R.string.str_black_miwok), R.drawable.color_black, R.raw.color_black));
        words.add(new Word(getString(R.string.str_white), getString(R.string.str_white_miwok), R.drawable.color_white, R.raw.color_white));
        words.add(new Word(getString(R.string.str_dusty_yellow), getString(R.string.str_dusty_yellow_miwok), R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word(getString(R.string.str_mustard_yellow), getString(R.string.str_mustard_yellow_miwok), R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        ListView lista = (ListView)findViewById(R.id.list);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mReproductor = MediaPlayer.create(ColorsActivity.this, words.get(position).getRelatedAudioId());
                mReproductor.start();
                mReproductor.setOnCompletionListener(mListenerReproduccion);
            }
        });
    }

    private void limpiarRecursosAudio() {
        if (mReproductor != null) {
            mReproductor.release();
            mReproductor = null;
        }
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "calling onStop() method");
        super.onStop();
        limpiarRecursosAudio();
    }
}
