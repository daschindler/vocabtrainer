package at.davidomi.vocabtrainer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import at.davidomi.vocabtrainer.viewmodel.WordViewModel;

public class WordViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;


    public WordViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new WordViewModel(mApplication, mParam);
    }
}
