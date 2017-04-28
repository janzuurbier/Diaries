package zrb.hu.nl.diaries;

/**
 * Created by JZuurbier on 20-4-2016.
 */

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.MyViewHolder> {

    private Cursor c;
    MyDBHelper mdbh;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, dateText;

        public MyViewHolder(View view) {
            super(view);
            //todo opgave e.
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            c.moveToPosition(pos);
            Date date = new Date(c.getLong(c.getColumnIndex(Constants.DATE_NAME)));
            String datedata = DateFormat.format("MMM dd, yyyy h:mmaa", date).toString();
            String title = c.getString(c.getColumnIndex(Constants.TITLE_NAME));
            String content = c.getString(c.getColumnIndex(Constants.CONTENT_NAME));
            int id = c.getInt(c.getColumnIndex(Constants.KEY_ID));
            Intent intent = new Intent(v.getContext(), ShowContent.class);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("recorddate", datedata);
            intent.putExtra("id", id);
            v.getContext().startActivity(intent);
        }
    }


    public EntriesAdapter(MyDBHelper mdbh) {
        this.mdbh = mdbh;
        c = mdbh.getDiaries();
    }

    public void refresh() {
        c = mdbh.getDiaries();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //todo opgave f.
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       //todo opgave f.
    }

    @Override
    public int getItemCount() {
        //todo opgave f.
    }
}

