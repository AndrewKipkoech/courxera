package com.example.courxera;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddCourseActivity extends AppCompatActivity {
    DatabaseHelper courseDatabase;
    EditText cName, ctime, cCredit, cId;
    Button btnAdd;
    Button btnView;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        courseDatabase = new DatabaseHelper(this);
        cName =(EditText) findViewById(R.id.editCourseName);
        ctime = (EditText)findViewById(R.id.editCourseTime);
        cCredit = (EditText) findViewById(R.id.editCourseCredits);
        cId = (EditText) findViewById(R.id.editCourseId);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
       AddData();
       viewAll();
       updateData();
       deleteData();

    }

    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = courseDatabase.updateData(cId.getText().toString(),cName.getText().toString(),ctime.getText().toString(),
                cCredit.getText().toString());
                if(isUpdate == true){
                    cName.setText("");
                    cCredit.setText("");
                    ctime.setText("");
                    cId.setText("");
                    Toast toast = Toast.makeText(AddCourseActivity.this, "Data updated", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(AddCourseActivity.this, "Data Not updated", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }
            }
        });
    }

    public void AddData(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if any field is empty, if its empty print a toast error message
                if(TextUtils.isEmpty(cName.getText().toString())  ||
                        TextUtils.isEmpty(ctime.getText().toString()) || TextUtils.isEmpty(cCredit.getText().toString())){
                    Toast toast = Toast.makeText(AddCourseActivity.this, "Empty fields", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }else{
                    //add course details to the database
                    boolean isInserted = courseDatabase.insertData(cName.getText().toString(),ctime.getText().toString(), cCredit.getText().toString());
                    if(isInserted == true){
                        Toast toast = Toast.makeText(AddCourseActivity.this, "Successfully Added", Toast.LENGTH_LONG);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextSize(25);
                        toast.show();
                        cName.setText("");
                        cCredit.setText("");
                        ctime.setText("");
                        cId.setText("");

                    }else {
                        Toast toast = Toast.makeText(AddCourseActivity.this, "Something went wrong!", Toast.LENGTH_LONG);
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextSize(25);
                        toast.show();
                        cName.setText("");
                        cCredit.setText("");
                        ctime.setText("");

                    }

                }


            }
        });
    }

    public void viewAll(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res = courseDatabase.getAllData();
               if(res.getCount() == 0){
                   showMessage("Error", "No data was found");
                   return;
               }
                   StringBuffer buffer = new StringBuffer();
               while(res.moveToNext()){
                   buffer.append(" ID : " + res.getString(0 ) + "\n");
                   buffer.append(" Name : " + res.getString(1 ) + "\n");
                   buffer.append(" Time : " + res.getString(2 ) + "\n");
                   buffer.append(" Credits : " + res.getString(3 ) + "\n\n");
               }
               //show all data
                showMessage("Added Courses", buffer.toString());
            }
        });
    }
    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = courseDatabase.deleteData(cId.getText().toString());
                if(deletedRows > 0){
                    Toast toast = Toast.makeText(AddCourseActivity.this, "Course deleted!", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(AddCourseActivity.this, "Not deleted!", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
