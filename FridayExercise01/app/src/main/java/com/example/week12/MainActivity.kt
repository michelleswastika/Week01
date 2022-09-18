package com.example.week12

import Adaptor.ListDataAdaptor
import Database.GlobalVar
import Interface.Cardlistener
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week12.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), Cardlistener {
    private lateinit var viewBind: ActivityMainBinding
    private var adapter = ListDataAdaptor(GlobalVar.listDataAnimal, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        setupRecyclerView()
        Listener()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        Toast.makeText(this,adapter.itemCount.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun Listener() {
        viewBind.add.setOnClickListener {
            val addmovie = Intent(this, AddAnimalActivity::class.java)
            startActivity(addmovie)
        }
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewBind.listAnimal.layoutManager = layoutManager
        viewBind.listAnimal.adapter = adapter
    }

    override fun oncardclick(clicked: Boolean, position:Int){
        if(!clicked){
            AlertDialog.Builder(this).setMessage("Hapus data?")
                .setPositiveButton("Yes") { dialog, id ->
                    GlobalVar.listDataAnimal.removeAt(position)
                    val snackBar =
                        Snackbar.make(viewBind.root, "Data telah dihapus", Snackbar.LENGTH_SHORT)

                    adapter.notifyDataSetChanged()
                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }.create().show()
        }
        else{
            val editIntent = Intent(this, AddAnimalActivity::class.java).putExtra("editing", position)
            startActivity(editIntent)
        }
    }
}