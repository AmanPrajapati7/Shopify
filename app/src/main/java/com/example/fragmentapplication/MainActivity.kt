package com.example.fragmentapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.fragment_checkout.*
import android.content.Intent
import android.view.View


val products = arrayOf<Product>(
    Product(
        1, "Pixel3a", 45000F, R.drawable.pixel3a,
        "Meet Pixel 3a premium for less",
        "Save your photos and videos with free, unlimited strorage in high qualtiy with Google Photos."
    ), Product(
        2, "Google Home", 7000F, R.drawable.google_home,
        "Help is here. Meet Google Home",
        "Get answers, play songs, tackle your day, enjoy your entertainment and control your smart home with just a touch."
    ), Product(
        3, "Pixel Stand", 6500F, R.drawable.pixel_stand,
        "Fast, wireless charging",
        "Charge your Pixel wirelessly with Pixel stand, and do more with your Google Assistant"
    ), Product(
        4, "ChromeCast", 3500F, R.drawable.google_chromecast,
        "Stream from your device to your TV",
        "Enjoy hundreds of Android or iPhone apps2, and play or pause directly from your phone"
    )
)

var productId = 0

class MainActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPaymentSuccess(p0: String?) {
        var alertDialogBuilder= AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(getString(R.string.thank_you_message, products[productId-1].name))
        alertDialogBuilder.setMessage(getString(R.string.shipping_details))
        alertDialogBuilder.show()
    }

    override fun onPaymentError(p0: Int, response: String?) {
        val snack = Snackbar.make(
            pay_button,
            "Payment Failed: $response",
            Snackbar.LENGTH_INDEFINITE
        )
        snack.setAction("OK") {
            snack.dismiss()
        }

        snack.show();
    }
}

