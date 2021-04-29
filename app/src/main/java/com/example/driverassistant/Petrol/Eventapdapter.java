package com.example.driverassistant.Petrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driverassistant.R;

import java.util.List;

public class Eventapdapter extends RecyclerView.Adapter<Eventapdapter.EventHolder>{
    private List<Event> mlistuser;
    private IClickItems iClickItems;

    public void setData(List<Event> mlistuser){
        this.mlistuser = mlistuser;
        notifyDataSetChanged();
    }

    public interface IClickItems{
        void update(Event event);

        void delete(Event event);
    }

    public Eventapdapter(IClickItems iClickItems) {
        this.iClickItems = iClickItems;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petrol_items,parent,false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event event = mlistuser.get(position);
        if(event == null){
            return;
        }

        holder.name.setText(event.getName());
        holder.address.setText(event.getAddress());
        holder.date.setText(event.getDate());
        holder.time.setText(event.getTime());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItems.update(event);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItems.delete(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mlistuser != null) {
            return mlistuser.size();
        }
        return 0;
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private TextView time;
        private TextView date;
        private Button update;
        private Button delete;

        public EventHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvusername);
            address = itemView.findViewById(R.id.tvaddress);
            time = itemView.findViewById(R.id.tvtime);
            date = itemView.findViewById(R.id.tvdate);
            update = itemView.findViewById(R.id.btn_update);
            delete = itemView.findViewById(R.id.btn_delete );
        }
    }
}
