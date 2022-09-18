package com.example.week12

import Database.GlobalVar
import Model.Animal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week12.databinding.ActivityAddAnimalBinding

class AddAnimalActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityAddAnimalBinding
    private lateinit var animal:Animal
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = com.example.week12.databinding.ActivityAddAnimalBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        GetIntent()
        Listener()
    }

    private fun GetIntent(){
        position = intent.getIntExtra("editing", -1)
        if(position != -1){
            val animal = GlobalVar.listDataAnimal[position]
            viewBind.toolbar3.title = "Ubah Hewan"
            viewBind.inputnama.editText?.setText(animal.nama)
            viewBind.inputjenis.editText?.setText(animal.jenis)
            viewBind.inputusia.editText?.setText(animal.usia.toString())
        }
    }

    private fun Listener(){
        viewBind.saveedit.setOnClickListener{
            var nama = viewBind.inputnama.editText?.text.toString().trim()
            var jenis = viewBind.inputjenis.editText?.text.toString().trim()
            var usia = 0

            animal = Animal(nama, jenis, usia)
            Checking()
        }
        viewBind.toolbar3.getChildAt(1).setOnClickListener{
            finish()
        }
    }

    private fun Checking(){
        var isCompleted:Boolean = true

        if(animal.nama!!.isEmpty()){
            viewBind.inputnama.error = "Kolom nama masih kosong."
            isCompleted = false
        }else{
            viewBind.inputnama.error = ""
        }

        if(animal.jenis!!.isEmpty()){
            viewBind.inputjenis.error = "Kolom jenis masih kosong."
            isCompleted = false
        }else{
            viewBind.inputjenis.error = ""
        }

        if(animal.usia!!.toString().isEmpty()){
            viewBind.inputusia.error = "Kolom usia masih kosong."
            isCompleted = false
        }else{
            viewBind.inputusia.error = ""
            animal.usia = viewBind.inputusia.editText?.text.toString().toInt()
        }

        if(isCompleted) {
            if (position == -1) {
                GlobalVar.listDataAnimal.add(animal)
            } else {
                GlobalVar.listDataAnimal[position] = animal
            }
            finish()
        }
    }

}