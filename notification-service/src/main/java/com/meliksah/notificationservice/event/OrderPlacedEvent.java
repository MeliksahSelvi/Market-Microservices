package com.meliksah.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author mselvi
 * @Created 02.08.2023
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {

    private String orderNumber;
}
