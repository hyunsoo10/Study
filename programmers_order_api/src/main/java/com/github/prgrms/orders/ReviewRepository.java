package com.github.prgrms.orders;

import java.util.Optional;

public interface ReviewRepository {

    Review save(Review review);
    Optional<Review> findById(Long id);
}
