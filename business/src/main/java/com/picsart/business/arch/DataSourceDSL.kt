package com.picsart.business.arch
import com.picsart.business.arch.mapping.Bijection

/**
 * Transforma a StoreDataSource<F> into StoreDataSource<P>
 * @param bijection the corresponding bijection that translates [F] to [P] and [P] to [F]
 */
fun <F, P> StoreDataSource<F>.biject(bijection: Bijection<F, P>): StoreDataSource<P> = BijectedDataSource(this, bijection)

/**
 * Transforma a GetListDataSource<F> into GetListDataSource<P>
 * @param f a mapping function that translates [F] to [P]
 */
fun <F, P> GetListDataSource<F>.map(f: (F) -> P): GetListDataSource<P> = MappedGetListDataSource(this, f)