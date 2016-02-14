package fr.sio.ecp.federatedbirds.app;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import fr.sio.ecp.federatedbirds.ApiClient;
import fr.sio.ecp.federatedbirds.R;
import fr.sio.ecp.federatedbirds.model.User;

/**
 * Created by Olivier 08/01/2016.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MessageViewHolder> {

    private List<User> mUsers;

    public void setUsers(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        User user = mUsers.get(position);

        Picasso.with(holder.mAvatarView.getContext())
                .load(user.avatar)
                .into(holder.mAvatarView);

        holder.mUsernameView.setText(user.login);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        private ImageView mAvatarView;
        private TextView mUsernameView;
        private Button mButtonFollow;
        private Button mButtonUnfollow;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mAvatarView = (ImageView) itemView.findViewById(R.id.avatar);
            mUsernameView = (TextView) itemView.findViewById(R.id.username);
            mButtonFollow = (Button) itemView.findViewById(R.id.follow);
            mButtonUnfollow = (Button) itemView.findViewById(R.id.unfollow);
        }

    }
    private class SetFollowingTask extends AsyncTask<Void, Void, User> {

        private Context mContext;
        private long mFollowId;
        private boolean mFollow;

        public SetFollowingTask(Context context, long following_id, boolean follow){
            mContext = context;
            mFollowId = following_id;
            mFollow = follow;
        }

        @Override
        protected User doInBackground(Void... params) {
            try {
                return ApiClient.getInstance(mContext).setFollow(mFollowId, mFollow);
            } catch (IOException e) {
                Log.e(UsersAdapter.class.getSimpleName(), "No Following possible", e);
                return null;
            }
        }
    }

}