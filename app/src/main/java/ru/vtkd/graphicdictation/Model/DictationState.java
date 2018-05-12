package ru.vtkd.graphicdictation.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import ru.vtkd.graphicdictation.Service.SpeechService;

public class DictationState {
    private static DictationState instance = null;

    private Paint p;

    private static final int WIDTH = 0;
    private static final int HEIGHT = 1;
    private static final int START_X = 2;
    private static final int START_Y = 3;
    private static final int FIRST_DATA = 4;

    public static final int MODE_SIMPLE = 0;
    public static final int MODE_NORMAL = 1;


    private int n;
    private String[] images = {
            "10 8 1 1 r1 d1 r2 u1 r1 d2 r5 u2 l1 u1 r2 d8 l1 u2 l1 d2 l1 u2 l3 d2 l1 u2 l1 d2 l1 u3 l1 u4",
            "9 6 1 1 r2 d4 r1 u2 r1 u1 r4 d1 r1 d3 l1 d1 l1 u1 l4 d1 l1 u1 l1 u3 l1 u2",
            "14 7 1 5 r1 u1 r1 u2 r2 u1 r4 d1 r2 d2 r1 u1 r1 u1 r2 d2 l1 d1 r1 d2 l2 u1 l2 d1 l2 d1 l4 u1 l2 u1 l2 u1",
            "15 8 1 3 r2 u1 r2 u1 r1 d1 r8 d1 r1 d1 r1 d2 l1 u1 l1 u1 l1 d5 l2 u1 r1 u3 l5 d4 l2 u1 r1 u3 l2 u1 l3 u1"
    };

    private int index;
    private String[] image;
    private int mode = MODE_NORMAL;

    private Random random;
    private SpeechService speechService;

    private DictationState(SpeechService speechService)
    {
        this.speechService = speechService;
        random = new Random();

        p = new Paint();

        setImage(0);
    }

    private void setImage(int n) {
        this.n = n;
        image = images[n].split(" ");
        index = FIRST_DATA - 1;
    }

    public static void initialize(SpeechService speechService)
    {
        instance = new DictationState(speechService);
    }

    public static DictationState getInstance()
    {
        return instance;
    }

    public void draw(Canvas canvas) {
        int w = Integer.valueOf(image[WIDTH]);
        int h = Integer.valueOf(image[HEIGHT]);
        int x = Integer.valueOf(image[START_X]);
        int y = Integer.valueOf(image[START_Y]);

        int cellSize;
        if (w / h > canvas.getWidth() / canvas.getHeight()) {
            cellSize = canvas.getWidth() / (w + 2 + 1);
        } else {
            cellSize = canvas.getHeight() / (h + 2 + 1);
        }

        p.setStrokeWidth(1);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.DKGRAY);
        for (int a = 15 ; a < canvas.getWidth() ; a += cellSize) {
            canvas.drawLine(a, 0, a, canvas.getHeight(), p);
        }
        for (int b = 15 ; b < canvas.getHeight() ; b += cellSize) {
            canvas.drawLine(0, b, canvas.getWidth(), b, p);
        }

        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x * cellSize + 15, y * cellSize + 15, 10, p);

        if (mode == MODE_SIMPLE || isDone()) {
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(7);
            for (int i = FIRST_DATA; i <= index; i++) {
                if (i < index) {
                    p.setColor(Color.BLACK);
                } else {
                    p.setColor(Color.BLUE);
                }
                int dx = 0, dy = 0;

                switch (image[i].charAt(0)) {
                    case 'd':
                        dy = Integer.valueOf(image[i].substring(1));
                        break;
                    case 'u':
                        dy = -Integer.valueOf(image[i].substring(1));
                        break;
                    case 'l':
                        dx = -Integer.valueOf(image[i].substring(1));
                        break;
                    case 'r':
                        dx = Integer.valueOf(image[i].substring(1));
                        break;
                }

                canvas.drawLine(x * cellSize + 15, y * cellSize + 15, (x + dx) * cellSize + 15, (y + dy) * cellSize + 15, p);

                x += dx;
                y += dy;
            }
        }
    }

    private boolean isDone()
    {
        return index == image.length - 1;
    }

    public void nextStep() {
        if (!isDone()) {
            index++;
            speechService.sayKey(image[index]);
        } else {
            start(mode);
        }
    }

    public void start(int mode)
    {
        int nextImage;
        do {
            nextImage = random.nextInt(images.length);
        } while (nextImage == n);
        this.mode = mode;
        setImage(nextImage);

        speechService.say("Выбери точку с которой ты начнешь рисовать");
    }
}
