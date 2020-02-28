package com.mengxyz.example.firebase_admin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.mengxyz.example.firebase_admin.util.FirebaseResponse
import com.mengxyz.example.firebase_admin.R
import com.mengxyz.example.firebase_admin.ui.viewmodel.ViewModel
import com.mengxyz.example.firebase_admin.util.await
import com.mengxyz.example.firebase_admin.db.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

val TAG = MainActivity::class.java.simpleName

@Suppress("IMPLICIT_CAST_TO_ANY")
class MainActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var viewModel: ViewModel
    private lateinit var adapter: UserAdapter

    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = UserAdapter(this)

        re1.layoutManager = LinearLayoutManager(this)
        re1.adapter = adapter

        dialog = AlertDialog.Builder(this@MainActivity)
            .setTitle("Loading")
            .setMessage("loading...")
            .create()

        initView()


        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.isLoading.observe(this, Observer {
            it?.let {
                if (it)
                    dialog.show()
                else
                    dialog.dismiss()
            }
        })

        viewModel.allUser.observe(this, Observer {
            Log.e(TAG, "allUser: $it")
            adapter.update(it)
        })

        viewModel.response.observe(this, Observer {
            it?.let { res ->
                val msg =
                    FirebaseResponse.getValue(
                        res
                    )
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        })

        create.setOnClickListener {
            //viewModel.updateUser()
            createUser()
        }

        logout.setOnClickListener {
            firebaseAuth.signOut().also {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

    }


    fun createUser() = GlobalScope.launch(Dispatchers.Main) {
        val token = firebaseAuth.currentUser!!.getIdToken(false).await()
        token.token?.let { _token ->
            Log.e(TAG, "token: $_token")
            viewModel.createUser(email.text.toString(), "111111", _token)
        }
    }

    private fun initView() {
        firebaseAuth.currentUser?.uid.let {
            uuid.setText(it)
        }
    }
}