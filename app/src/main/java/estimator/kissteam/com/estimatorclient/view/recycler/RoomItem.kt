package estimator.kissteam.com.estimatorclient.view.recycler

import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.dal.entities.Room


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class RoomItem(val room: Room, val issues: List<Issue>, val totalEstimation: Float?)