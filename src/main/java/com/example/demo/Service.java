package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import java.util.UUID;

@Component
public class Service {
    private static final Logger LOG = LoggerFactory.getLogger(Service.class);

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private ChildDao childDao;

    @Transactional(value = TxType.REQUIRES_NEW)
    public ParentEntity setup() {
        final ChildEntity childEntity = childDao.save(new ChildEntity());
        final ParentEntity parentEntity = new ParentEntity();
        parentEntity.setChild(childEntity);

        return parentDao.save(parentEntity);
    }

    @Transactional(value = TxType.REQUIRES_NEW)
    public void delete(final UUID id) {
        final ParentEntity parentEntity = parentDao.findById(id).get();
        final ChildEntity childEntity = parentEntity.getChild();

        parentEntity.removeChild();

        childDao.delete(childEntity);
    }

    public void test() {
        LOG.info("test");
    }
}
