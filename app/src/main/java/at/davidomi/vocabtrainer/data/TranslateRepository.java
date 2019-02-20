package at.davidomi.vocabtrainer.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import at.davidomi.vocabtrainer.api.APIClient;
import at.davidomi.vocabtrainer.api.APIService;
import at.davidomi.vocabtrainer.dao.DictDao;
import at.davidomi.vocabtrainer.dao.LanguageDao;
import at.davidomi.vocabtrainer.dao.WordDao;
import at.davidomi.vocabtrainer.entity.Dict;
import at.davidomi.vocabtrainer.entity.Languages;
import at.davidomi.vocabtrainer.entity.Word;
import at.davidomi.vocabtrainer.pojo.LanguagesResponse;
import at.davidomi.vocabtrainer.pojo.WordResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateRepository {

    private static String APIKEY = "trnsl.1.1.20190216T152301Z.1e3d45821dad6c4a.6b53b780b4b035542b09c5a69ab167321b574fa9";

    private DictDao mDictDao;
    private WordDao mWordDao;
    private LanguageDao mLanguageDao;
    private APIService apiService;
    private LiveData<List<Dict>> mAllDicts;

    public TranslateRepository(Application application) {
        TranslateRoomDatabase db = TranslateRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mDictDao = db.dictDao();
        mLanguageDao = db.languageDao();
        mAllDicts = mDictDao.getAllDicts();
        apiService = APIClient.getClient().create(APIService.class);
    }

    public void InsertLanguagesFromAPI() {
        Call<LanguagesResponse> call = apiService.doGetLanguages();
        call.enqueue(new Callback<LanguagesResponse>() {
            @Override
            public void onResponse(Call<LanguagesResponse> call, Response<LanguagesResponse> response) {
                new insertLanguagesAsyncTask(mLanguageDao).execute(new Languages(response.body().dirs));
            }

            @Override
            public void onFailure(Call<LanguagesResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<Integer> getRowCountLanguagesTable() {
        return mLanguageDao.getRowCount();
    }

    public LiveData<Languages> getLanguages() {
        return mLanguageDao.getLanguages();
    }

    public LiveData<List<Dict>> getAllDicts() {
        return mAllDicts;
    }

    public LiveData<List<Word>> getAllWordsForDict(String dictType) {
        return mWordDao.getAllWords(dictType);
    }

    public LiveData<Word> getWord(String wordToTranslate) {
        return mWordDao.getWord(wordToTranslate);
    }

    public void insertWord(final Word word) {
        if (word != null) {
            Call<WordResponse> call = apiService.doGetTranslation(word.getInput(), word.getDictyType());
            call.enqueue(new Callback<WordResponse>() {
                @Override
                public void onResponse(Call<WordResponse> call, Response<WordResponse> response) {
                    word.setOutput(response.body().text);
                    new insertWordAsyncTask(mWordDao).execute(word);
                }

                @Override
                public void onFailure(Call<WordResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }



    public void insertDict (Dict dict) {
        new insertDictAsyncTask(mDictDao).execute(dict);
    }

    public void deleteAllWords() {
        new deleteAllWordsAsyncTask(mWordDao).execute();
    }


    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


    private static class insertWordAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertLanguagesAsyncTask extends AsyncTask<Languages, Void, Void> {

        private LanguageDao mAsyncTaskDao;

        insertLanguagesAsyncTask(LanguageDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Languages... params) {
            Languages languages = params[0];
            List<String> splitLanguages = new ArrayList<>();

            for (int i = 0; i < languages.getDirections().size(); i++) {
                splitLanguages.add(languages.getDirections().get(i).split("-")[0]);
                splitLanguages.add(languages.getDirections().get(i).split("-")[1]);
            }

            languages.setSupportedLanguages(new ArrayList<String>(new LinkedHashSet<String>(splitLanguages)));

            mAsyncTaskDao.insert(languages);
            return null;
        }
    }

    private static class insertDictAsyncTask extends AsyncTask<Dict, Void, Void> {

        private DictDao mAsyncTaskDao;

        insertDictAsyncTask(DictDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Dict... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}