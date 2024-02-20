package com.github.prgrms.orders;

import com.github.prgrms.products.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class JdbcOrderRepository implements OrderRepository{

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public Order findById(Long id) {
        // TODO Auto-generated method stub
        Order result = jdbcTemplate.queryForObject(
                "select * from orders where seq=?",
                orderMapper,
                id);

        return result;
    }


    @Override
    public List<OrderResponseDto> findAll(Long userSeq, long offset, int size) {
        String sql =  "select o.seq, o.review_seq, o.product_seq, o.state, o.request_msg, o.reject_msg, o.completed_at, o.rejected_at, o.create_at," +
                " r.content, r.create_at as r_create_at" +
                " from orders o left join reviews r on o.review_seq = r.seq" +
                " where o.user_seq =:userSeq order by o.seq desc" +
                " limit :size offset :offset";


        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userSeq", userSeq)
                .addValue("size", size)
                .addValue("offset", offset);

        return namedJdbcTemplate.query(sql, param, OrderResponseDTOMapper);
    }

    @Override
    public OrderResponseDto findWithReviewById(Long orderSeq) {
        String sql = "select o.seq, o.review_seq, o.product_seq, o.state, o.request_msg, o.reject_msg, o.completed_at, o.rejected_at, o.create_at," +
                " r.content, r.create_at as r_create_at" +
                " from orders o left join reviews r on o.review_seq = r.seq" +
                " where o.seq =?";

        OrderResponseDto result = jdbcTemplate.queryForObject(sql, OrderResponseDTOMapper, orderSeq);

        return result;
    }

    @Override
    public void update(Order order) {

        String sql = "update orders " +
                "set state =:state, review_seq =:review_seq, reject_msg =:reject_msg, rejected_at =:rejected_at, completed_at =:completed_at " +
                "where seq =:id";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("state", order.getState().toString())
                .addValue("review_seq", order.getReviewSeq() == 0 ? null : order.getReviewSeq())
                .addValue("reject_msg", order.getRejectMsg())
                .addValue("rejected_at", order.getRejectedAt())
                .addValue("completed_at", order.getCompletedAt())
                .addValue("id", order.getSeq());

        namedJdbcTemplate.update(sql, parameters);

    }


    private RowMapper<OrderResponseDto> OrderResponseDTOMapper = (rs, rowNum) -> {

        OrderResponseDto orderResponseDto = new OrderResponseDto.OrderResponseDtoBuilder()
                .seq(rs.getLong("seq"))
                .productId(rs.getLong("product_seq"))
                .state(OrderState.valueOf(rs.getString("state")))
                .requestMessage(rs.getString("request_msg"))
                .rejectMessage(rs.getString("reject_msg"))
                .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
                .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
                .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                .build();

        long reviewSeq = rs.getLong("review_seq");

        if (reviewSeq > 0) {
            Long productSeq = rs.getLong("product_seq");
            String content = rs.getString("content");
            LocalDateTime createAt = dateTimeOf(rs.getTimestamp("r_create_at"));

            orderResponseDto.setReview(reviewSeq, productSeq, content, createAt);
        }

        return orderResponseDto;
    };
    static RowMapper<Order> orderMapper = (rs, rowNum) ->
            new Order.OrderBuilder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productSeq(rs.getLong("product_seq"))
                    .reviewSeq(rs.getLong("review_seq"))
                    .state(OrderState.valueOf(rs.getString("state")))
                    .requestMsg(rs.getString("request_msg"))
                    .rejectMsg(rs.getString("reject_msg"))
                    .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
                    .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();

}
