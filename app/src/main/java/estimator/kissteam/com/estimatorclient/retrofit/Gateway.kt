package estimator.kissteam.com.estimatorclient.retrofit

/**
 * Created by: anna
 * Date: 3/16/18.
 */
interface Gateway<Entity> {

    /**
     * Execute gateway request synchronously
     */
    fun execute(): GatewayResult<Entity>

    /**
     * Execute gateway request asynchronously
     */
//    fun executeAsync(domainGatewayHandler: GatewayHandler<Entity>)
}

