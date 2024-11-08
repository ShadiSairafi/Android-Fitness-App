package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ListView listView;
    private RecyclerView recyclerView;
    private List<TrainingClasses> classesList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.textview1);
        Button listviewB = findViewById(R.id.listviewB);
        Button cardviewB = findViewById(R.id.cardsviewB);
        Button backB = findViewById(R.id.BackB);

        backB.setOnClickListener(e ->{
            finish();
        });
        // Get the username from the intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");
        textView.setText(username);

        //dummy data for the fitness classes
        classesList = new ArrayList<>();
        classesList.add(new TrainingClasses(R.mipmap.body_building_foreground, "Bodybuilding Basics", 75, new classess[]{
                new classess("Bicep Curls", 25),
                new classess("Bench Press", 25),
                new classess("Lat Pulldown", 25)
        }));

        classesList.add(new TrainingClasses(R.mipmap.powerlifting_foreground, "Powerlifting", 90, new classess[]{
                new classess("Squat Technique", 30),
                new classess("Deadlift Variations", 30),
                new classess("Bench Press Form", 30)
        }));

        classesList.add(new TrainingClasses(R.mipmap.crossfit_foreground, "CrossFit", 60, new classess[]{
                new classess("Burpees", 20),
                new classess("Kettlebell Swings", 20),
                new classess("Box Jumps", 20)
        }));

        classesList.add(new TrainingClasses(R.mipmap.strongman_foreground, "Strongman Training", 80, new classess[]{
                new classess("Atlas Stones", 27),
                new classess("Farmer's Walk", 26),
                new classess("Tire Flipping", 27)
        }));

        classesList.add(new TrainingClasses(R.mipmap.sculptandtone_foreground, "Sculpt and Tone", 50, new classess[]{
                new classess("Lunges", 17),
                new classess("Plank Variations", 16),
                new classess("Resistance Band Workouts", 17)
        }));

        classesList.add(new TrainingClasses(R.mipmap.kickboxing_foreground, "Kickboxing Strength", 45, new classess[]{
                new classess("Jab-Cross Combos", 15),
                new classess("Roundhouse Kicks", 15),
                new classess("Speed Bag Drills", 15)
        }));

        backB.setOnClickListener(e ->{
            finish();
                });
        // Initialize ListView (for List View display)
        listView = findViewById(R.id.listview);
        List<String> displayList = new ArrayList<>();
        for (TrainingClasses trainingClass : classesList) {
            displayList.add(trainingClass.getName() + " - " + trainingClass.getTime() + " minutes");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_custom_layout, displayList);
        listView.setAdapter(adapter);

        // Initialize RecyclerView (for CardView display)
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardAdapter cardAdapter = new CardAdapter(classesList);
        recyclerView.setAdapter(cardAdapter);

        // ListView Button Click Listener
        listviewB.setOnClickListener(v -> {
            // Hide RecyclerView and show ListView
            recyclerView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        });

        // CardView Button Click Listener
        cardviewB.setOnClickListener(v -> {
            // Hide ListView and show RecyclerView
            listView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        });

        // Set ListView Item Click Listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TrainingClasses selectedClass = classesList.get(position);
            Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);
            intent1.putExtra("className", selectedClass.getName());
            intent1.putExtra("classTime", selectedClass.getTime());

            String[] classDetails = new String[selectedClass.getClasses().length];
            for (int i = 0; i < selectedClass.getClasses().length; i++) {
                classDetails[i] = selectedClass.getClasses()[i].getClasses() + " - " + selectedClass.getClasses()[i].getTime() + " mins";
            }
            intent1.putExtra("classDetails", classDetails);
            startActivity(intent1);
        });



        // Set RecyclerView Item Click Listener
        cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TrainingClasses selectedClass = classesList.get(position);

                // Create an Intent to pass data to MainActivity3
                Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);
                intent1.putExtra("className", selectedClass.getName());
                intent1.putExtra("classTime", selectedClass.getTime());

                String[] classDetails = new String[selectedClass.getClasses().length];
                for (int i = 0; i < selectedClass.getClasses().length; i++) {
                    classDetails[i] = selectedClass.getClasses()[i].getClasses() + " - " + selectedClass.getClasses()[i].getTime() + " mins";
                }
                intent1.putExtra("classDetails", classDetails);

                startActivity(intent1);
            }
        });

    }
}
