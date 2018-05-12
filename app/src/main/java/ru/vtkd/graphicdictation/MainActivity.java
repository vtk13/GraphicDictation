package ru.vtkd.graphicdictation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import ru.vtkd.graphicdictation.Model.DictationState;
import ru.vtkd.graphicdictation.Service.SpeechService;

public class MainActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpeechService.initialize(getApplicationContext());
        DictationState.initialize(SpeechService.getInstance());
    }

    public void openDictationSimple(View view)
    {
        startActivity(new Intent(this, Dictation.class));
        DictationState.getInstance().start(DictationState.MODE_SIMPLE);
    }

    public void openDictationNormal(View view)
    {
        startActivity(new Intent(this, Dictation.class));
        DictationState.getInstance().start(DictationState.MODE_NORMAL);
    }
}
