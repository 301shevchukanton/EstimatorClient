package estimator.kissteam.com.estimatorclient.dal.entities

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
data class Issue(val id: Int,
                 val title: String,
                 val description: String,
                 val estimationValue : Int)