package at.davidomi.vocabtrainer.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WordResponse {
    @SerializedName("code")
    public Integer code;
    @SerializedName("lang")
    public String lang;
    @SerializedName("text")
    public List<String> text = new ArrayList<>();
}
