package sheridan.caluagd.todolist_assignment3.data

import kotlinx.coroutines.*
class Logic{

    init{
        println("hi")
    }
    companion object{

        //Returns either a number or null, so it's easier to check for null.

        suspend fun ValidateInteger(int: String) = coroutineScope {

            var x: Int? = null

            if(ValidateEntry(int))
                x = try{
                    int.toInt()
                } catch(_: Exception){
                    null
                }

            x
        }

        fun ValidateEntry(entry: Any): Boolean{
            if(entry.toString() == "")
                return false

            return true
        }
    }
}