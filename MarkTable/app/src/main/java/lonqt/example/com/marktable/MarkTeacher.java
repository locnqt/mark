package lonqt.example.com.marktable;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by locnq on 5/2/2018.
 */

public class MarkTeacher extends AppCompatActivity {
    Spinner spinnerMonhoc, spinnerClass;
    TextView tvdanhsachSV, tvteacherCC_Percentage, tvteacherTH_Percentage, tvteacherBT_Percentage, tvteacherKT_Percentage, tvteacherTHI_Percentage;
    ListView lvDanhsachSV;
    ImageView imgEdit_Percentage;

    ArrayList<Subject> arrsub;

    ArrayList<Mark> arrmark;
    MarkTeacherAdapter markTeacherAdapter;

    DatabaseReference database;
    DatabaseReference mdatamarkteacher;
    DatabaseReference mdatasubject;
    DatabaseReference mdatastudent;

    //    String subject;
    String cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_teacher);

        init();

        database = FirebaseDatabase.getInstance().getReference();
        mdatamarkteacher = database.child("TB_MARK_TEACHER").child("TG134");
        mdatasubject = database.child("TB_subject").child("TG134");
        mdatastudent = database.child("TB_STUDENT");

        getdataspinner();
        addControl();

    }

    public void getdataspinner() {
        mdatasubject.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> arrsubect = new ArrayList<>();
                arrsub = new ArrayList<>();
//                arrsub.clear();
//                arrsubect.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String sub = ds.getKey();
                    Subject c = ds.getValue(Subject.class);
                    String subname = c.getSubjectName();
//                    String id = c.getSubjectID();
                    Log.d("Get Data From Firebase", "Get " +c);
                    Log.d("sub ", "Get " + subname);
                    arrsubect.add(subname);
                    arrsub.add(c);
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(MarkTeacher.this, R.layout.spinner_center_item, arrsubect);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMonhoc.setAdapter(arrayAdapter);
//                subject = spinnerMonhoc.getSelectedItem().toString();

                mdatastudent.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final ArrayList<String> arrclass = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String classstudent = ds.getKey();
                            Log.d("classstudent ", "Get " + classstudent);
                            arrclass.add(classstudent);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MarkTeacher.this, R.layout.spinner_center_item, arrclass);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerClass.setAdapter(arrayAdapter);
                        cl = spinnerClass.getSelectedItem().toString();
//                        addControl();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addControl() {
        spinnerMonhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                subject = spinnerMonhoc.getSelectedItem().toString();
                arrsub.clear();
                arrmark = new ArrayList<>();
                markTeacherAdapter = new MarkTeacherAdapter(MarkTeacher.this, R.layout.mark_teacher_item, arrmark);
                lvDanhsachSV.setAdapter(markTeacherAdapter);
                getDataNamhoc(markTeacherAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                cl = spinnerClass.getSelectedItem().toString();
                arrmark = new ArrayList<>();
                markTeacherAdapter = new MarkTeacherAdapter(MarkTeacher.this, R.layout.mark_teacher_item, arrmark);
                lvDanhsachSV.setAdapter(markTeacherAdapter);
                getDataNamhoc(markTeacherAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imgEdit_Percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEdit();
            }
        });
    }

    private void getDataNamhoc(final MarkTeacherAdapter adapter) {
//        subject = spinnerMonhoc.getSelectedItem().toString();
        cl = spinnerClass.getSelectedItem().toString();
        tvdanhsachSV.setText("Danh sách sinh viên lớp " + cl);
        Subject vitri = arrsub.get(spinnerMonhoc.getSelectedItemPosition());
        mdatamarkteacher.child(vitri.getSubjectID()).child(cl).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                final ArrayList<Student> arrstudent = new ArrayList<>();
                mdatastudent.child(cl).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot student : dataSnapshot.getChildren()) {
                            Student stu = student.getValue(Student.class);
                            arrstudent.add(stu);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Mark mark = ds.getValue(Mark.class);
                    String studentid = ds.getKey();
                    int i;
                    for (i=0; i<arrstudent.size(); i++){
                        if (studentid.equals(arrstudent.get(i).getStudentID())) break;
                    }
                    mark.setStudentid(studentid);
                    mark.setSubjectName(arrstudent.get(i).getFullName());
                    Log.d("Get Data From Firebase", "Get " + mark);
                    adapter.add(mark);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void dialogEdit() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_percentage);

        final EditText edtEditCC = (EditText) dialog.findViewById(R.id.edtEditCC);
        final EditText edtEditTH = (EditText) dialog.findViewById(R.id.edtEditTH);
        final EditText edtEditBT = (EditText) dialog.findViewById(R.id.edtEditBT);
        final EditText edtEditKT = (EditText) dialog.findViewById(R.id.edtEditKT);
        final EditText edtEditTHI = (EditText) dialog.findViewById(R.id.edtEditTHI);
        Button btedit = (Button) dialog.findViewById(R.id.btedit);
        Button btedback = (Button) dialog.findViewById(R.id.btedback);

        btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cc = edtEditCC.getText().toString().trim();
                String th = edtEditTH.getText().toString().trim();
                String bt = edtEditBT.getText().toString().trim();
                String kt = edtEditKT.getText().toString().trim();
                String thi = edtEditTHI.getText().toString().trim();
                boolean addGioiinh = true;
                if (cc.equals("") || th.equals("") || bt.equals("") || kt.equals("") || thi.equals("")) {
                    Toast.makeText(MarkTeacher.this, "Chưa điền đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    tvteacherCC_Percentage.setText(cc + "%");
                    tvteacherTH_Percentage.setText(th + "%");
                    tvteacherBT_Percentage.setText(bt + "%");
                    tvteacherKT_Percentage.setText(kt + "%");
                    tvteacherTHI_Percentage.setText(thi + "%");
                    Subject subject = new Subject();
                    subject.setCC_Percentage(Integer.parseInt(cc));
                    subject.setTH_Percentage(Integer.parseInt(th));
                    subject.setBT_Percentage(Integer.parseInt(bt));
                    subject.setKT_Percentage(Integer.parseInt(kt));
                    subject.setTHI_Percentage(Integer.parseInt(thi));

                    Subject vitri = arrsub.get(spinnerMonhoc.getSelectedItemPosition());
                    subject.setSubjectID(vitri.getSubjectID());
                    subject.setSubjectName(vitri.getSubjectName());
                    subject.setSoTC(vitri.getSoTC());
                    mdatasubject.child(vitri.getSubjectID()).setValue(subject);
                    Toast.makeText(MarkTeacher.this, "Đã cập nhật" + subject.getSubjectID() + " - " + subject.getSubjectName() + " vao list.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void dialogEdititem() {

    }

    private void init() {
        tvdanhsachSV = findViewById(R.id.tvdanhsachSV);
        lvDanhsachSV = findViewById(R.id.lvDanhsachSV);
        spinnerMonhoc = (Spinner) findViewById(R.id.spinnerMonhoc);
        spinnerClass = (Spinner) findViewById(R.id.spinnerClass);

        imgEdit_Percentage = findViewById(R.id.imgEdit_Percentage);

        tvteacherCC_Percentage = (TextView) findViewById(R.id.tvteacherCC_Percentage);
        tvteacherTH_Percentage = (TextView) findViewById(R.id.tvteacherTH_Percentage);
        tvteacherBT_Percentage = (TextView) findViewById(R.id.tvteacherBT_Percentage);
        tvteacherKT_Percentage = (TextView) findViewById(R.id.tvteacherKT_Percentage);
        tvteacherTHI_Percentage = (TextView) findViewById(R.id.tvteacherTHI_Percentage);
    }
}
