package com.vncoder.logindemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var Email: String? = null
    var password: String? = null
    private lateinit var sharedPreferences :SharedPreferences
    private var PREF_MAILS = "MAILS"
    private var PREF_PASSS = "PASS"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "http://www.fb.com"
        imageView2.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(intent)
        }
        val url2 = "http://www.google.com"
        imageView3.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url2)
            startActivity(intent)
        }

        val url3 = "http://https://twitter.com/explore"
        imageView.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url3)
            startActivity(intent)
        }



        loadDataSignIn()
        edt_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {
                if (s.length == 0) {
                    edt_email.setError("Email is not null")
                } else {
                    edt_email.setError(null)
                }
            }
        })

        edt_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                 if (s.length == 0) {
                    edt_password.setError("Password is not null")
                } else {
                    edt_password.setError(null)
                }
            }
        })

        btn_login.setOnClickListener {

                Email = edt_email.getText().toString();
                password = edt_password.getText().toString();

                val pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}"
                 if ( Email!!.isEmpty()|| password!!.isEmpty()|| password!!.matches(pattern.toRegex())==false){
                   val builder = AlertDialog.Builder(this)
                    builder.setTitle("ERROR");
                    builder.setIcon(R.drawable.coffe2)
                    builder.setMessage("You enter information is missing");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok"){ dialogInterface, i ->
                        FancyToast.makeText(this, "\n" +"The password is not strong enough or information is missing",
                            Toast.LENGTH_SHORT,FancyToast.ERROR,true).show()}
                    val alertDialog = builder.create()
                    alertDialog.show()
                }else {

                     var ed = sharedPreferences.edit()
                     ed.putString(PREF_MAILS, Email)
                     ed.putString(PREF_PASSS, password)
                     ed.commit()
                     FancyToast.makeText(applicationContext,"Login success",FancyToast.SUCCESS,FancyToast.LENGTH_SHORT,true).show()
                     val intent = Intent(this, MainActivity2::class.java)
                     startActivity(intent)
                }
        }
    }
      private fun loadDataSignIn() {
          sharedPreferences = this.getSharedPreferences("save_dataLogin", Context.MODE_PRIVATE)
        edt_email.setText(sharedPreferences.getString(PREF_MAILS, ""))
        edt_password.setText(sharedPreferences.getString(PREF_PASSS, ""))
    }



    fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.coffe2)
        builder.setTitle("The coffee house")
        builder.setMessage("Do you want exit app?")
        builder.setCancelable(false)
        builder.setPositiveButton("No") { dialogInterface, i ->
            FancyToast.makeText(this, "Thanks you",FancyToast.SUCCESS, Toast.LENGTH_SHORT,true).show() }
        builder.setNegativeButton("Yes") { dialogInterface, i -> dialogInterface.dismiss()
            System.exit(0) }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        showAlertDialog()
    }
}