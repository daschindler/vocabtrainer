package at.davidomi.vocabtrainer.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import at.davidomi.vocabtrainer.entity.Word;


@Dao
public interface WordDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table where input=:wordToTranslate")
    LiveData<Word> getWord(String wordToTranslate);

    @Query("Select * from word_table where dict_type=:dictType")
    LiveData<List<Word>> getAllWords(String dictType);
}
