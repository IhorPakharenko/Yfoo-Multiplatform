package com.isao.yfoo3.core.database

//expect fun Scope.getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>

//val databaseModule = module {
//    single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder() }
////    single<RoomDatabase.Builder<AppDatabase>> { TODO() }
//    single<AppDatabase> {
//        get<RoomDatabase.Builder<AppDatabase>>()
//            .setDriver(BundledSQLiteDriver())
//            .build()
//    }
//    single<FeedImageDao> { get<AppDatabase>().feedImageDao() }
////    single<FeedImageDao> { TODO() }
////    single<LikedImageDao> { TODO() }
//    single<LikedImageDao> { get<AppDatabase>().likedImageDao() }
//}