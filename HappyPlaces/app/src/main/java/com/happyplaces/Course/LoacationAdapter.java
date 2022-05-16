package com.happyplaces.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happyplaces.R;

import java.util.ArrayList;

public class LoacationAdapter extends RecyclerView.Adapter<LoacationAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<LocationModal> locationModalArrayList;
    private Context context;

    // creating a constructor for our variables.
    public LoacationAdapter(ArrayList<LocationModal> locationModalArrayList, Context context) {
        this.locationModalArrayList = locationModalArrayList;
        this.context = context;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<LocationModal> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        locationModalArrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoacationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoacationAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        LocationModal modal = locationModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getLocationName());
        holder.courseDescTV.setText(modal.getLocationDescription());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return locationModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView courseNameTV, courseDescTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
        }
    }
}