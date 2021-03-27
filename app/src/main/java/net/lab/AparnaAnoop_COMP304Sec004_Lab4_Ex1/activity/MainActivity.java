package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.R;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.adapter.TestAdapter;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.model.TestViewModel;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters.TestTrafficSetters;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private TestViewModel testViewModel;
    Button main_nv,view_btn,edit_liscene,dlt_liscene;
    TextView txt_area,edit_txt_area,delete_txt_area;

    boolean canSwipe = false;
    boolean canEdit = false;

    TestAdapter adapter;
    RecyclerView recyclerView;
public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_nv = findViewById(R.id.main_nv);
        view_btn = findViewById(R.id.view_btn);
        edit_liscene = findViewById(R.id.edit_liscene);
        dlt_liscene = findViewById(R.id.dlt_liscene);

        //shared perefrences to retrieve the value of examiner id

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String ExaminerId = prefs.getString("ExaminerId", "xxxz");//"xxxz" is the default value.

        if(ExaminerId.equals("xxxz")){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }




        txt_area = findViewById(R.id.txt_area);
        edit_txt_area = findViewById(R.id.edit_txt_area);
        delete_txt_area = findViewById(R.id.delete_txt_area);

        recyclerView = findViewById(R.id.recycler_view);

        main_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_area.setText(getResources().getString(R.string.view_liscenes_information));

                canSwipe = false;
                canEdit = false;

                main_nv.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                txt_area.setVisibility(View.VISIBLE);
                edit_txt_area.setVisibility(View.VISIBLE);
                delete_txt_area.setVisibility(View.VISIBLE);

                view_btn.setVisibility(View.VISIBLE);
                edit_liscene.setVisibility(View.VISIBLE);
                dlt_liscene.setVisibility(View.VISIBLE);
            }
        });


        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_nv.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                txt_area.setVisibility(View.VISIBLE);
                edit_txt_area.setVisibility(View.GONE);
                delete_txt_area.setVisibility(View.GONE);

                view_btn.setVisibility(View.GONE);
                edit_liscene.setVisibility(View.GONE);
                dlt_liscene.setVisibility(View.GONE);
            }
        });

        edit_liscene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canEdit = true;
                txt_area.setText(getResources().getString(R.string.click_edit_liscenes_information));

                main_nv.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                txt_area.setVisibility(View.VISIBLE);
                edit_txt_area.setVisibility(View.GONE);
                delete_txt_area.setVisibility(View.GONE);

                view_btn.setVisibility(View.GONE);
                edit_liscene.setVisibility(View.GONE);
                dlt_liscene.setVisibility(View.GONE);

                edit();
            }
        });

        dlt_liscene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canSwipe = true;

                txt_area.setText(getResources().getString(R.string.swipe_dlt_liscenes_information));

                main_nv.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                txt_area.setVisibility(View.VISIBLE);
                edit_txt_area.setVisibility(View.GONE);
                delete_txt_area.setVisibility(View.GONE);

                view_btn.setVisibility(View.GONE);
                edit_liscene.setVisibility(View.GONE);
                dlt_liscene.setVisibility(View.GONE);

                swipe();


            }
        });


        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
         adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.getAllNotes().observe(this, new Observer<List<TestTrafficSetters>>() {
            @Override
            public void onChanged(@Nullable List<TestTrafficSetters> notes) {
                adapter.submitList(notes);
            }
        });





    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String result = data.getStringExtra(AddEditNoteActivity.EXTRA_TEST_RESULT);
            String date = data.getStringExtra(AddEditNoteActivity.EXTRA_TEST_DATE);
            String route = data.getStringExtra(AddEditNoteActivity.EXTRA_TEST_ROUTE);
            String test_types = data.getStringExtra(AddEditNoteActivity.EXTRA_getTest_type);

            int exm_id = data.getIntExtra(AddEditNoteActivity.EXTRA_TEST_EAXMINER_ID,1);
            int app_id = data.getIntExtra(AddEditNoteActivity.EXTRA_APPLICANT_ID,1);

          //  String testcenter = data.getStringExtra(AddEditNoteActivity.EXTRA_TEST_CENTER);
            TestTrafficSetters note = new TestTrafficSetters(result, date, route,test_types,exm_id,app_id);
            testViewModel.insert(note);
            Toast.makeText(this, "Traffic license has been saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            System.out.println(data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1) + " dfdfgdsfdsa");
            int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Traffic license can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String result = data.getStringExtra(AddEditNoteActivity.EXTRA_TEST_RESULT);
            String date = data.getStringExtra(AddEditNoteActivity.EXTRA_TEST_DATE);
            String route = data.getStringExtra(AddEditNoteActivity.EXTRA_TEST_ROUTE);
            String test_types = data.getStringExtra(AddEditNoteActivity.EXTRA_getTest_type);
            int exm_id = data.getIntExtra(AddEditNoteActivity.EXTRA_TEST_EAXMINER_ID,1);
            int app_id = data.getIntExtra(AddEditNoteActivity.EXTRA_APPLICANT_ID,1);
            TestTrafficSetters note = new TestTrafficSetters(result, date, route,test_types,exm_id,app_id);
            note.setId(id);
            testViewModel.update(note);
            Toast.makeText(this, "Traffic license has been updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Traffic license has not been  saved", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                testViewModel.deleteAllNotes();
                Toast.makeText(this, "All Traffic licenses deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }

    private void swipe(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (canSwipe){
                    testViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "license has been deleted", Toast.LENGTH_SHORT).show();
                }

            }
        }).attachToRecyclerView(recyclerView);
    }
    private void edit(){
        adapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TestTrafficSetters note) {
                if (canEdit){
                    Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
                    intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
                    intent.putExtra(AddEditNoteActivity.EXTRA_TEST_RESULT, note.getTestResult());
                    intent.putExtra(AddEditNoteActivity.EXTRA_TEST_DATE, note.getTestDate());
                    intent.putExtra(AddEditNoteActivity.EXTRA_TEST_ROUTE, note.getTestRoute());
                    intent.putExtra(AddEditNoteActivity.EXTRA_getTest_type, note.getTest_type());
                    startActivityForResult(intent, EDIT_NOTE_REQUEST);
                }

            }
        });

    }
}