package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class EditNoteActivity extends AppCompatActivity {
    public static String EDITPAGE_PUT_TITLE="EditPage_PutTitle";
    public static String EDITPAGE_PUT_DESCRIPTION="EditPage_PutDescription";
    public static String EDITPAGE_PUT_POSITION="position";
    int position=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);


        NoteListAdapter noteListAdapter = new NoteListAdapter(this);
        final EditText TitleEditText=findViewById(R.id.editText_edit_title);
        final EditText DescriptionEditText=findViewById(R.id.editText_edit_description);
        Button ButtonBack=findViewById(R.id.button_edit_back);
        Button SaveButton=findViewById(R.id.button_edit_save);
        final Intent intent=getIntent();
        try {
           position= intent.getIntExtra(NoteListAdapter.PUT_EX_POSITION,0);
        }catch (Exception e){

        }
        String titlestr= intent.getStringExtra(noteListAdapter.PUT_EX_1);
        String Descriptionstr=intent.getStringExtra(noteListAdapter.PUT_EX_2);
        TitleEditText.setText(titlestr);
        DescriptionEditText.setText(Descriptionstr);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsEmptyTitle(TitleEditText) && IsEmptyDescription(DescriptionEditText)){
                    Intent intentback= new Intent();
                    intentback.putExtra(EDITPAGE_PUT_TITLE,TitleEditText.getText().toString());
                    intentback.putExtra(EDITPAGE_PUT_DESCRIPTION,DescriptionEditText.getText().toString());
                    intentback.putExtra(EDITPAGE_PUT_POSITION,position);
                    setResult(RESULT_OK,intentback);
                    finish();
                }
            }
        });





        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private boolean IsEmptyTitle(EditText editText){
        return editText.getText().toString().trim().length()!=0;
    }
    private boolean IsEmptyDescription(EditText editText) {
        return editText.getText().toString().trim().length()!=0;
    }

}
