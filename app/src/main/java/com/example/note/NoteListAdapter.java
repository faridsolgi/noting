package com.example.note;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ItemViewHolder> {
    private static final int RESULT_OK = -1;
    private Context context;
    private List<Note> notes = new ArrayList<>();
    public static final String PUT_EX_1="title_toEdit_ex";
    public static final String PUT_EX_2="description_toEdit_ex";
    public static final String PUT_EX_POSITION="position_toEdit_ex";
    public static final int REQUEST_FOR_ACTIVITY_CODE=1004;


    //start item holder implement
    public NoteListAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.note_model,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.BindNotes(notes.get(position));

        //start del
        holder.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveNote(position);
            }
        });
        //end del

        //start edit
        holder.editnotebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(context,""+position,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, EditNoteActivity.class);
                String putTitle=holder.titleTextview.getText().toString();
                String putDescription=holder.descriptionTextview.getText().toString();
                intent.putExtra(PUT_EX_POSITION,position);
                intent.putExtra(PUT_EX_1,putTitle);
                intent.putExtra(PUT_EX_2,putDescription);
                ((Activity) context).startActivityForResult(intent, REQUEST_FOR_ACTIVITY_CODE);
                MainActivity mainActivity=new MainActivity();

            }
        });
        //end edit
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    //end item holder implement


    //start item holder class
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextview;
        TextView descriptionTextview;
        Button deletebutton;
        Button editnotebutton;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextview =itemView.findViewById(R.id.textView_notemodel_title);
            descriptionTextview =itemView.findViewById(R.id.textView_notemodel_description);
            deletebutton = itemView.findViewById(R.id.buttonnotemodel_delete);
            editnotebutton=itemView.findViewById(R.id.button_notemodel_edit);
        }
        public void BindNotes(Note note){
            titleTextview.setText(note.getTitle());
            descriptionTextview.setText(note.getDescription());



        }


    }

    public void  AddNote(Note note){
        notes.add(note);

        notifyItemInserted(notes.size()-1);

    }

    public void EditNote(int position ,Note note){
            notes.set(position,note);
            notifyItemChanged(position);

    }

    public void RemoveAllNote(){
        int size = notes.size();
        notes.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void RemoveNote(int position){
        notes.remove(position);
        notifyItemRemoved(position);
    }

        public  void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode==REQUEST_FOR_ACTIVITY_CODE
                    && resultCode==RESULT_OK
                    && data!=null){
                Note note =new Note();
                String titlenewputextra=data.getStringExtra(EditNoteActivity.EDITPAGE_PUT_TITLE);
                String descriptionnewputextra=data.getStringExtra(EditNoteActivity.EDITPAGE_PUT_DESCRIPTION);
               int positionnewputextra=data.getIntExtra(EditNoteActivity.EDITPAGE_PUT_POSITION,0);

                note.setTitle(titlenewputextra);
                note.setDescription(descriptionnewputextra);
                EditNote(positionnewputextra,note);

        }

    }
}
