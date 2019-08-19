package com.example.trackyourtrek.System.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Walker;

import java.util.List;
import java.util.Random;

public class WalkersAdapter extends RecyclerView.Adapter<WalkersAdapter.WalkerViewHolder> {
    /**
     * You need to define a View Holder, which will hold the data in its view for
     * a specific person.
     */
    public static class WalkerViewHolder extends RecyclerView.ViewHolder {
        public TextView lblName;
        public TextView lblEmail;
        public TextView lblUsername;
        public ImageView imgAvatar;
        public Walker walker;

        public WalkerViewHolder(@NonNull View view) {
            super(view);

            // Get references to commonly used Views in the layout.
            lblName = view.findViewById(R.id.lblName);
            lblUsername = view.findViewById(R.id.lblUsername);
            lblEmail=view.findViewById(R.id.lblEmail);
            imgAvatar = view.findViewById(R.id.imgAvatar);
        }

        public void setWalker(Walker walker) {
            this.walker = walker;

            lblName.setText("Name: "+walker.getfName() +" " +walker.getlName());
            lblUsername.setText("Username: " + walker.getUsername()); // force conversion to string
            lblEmail.setText(walker.getEmail());
            // Set image. Might not exist depending on the layout used. So check for null
            // values before setting.
            if(imgAvatar != null) {
                // Set image.
                Random random = new Random();
                switch(random.nextInt(8)) {
                    case 0:
                        imgAvatar.setImageResource(R.drawable.avatar_01);
                        break;
                    case 1:
                        imgAvatar.setImageResource(R.drawable.avatar_02);
                        break;
                    case 2:
                        imgAvatar.setImageResource(R.drawable.avatar_03);
                        break;
                    case 3:
                        imgAvatar.setImageResource(R.drawable.avatar_04);
                        break;
                    case 4:
                        imgAvatar.setImageResource(R.drawable.avatar_05);
                        break;
                    case 5:
                        imgAvatar.setImageResource(R.drawable.avatar_06);
                        break;
                    case 6:
                        imgAvatar.setImageResource(R.drawable.avatar_07);
                        break;
                    case 7:
                        imgAvatar.setImageResource(R.drawable.avatar_08);
                        break;
                    default:
                        imgAvatar.setImageResource(R.drawable.avatar_09);
                        break;
                }
            }

        }
    }

    // The collection of data that this adapter is currently displaying.
    private final List<Walker> walkers;

    public WalkersAdapter(List<Walker> walkers) {
        this.walkers = walkers;
    }

    @NonNull
    @Override
    public WalkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method is called by Android when it needs a brand new View to display
        // a single person. The ViewHolder will hold a reference to this newly created View.

        // Inflate (create) the UI scenegraph from the layout xml resource.
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recyclerview_person,
                        //.inflate(R.layout.recyclerview_person_simple_details,
                        parent, false);

        // Put it into a View Holder object and return this.
        WalkerViewHolder pvh = new WalkerViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull WalkerViewHolder holder, int position) {
        // Given the View Holder and an index to the View to be used to display it,
        // fill the data item's values into the view.

        // Get the data to be displayed
        Walker person = walkers.get(position);

        // Fill the data from person into the view.
        holder.setWalker(person);
    }


    @Override
    public int getItemCount() {
        return walkers.size();
    }

    public void add(Walker person) {
        // Add the person and notify the view of changes.
        walkers.add(person);
        // In this case, specify WHICH person changed.
        notifyItemChanged(walkers.size() - 1);
    }

    public void remove(int position) {
        // Remove the person.
        walkers.remove(position);
        // Notify view of underlying data changed.
        notifyItemRemoved(position);
    }
}

