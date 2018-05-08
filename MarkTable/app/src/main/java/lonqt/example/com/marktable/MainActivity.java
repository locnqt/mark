package lonqt.example.com.marktable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
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
    LinearLayout linearsemester, linearexplist;
    TextView tvnamhoc;
    ListView lvnamhoc;
    ArrayList<SemesterMark> arrSemester;
    Semester_mark_adapter semester_mark_adapter;

    Spinner spinnerhocky, spinnernamhoc;
//    ArrayList<String> arrayhocky;
//    ArrayList<String> arraynamhoc;
    ImageView imgmn;

    TextView tvdiemtbhe10, tvdiemtbhe4, tvphanloaihocluc, tvphanloaitenluyen, tvdiemtbtichluyhe10, tvdiemtbtichluyhe4, tvsotinchitichluy, tvbangdiemchitiet;

    ExpandableListView expandableListView;
    List<Mark> listMonhoc;
    HashMap<Mark, List<Mark>> listchitietmonhoc;
    Expandable_monhoc expandable_monhoc;
    SemesterMarkAdapter semesterMarkAdapter;

    DatabaseReference database;
    DatabaseReference mdata;

    //    boolean flag = false;
    String namhoc;
    String hocky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        database = FirebaseDatabase.getInstance().getReference();
        mdata = database.child("TB_MARK").child("N14DCAT069");

        imgmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupmenu();
            }
        });

        getdataspinner();
//        setSpinnernamhoc();
//        setSpinnerhocky();
//
        addControl();
//        expandable_monhoc = new Expandable_monhoc(MainActivity.this, listMonhoc, listchitietmonhoc);
//        expandableListView.setAdapter(expandable_monhoc);
    }
    private void showPopupmenu(){
        PopupMenu popupMenu = new PopupMenu(this,imgmn);
        popupMenu.getMenuInflater().inflate(R.menu.mark_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuhocky:
                        spinnernamhoc.setVisibility(View.VISIBLE);
                        spinnerhocky.setVisibility(View.VISIBLE);
                        linearsemester.setVisibility(View.VISIBLE);
                        linearexplist.setVisibility(View.VISIBLE);
                        tvnamhoc.setVisibility(View.GONE);
                        lvnamhoc.setVisibility(View.GONE);
                        getdataspinner();
                        addControl();
                        break;
                    case R.id.menunamhoc:
                        spinnernamhoc.setVisibility(View.GONE);
                        spinnerhocky.setVisibility(View.GONE);
                        linearsemester.setVisibility(View.GONE);
                        linearexplist.setVisibility(View.GONE);
                        tvnamhoc.setVisibility(View.VISIBLE);
                        lvnamhoc.setVisibility(View.VISIBLE);
                        arrSemester = new ArrayList<>();

                        semester_mark_adapter = new Semester_mark_adapter(MainActivity.this, R.layout.mark_semester_item, arrSemester);
                        lvnamhoc.setAdapter(semester_mark_adapter);
                        getDataNamhoc(semester_mark_adapter);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void addControl() {
        spinnernamhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                namhoc = spinnernamhoc.getSelectedItem().toString();
                mdata.child(namhoc).addValueEventListener(new ValueEventListener() {
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

    public void getdataspinner() {
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
        tvbangdiemchitiet.setText("Bảng điễm chi tiết "+hocky+" năm "+namhoc);
        mdata.child(namhoc).child(hocky).child("Tongket").addValueEventListener(new ValueEventListener() {
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
                tvsotinchitichluy.setText(String.valueOf(semesterMark.getSotinchitichluy()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mdata.child(namhoc).child(hocky).child("Subject").addValueEventListener(new ValueEventListener() {
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

    private void getDataNamhoc(final Semester_mark_adapter adapter){
//        String year, semester;
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot y : dataSnapshot.getChildren()) {
                    final String year = y.getKey();
                    Log.d("year ", year);
                    mdata.child(year).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot se : dataSnapshot.getChildren()){
                                final String semester = se.getKey();
                                Log.d("Semester ", semester);
                                mdata.child(year).child(semester).child("Tongket").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        SemesterMark semesterMark = dataSnapshot.getValue(SemesterMark.class);
                                        Log.d("Get Data From Firebase", "Get " + semesterMark);
                                        adapter.add(semesterMark);
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void init() {
        tvnamhoc = findViewById(R.id.tvnamhoc);
        lvnamhoc = findViewById(R.id.lvnamhoc);
        linearsemester = findViewById(R.id.linearsemester);
        linearexplist = findViewById(R.id.linearexplist);
        spinnerhocky = (Spinner) findViewById(R.id.spinnerHocky);
        spinnernamhoc = (Spinner) findViewById(R.id.spinnerNamhoc);
        imgmn = (ImageView) findViewById(R.id.imgmn);

        tvbangdiemchitiet = (TextView) findViewById(R.id.tvbangdiemchitiet);

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
