package zrb.hu.nl.diaries;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class InsertDiary extends AppCompatActivity {
    EditText titleET, contentET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titleET = (EditText) findViewById(R.id.editText1);
        contentET = (EditText) findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.saveItem) {
            MyDBHelper mdbh = MyDBHelper.getInstance(this);
            String title = titleET.getText().toString();
            String content = contentET.getText().toString();
            mdbh.insertDiary(title, content);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
