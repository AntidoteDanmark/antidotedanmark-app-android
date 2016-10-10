package dk.antidotedanmark;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewExample";
    private Button mBtnFirstAidGuide;
    private Button mBtnYouTube;
    private List<Item> rssFeedItemList = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private MyRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBtnFirstAidGuide();
        setupBtnYouTube();

        new setupFeed().execute();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyRecyclerAdapter(MainActivity.this, rssFeedItemList);
        mRecyclerView.setAdapter(adapter);
    }

    public void intentAbout(View v) {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }

    private Button getBtnFirstAidGuide() {
        if (mBtnFirstAidGuide == null) {
            mBtnFirstAidGuide = (Button) findViewById(R.id.btnFirstAidGuide);
        }
        return mBtnFirstAidGuide;
    }

    private void setupBtnFirstAidGuide() {
        getBtnFirstAidGuide().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstAidGuide.class);
                startActivity(intent);
            }
        });
    }

    private Button getBtnYouTube() {
        if (mBtnYouTube == null) {
            mBtnYouTube = (Button) findViewById(R.id.btnYouTube);
        }
        return mBtnYouTube;
    }

    private void setupBtnYouTube() {
        getBtnYouTube().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YouTube.class);
                startActivity(intent);
            }
        });
    }


    public class setupFeed extends AsyncTask<String, Void, List<Item>> {

        //TODO String... urls?!
        protected List<Item> doInBackground(String... urls) {


            InputStream inputStream = null;

            if (null == rssFeedItemList) {
                rssFeedItemList = new ArrayList<>();
            }

            try {
                inputStream = new URL("http://antidote.dk/rss.xml").openConnection().getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("inputStreamIsNull", "InputStream is null");
            }

            if (inputStream != null) {

                Feed feed = null;

                try {
                    feed = EarlParser.parseOrThrow(inputStream, 0);
                } catch (XmlPullParserException | IOException | DataFormatException e) {
                    e.printStackTrace();
                }

                if (feed != null) {
                    for (int i = 0; i < feed.getItems().size(); i++) {
                        Item item = feed.getItems().get(i);
                        rssFeedItemList.add(i, item);
                    }
                }
            } else {
                rssFeedItemList = null;
            }

            return rssFeedItemList;
        }

        @Override
        protected void onPostExecute(List<Item> rssFeedItemList) {
            if (rssFeedItemList != null) {
                adapter = new MyRecyclerAdapter(MainActivity.this, rssFeedItemList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }
}

