package estimator.kissteam.com.estimatorclient.retrofit

import java.net.URLDecoder

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class NormalizationStrategyImpl(private val defaultString: String = "",
                                private val defaultInt: Int = 0,
                                private val defaultLong: Long = 0,
                                private val defaultBoolean: Boolean = false) : NormalizationStrategy {

    override fun normalize(value: String?): String = value?.let { URLDecoder.decode(it, "utf-8") }
            ?: defaultString

    override fun normalize(value: Int?): Int = value ?: defaultInt

    override fun normalize(value: Long?): Long = value ?: defaultLong

    override fun normalize(value: Boolean?): Boolean = value ?: defaultBoolean
}
