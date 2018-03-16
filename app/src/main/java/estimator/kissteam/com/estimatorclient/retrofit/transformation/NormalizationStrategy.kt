package estimator.kissteam.com.estimatorclient.retrofit.transformation

/**
 * Created by: anna
 * Date: 3/16/18.
 */
interface NormalizationStrategy {

    fun normalize(value: String?): String

    fun normalize(value: Int?): Int

    fun normalize(value: Long?): Long

    fun normalize(value: Boolean?): Boolean
}
