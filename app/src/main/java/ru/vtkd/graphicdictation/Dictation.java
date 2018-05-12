package ru.vtkd.graphicdictation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ru.vtkd.graphicdictation.View.DictationView;

public class Dictation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictation);
    }

    public void nextStep(View view) {
        DictationView dictation = (DictationView)findViewById(R.id.dictationView);
        dictation.nextStep();
    }
}
