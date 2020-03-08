package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity {
    public static final String KEY_TITLE="title";
    public static final String KEY_DESCRIPTION="description";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        final EditText titleeditText=findViewById(R.id.editText_edit_title);
        final EditText descriptioneditText= findViewById(R.id.editText_edit_description);

        Button savebutton =findViewById(R.id.button_edit_save);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title;
                String description;

                if (IsEmptyTitle(titleeditText) && IsEmptyDescription(descriptioneditText)){
                    title=titleeditText.getText().toString();
                    description=descriptioneditText.getText().toString();
                    Intent intent=new Intent();
                    intent.putExtra(KEY_TITLE,title);
                    intent.putExtra(KEY_DESCRIPTION,description);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    String emptynotetoast= getResources().getString(R.string.empty_new_note);
                    Toast.makeText(NewNoteActivity.this,emptynotetoast,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
 private boolean IsEmptyTitle(EditText editText){
        return editText.getText().toString().trim().length()!=0;
    }
    private boolean IsEmptyDescription(EditText editText){
        return editText.getText().toString().trim().length()!=0;
    }

}
