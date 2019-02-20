package at.davidomi.vocabtrainer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import at.davidomi.vocabtrainer.data.TranslateRepository;
import at.davidomi.vocabtrainer.entity.Word;


public class WordViewModel extends AndroidViewModel {

    private TranslateRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application, String dictType) {
        super(application);
        mRepository = new TranslateRepository(application);
        mAllWords = mRepository.getAllWordsForDict(dictType);
    }

    public LiveData<Word> getWord(String wordToTranslate) {
        return mRepository.getWord(wordToTranslate);
    }

    public void deleteAllWords() {
        mRepository.deleteAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}
