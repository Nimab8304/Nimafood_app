package balazadeh.nima.dunifood

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import balazadeh.nima.dunifood.Room.FoodDao
import balazadeh.nima.dunifood.Room.FoodDatabase
import balazadeh.nima.dunifood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: FoodAdapter
    lateinit var foodDao:FoodDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foodDao=FoodDatabase.getDatabase(this).foodDao
        val sharedPreferences=getSharedPreferences("start",Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("firstTime",true)){
            firstRun()
            sharedPreferences.edit().putBoolean("firstTime",false).apply()
        }

        showAllData()

//        binding.btnAdd.setOnClickListener {
//            val dialog = AlertDialog.Builder(this).create()
//            val dialogeBinding = DialogeAddItemBinding.inflate(layoutInflater)
//            dialog.setView(dialogeBinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//            dialogeBinding.dialogButton.setOnClickListener {
//                if (dialogeBinding.dialogName.length() > 0 &&
//                    dialogeBinding.dialogCity.length() > 0 &&
//                    dialogeBinding.dialogDistance.length() > 0 &&
//                    dialogeBinding.dialogPrice.length() > 0
//                ) {
//                    val name = dialogeBinding.dialogName.text.toString()
//                    val price = dialogeBinding.dialogPrice.text.toString()
//                    val distance = dialogeBinding.dialogDistance.text.toString()
//                    val city = dialogeBinding.dialogCity.text.toString()
//                    val ratingNumber: Int = (1..150).random()
//                    val rating: Float = (0..5).random().toFloat()
//                    val urlImage = foodList[(0..11).random()].urlImage
//                    val newFood = Food(name, price, distance, city, urlImage, ratingNumber, rating)
//                    myAdapter.addFood(newFood)
//                    binding.recyvlerview.scrollToPosition(0)
//                    dialog.dismiss()
//
//                } else {
//                    Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//
//        binding.edtSearch.addTextChangedListener { editTextInput ->
//            if (editTextInput!!.isNotEmpty()) {
//                val cloneList = foodList.clone() as ArrayList<Food>
//                val filterList = cloneList.filter { food ->
//                    food.txtName.contains(editTextInput, true)
//                }
//                myAdapter.setData(filterList as ArrayList<Food>)
//            } else {
//                myAdapter.setData(foodList.clone() as ArrayList<Food>)
//            }
//        }
//    }
//
//        override fun onFoodClicked(food: Food, position: Int) {
//            val dialog = AlertDialog.Builder(this).create()
//            val updateDialogBinding = UpdateItemBinding.inflate(layoutInflater)
//            dialog.setView(updateDialogBinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//
//            updateDialogBinding.dialogName.setText(food.txtName)
//            updateDialogBinding.dialogCity.setText(food.txtCity)
//            updateDialogBinding.dialogPrice.setText(food.txtPrice)
//            updateDialogBinding.dialogDistance.setText(food.txtDistance)
//
//            updateDialogBinding.Cancle.setOnClickListener {
//                dialog.dismiss()
//            }
//            updateDialogBinding.Done.setOnClickListener {
//                if (updateDialogBinding.dialogName.length() > 0 &&
//                    updateDialogBinding.dialogCity.length() > 0 &&
//                    updateDialogBinding.dialogDistance.length() > 0 &&
//                    updateDialogBinding.dialogPrice.length() > 0
//                ) {
//                    val name = updateDialogBinding.dialogName.text.toString()
//                    val price = updateDialogBinding.dialogPrice.text.toString()
//                    val distance = updateDialogBinding.dialogDistance.text.toString()
//                    val city = updateDialogBinding.dialogCity.text.toString()
//
//                    val newFood =
//                        Food(
//                            name,
//                            price,
//                            distance,
//                            city,
//                            food.urlImage,
//                            food.numOfRating,
//                            food.raing
//                        )
//                    myAdapter.update(newFood, position)
//                    dialog.dismiss()
//                } else {
//                    Toast.makeText(this, "لطفا تمام مقادیر را وارد کنید", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//
//        override fun onFoodLongClicked(food: Food, position: Int) {
//            val dialog = AlertDialog.Builder(this).create()
//            val dialogDeleteBinding = DeleteBinding.inflate(layoutInflater)
//            dialog.setView(dialogDeleteBinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//
//            dialogDeleteBinding.cancelbtn.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialogDeleteBinding.surebtn.setOnClickListener {
//                dialog.dismiss()
//                myAdapter.removeFood(food, position)
//            }
//        }


    }

    private fun showAllData() {
        val foodData=foodDao.getAllFoods()
        myAdapter = FoodAdapter(ArrayList(foodData), this)
        binding.recyvlerview.adapter = myAdapter
        binding.recyvlerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun firstRun() {
        val foodList = listOf(
            Food(
                txtName = "Pepperoni",
                txtPrice = "18",
                txtDistance = "10",
                txtCity ="Tehran",
                urlImage = "https://www.simplyrecipes.com/thmb/pjYMLcsKHkr8D8tYixmaFNxppPw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-3-8f256746d649404baa36a44d271329bc.jpg",
                numOfRating =  8,
               raing =  2.7f
            ),
            Food(
                txtName ="Sushi",
                txtPrice = "21",
                txtDistance ="7",
                txtCity ="Tehran",
                urlImage ="https://www.yummyhealthyeasy.com/wp-content/uploads/2018/04/california-sushi-rolls-square.jpg",
                numOfRating =   42,
                raing =3.7f
            ),
            Food(
                txtName ="Kebab",
                txtPrice = "10",
                txtDistance ="1",
                txtCity ="Tehran",
                urlImage ="https://cookingorgeous.com/wp-content/uploads/2021/06/lamb-shish-kebab-20-500x500.jpg",
                numOfRating =   100,
                raing =5f
            ),
            Food(
                txtName ="Ghorme Sabzi",
                txtPrice = "16",
                txtDistance ="3",
                txtCity ="Tehran",
                urlImage ="https://littlespicejar.com/wp-content/uploads/2023/02/Ghormeh-Sabzi-4-720x540.jpg",
                numOfRating =   20,
                raing =4.5f
            ),
            Food(
                txtName ="Fried Chicken",
                txtPrice =  "16",
                txtDistance ="3",
                txtCity ="Tehran",
                urlImage ="https://twoplaidaprons.com/wp-content/uploads/2023/02/Korean-fried-chicken-wings-on-a-plate-thumbnail-shot.jpg",
                numOfRating =   41,
                raing =3f
            ),
            Food(
                txtName ="Hot Dog",
                txtPrice = "16",
                txtDistance ="3",
                txtCity ="Tehran",
                urlImage = "https://static01.nyt.com/images/2021/05/24/dining/gk-hotdogs/merlin_187538316_cfa51291-b8ea-4bfd-9455-18b9d906d9db-threeByTwoMediumAt2X.jpg",
                numOfRating =   37,
                raing =4f
            ),
            Food(
                txtName ="Hamburger",
                txtPrice =  "24",
                txtDistance ="3",
                txtCity ="Tehran",
                urlImage ="https://heygrillhey.com/static/47c08cf31c9c6df778aee3088264eddf/Smoked-Hamburgers-Feature.png",
                numOfRating =   74,
                raing =3.5f
            ),
            Food(
                txtName ="Fish fry",
                txtPrice = "40",
                txtDistance ="9",
                txtCity ="Tehran",
                urlImage =  "https://www.kannammacooks.com/wp-content/uploads/masala-fish-fry-recipe-ayala-meen-Mackerel-fry-8.jpg",
                numOfRating = 52,
                raing =3.9f
            ),
            Food(
                txtName ="Pizza",
                txtPrice =  "16",
                txtDistance ="3",
                txtCity ="Tehran",
                urlImage =  "https://cdn.loveandlemons.com/wp-content/uploads/2023/02/vegetarian-pizza-500x500.jpg",
                numOfRating =  20,
                raing =4.5f
            ),
            Food(
                txtName ="Mohito",
                txtPrice =  "2",
                txtDistance ="3",
                txtCity ="Tehran",
                urlImage = "https://www.przez-zoladek-do-serca.pl/wp-content/uploads/2023/09/mohito-drink.webp",
                numOfRating =  2,
                raing =1f
            ),
            Food(
                txtName ="dessert",
                txtPrice = "3",
                txtDistance ="3",
                txtCity ="Tehran",
                urlImage =  "https://cdn.loveandlemons.com/wp-content/uploads/2021/06/summer-desserts.jpg",
                numOfRating =  5,
                raing =2.5f
            ),
            Food(
                txtName ="Milkshake",
                txtPrice = "2",
                txtDistance ="1",
                txtCity = "Tehran",
                urlImage = "https://bakingmischief.com/wp-content/uploads/2022/03/coffee-milkshake-square.jpg",
               numOfRating =  110,
                raing =4f
            ),
        )
        foodDao.insertAll(foodList)
    }

    override fun onFoodClicked(food: Food, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onFoodLongClicked(food: Food, position: Int) {
        TODO("Not yet implemented")
    }
}