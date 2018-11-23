package eu.bufa.prodan.myapplication.text_views;
import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by barbaros.vasile on 8/11/2017.
 */

public class FontManager {
    private static final FontManager ourInstance = new FontManager();

    public static FontManager getInstance() {
        return ourInstance;
    }

    private FontManager() {
    }

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public Typeface getTypeface(String fontName, Context context) {
        Typeface typeFace = fontCache.get(fontName);
        if (typeFace == null) {
            try {
                typeFace = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontName, typeFace);
        }

        return typeFace;
    }
}