package at.davidomi.vocabtrainer.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LanguagesResponse {
    @SerializedName("dirs")
    public List<String> dirs;
}
