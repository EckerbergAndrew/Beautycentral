package org.wit.example.beautycentral.activities
//REAL PROJECT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.example.beautycentral.R

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker on iheartnails and move the camera
        val IHN = LatLng(52.26, -7.114)
        mMap.addMarker(MarkerOptions().position(IHN).title("iHeartNails"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IHN,6.2f))

        val maniCo=LatLng(52.25618,-7.14687)
        mMap.addMarker(MarkerOptions().position(maniCo).title("The Manicure Company"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(maniCo,6.2f))

        val terrisales=LatLng(53.34928,-6.26077)
        mMap.addMarker(MarkerOptions().position(terrisales).title("Terrisales Salon Supplies"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(terrisales,6.2f))

        val scratchnails=LatLng(54.85242,-5.81533)
        mMap.addMarker(MarkerOptions().position(scratchnails).title("Scratch Nails Supplies"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(scratchnails,6.2f))

        val salonwholesalers=LatLng(52.87352,-8.97981)
        mMap.addMarker(MarkerOptions().position(salonwholesalers).title("The Salon Wholesalers"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(salonwholesalers,6.2f))
    }
}