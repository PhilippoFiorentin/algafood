package com.philippo.algafood.domain.infrastructure.repository;

import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.repository.RestaurantRepository;
import com.philippo.algafood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.philippo.algafood.domain.infrastructure.repository.spec.RestaurantSpecs.freeDelivery;
import static com.philippo.algafood.domain.infrastructure.repository.spec.RestaurantSpecs.similarName;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find (
        String name,
        BigDecimal initialDeliveryFee,
        BigDecimal finalDeliveryFee ){

        var builder = manager.getCriteriaBuilder();
        var criteria = builder.createQuery(Restaurant.class);
        var root = criteria.from(Restaurant.class);
        var predicates = new ArrayList<Predicate>();

//        CriteriaBuilder builder = manager.getCriteriaBuilder();
//        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);

//        Root<Restaurant> root = criteria.from(Restaurant.class); // from Restaurant

        if(StringUtils.hasText(name)){
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if(initialDeliveryFee != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("deliveryFee"),
                initialDeliveryFee));
        }

        if(finalDeliveryFee != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("deliveryFee"),
                finalDeliveryFee));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        var query = manager.createQuery(criteria);

//        TypedQuery<Restaurant> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    @Override public List<Restaurant> findFreeDelivery (String name) {
        return restaurantRepository.findAll(freeDelivery().and(similarName(name)));
    }
}
