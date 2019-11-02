package com.example.buscomida.about;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.buscomida.R;
import java.util.ArrayList;

public class AdapterRecyclerViewAbout extends RecyclerView.Adapter<AdapterRecyclerViewAbout.ViewHolderStudents> {

    private ArrayList<Student> listStudents;

    public AdapterRecyclerViewAbout(ArrayList<Student> listStudents) {
        this.listStudents = listStudents;
    }

    @NonNull
    @Override
    public ViewHolderStudents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_about, parent, false);

        return new ViewHolderStudents(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerViewAbout.ViewHolderStudents holder, int position) {

        holder.textViewName.setText(listStudents.get(position).getName());
        holder.textViewId.setText(listStudents.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }

    public class ViewHolderStudents extends RecyclerView.ViewHolder {
        TextView textViewName, textViewId;

        public ViewHolderStudents(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tv_estudent_name);
            textViewId = itemView.findViewById(R.id.tv_student_id);

        }
    }
}
