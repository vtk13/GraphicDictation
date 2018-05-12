package ru.vtkd.graphicdictation.View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import ru.vtkd.graphicdictation.Model.DictationState;

public class DictationView extends View
{
    DictationState state;

    public DictationView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        state = DictationState.getInstance();
    }

    public void onDraw(Canvas canvas)
    {
        state.draw(canvas);
    }

    public void nextStep()
    {
        state.nextStep();
        invalidate();
        requestLayout();
    }
}
