package at.davidomi.vocabtrainer.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "word_table", foreignKeys = @ForeignKey(entity = Dict.class,
        parentColumns = "type",
        childColumns = "dict_type",
        onDelete = CASCADE))
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "input")
    private String mInput;

    @ColumnInfo (name = "output")
    private List<String> mOutput;

    @ColumnInfo(name = "dict_type")
    private String mDictyType;

    public Word() {
    }

    public Word(@NonNull String input, @NonNull String dictType) {
        this.mInput = input;
        this.mOutput = new ArrayList<>();
        this.mDictyType = dictType;

    }

    @NonNull
    public String getInput() {
        return this.mInput;
    }

    public List<String> getOutput() {
        return this.mOutput;
    }

    public String getDictyType() {
        return this.mDictyType;
    }

    public void setInput(@NonNull String mInput) {
        this.mInput = mInput;
    }

    public void setOutput(List<String> mOutput) {
        this.mOutput = mOutput;
    }

    public void setDictyType(String mDictyType) {
        this.mDictyType = mDictyType;
    }
}
