package eu.bufa.prodan.myapplication.utils.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;

import java.net.URISyntaxException;

public class ImagePickerUtils {

    private static final ImagePickerUtils ourInstance = new ImagePickerUtils();

    public static ImagePickerUtils getInstance() {
        return ourInstance;
    }

    private ImagePickerUtils() {
    }


    @SuppressLint("CheckResult")
    public void chooseImage(FragmentActivity fragmentActivity, PhotoListener listener){
        RxImagePicker.with(fragmentActivity.getFragmentManager()).requestImage(Sources.GALLERY).subscribe(uri -> {
            if (listener != null) {
                listener.onSelected(getRealPathFromURI(fragmentActivity, uri));
            }
        });
    }

    private static String getRealPathFromURI(Context context, Uri contentURI) {
        try {
            return RealPathUtil.getPath(context, contentURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface PhotoListener {
        void onSelected(String path);
    }
}
