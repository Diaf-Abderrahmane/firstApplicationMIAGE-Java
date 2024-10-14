package com.example.firstapplicationmiage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> personList;
    private Context context;

    // Constructor
    public PersonAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.nameTextView.setText(person.getName());
        holder.firstNameTextView.setText(person.getFirstName());
        holder.emailTextView.setText(person.getEmail());

        // Set a click listener on the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start PersonDetailActivity with the selected Person data
                Intent intent = new Intent(context, PersonDetailActivity.class);
                intent.putExtra("person", person); // Pass the Person object to the detail activity
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    // ViewHolder class
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, firstNameTextView, emailTextView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            firstNameTextView = itemView.findViewById(R.id.textViewFirstName);
            emailTextView = itemView.findViewById(R.id.textViewEmail);
        }
    }
}

