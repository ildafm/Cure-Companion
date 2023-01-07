package com.if5a.cure_companion.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5a.cure_companion.R;
import com.if5a.cure_companion.activities.MainDoctorActivity;
import com.if5a.cure_companion.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class DoctorScheduleAdapter extends RecyclerView.Adapter<DoctorScheduleAdapter.ViewHolder> {
    private List<Schedule> data = new ArrayList<>();

    public void setData(List<Schedule> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int sc = holder.getAdapterPosition();
        Schedule schedule = data.get(sc);
        holder.tvPatientName.setText(schedule.getPatient_id() + "(patient_id)");
        holder.tvDate.setText(schedule.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPatientName, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPatientName = itemView.findViewById(R.id.tv_patient_name);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
