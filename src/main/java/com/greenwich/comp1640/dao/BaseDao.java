package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.repository.readonly.GenericRORepository;
import com.greenwich.comp1640.repository.readwrite.GenericRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public class BaseDao<T, I extends Serializable> implements GenericRORepository<T, I>, GenericRepository<T, I> {
    private GenericRepository<T, I> genericRepository;
    private GenericRORepository<T, I> GenericRORepository;


    public BaseDao(GenericRepository<T, I> genericRepository, GenericRORepository<T, I> GenericRORepository) {
        this.genericRepository = genericRepository;
        this.GenericRORepository = GenericRORepository;
    }

    @Override
    public List<T> findAll() {
        return GenericRORepository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return GenericRORepository.findAll(sort);
    }

    @Override
    public List<T> findAllById(Iterable<I> iterable) {
        return GenericRORepository.findAllById(iterable);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> iterable) {
        return genericRepository.saveAll(iterable);
    }

    @Override
    public void flush() {
        genericRepository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return genericRepository.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        genericRepository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        genericRepository.deleteAllInBatch();
    }

    @Override
    public T getOne(I id) {
        return GenericRORepository.getOne(id);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return GenericRORepository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return GenericRORepository.findAll(example, sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return GenericRORepository.findAll(pageable);
    }

    @Override
    public <S extends T> S save(S s) {
        return genericRepository.save(s);
    }

    @Override
    public Optional<T> findById(I id) {
        return GenericRORepository.findById(id);
    }

    @Override
    public boolean existsById(I id) {
        return GenericRORepository.existsById(id);
    }

    @Override
    public long count() {
        return GenericRORepository.count();
    }

    @Override
    public void deleteById(I id) {
        genericRepository.deleteById(id);
    }

    @Override
    public void delete(T t) {
        genericRepository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        genericRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        genericRepository.deleteAll();
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return GenericRORepository.findOne(example);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return GenericRORepository.findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return GenericRORepository.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return GenericRORepository.exists(example);
    }
}
