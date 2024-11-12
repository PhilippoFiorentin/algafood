package com.philippo.algafood.domain.infrastructure.service.query;

import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.model.OrderStatus;
import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.model.dto.DailySale;
import com.philippo.algafood.domain.service.SaleQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySale> checkDailySales(DailySaleFilter filter, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(RestaurantOrder.class);
        var predicates = new ArrayList<Predicate>();

        var creationDateConvertTzFunction = builder.function(
                "convert_tz", Date.class, root.get("creationDate"), builder.literal("+00:00"), builder.literal(timeOffset));

        var creationDateFunction = builder.function("date", Date.class, creationDateConvertTzFunction);

        var selection = builder.construct(
                DailySale.class,
                creationDateFunction,
                builder.count(root.get("id")),
                builder.sum(root.get("total")));

        if (filter.getRestaurantId() != null){
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }

        if (filter.getDateCreationStart() != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"), filter.getDateCreationStart()));
        }

        if (filter.getDateCreationEnd() != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), filter.getDateCreationEnd()));
        }

        predicates.add(root.get("status").in(OrderStatus.CONFIRMED, OrderStatus.DELIVERED));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(creationDateFunction);

        return manager.createQuery(query).getResultList();
    }
}
