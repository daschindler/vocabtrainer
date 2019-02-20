package at.davidomi.vocabtrainer.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import at.davidomi.vocabtrainer.entity.Dict;

@Dao
public interface DictDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Dict dict);

    @Query("Select * from dict_table")
    LiveData<List<Dict>> getAllDicts();

    @Query("Select * from dict_table where type=:type")
    Dict getDict(String type);

    @Query("delete from dict_table")
    void deleteAll();

}
