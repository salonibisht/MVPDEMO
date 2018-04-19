package com.example.appinventiv.finalmvpdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.appinventiv.finalmvpdemo.interfaces.EventInfoInterface;
import com.example.appinventiv.finalmvpdemo.R;
import com.example.appinventiv.finalmvpdemo.pojo.NotificationInfoBean;

import java.util.ArrayList;

/**
 * Created by appinventiv on 17/4/18.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.RecyclerViewHolder> implements Filterable {
    private ArrayList<NotificationInfoBean> mNotificationInfoBeanList;
    private Context mContext;
    ArrayList<NotificationInfoBean> mNotificationFilteredList;
    private EventInfoInterface mEventInfoInterface;
   public NotificationAdapter(Context context, ArrayList<NotificationInfoBean> notificationList,EventInfoInterface eventInfoInterface){
     mContext=context;
     mNotificationInfoBeanList=notificationList;
     mNotificationFilteredList=mNotificationInfoBeanList;
     mEventInfoInterface=eventInfoInterface;

   }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification, parent, false);
        return new RecyclerViewHolder(view);    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final NotificationInfoBean notificationInfoBean = mNotificationFilteredList.get(position);
        holder.tvTodo.setText(notificationInfoBean.getTodo());
        holder.tvDescription.setText(notificationInfoBean.getDescription());
        holder.tvTime.setText(notificationInfoBean.getTime());
        holder.tvDate.setText(notificationInfoBean.getDate());

    }

    @Override
    public int getItemCount() {
        return mNotificationFilteredList.size();
    }
    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       TextView tvTodo,tvTime,tvDate,tvDescription;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvTodo=itemView.findViewById(R.id.tv_todo);
            tvDate=itemView.findViewById(R.id.tv_date);
            tvDescription=itemView.findViewById(R.id.tv_discription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mEventInfoInterface.selectEventListener(view, getAdapterPosition());

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mNotificationFilteredList = mNotificationInfoBeanList;
                } else {
                    ArrayList<NotificationInfoBean> filteredList = new ArrayList<>();
                    for (NotificationInfoBean row : mNotificationInfoBeanList) {
                        if (row.getTodo().toLowerCase().contains(charString.toLowerCase()) || row.getDescription().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mNotificationFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mNotificationFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mNotificationFilteredList = (ArrayList<NotificationInfoBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
