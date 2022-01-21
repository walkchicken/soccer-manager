package com.example.playermanager.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.playermanager.Models.MD_Event;
import com.example.playermanager.R;

import java.util.List;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private Context context;
    private List<MD_Event> eventsList;
    private EventsListAdapter.ClickListener clickListner;
    private String user;

    public EventsListAdapter(Context context, List<MD_Event> mdEvents, EventsListAdapter.ClickListener listener, String userType) {
        this.context = context;
        this.eventsList = mdEvents;
        this.clickListner = listener;
        this.user = userType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final int[] result = {0};
        final boolean[] calculated = {false};

        holder.name.setText(eventsList.get(position).getName());
        holder.duration.setText(String.valueOf(eventsList.get(position).getTimeInMins()));
        holder.capacity.setText(String.valueOf(eventsList.get(position).getTotalCapacity()));
        holder.date.setText(eventsList.get(position).getDate());

        if(user.equalsIgnoreCase("Hall")){
            holder.register.setVisibility(View.GONE);
        } else {

            holder.register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                    dialogBuilder.setView(dialogView);

                    final EditText edt = (EditText) dialogView.findViewById(R.id.playerNumbers);

                    dialogBuilder.setTitle("Register For Event");
                    dialogBuilder.setMessage("Number of Players");
                    dialogBuilder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            int playerRegistered = Integer.parseInt(edt.getText().toString());
                            int capacity = eventsList.get(position).getTotalCapacity();
                            result[0] = capacity - playerRegistered;
                            calculated[0] = true;
                            holder.capacity.setText(String.valueOf(result[0]));
                            eventsList.get(position).setTotalCapacity(result[0]);
                            clickListner.onClick(position, eventsList);

                            dialog.dismiss();
                        }
                    });
                    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog b = dialogBuilder.create();
                    b.show();
                }
            });
        }
        //final MD_Event item = eventsList.getEventListInside().get(position);
        /*holder.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int[] val = {0};
                new MaterialDialog.Builder(context)
                        .title("Register For Event")
                        .inputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL)
                        .inputRange(1,item.totalCapacity)
                        .input("Number Of Players", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                //val[0] = Integer.parseInt((String) input);
                            }
                        })
                        .positiveText("Register")
                        /*.onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                //int countPlayers = Integer.parseInt(dialog.getInputEditText().toString());
                                //int remainingCapacityOfPlayers = eventsList.get(position).getTotalCapacity() - countPlayers;
                               // item.totalCapacity = item.totalCapacity - val[0];
                                //clickListner.onClick(remainingCapacityOfPlayers, position);
                            }
                        })
                        .show();
            }
        });
       */

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, duration,capacity,date ;
        Button register;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventNameDisplay);
            duration = itemView.findViewById(R.id.eventDurationDisplay);
            capacity = itemView.findViewById(R.id.eventCapacityDisplay);
            date = itemView.findViewById(R.id.eventDateDisplay);
            register = itemView.findViewById(R.id.registerEventButton);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface ClickListener {

        void onClick(int position, List<MD_Event> eventUpdatedList);
    }
}
