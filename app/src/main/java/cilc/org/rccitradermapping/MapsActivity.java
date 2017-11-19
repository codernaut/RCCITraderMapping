package cilc.org.rccitradermapping;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.GeoPoint;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Gul", "School", "Office"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String locationString = "";
                if (position == 0)
                    locationString = "GulzareQuaid Rawalpindi";
                else if (position == 1)
                    locationString = "ufone tower islamabad";
                else if (position == 2)
                    locationString = "faisalabad";

                Geocoder coder = new Geocoder(mapFragment.getContext());

                List<Address> address = null;
                GeoPoint p1 = null;
                try {
                    address = coder.getFromLocationName(locationString, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
                //  p1 = new GeoPoint(location.getLatitude() * 1E6,
                //          location.getLongitude() * 1E6);
                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                //mMap.addMarker(new MarkerOptions().title("Marker in "+locationString));
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in " + locationString));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17.0f));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        Geocoder coder = new Geocoder(this);

        List<Address> address = null;
        GeoPoint p1 = null;
        try {
            address = coder.getFromLocationName("Gulzarequaid Rawalpindi", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address location = address.get(0);
        location.getLatitude();
        location.getLongitude();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        //  p1 = new GeoPoint(location.getLatitude() * 1E6,
        //          location.getLongitude() * 1E6);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
        //mMap.addMarker(new MarkerOptions().title("Marker in Gularequaid"));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Gulzairequaid"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17.0f));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
