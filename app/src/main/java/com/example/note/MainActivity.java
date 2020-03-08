package com.example.note;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 private static final int REQUEST_CODE=1001;
    private static final int REQUEST_CODE_EDIT=1002;
    final NoteListAdapter noteListAdapter=new NoteListAdapter(this);
    List<Note> notes =new ArrayList<>();
     int parseInt=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Note note=new Note();
        EditNoteActivity editNoteActivity=new EditNoteActivity();
      /*  Intent intent=getIntent();*/
       /* try {
            String getTitleIntent = intent.getStringExtra(editNoteActivity.EDITPAGE_PUT_TITLE);
            String getDescriptionIntent = intent.getStringExtra(editNoteActivity.EDITPAGE_PUT_DESCRIPTION);
           int getPositionIntent = intent.getIntExtra("position",0);
            try {
                  parseInt = getPositionIntent;

            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
            if (getTitleIntent != null) {
                note.setTitle(getTitleIntent);
                note.setDescription(getDescriptionIntent);
                noteListAdapter.EditNote(parseInt, note);
                }
            }catch(NullPointerException npe){
                System.out.println("Could not parse " + npe);
            }
*/
        RecyclerView recyclerView=findViewById(R.id.recyclerview_main_rcview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteListAdapter);

        // start add
        final Button newnotebutton=findViewById(R.id.button_main_addnote);
        newnotebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,NewNoteActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        //end add
        //start remove all
        Button removeallbutton=findViewById(R.id.button_main_deleteall);
        removeallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteListAdapter.RemoveAllNote();
            }
        });


        //end remove all



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Note note = new Note();
        if (requestCode==REQUEST_CODE
                && resultCode==RESULT_OK
                && data!=null){
            String titlenewputextra=data.getStringExtra(NewNoteActivity.KEY_TITLE);
            String descriptionnewputextra=data.getStringExtra(NewNoteActivity.KEY_DESCRIPTION);
            note.setTitle(titlenewputextra);
            note.setDescription(descriptionnewputextra);
            noteListAdapter.AddNote(note);
        }

        noteListAdapter.onActivityResult(requestCode, resultCode, data);


    }
/*    @NotNull
    private List<Note> noteGenerate(){
        List<Note> notes=new ArrayList<>();
        Note note =new Note();
        note.setTitle();
        note.setDescription();
        notes.add(note);



        return notes;
    }*/
}