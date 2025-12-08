package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.notice.dto.NoticeCommand;
import com.wisehero.javaboard.notice.dto.NoticeRequest;
import com.wisehero.javaboard.notice.dto.NoticeResponse;
import com.wisehero.javaboard.support.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeResponse.Detail> getNotice(@PathVariable Long noticeId) {
        return ApiResponse.success(noticeService.getNotice(noticeId));
    }

    @PostMapping
    public ApiResponse<NoticeResponse.Detail> createNotice(@RequestHeader("X-USER-ID") Long memberId,
                                                           @RequestBody NoticeRequest.Create request) {
        NoticeCommand.Create command = request.toCommand(memberId);
        NoticeResponse.Detail result = noticeService.writeNotice(command);

        return ApiResponse.success(result);
    }

    @PatchMapping("/{noticeId}")
    public ApiResponse<Void> updateNotice(@PathVariable Long noticeId,
                                         @RequestBody NoticeRequest.Update request) {
        NoticeCommand.Update command = request.toCommand(noticeId);

        noticeService.updateNotice(command);

        return ApiResponse.success(null);
    }

    @DeleteMapping("/{noticeId}")
    public ApiResponse<Void> deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);

        return ApiResponse.success(null);
    }

}
