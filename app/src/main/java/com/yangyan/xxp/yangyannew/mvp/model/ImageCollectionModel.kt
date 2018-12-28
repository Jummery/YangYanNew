package com.yangyan.xxp.yangyannew.mvp.model

import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.yangyan.xxp.yangyannew.mvp.contract.ImageCollectionContract
import com.yangyan.xxp.yangyannew.mvp.model.entity.ImagesDetailInfo
import com.yangyan.xxp.yangyannew.mvp.model.entity.ImagesInfo
import com.yangyan.xxp.yangyannew.mvp.model.service.CommonService
import com.yangyan.xxp.yangyannew.mvp.model.service.cache.CommonCacheService
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.Reply
import javax.inject.Inject

/**
 * Author : zhongwenpeng
 * Email : 1340751953@qq.com
 * Time :  2018/5/25
 * Description :  图片集合
 */
@ActivityScope
class ImageCollectionModel @Inject
constructor(repositoryManager: IRepositoryManager) : FavoriteModel(repositoryManager), ImageCollectionContract.Model {

    override fun getImageCollection(id: Int): Observable<List<String>> {
        return mRepositoryManager.obtainCacheService(CommonCacheService::class.java)
                .getImagesDetailById(mRepositoryManager.obtainRetrofitService(CommonService::class.java)
                        .getImagesDetailById(id),
                        DynamicKey(id))
                .map { imagesContent: Reply<ImagesDetailInfo> ->
                    imagesContent.data.content.split(",")
                }
    }

}