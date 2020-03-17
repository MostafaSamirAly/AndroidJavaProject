package com.example.mishwary.ui.addactivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mishwary.Models.Trip;
import com.example.mishwary.PlaceAutoSuggestAdapter;
import com.example.mishwary.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, AddContract.AddView {
    Button btnDatePicker, btnTimePicker, btnAdd;
    TextView txtDate, txtTime;
    EditText titleTxt;
    AutoCompleteTextView startTxt, endTxt;
    final Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;
    private AddPresenter addPresenter;
    private String userID;
    private Spinner repeatSpinner, descSpinner;
    private ArrayAdapter<CharSequence> repeatAdapter, descAdapter;
    PlacesClient placesClient;
    String TAG = "AddActivity", startPoint = " ", endPoint = " ",tripId;
    Geocoder gc2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent intent = getIntent();
        userID = intent.getStringExtra("id");
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        btnAdd = (Button) findViewById(R.id.btn_add);
        txtDate = (TextView) findViewById(R.id.in_date);
        txtTime = (TextView) findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        titleTxt = findViewById(R.id.trip_title);
        startTxt = findViewById(R.id.trip_start_point);
        endTxt = findViewById(R.id.trip_end_point);
        gc2 = new Geocoder(this);
      /*  String apiKey = "AIzaSyA6iKHcXcfCYH8jXglkFDdKxxdjElaKK2U";
        if(!Places.isInitialized())
        {
            Places.initialize(getApplicationContext(), apiKey);
        }
        placesClient = Places.createClient(this);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                startPoint = place.getName();
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        AutocompleteSupportFragment autocompleteFragment2 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment2);

        autocompleteFragment2.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                endPoint = place.getName();
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
*/


        startTxt.setAdapter(new PlaceAutoSuggestAdapter(AddActivity.this, android.R.layout.simple_list_item_1));

        startTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* Log.d("Address : ", startTxt.getText().toString());
                LatLng latLng = getLatLngFromAddress(startTxt.getText().toString());
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ", "" + address.getAddressLine(0));
                        Log.d("Phone : ", "" + address.getPhone());
                        Log.d("Pin Code : ", "" + address.getPostalCode());
                        Log.d("Feature : ", "" + address.getFeatureName());
                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }*/

            }
        });


        endTxt.setAdapter(new PlaceAutoSuggestAdapter(AddActivity.this, android.R.layout.simple_list_item_1));

        endTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  Log.d("Address : ", endTxt.getText().toString());
                LatLng latLng = getLatLngFromAddress(endTxt.getText().toString());
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ", "" + address.getAddressLine(0));
                        Log.d("Phone : ", "" + address.getPhone());
                        Log.d("Pin Code : ", "" + address.getPostalCode());
                        Log.d("Feature : ", "" + address.getFeatureName());
                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }*/

            }
        });

        repeatSpinner = findViewById(R.id.spinner_repeat);
        descSpinner = findViewById(R.id.spinner_desc);
        btnAdd.setOnClickListener(this);
        repeatAdapter = ArrayAdapter.createFromResource(this, R.array.repeatSpinner, R.layout.support_simple_spinner_dropdown_item);
        repeatAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        repeatSpinner.setAdapter(repeatAdapter);
        descAdapter = ArrayAdapter.createFromResource(this, R.array.descSpinner, R.layout.support_simple_spinner_dropdown_item);
        descAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        descSpinner.setAdapter(descAdapter);
        addPresenter = new AddPresenter(this);
    }

    private LatLng getLatLngFromAddress(String address) {

        Geocoder geocoder = new Geocoder(AddActivity.this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if (addressList != null) {
                Address singleaddress = addressList.get(0);
                LatLng latLng = new LatLng(singleaddress.getLatitude(), singleaddress.getLongitude());
                return latLng;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private Address getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(AddActivity.this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if (addresses != null) {
                Address address = addresses.get(0);
                return address;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            c.set(Calendar.YEAR, year);
                            c.set(Calendar.MONTH, monthOfYear);
                            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            c.set(Calendar.MINUTE, minute);
                            c.set(Calendar.SECOND, 0);
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == btnAdd) {
            if (validateInputs()) {
                addTriptoFireBase();
                startAlarm(c);
                btnAdd.setEnabled(false);
                //finish();
            }

        }
    }

    private boolean validateInputs() {
        boolean flag = true;
        if (titleTxt.getText().toString().trim().isEmpty()) {
            flag = false;
            titleTxt.setError("Enter Title");
            titleTxt.requestFocus();
        }
        if (startTxt.toString().trim().isEmpty()) {
            flag = false;
            startTxt.setError("Enter Start Point");
            //Toast.makeText(this,"Please Enter Start Point",Toast.LENGTH_LONG).show();
            startTxt.requestFocus();
        }

        if (endTxt.toString().trim().isEmpty()) {
            flag = false;
            //Toast.makeText(this,"Please Enter the Destination",Toast.LENGTH_LONG).show();
            endTxt.setError("Enter Destination");
            endTxt.requestFocus();
        }
        if (txtDate.getText().toString().trim().isEmpty()) {
            flag = false;
            Toast.makeText(this, "Please Select Date", Toast.LENGTH_LONG).show();
        }
        if (txtTime.getText().toString().trim().isEmpty()) {
            flag = false;
            Toast.makeText(this, "Please Select Time", Toast.LENGTH_LONG).show();
        }

       /* try {

            List<Address> addresses= null; // get the found Address Objects
            addresses = gc2.getFromLocationName(startTxt.toString(), 5);
            if( addresses.size()==0){
                    Toast.makeText(this,"Please Enter  valid Start Point",Toast.LENGTH_LONG).show();
                    startTxt.requestFocus();
                    flag = false;

                }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<Address> addresses2= null; // get the found Address Objects
            addresses2 = gc2.getFromLocationName(endTxt.toString(), 5);
            if( addresses2.size()==0)
            {
                Toast.makeText(this,"Please Enter valid End Point",Toast.LENGTH_LONG).show();
                endTxt.requestFocus();
                flag = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return flag;
    }

    private void addTriptoFireBase() {
        Trip trip = new Trip();
        trip.setUserId(userID);
        trip.setTripName(titleTxt.getText().toString());
        trip.setStartPoint(startTxt.getText().toString());
        trip.setDestination(endTxt.getText().toString());
        trip.setTime(txtTime.getText().toString());
        trip.setDate(txtDate.getText().toString());
        trip.setRepeat(repeatSpinner.getSelectedItem().toString());
        trip.setDescription(descSpinner.getSelectedItem().toString());
        addPresenter.addTrip(trip);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("tripId",tripId);
        intent.putExtra("userId", userID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, tripId.hashCode(), intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        if (repeatSpinner.getSelectedItem().toString().equals("No Repeat")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            }
        } else if (repeatSpinner.getSelectedItem().toString().equals("daily")) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
        } else if (repeatSpinner.getSelectedItem().toString().equals("Weekly")) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);
        } else {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY*30, pendingIntent);
        }

    }

    @Override
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    @Override
    public void gotoHome() {
        finish();
    }
}
