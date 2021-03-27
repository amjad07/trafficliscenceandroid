package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.R;

public class AddEditNoteActivity extends AppCompatActivity {

    // intent variables declaration starts
    public static final String EXTRA_ID =
            "EXTRA_ID";
    public static final String EXTRA_TEST_RESULT =
            "EXTRA_TEST_RESULT";
    public static final String EXTRA_TEST_DATE =
            "EXTRA_TEST_DATE";
    public static final String EXTRA_TEST_ROUTE =
            "EXTRA_TEST_ROUTE";
    public static final String EXTRA_TEST_EAXMINER_ID =
            "EXTRA_TEST_EAXMINER_ID";
    public static final String EXTRA_APPLICANT_ID =
            "EXTRA_APPLICANT_ID";
    public static final String EXTRA_getTest_type =
            "EXTRA_getTest_type";
    // intent variables declaration end

    private EditText editTextRESULT;
    private EditText editTextDATE;
    private EditText EDITROUTE;
    private EditText test_type;

    private int app_id,ep_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextRESULT = findViewById(R.id.editTextRESULT);
        editTextDATE = findViewById(R.id.editTextDATE);
        EDITROUTE = findViewById(R.id.editroute);
        test_type = findViewById(R.id.test_type);


        app_id = 1;
        ep_id= 1;


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_save_24);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Traffic license");
            editTextRESULT.setText(intent.getStringExtra(EXTRA_TEST_RESULT));
            editTextDATE.setText(intent.getStringExtra(EXTRA_TEST_DATE));
            EDITROUTE.setText(intent.getStringExtra(EXTRA_TEST_ROUTE)+"");
            test_type.setText(intent.getStringExtra(EXTRA_getTest_type)+"");
        } else {
            setTitle("Add Traffic license");
        }
    }
    private void saveNote() {
        String result = editTextRESULT.getText().toString();
        String date = editTextDATE.getText().toString();
        String route = EDITROUTE.getText().toString();
        String test_types = test_type.getText().toString();

        if (result.trim().isEmpty() || date.trim().isEmpty() || route.trim().isEmpty()|| test_types.trim().isEmpty()) {
            Toast.makeText(this, "Please insert details", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TEST_RESULT, result);
        data.putExtra(EXTRA_TEST_DATE, date);
        data.putExtra(EXTRA_TEST_ROUTE, route);
        data.putExtra(EXTRA_getTest_type, test_types);
        data.putExtra(EXTRA_TEST_EAXMINER_ID, app_id);
        data.putExtra(EXTRA_APPLICANT_ID, ep_id);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}