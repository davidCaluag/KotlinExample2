package sheridan.caluagd.todolist_assignment3.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import sheridan.caluagd.todolist_assignment3.domain.ToDoObject
import sheridan.caluagd.todolist_assignment3.data.local.LocalToDoObject
import sheridan.caluagd.todolist_assignment3.data.local.ToDoDao
import java.util.Date

@OptIn(DelicateCoroutinesApi::class)
class LocalRepository(
    private val ToDoDao: ToDoDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
    ): ToDoRepository {


        constructor(ToDoDao: ToDoDao):this(ToDoDao, GlobalScope, Dispatchers.IO)

    override fun getAllToDo() : Flow<List<ToDoObject>> =
        ToDoDao.getAllToDo().map{
            list ->list.map{localItem -> localItem.toToDo()}
        }.flowOn(dispatcher)


    override suspend fun updateToDo(toDo: ToDoObject){
        externalScope.launch(dispatcher){
            ToDoDao.updateToDo(toDo.toLocal())
        }.join()
    }

    override suspend fun updateProgress(boolean: Boolean, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateProgress(id, boolean)
        }.join()
    }

    override suspend fun updateTitle(title: String, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateTitle(title, id)
        }
    }

    override suspend fun updateMemo(memo: String, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateMemo(id, memo)
        }
    }

    override suspend fun updatePriority(float: Float, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updatePriority(id, float)
        }
    }
    override suspend fun updateDue(due: Date, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateDue(due, id)
        }
    }

    override suspend fun updateTime(date: Date, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateTime(date, id)
        }
    }

    override suspend fun deleteProduct(toDo: ToDoObject){
        externalScope.launch(dispatcher){
            ToDoDao.deleteProduct(toDo.toLocal())
        }
    }


    override suspend fun deleteToDoById(id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.deleteToDoById(id)
        }
    }

//    override suspend fun finishDoneToDo(){
//        externalScope.launch(dispatcher) {
//            ToDoDao.finishDoneToDo()
//        }
//    }
}



fun LocalToDoObject.toToDo(): ToDoObject = ToDoObject(
    id = this.id,
    title = this.title,
    memo = this.memo,
    priority = this.priority,
    category = this.category,
    isDone = this.isDone,
    date = this.date,
    due = this.due
)

fun ToDoObject.toLocal(): LocalToDoObject = LocalToDoObject(
    id = this.id,
    title = this.title,
    memo = this.memo,
    priority = this.priority,
    category = this.category,
    isDone = this.isDone,
    date = this.date,
    due = this.due
)