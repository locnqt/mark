package lonqt.example.com.marktable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by locnq on 4/13/2018.
 */

public class SemesterMarkAdapter extends ArrayAdapter<Class> {

    private Context context;
    private int resource;
    private List<Class> objects;

    private ArrayList<String> arrayhocky;
    private ArrayList<Integer> arraynamhoc;

    public SemesterMarkAdapter(@NonNull Context context, int resource, @NonNull List<Class> objects) {
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

        Spinner spinnerhocky = (Spinner) convertView.findViewById(R.id.spinnerHocky);
        Spinner spinnernamhoc = (Spinner) convertView.findViewById(R.id.spinnerNamhoc);

        TextView tvdiemtbhe10 = (TextView) convertView.findViewById(R.id.tvdiemtbhe10);
        TextView tvdiemtbhe4 = (TextView) convertView.findViewById(R.id.tvdiemtbhe4);
        TextView tvphanloaihocluc = (TextView) convertView.findViewById(R.id.tvphanloaihocluc);
        TextView tvphanloaitenluyen = (TextView) convertView.findViewById(R.id.tvphanloaitenluyen);
        TextView tvdiemtbtichluyhe10 = (TextView) convertView.findViewById(R.id.tvdiemtbtichluyhe10);
        TextView tvdiemtbtichluyhe4 = (TextView) convertView.findViewById(R.id.tvdiemtbtichluyhe4);
        TextView tvsotinchitichluy = (TextView) convertView.findViewById(R.id.tvsotinchitichluy);

        Class Class = objects.get(position);

        return convertView;
    }
}
