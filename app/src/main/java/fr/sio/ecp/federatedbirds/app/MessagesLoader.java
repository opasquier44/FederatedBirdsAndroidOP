package fr.sio.ecp.federatedbirds.app;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import fr.sio.ecp.federatedbirds.ApiClient;
import fr.sio.ecp.federatedbirds.model.Message;

/**
 * Created by Michaël on 24/11/2015.
 */
public class MessagesLoader extends AsyncTaskLoader<List<Message>> {

    private List<Message> mResult;

    public MessagesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mResult != null) {
            deliverResult(mResult);
        } else {
            forceLoad();
        }
    }

    @Override
    public List<Message> loadInBackground() {
        try {
            return ApiClient.getInstance().getMessages();
        } catch (IOException e) {
            Log.e("MessagesLoader", "Failed to load messages", e);
            return null;
        }
    }

}