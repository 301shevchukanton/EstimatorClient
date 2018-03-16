package estimator.kissteam.com.estimatorclient.retrofit

import java.io.IOException

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class GatewayResult<Entity>(val result: Entity?, val exception: IOException?) {
    constructor(exception: IOException) : this(null, exception)
    constructor(result: Entity?) : this(result, null)
}