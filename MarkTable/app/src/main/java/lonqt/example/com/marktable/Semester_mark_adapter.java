package lonqt.example.com.marktable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by locnq on 5/1/2018.
 */

public class Semester_mark_adapter extends ArrayAdapter<SemesterMark> {
    private Context context;
    private int resource;
    private ArrayList<SemesterMark> objects;

    public Semester_mark_adapter(@NonNull Context context, int resource, @NonNull ArrayList<SemesterMark> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvbangdiemchitietse = convertView.findViewById(R.id.tvbangdiemchitietse);
        TextView tvdiemtbhe4se = convertView.findViewById(R.id.tvdiemtbhe4se);
        TextView tvdiemtbhe10se = convertView.findViewById(R.id.tvdiemtbhe10se);
        TextView tvdiemtbtichluyhe4se = convertView.findViewById(R.id.tvdiemtbtichluyhe4se);
        TextView tvdiemtbtichluyhe10se = convertView.findViewById(R.id.tvdiemtbtichluyhe10se);
        TextView tvphanloaihoclucse = convertView.findViewById(R.id.tvphanloaihoclucse);
        TextView tvphanloaitenluyense = convertView.findViewById(R.id.tvphanloaitenluyense);
        TextView tvsotinchitichluyse = convertView.findViewById(R.id.tvsotinchitichluyse);

        final SemesterMark semesterMark = objects.get(position);

        tvbangdiemchitietse.setText("Bảng điễm chi tiết "+semesterMark.getSemester()+" năm "+semesterMark.getYear());
        tvdiemtbhe4se.setText(String.valueOf(semesterMark.getTBhe4()));
        tvdiemtbhe10se.setText(String.valueOf(semesterMark.getTBhe10()));
        tvdiemtbtichluyhe4se.setText(String.valueOf(semesterMark.getTBtichluyhe4()));
        tvdiemtbtichluyhe10se.setText(String.valueOf(semesterMark.getTBtichluyhe10()));
        tvphanloaihoclucse.setText(semesterMark.getPlhocluc());
        tvphanloaitenluyense.setText(semesterMark.getPlrenluyen());
        tvsotinchitichluyse.setText(String.valueOf(semesterMark.getSotinchitichluy()));

        return convertView;
    }
}
