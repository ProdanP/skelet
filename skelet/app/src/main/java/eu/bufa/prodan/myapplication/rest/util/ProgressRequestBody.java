package eu.bufa.prodan.myapplication.rest.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;


public class ProgressRequestBody extends RequestBody {
    private File mFile;
    private UploadCallbacks mListener;

    private static final int DEFAULT_BUFFER_SIZE = 2048;

    public interface UploadCallbacks {
        void onProgressUpdate(String fileName, int percentage);
        void onError(String fileName);
        void onFinish(String fileName);
    }

    public ProgressRequestBody(final File file, final  UploadCallbacks listener) {
        mFile = file;
        mListener = listener;
    }

     @Override
    public MediaType contentType() {
         String type;
         String extension = MimeTypeMap.getFileExtensionFromUrl(mFile.getAbsolutePath());
         if (extension != null) {
             type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
             if (type != null) {
                 return MediaType.parse(type);
             }
         }

        return MediaType.parse("image/jpeg");
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        long fileLength = mFile.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        FileInputStream in = new FileInputStream(mFile);
        long uploaded = 0;

        try {
            int read;
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer)) != -1) {

                // update progress on UI thread
                uploaded += read;
                sink.write(buffer, 0, read);
                handler.post(new ProgressUpdater(uploaded, fileLength));
            }
        } finally {
            in.close();
        }
    }

    private class ProgressUpdater implements Runnable {
        private long mUploaded;
        private long mTotal;
        public ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
        }

        @Override
        public void run() {

            mListener.onProgressUpdate(mFile.getAbsolutePath(), (int)(100 * mUploaded / mTotal));

            if(mUploaded == mTotal){
                mListener.onFinish(mFile.getAbsolutePath());
            }
        }
    }

    public File getmFile() {
        return mFile;
    }
}