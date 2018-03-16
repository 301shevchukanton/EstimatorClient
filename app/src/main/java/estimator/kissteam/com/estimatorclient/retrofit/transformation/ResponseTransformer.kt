package estimator.kissteam.com.estimatorclient.retrofit.transformation

/**
 * Created by: anna
 * Date: 3/16/18.
 */
@Suppress("AddVarianceModifier")
interface ResponseTransformer<Response, Entity> {

    /**
     * Transforms response object to entity
     */
    fun transform(response: Response): Entity
}
