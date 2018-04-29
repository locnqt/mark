package lonqt.example.com.marktable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    View view;
    Spinner spinnerhocky, spinnernamhoc;
//    ArrayList<String> arrayhocky;
//    ArrayList<String> arraynamhoc;

    TextView tvdiemtbhe10, tvdiemtbhe4, tvphanloaihocluc, tvphanloaitenluyen, tvdiemtbtichluyhe10, tvdiemtbtichluyhe4, tvsotinchitichluy;

    ExpandableListView expandableListView;
    List<Mark> listMonhoc;
    HashMap<Mark, List<Mark>> listchitietmonhoc;
    Expandable_monhoc expandable_monhoc;
    SemesterMarkAdapter semesterMarkAdapter;

    DatabaseReference database;

    //    boolean flag = false;
    String namhoc;
    String hocky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        database = FirebaseDatabase.getInstance().getReference();
        getAll();
//        setSpinnernamhoc();
//        setSpinnerhocky();
//
        addControl();
//        expandable_monhoc = new Expandable_monhoc(MainActivity.this, listMonhoc, listchitietmonhoc);
//        expandableListView.setAdapter(expandable_monhoc);
    }

    private void addControl() {
        spinnernamhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                namhoc = spinnernamhoc.getSelectedItem().toString();
                database.child("Mark").child("N14DCAT069").child(namhoc).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final ArrayList<String> arrayhocky = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String hocky = ds.getKey();
                            Log.d("reset hocky ", hocky);
                            arrayhocky.add(hocky);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_center_item, arrayhocky);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerhocky.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                spinnerhocky.setSelection(0);
                hocky = spinnerhocky.getSelectedItem().toString();
//                Log.d("hoc ky ", "get "+hocky);
                getData();
                expandable_monhoc = new Expandable_monhoc(MainActivity.this, listMonhoc, listchitietmonhoc);
                expandableListView.setAdapter(expandable_monhoc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerhocky.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hocky = spinnerhocky.getSelectedItem().toString();
                getData();
                expandable_monhoc = new Expandable_monhoc(MainActivity.this, listMonhoc, listchitietmonhoc);
                expandableListView.setAdapter(expandable_monhoc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAll() {
        final DatabaseReference mdata = database.child("Mark").child("N14DCAT069");
        HashMap<String, HashMap<String, Object>> value;
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> arraynamhoc = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nam = ds.getKey();
                    Log.d("year ", nam);
                    arraynamhoc.add(nam);
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_center_item, arraynamhoc);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnernamhoc.setAdapter(arrayAdapter);
                namhoc = spinnernamhoc.getSelectedItem().toString();
                mdata.child(namhoc).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final ArrayList<String> arrayhocky = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String hocky = ds.getKey();
                            Log.d("hocky ", hocky);
                            arrayhocky.add(hocky);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_center_item, arrayhocky);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerhocky.setAdapter(arrayAdapter);
                        hocky = spinnerhocky.getSelectedItem().toString();
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

    private void getData() {
        listMonhoc = new ArrayList<>();
        listchitietmonhoc = new HashMap<>();
        namhoc = spinnernamhoc.getSelectedItem().toString();
        hocky = spinnerhocky.getSelectedItem().toString();
        database.child("Mark").child("N14DCAT069").child(namhoc).child(hocky).child("Tongket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SemesterMark semesterMark = dataSnapshot.getValue(SemesterMark.class);

                Log.d("Get Data From Firebase", "Get " + semesterMark);
                tvdiemtbhe4.setText(String.valueOf(semesterMark.getTBhe4()));
                tvdiemtbhe10.setText(String.valueOf(semesterMark.getTBhe10()));
                tvdiemtbtichluyhe4.setText(String.valueOf(semesterMark.getTBtichluyhe4()));
                tvdiemtbtichluyhe10.setText(String.valueOf(semesterMark.getTBtichluyhe10()));
                tvphanloaihocluc.setText(semesterMark.getPlhocluc());
                tvphanloaitenluyen.setText(semesterMark.getPlrenluyen());
                tvsotinchitichluy.setText(String.valueOf(semesterMark.getSotinchotichluy()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.child("Mark").child("N14DCAT069").child(namhoc).child(hocky).child("Subject").addValueEventListener(new ValueEventListener() {
            int counter = 0;
            List<Mark> childItem;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMonhoc.clear();
                listchitietmonhoc.clear();
//                childItem = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Mark mark = ds.getValue(Mark.class);
                    Log.d("Get Data From Firebase", "Get " + mark);
                    childItem = new ArrayList<>();
                    childItem.add(mark);
                    listMonhoc.add(mark);
                    listchitietmonhoc.put(listMonhoc.get(counter), childItem);
                    counter++;
                }
                expandable_monhoc.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        spinnerhocky = (Spinner) findViewById(R.id.spinnerHocky);
        spinnernamhoc = (Spinner) findViewById(R.id.spinnerNamhoc);

        tvdiemtbhe10 = (TextView) findViewById(R.id.tvdiemtbhe10);
        tvdiemtbhe4 = (TextView) findViewById(R.id.tvdiemtbhe4);
        tvphanloaihocluc = (TextView) findViewById(R.id.tvphanloaihocluc);
        tvphanloaitenluyen = (TextView) findViewById(R.id.tvphanloaitenluyen);
        tvdiemtbtichluyhe10 = (TextView) findViewById(R.id.tvdiemtbtichluyhe10);
        tvdiemtbtichluyhe4 = (TextView) findViewById(R.id.tvdiemtbtichluyhe4);
        tvsotinchitichluy = (TextView) findViewById(R.id.tvsotinchitichluy);

        expandableListView = (ExpandableListView) findViewById(R.id.Expandlv);
    }

//    private void setSpinnerhocky() {
//        arrayhocky = new ArrayList<>();
//        arrayhocky.add("Học Kỳ I");
//        arrayhocky.add("Học Kỳ II");
//        arrayhocky.add("Học Kỳ III");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_center_item, arrayhocky);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerhocky.setAdapter(arrayAdapter);
////        namhoc = spinnernamhoc.getSelectedItem().toString();
////        database.child("Mark").child("N14DCAT069").child(namhoc).addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                final ArrayList<String> arrayhocky = new ArrayList<>();
////                for (DataSnapshot ds : dataSnapshot.getChildren()) {
////                    String hocky = ds.getKey();
////                    Log.d("key hocky ", hocky);
////                    arraynamhoc.add(hocky);
////                }
////                spinnerhocky = (Spinner) findViewById(R.id.spinnerHocky);
////                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_center_item, arrayhocky);
////                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                spinnerhocky.setAdapter(arrayAdapter);
////                flag = true;
////                Log.d("flag ", "Get " + flag);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////        addControl();
//    }

//    private void setSpinnernamhoc() {
////        database.child("Mark").child("N14DCAT069").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                final ArrayList<String> arraynamhoc = new ArrayList<>();
////                for (DataSnapshot ds : dataSnapshot.getChildren()) {
////                    String nam = ds.getKey();
////                    Log.d("key year ", nam);
////                    arraynamhoc.add(nam);
////                }
////                spinnernamhoc = (Spinner) findViewById(R.id.spinnerNamhoc);
////                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_center_item, arraynamhoc);
////                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                spinnernamhoc.setAdapter(arrayAdapter);
////                flag = true;
////                Log.d("flag ", "Get " + flag);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////        addControl();
//        arraynamhoc = new ArrayList<>();
//        arraynamhoc.add("2014-2015");
//        arraynamhoc.add("2015-2016");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_center_item, arraynamhoc);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnernamhoc.setAdapter(arrayAdapter);
//    }
}
