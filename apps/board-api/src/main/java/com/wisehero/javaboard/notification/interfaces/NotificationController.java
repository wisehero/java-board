package com.wisehero.javaboard.notification.interfaces;

import com.wisehero.javaboard.notification.Notification;
import com.wisehero.javaboard.notification.application.NotificationService;
import com.wisehero.javaboard.notification.interfaces.dto.NotificationResponse;
import com.wisehero.javaboard.support.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notifications/my/count")
    public ApiResponse<Integer> getMyUnreadNotificationCount(
            @RequestHeader("X-USER-ID") Long receiverId
    ) {
        int result = notificationService.getMyUnreadNotificationCount(receiverId);
        return ApiResponse.success(result);
    }

    @GetMapping("/notifications/my/summary")
    public ApiResponse<?> getMyNotificationSummary(
            @RequestHeader("X-USER-ID") Long receiverId
    ) {
        List<NotificationResponse.Summary> summaries =
                notificationService.getRecentUnreadNotifications(receiverId);
        return ApiResponse.success(summaries);
    }


    @PatchMapping("/notifications/{notificationId}/read")
    public ApiResponse<NotificationResponse.Read> readNotificationAndRedirect(
            @RequestHeader("X-USER-ID") Long receiverId,
            @PathVariable Long notificationId
    ) {
        String result = notificationService.readNotification(receiverId, notificationId);
        NotificationResponse.Read response = new NotificationResponse.Read(result);
        return ApiResponse.success(response);
    }
}
