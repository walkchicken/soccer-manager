package com.example.playermanager.MainScreens;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.playermanager.Models.MD_Event;
import com.example.playermanager.R;
import com.example.playermanager.SQLite.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreateEventFragment extends DialogFragment implements DialogInterface.OnDismissListener {
    EditText eventName, eventDuration, eventCapacity;
    TextView selectedDate;
    DatabaseHelper dh;
    Calendar myCalendar = Calendar.getInstance();
    String eventNameStr, eventDurationStr, eventCapacityStr, eventSelectedDateStr;
    Button saveEvent;
    List<MD_Event> eventList = new ArrayList<>();
    String userType, hallName;
    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_event, container,
                false);
        getDialog().setTitle("Create Event");
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dh = new DatabaseHelper(getActivity());
        initViews();
        init();
    }

    public void initViews(){
        eventName = (EditText) getView().findViewById(R.id.eventName);
        eventDuration = (EditText) getView().findViewById(R.id.eventDuration);
        eventCapacity = (EditText) getView().findViewById(R.id.eventCapacity);
        selectedDate = (TextView) getView().findViewById(R.id.selectedDate);
        saveEvent = (Button) getView().findViewById(R.id.saveEvent);

        // Show soft keyboard automatically and request focus to field
        eventDuration.requestFocus();
        eventCapacity.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void init(){
        final MD_Event eventListModel = new MD_Event();
        userType = getArguments().getString("userType");
        position = getArguments().getInt("position");
        hallName = getArguments().getString("hallName");
        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });

        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventNameStr = eventName.getText().toString();
                eventDurationStr = eventDuration.getText().toString();
                eventCapacityStr = eventCapacity.getText().toString();
                eventSelectedDateStr = selectedDate.getText().toString();

                if((eventNameStr.isEmpty() || eventNameStr.equals(null) || eventNameStr.equals("")) ||
                        (eventCapacityStr.isEmpty() || eventCapacityStr.equals(null)|| eventCapacityStr.equals("")) ||
                        (eventDurationStr.isEmpty() || eventDurationStr.equals(null) || eventDurationStr.equals("")) ||
                        (eventSelectedDateStr.isEmpty() || eventSelectedDateStr.equals(null) || eventSelectedDateStr.equals("")))
                {
                    Toast.makeText(getActivity(), "Please fill all details", Toast.LENGTH_LONG).show();

                } else {
                    eventListModel.setName(eventNameStr);
                    eventListModel.setTimeInMins(Integer.parseInt(eventDurationStr));
                    eventListModel.setTotalCapacity(Integer.parseInt(eventCapacityStr));
                    eventListModel.setDate(eventSelectedDateStr);
                    boolean isinserted = dh.insertEventData(eventListModel.getName(),eventListModel.getDate(),String.valueOf(eventListModel.getTimeInMins()),String.valueOf(eventListModel.getTotalCapacity()),hallName,String.valueOf(position));
                    if(isinserted) {
                        Toast.makeText(getActivity(),"Event Created Successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), HallDetailActivity.class);
                        intent.putExtra("user", userType);
                        intent.putExtra("position", position);
                        intent.putExtra("hallName", hallName);
                        getDialog().dismiss();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Event not Created",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public interface DialogInterface{}

    public void selectDate() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };

        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateDate() {
        String myFormat = "MM/dd/yyyy"; //date format
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        selectedDate.setText(sdf.format(myCalendar.getTime()));
    }

}
