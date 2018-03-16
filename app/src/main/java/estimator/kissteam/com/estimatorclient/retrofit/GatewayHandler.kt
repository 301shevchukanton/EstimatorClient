package estimator.kissteam.com.estimatorclient.retrofit

/**
 * Created by: anna
 * Date: 3/17/18.
 */
interface GatewayHandler<Entity> {
    fun handleResult(result: GatewayResult<Entity>)
}