package at.davidomi.vocabtrainer.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import at.davidomi.vocabtrainer.R;
import at.davidomi.vocabtrainer.entity.Word;
import at.davidomi.vocabtrainer.viewmodel.WordViewModel;
import at.davidomi.vocabtrainer.viewmodel.WordViewModelFactory;


public class WordsActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;

    private EditText etWord;
    private Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etWord = findViewById(R.id.et_word);
        btSave = findViewById(R.id.bt_save);

        Intent intent = getIntent();
        final String dictType = intent.getStringExtra("dict_type");


        mWordViewModel = ViewModelProviders.of(this, new WordViewModelFactory(this.getApplication(), dictType))
                .get(WordViewModel.class);

        //mWordViewModel.insert(new Word("hallo", dictType));
        //mWordViewModel.insert(new Word("lul", dictType));

        mWordViewModel.deleteAllWords();


        mWordViewModel.getWord("hallo").observe(this, new Observer<Word>() {
            @Override
            public void onChanged(@Nullable Word word) {
                if (word != null) {
                    Toast.makeText(getApplicationContext(), word.getOutput(), Toast.LENGTH_LONG).show();
                    Log.e("getWord()", word.getOutput());
                }
            }
        });




        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                StringBuilder sb = new StringBuilder();


                for (int i = 0; i < words.size(); i++) {
                    sb.append(words.get(i).getInput() + ", " + words.get(i).getOutput() + ", " + words.get(i).getDictyType());
                }

                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etWord.getText().toString().isEmpty()) {
                    mWordViewModel.insert(new Word(etWord.getText().toString(), dictType));
                }

            }
        });
    }
}
