package dk.antidotedanmark;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.einmalfel.earl.Item;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<FeedListRowHolder> {


    private List<Item> rssFeedItemList;

    private Context mContext;
    //On click stuff
    //TODO fix stuff
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FeedListRowHolder holder = (FeedListRowHolder) view.getTag();
            //getLayoutPosition() or getAdapterPosition()
            int position = holder.getPosition();

            Item item = rssFeedItemList.get(position);

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
            mContext.startActivity(browserIntent);
        }
    };

    public MyRecyclerAdapter(Context context, List<Item> rssFeedItemList) {
        this.rssFeedItemList = rssFeedItemList;
        this.mContext = context;
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new FeedListRowHolder(v);
    }

    @Override
    //TODO index
    //TODO set onclicklistener to handle to complete layout instead of each individual element
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {
        Item item = rssFeedItemList.get(i);

        Picasso.with(mContext).load(item.getImageLink())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(feedListRowHolder.thumbnail);

        feedListRowHolder.title.setText(Html.fromHtml(item.getTitle()));

        //Handle click event on both title and image click
        feedListRowHolder.title.setOnClickListener(clickListener);
        feedListRowHolder.thumbnail.setOnClickListener(clickListener);

        feedListRowHolder.title.setTag(feedListRowHolder);
        feedListRowHolder.thumbnail.setTag(feedListRowHolder);


    }

    @Override
    public int getItemCount() {
        return (null != rssFeedItemList ? rssFeedItemList.size() : 0);
    }
}
