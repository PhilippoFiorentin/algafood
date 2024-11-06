package com.philippo.algafood.domain.infrastructure.repository.spec;

import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.repository.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpecs {

    public static Specification<RestaurantOrder> usingFilter(OrderFilter filter){
        return ( root, query, builder ) ->{

            if (RestaurantOrder.class.equals(query.getResultType())){
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("client");
            }


            var predicates = new ArrayList<Predicate>();

            if (filter.getClientId() != null){
                predicates.add(builder.equal(root.get("client"), filter.getClientId()));
            }

            if (filter.getRestaurantId() != null){
                predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
            }

            if (filter.getDateCreationStart() != null){
                predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"), filter.getDateCreationStart()));
            }

            if (filter.getDateCreationEnd() != null){
                predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), filter.getDateCreationEnd()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
