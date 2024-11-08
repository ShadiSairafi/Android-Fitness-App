package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<TrainingClasses> classList;
    private OnItemClickListener onItemClickListener;

    // Constructor
    public CardAdapter(List<TrainingClasses> classList) {
        this.classList = classList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        TrainingClasses currentClass = classList.get(position);
        holder.className.setText(currentClass.getName());
        holder.classTime.setText(currentClass.getTime() + " minutes");

        // Format class details to include name and time for each activity
        StringBuilder detailsBuilder = new StringBuilder();
        for (classess exercise : currentClass.getClasses()) {
            detailsBuilder.append(exercise.getClasses()).append(" - ").append(exercise.getTime()).append(" mins\n");
        }
        holder.classDetails.setText(detailsBuilder.toString().trim());

        // Set image for the class
        holder.classImage.setImageResource(currentClass.getImageResId());
    }


    @Override
    public int getItemCount() {
        return classList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView className;
        TextView classTime;
        TextView classDetails;
        ImageView classImage;  // Added ImageView for the class image

        public CardViewHolder(View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.cardClassName);
            classTime = itemView.findViewById(R.id.cardClassTime);
            classDetails = itemView.findViewById(R.id.cardClassDetails);
            classImage = itemView.findViewById(R.id.cardClassImage);  // Initialize the ImageView

            // Set item click listener
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
