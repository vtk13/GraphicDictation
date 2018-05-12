package ru.vtkd.graphicdictation.Service;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Locale;

public class SpeechService
{
    private static SpeechService instance = null;

    private TextToSpeech tts;

    private HashMap<String, String> keys = new HashMap<>();

    private SpeechService(Context context)
    {
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    int result = tts.setLanguage(new Locale("ru"));

//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        Log.e("TTS", "Извините, этот язык не поддерживается");
//                    }
                }
            }
        });

        keys.put("r1", "Проведи одну клетку вправо");
        keys.put("r2", "Проведи две клетки вправо");
        keys.put("r3", "Проведи три клетки вправо");
        keys.put("r4", "Проведи четыре клетки вправо");
        keys.put("r5", "Проведи пять клеток вправо");
        keys.put("r6", "Проведи шесть клеток вправо");
        keys.put("r7", "Проведи семь клеток вправо");
        keys.put("r8", "Проведи восемь клеток вправо");

        keys.put("d1", "Проведи одну клетку вниз");
        keys.put("d2", "Проведи две клетки вниз");
        keys.put("d3", "Проведи три клетки вниз");
        keys.put("d4", "Проведи четыре клетки вниз");
        keys.put("d5", "Проведи пять клеток вниз");
        keys.put("d6", "Проведи шесть клеток вниз");
        keys.put("d7", "Проведи семь клеток вниз");
        keys.put("d8", "Проведи восемь клеток вниз");

        keys.put("u1", "Проведи одну клетку вверх");
        keys.put("u2", "Проведи две клетки вверх");
        keys.put("u3", "Проведи три клетки вверх");
        keys.put("u4", "Проведи четыре клетки вверх");
        keys.put("u5", "Проведи пять клеток вверх");
        keys.put("u6", "Проведи шесть клеток вверх");
        keys.put("u7", "Проведи семь клеток вверх");
        keys.put("u8", "Проведи восемь клеток вверх");

        keys.put("l1", "Проведи одну клетку влево");
        keys.put("l2", "Проведи две клетки влево");
        keys.put("l3", "Проведи три клетки влево");
        keys.put("l4", "Проведи четыре клетки влево");
        keys.put("l5", "Проведи пять клеток влево");
        keys.put("l6", "Проведи шесть клеток влево");
        keys.put("l7", "Проведи семь клеток влево");
        keys.put("l8", "Проведи восемь клеток влево");
    }

    public void sayKey(String key)
    {
        if (keys.containsKey(key)) {
            say(keys.get(key));
        }
    }

    public void say(String text)
    {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public static void initialize(Context context)
    {
        instance = new SpeechService(context);
    }

    public static SpeechService getInstance()
    {
        return instance;
    }
}
