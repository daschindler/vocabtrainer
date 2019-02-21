package at.davidomi.vocabtrainer.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity (tableName = "language_table")
public class Languages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "languages_id")
    private int id;

    @ColumnInfo(name = "directions")
    private List<String> directions;

    @ColumnInfo(name = "supported_language_codes")
    private List<String> supportedLanguageCodes;

    @ColumnInfo (name = "supported_languages")
    private List<String> supportedLanguages;

    public Languages() {
    }

    public Languages(List<String> directions) {
        this.directions = directions;
        this.supportedLanguageCodes = new ArrayList<>();
        this.supportedLanguages = new ArrayList<>();
    }

    public List<String> getDirections() {
        return directions;
    }

    public List<String> getSupportedLanguageCodes() {
        return supportedLanguageCodes;
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

    public void setSupportedLanguageCodes(List<String> supportedLanguageCodes) {
        this.supportedLanguageCodes = supportedLanguageCodes;
    }

    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(List<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }
}
