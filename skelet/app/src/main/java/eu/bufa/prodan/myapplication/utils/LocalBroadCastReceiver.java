package eu.bufa.prodan.myapplication.utils;

import android.content.Context;

/**
 * Created by barbaros.vasile on 1/12/2017.
 */

public interface LocalBroadCastReceiver {
    void Receive(Context context, String action, String sender, String json);
}
