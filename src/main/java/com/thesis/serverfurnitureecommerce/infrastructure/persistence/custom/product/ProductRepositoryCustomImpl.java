package com.thesis.serverfurnitureecommerce.infrastructure.persistence.custom.product;

import com.thesis.serverfurnitureecommerce.presentation.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.domain.model.entity.CategoryEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.RoomEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.SupplierEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryCustomImpl implements IProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductEntity> findAllMultiField(ProductSearchRequest productSearchRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = criteriaQuery.from(ProductEntity.class);

        Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category");
        Join<ProductEntity, SupplierEntity> supplierJoin = root.join("supplier");
        Join<ProductEntity, RoomEntity> roomJoin = root.join("rooms");

        List<Predicate> predicates = buildPredicates(productSearchRequest, cb, root, categoryJoin, supplierJoin, roomJoin);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        List<ProductEntity> result = typedQuery.getResultList();
        return result;
    }

    private List<Predicate> buildPredicates(ProductSearchRequest productSearchRequest,
                                            CriteriaBuilder cb,
                                            Root<ProductEntity> product,
                                            Join<ProductEntity, CategoryEntity> categoryJoin,
                                            Join<ProductEntity, SupplierEntity> supplierJoin,
                                            Join<ProductEntity, RoomEntity> roomJoin) {

        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(productSearchRequest.getName())
                .ifPresent(name -> predicates.add(cb.like(product.get("name"), "%" + name + "%")));

        Optional.ofNullable(productSearchRequest.getCategory())
                .ifPresent(category -> predicates.add(cb.equal(categoryJoin.get("name"), category)));

        Optional.ofNullable(productSearchRequest.getSupplier())
                .ifPresent(supplier -> predicates.add(cb.equal(supplierJoin.get("name"), supplier)));

        Optional.ofNullable(productSearchRequest.getRoom())
                        .ifPresent(room -> predicates.add(cb.equal(roomJoin.get("name"), room)));

        Optional.ofNullable(productSearchRequest.getMinPrice())
                .ifPresent(minPrice -> predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice)));

        Optional.ofNullable(productSearchRequest.getMaxPrice())
                .ifPresent(maxPrice -> predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice)));

        return predicates;
    }
}