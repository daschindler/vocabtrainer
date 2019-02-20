package at.davidomi.vocabtrainer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import at.davidomi.vocabtrainer.data.TranslateRepository;
import at.davidomi.vocabtrainer.entity.Dict;


public class DictViewModel extends AndroidViewModel {

    private TranslateRepository mRepository;

    private LiveData<List<Dict>> mAllDicts;

    public DictViewModel(Application application) {
        super(application);
        mRepository = new TranslateRepository(application);
        mAllDicts = mRepository.getAllDicts();
    }

    public LiveData<List<Dict>> getAllDicts() {
        return mAllDicts;
    }

    public void insert(Dict dict) {
        mRepository.insertDict(dict);
    }
}