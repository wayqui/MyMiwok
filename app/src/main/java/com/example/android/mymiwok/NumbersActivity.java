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

public class NumbersActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ArrayList<Word> words = new ArrayList<Word>();
        setContentView(R.layout.word_list);

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

        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                limpiarRecursosAudio();
                mReproductor = MediaPlayer.create(NumbersActivity.this, words.get(position).getRelatedAudioId());
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
