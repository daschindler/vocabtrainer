package at.davidomi.vocabtrainer.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import at.davidomi.vocabtrainer.entity.Languages;

@Dao
public interface LanguageDao {

    @Insert
    void insert(Languages languages);

    @Query("Select * from language_table where languages_id=1")
    LiveData<Languages> getLanguages();

    @Query("Select count(*) from language_table")
    LiveData<Integer> getRowCount();

    @Query("delete from language_table")
    void deleteAll();
}
