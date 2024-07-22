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

    override suspend fun updateProgress(_string: Boolean, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateProgress(_string, id)
        }.join()
    }

    override suspend fun updateTitle(title: String, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateTitle(title, id)
        }
    }

    override suspend fun updateMemo(memo: String, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updateMemo(memo, id)
        }
    }

    override suspend fun updatePriority(priority: Int, id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.updatePriority(priority, id)
        }
    }

    override suspend fun deleteProduct(toDo: ToDoObject){
        externalScope.launch(dispatcher){
            ToDoDao.deleteProduct(toDo)
        }
    }

    override suspend fun deleteToDoById(id: Int){
        externalScope.launch(dispatcher){
            ToDoDao.deleteToDoById(id)
        }
    }

}



fun LocalToDoObject.toToDo(): ToDoObject = ToDoObject(
    id = this.id,
    title = this.title,
    memo = this.memo,
    priority = this.priority,
    category = this.category,
    isDone = this.isDone,
    date = this.date
)

fun ToDoObject.toLocal(): LocalToDoObject = LocalToDoObject(
    id = this.id,
    title = this.title,
    memo = this.memo,
    priority = this.priority,
    category = this.category,
    isDone = this.isDone,
    date = this.date
)