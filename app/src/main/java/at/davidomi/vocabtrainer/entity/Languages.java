package at.davidomi.vocabtrainer.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity (tableName = "language_table")
public class Languages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "languages_id")
    private int id;

    @ColumnInfo(name = "directions")
    private List<String> directions;

    @ColumnInfo(name = "supported_languages")
    private List<String> supportedLanguages;

    public Languages() {
    }

    public Languages(List<String> directions) {
        this.directions = directions;
        this.supportedLanguages = new ArrayList<>();
    }

    public List<String> getDirections() {
        return directions;
    }

    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public void setSupportedLanguages(List<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }
}
