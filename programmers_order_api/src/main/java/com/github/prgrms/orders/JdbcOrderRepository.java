package com.github.prgrms.orders;

import com.github.prgrms.products.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class JdbcOrderRepository implements OrderRepository{

    private final JdbcTemplate jdbcTemplate;

}
