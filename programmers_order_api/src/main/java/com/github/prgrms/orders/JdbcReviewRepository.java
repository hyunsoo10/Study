package com.github.prgrms.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class JdbcReviewRepository implements ReviewRepository{

    private final JdbcTemplate jdbcTemplate;

//    @Override
//    public Review save(Review review) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("reviews").usingGeneratedKeyColumns("seq");
//
//        Long userSeq = review.getSeq();
//        Long productSeq = review.getProductSeq();
//        String content = review.getContent();
//        LocalDateTime createAt = review.getCreateAt();
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("user_seq", userSeq);
//        parameters.put("product_seq", productSeq);
//        parameters.put("content", content);
//        parameters.put("create_at", createAt);
//
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        Long reviewSeq = key.longValue();
//
//        return new Review(reviewSeq, userSeq, productSeq, content, createAt);
//    }
//
//    @Override
//    public Optional<Review> findById(Long id) {
//        List<Review> review = jdbcTemplate.query(
//                "SELECT * FROM reviews WHERE seq=?",
//                mapper,
//                id
//        );
//        return ofNullable(review.isEmpty() ? null : review.get(0));
//    }
//
//    static RowMapper<Review> mapper = (rs, rowNum) ->
//            new Review.ReviewBuilder()
//                    .seq(rs.getLong("seq"))
//                    .productSeq(rs.getLong("product_seq"))
//                    .content(rs.getString("content"))
//                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
//                    .build();
@Override
public Review save(Review review) {
    // TODO Auto-generated method stub
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("reviews").usingGeneratedKeyColumns("seq");

    long userSeq = review.getUserSeq();
    long productSeq = review.getProductSeq();
    String content = review.getContent();
    LocalDateTime createAt = review.getCreateAt();

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("user_seq", userSeq);
    parameters.put("product_seq", productSeq);
    parameters.put("content", content);
    parameters.put("create_at", createAt);

    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    long reviewSeq = key.longValue();

    return new Review(reviewSeq, userSeq, productSeq, content, createAt);
}

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.empty();
    }
}
