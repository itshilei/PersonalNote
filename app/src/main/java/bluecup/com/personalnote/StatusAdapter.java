package bluecup.com.personalnote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by hanjing on 15/3/6.
 */

//adapter can do some quary
public class StatusAdapter extends ArrayAdapter<ParseObject> {
    protected Context mContext;
    protected List<ParseObject> mStatus;

    //constructor
    public StatusAdapter(Context context, List<ParseObject> status){
        super(context, R.layout.homecustom, status);
        mContext = context;
        mStatus = status;

    }

    //initialize ViewHolder
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (null == convertView){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.homecustom, null);
            holder = new ViewHolder();
            holder.usernameViewHolder = (TextView) convertView.findViewById(R.id.usernameHP);
            holder.statusViewHolder = (TextView) convertView.findViewById(R.id.statusHP);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ParseObject statusObj = mStatus.get(position);

        //usermane
        String usernameStr = statusObj.getString("user");
        holder.usernameViewHolder.setText(usernameStr);

        //status
        String statusStr = statusObj.getString("postedStatus");
        holder.statusViewHolder.setText(statusStr);

        return convertView;
    }

    public static class ViewHolder{
        TextView usernameViewHolder;
        TextView statusViewHolder;
    }

}
