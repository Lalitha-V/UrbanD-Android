package com.chaya.urband.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Definition {
    @SerializedName("word")
    @Expose
    String word;
    @SerializedName("definition")
    @Expose
    String definition;
    @SerializedName("thumbs_up")
    @Expose
    Long thumbs_up;
    @SerializedName("thumbs_down")
    @Expose
    Long thumbs_down;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getThumbs_up() {
        return thumbs_up.toString();
    }

    public void setThumbs_up(Long thumbs_up) {
        this.thumbs_up = thumbs_up;
    }

    public String getThumbs_down() {
        return thumbs_down.toString();
    }

    public void setThumbs_down(Long thumbs_down) {
        this.thumbs_down = thumbs_down;
    }
}
