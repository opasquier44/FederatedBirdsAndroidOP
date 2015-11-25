package fr.sio.ecp.federatedbirds.app;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import fr.sio.ecp.federatedbirds.R;
import fr.sio.ecp.federatedbirds.auth.TokenManager;
import fr.sio.ecp.federatedbirds.model.Message;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Message>> {

    private static final int LOADER_MESSAGES = 0;

    private MessagesAdapter mMessagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TokenManager.getUserToken(this) == null) {
            //launch login activity
            finish();
        }

        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        "Clicked",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        RecyclerView listView = (RecyclerView) findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        mMessagesAdapter = new MessagesAdapter();
        listView.setAdapter(mMessagesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportLoaderManager().initLoader(
                LOADER_MESSAGES,
                null,
                this
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Loader<List<Message>> onCreateLoader(int id, Bundle args) {
        return new MessagesLoader(this, null);
    }

    @Override
    public void onLoadFinished(Loader<List<Message>> loader, List<Message> messages) {
        mMessagesAdapter.setMessages(messages);
    }

    @Override
    public void onLoaderReset(Loader<List<Message>> loader) { }

}
