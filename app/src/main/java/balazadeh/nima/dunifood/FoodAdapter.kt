package balazadeh.nima.dunifood

import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>, private val foodEvent: FoodEvents) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View, private val contex: Context) :
        RecyclerView.ViewHolder(itemView) {

        val img = itemView.findViewById<ImageView>(R.id.imageView)
        val nameFood = itemView.findViewById<TextView>(R.id.nameFood)
        val city = itemView.findViewById<TextView>(R.id.country)
        val price = itemView.findViewById<TextView>(R.id.price)
        val distance = itemView.findViewById<TextView>(R.id.distance)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        val txtRating = itemView.findViewById<TextView>(R.id.ite_txt_rating)

        fun bindData(position: Int) {
            nameFood.text = data[position].txtName
            city.text = data[position].txtCity
            distance.text = data[position].txtDistance + " miles from you"
            price.text = "$" + data[position].txtPrice + " vip"
            ratingBar.rating = data[position].raing
            txtRating.text = "(" + data[position].numOfRating.toString() + " Ratings)"


            Glide.with(contex).load(data[position].urlImage)
                .transform(RoundedCornersTransformation(16, 4))
                .into(img)

            itemView.setOnClickListener {
                foodEvent.onFoodClicked(data[adapterPosition],adapterPosition)
            }
            itemView.setOnLongClickListener {
                foodEvent.onFoodLongClicked(data[adapterPosition],adapterPosition)
                true
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return FoodViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addFood(newFood: Food) {
        data.add(0, newFood)
        notifyItemInserted(0)
    }

    fun removeFood(oldFood: Food, oldPosition: Int) {
        data.remove(oldFood)
        notifyItemRemoved(oldPosition)
    }

    fun update(newFood: Food,position: Int){
        data.set(position,newFood)
        notifyItemChanged(position)
    }

    fun setData(newList: ArrayList<Food>){
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    interface FoodEvents {
        fun onFoodClicked(food: Food,position: Int)

        fun onFoodLongClicked(food: Food,position: Int)

    }

}