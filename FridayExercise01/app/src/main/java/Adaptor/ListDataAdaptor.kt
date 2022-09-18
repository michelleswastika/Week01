package Adaptor

import Database.GlobalVar
import Interface.Cardlistener
import Model.Animal
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.week12.MainActivity
import com.example.week12.R
import com.example.week12.databinding.ActivityCardAnimalBinding

class ListDataAdaptor(val listAnimal: ArrayList<Animal>,val cardlistener: Cardlistener):
    RecyclerView.Adapter<ListDataAdaptor.viewHolder>(){
    private var position = -1
    class viewHolder(itemView:View, val cardlistener: Cardlistener):RecyclerView.ViewHolder(itemView){
        val binding = ActivityCardAnimalBinding.bind(itemView)

        fun setData(data:Animal){
            binding.namaHewan.text = data.nama
            binding.jenisHewan.text = data.jenis
            binding.usiaHewan.text = data.usia.toString()
            binding.delete.setOnClickListener(){
                cardlistener.oncardclick(false, adapterPosition)
            }
            binding.edit.setOnClickListener(){
                cardlistener.oncardclick(true, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataAdaptor.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_card_animal,parent, false)
        return viewHolder(view, cardlistener)
    }

    override fun onBindViewHolder(holder: ListDataAdaptor.viewHolder, position: Int) {
        holder.setData(listAnimal[position])
    }

    override fun getItemCount(): Int {
        return listAnimal.size
    }

}