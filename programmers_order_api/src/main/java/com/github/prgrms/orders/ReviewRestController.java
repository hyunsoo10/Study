package com.github.prgrms.orders;

import com.github.prgrms.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.success;
import static java.lang.Thread.sleep;


@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;

    // TODO review 메소드 구현이 필요합니다.
    @PostMapping("/{id}/review")
    public ApiResult<ReviewResponseDto> review(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id,
            @RequestBody ReviewRequestDto reviewRequestDto
    ) {
        return success(reviewService.review(id, reviewRequestDto));
    }

}