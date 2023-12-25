package balazadeh.nima.dunifood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import balazadeh.nima.dunifood.databinding.ActivityMainBinding
import balazadeh.nima.dunifood.databinding.DeleteBinding
import balazadeh.nima.dunifood.databinding.DialogeAddItemBinding
import balazadeh.nima.dunifood.databinding.UpdateItemBinding

class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodList = arrayListOf<Food>(
            Food(
                "Pepperoni",
                "18",
                "10",
                "Tehran",
                "https://www.simplyrecipes.com/thmb/pjYMLcsKHkr8D8tYixmaFNxppPw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-3-8f256746d649404baa36a44d271329bc.jpg",
                8,
                2.7f
            ),
            Food(
                "Sushi",
                "21",
                "7",
                "Tehran",
                "https://www.yummyhealthyeasy.com/wp-content/uploads/2018/04/california-sushi-rolls-square.jpg",
                42,
                3.7f
            ),
            Food(
                "Kebab",
                "10",
                "1",
                "Tehran",
                "https://cookingorgeous.com/wp-content/uploads/2021/06/lamb-shish-kebab-20-500x500.jpg",
                100,
                5f
            ),
            Food(
                "Ghorme Sabzi",
                "16",
                "3",
                "Tehran",
                "https://littlespicejar.com/wp-content/uploads/2023/02/Ghormeh-Sabzi-4-720x540.jpg",
                20,
                4.5f
            ),
            Food(
                "Fried Chicken",
                "16",
                "3",
                "Tehran",
                "https://twoplaidaprons.com/wp-content/uploads/2023/02/Korean-fried-chicken-wings-on-a-plate-thumbnail-shot.jpg",
                41,
                3f
            ),
            Food(
                "Hot Dog",
                "16",
                "3",
                "Tehran",
                "https://static01.nyt.com/images/2021/05/24/dining/gk-hotdogs/merlin_187538316_cfa51291-b8ea-4bfd-9455-18b9d906d9db-threeByTwoMediumAt2X.jpg",
                37,
                4f
            ),
            Food(
                "Hamburger",
                "24",
                "3",
                "Tehran",
                "https://heygrillhey.com/static/47c08cf31c9c6df778aee3088264eddf/Smoked-Hamburgers-Feature.png",
                74,
                3.5f
            ),
            Food(
                "Fish fry",
                "40",
                "9",
                "Tehran",
                "https://www.kannammacooks.com/wp-content/uploads/masala-fish-fry-recipe-ayala-meen-Mackerel-fry-8.jpg",
                52,
                3.9f
            ),
            Food(
                "Pizza",
                "16",
                "3",
                "Tehran",
                "https://cdn.loveandlemons.com/wp-content/uploads/2023/02/vegetarian-pizza-500x500.jpg",
                20,
                4.5f
            ),
            Food(
                "Mohito",
                "2",
                "3",
                "Tehran",
                "https://www.przez-zoladek-do-serca.pl/wp-content/uploads/2023/09/mohito-drink.webp",
                2,
                1f
            ),
            Food(
                "dessert",
                "3",
                "3",
                "Tehran",
                "https://cdn.loveandlemons.com/wp-content/uploads/2021/06/summer-desserts.jpg",
                5,
                2.5f
            ),
            Food(
                "Milkshake",
                "2",
                "1",
                "Tehran",
                "https://bakingmischief.com/wp-content/uploads/2022/03/coffee-milkshake-square.jpg",
                110,
                4f
            ),
        )
        myAdapter = FoodAdapter(foodList.clone() as ArrayList<Food>, this)
        binding.recyvlerview.adapter = myAdapter
        binding.recyvlerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val dialogeBinding = DialogeAddItemBinding.inflate(layoutInflater)
            dialog.setView(dialogeBinding.root)
            dialog.setCancelable(true)
            dialog.show()
            dialogeBinding.dialogButton.setOnClickListener {
                if (dialogeBinding.dialogName.length() > 0 &&
                    dialogeBinding.dialogCity.length() > 0 &&
                    dialogeBinding.dialogDistance.length() > 0 &&
                    dialogeBinding.dialogPrice.length() > 0
                ) {
                    val name = dialogeBinding.dialogName.text.toString()
                    val price = dialogeBinding.dialogPrice.text.toString()
                    val distance = dialogeBinding.dialogDistance.text.toString()
                    val city = dialogeBinding.dialogCity.text.toString()
                    val ratingNumber: Int = (1..150).random()
                    val rating: Float = (0..5).random().toFloat()
                    val urlImage = foodList[(0..11).random()].urlImage
                    val newFood = Food(name, price, distance, city, urlImage, ratingNumber, rating)
                    myAdapter.addFood(newFood)
                    binding.recyvlerview.scrollToPosition(0)
                    dialog.dismiss()

                } else {
                    Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.edtSearch.addTextChangedListener { editTextInput ->
            if (editTextInput!!.isNotEmpty()) {
                val cloneList = foodList.clone() as ArrayList<Food>
                val filterList = cloneList.filter { food ->
                    food.txtName.contains(editTextInput, true)
                }
                myAdapter.setData(filterList as ArrayList<Food>)
            } else {
                myAdapter.setData(foodList.clone() as ArrayList<Food>)
            }
        }
    }

    override fun onFoodClicked(food: Food, position: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val updateDialogBinding = UpdateItemBinding.inflate(layoutInflater)
        dialog.setView(updateDialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        updateDialogBinding.dialogName.setText(food.txtName)
        updateDialogBinding.dialogCity.setText(food.txtCity)
        updateDialogBinding.dialogPrice.setText(food.txtPrice)
        updateDialogBinding.dialogDistance.setText(food.txtDistance)

        updateDialogBinding.Cancle.setOnClickListener {
            dialog.dismiss()
        }
        updateDialogBinding.Done.setOnClickListener {
            if (updateDialogBinding.dialogName.length() > 0 &&
                updateDialogBinding.dialogCity.length() > 0 &&
                updateDialogBinding.dialogDistance.length() > 0 &&
                updateDialogBinding.dialogPrice.length() > 0
            ) {
                val name = updateDialogBinding.dialogName.text.toString()
                val price = updateDialogBinding.dialogPrice.text.toString()
                val distance = updateDialogBinding.dialogDistance.text.toString()
                val city = updateDialogBinding.dialogCity.text.toString()

                val newFood =
                    Food(name, price, distance, city, food.urlImage, food.numOfRating, food.raing)
                myAdapter.update(newFood, position)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onFoodLongClicked(food: Food, position: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val dialogDeleteBinding = DeleteBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogDeleteBinding.cancelbtn.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.surebtn.setOnClickListener {
            dialog.dismiss()
            myAdapter.removeFood(food, position)
        }
    }


}