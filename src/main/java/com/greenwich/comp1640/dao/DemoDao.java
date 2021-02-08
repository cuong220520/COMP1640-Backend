package com.greenwich.comp1640.dao;

import com.greenwich.comp1640.model.Demo;
import com.greenwich.comp1640.repository.readonly.DemoRORepository;
import com.greenwich.comp1640.repository.readwrite.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDao extends BaseDao<Demo, Long> {
    private final DemoRepository demoRepository;
    private final DemoRORepository demoRORepository;

    @Autowired
    public DemoDao(DemoRepository demoRepository, DemoRORepository demoRORepository) {
        super(demoRepository, demoRORepository);
        this.demoRepository = demoRepository;
        this.demoRORepository = demoRORepository;
    }

    public Demo findByEmail(String email) {
        return this.demoRORepository.findByEmail(email);
    }

    public Page<Demo> findAll(Pageable pageable) {
        return this.demoRORepository.findAll(pageable);
    }

    public Demo saveDemo(Demo demo) {
        return this.demoRepository.save(demo);
    }
}
