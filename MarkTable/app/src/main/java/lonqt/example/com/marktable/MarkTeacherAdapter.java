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
 * Created by locnq on 5/2/2018.
 */

public class MarkTeacherAdapter extends ArrayAdapter<Mark> {
    private MarkTeacher context;
    private int resource;
    private ArrayList<Mark> objects;

    public MarkTeacherAdapter(@NonNull MarkTeacher context, int resource, @NonNull ArrayList<Mark> objects) {
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

        TextView tvteacherCC = convertView.findViewById(R.id.tvteacherCC);
        TextView tvteacherTH = convertView.findViewById(R.id.tvteacherTH);
        TextView tvteacherBT = convertView.findViewById(R.id.tvteacherBT);
        TextView tvteacherKT = convertView.findViewById(R.id.tvteacherKT);
        TextView tvteacherTHI = convertView.findViewById(R.id.tvteacherTHI);
        TextView tvmssv = convertView.findViewById(R.id.tvmssv);
        TextView tvTenSV = convertView.findViewById(R.id.tvTenSV);
        ImageView imgEditmark = convertView.findViewById(R.id.imgEditmark);

        final Mark mark = objects.get(position);

        tvteacherCC.setText(String.valueOf(mark.getCC()));
        tvteacherTH.setText(String.valueOf(mark.getTH()));
        tvteacherBT.setText(String.valueOf(mark.getBT()));
        tvteacherKT.setText(String.valueOf(mark.getKT()));
        tvteacherTHI.setText(String.valueOf(mark.getTHI()));
        tvmssv.setText(mark.getStudentid());
        tvTenSV.setText(String.valueOf(mark.getStudentName()));

        imgEditmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("id nhan vien", "Get " + nhanVien.getId());
//                context.dialogEdititem(nhanVien.getHotenNV(), nhanVien.getMaNV(), nhanVien.isGioiTinh(), nhanVien.getId());
            }
        });
        return convertView;
    }
}
