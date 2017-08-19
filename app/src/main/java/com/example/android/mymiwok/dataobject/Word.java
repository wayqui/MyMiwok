package com.example.android.mymiwok.dataobject;

/**
 * Created by joselobm on 14/08/17.
 */

public class Word {
    private String defaultTraslation;
    private String miwokTraslation;
    private int relatedImageId;
    private int relatedAudioId;

    private static int NO_IMAGE_PROVIDED = -1;
    private static int NO_AUDIO_PROVIDED = -1;

    public Word(String defaultTraslation, String miwokTraslation, int relatedImage, int relatedAudio) {
        this.defaultTraslation = defaultTraslation;
        this.miwokTraslation = miwokTraslation;
        this.relatedImageId = relatedImage;
        this.relatedAudioId = relatedAudio;
    }

    public Word(String defaultTraslation, String miwokTraslation) {
        this.defaultTraslation = defaultTraslation;
        this.miwokTraslation = miwokTraslation;
        this.relatedImageId = NO_IMAGE_PROVIDED;
        this.relatedAudioId = NO_AUDIO_PROVIDED;
    }

    public void setRelatedImageId(int relatedImageId) {
        this.relatedImageId = relatedImageId;
    }

    public void setRelatedAudioId(int relatedAudioId) {
        this.relatedAudioId = relatedAudioId;
    }

    public void setDefaultTraslation(String defaultTraslation) {
        this.defaultTraslation = defaultTraslation;
    }

    public void setMiwokTraslation(String miwokTraslation) {
        this.miwokTraslation = miwokTraslation;
    }

    public String getDefaultTraslation() {
        return defaultTraslation;
    }

    public String getMiwokTraslation() {
        return miwokTraslation;
    }

    public int getRelatedImageId() {
        return relatedImageId;
    }

    public int getRelatedAudioId() {
        return relatedAudioId;
    }

    public boolean hasImage() {
        return relatedImageId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "defaultTraslation='" + defaultTraslation + '\'' +
                ", miwokTraslation='" + miwokTraslation + '\'' +
                ", relatedImageId=" + relatedImageId +
                ", relatedAudioId=" + relatedAudioId +
                '}';
    }
}
