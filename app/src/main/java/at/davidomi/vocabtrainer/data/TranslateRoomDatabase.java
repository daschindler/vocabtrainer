package at.davidomi.vocabtrainer.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import at.davidomi.vocabtrainer.Converters;
import at.davidomi.vocabtrainer.dao.DictDao;
import at.davidomi.vocabtrainer.dao.LanguageDao;
import at.davidomi.vocabtrainer.dao.WordDao;
import at.davidomi.vocabtrainer.entity.Dict;
import at.davidomi.vocabtrainer.entity.Languages;
import at.davidomi.vocabtrainer.entity.Word;

@Database(entities = {Dict.class, Word.class, Languages.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class TranslateRoomDatabase extends RoomDatabase {

    public abstract DictDao dictDao();
    public abstract WordDao wordDao();
    public abstract LanguageDao languageDao();

    private static volatile TranslateRoomDatabase INSTANCE;

    static TranslateRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TranslateRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TranslateRoomDatabase.class, "translate_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
