package com.picsart.business.arch

/**
 * A representation of a [GetListDataSource] and [PutListDataSource] at the same time.
 */
interface StoreDataSource<F>: GetListDataSource<F>, PutListDataSource<F>