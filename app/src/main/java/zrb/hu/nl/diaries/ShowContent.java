package zrb.hu.nl.diaries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowContent extends AppCompatActivity {
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        TextView tv1 = (TextView)findViewById(R.id.titleView);
        tv1.setText(title);
        String content = intent.getStringExtra("content");
        TextView tv2 = (TextView) findViewById(R.id.contentView);
        tv2.setText(content);
        String tijdstip = intent.getStringExtra("recorddate");
        TextView tv3 = (TextView) findViewById(R.id.dateView);
        tv3.setText(tijdstip);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.deleteitem) {
            MyDBHelper.getInstance(this).verwijderDiary(id);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }





}
