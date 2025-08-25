package com.example.mymarketplace.hexa.application.rest.controller;

import com.example.mymarketplace.hexa.application.rest.mapper.BasketItemMapper;
import com.example.mymarketplace.hexa.application.rest.mapper.BasketMapper;
import com.example.mymarketplace.hexa.application.rest.requests.BasketItemRequest;
import com.example.mymarketplace.hexa.application.rest.requests.UpdateBasketItemRequest;
import com.example.mymarketplace.hexa.application.rest.responses.UpsertBasketResponse;
import com.example.mymarketplace.hexa.domain.exception.BasketItemNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.BasketNotFoundException;
import com.example.mymarketplace.hexa.domain.exception.InvalidArgumentException;
import com.example.mymarketplace.hexa.domain.exception.ProductNotFoundException;
import com.example.mymarketplace.hexa.domain.port.primary.BasketItemPort;
import com.example.mymarketplace.hexa.domain.port.primary.BasketPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/basketItems")
public class BasketItemController {

    @Autowired
    private BasketItemPort basketItemPort;

    @Autowired
    private BasketPort basketPort;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public UpsertBasketResponse AddItem(@Valid @RequestBody BasketItemRequest request) throws BasketNotFoundException, InvalidArgumentException, ProductNotFoundException {
        if (request.getBasketId() == null) {
            throw new InvalidArgumentException("basketId must be not null");
        }
        var basketItemCreated = basketItemPort.create(request.getBasketId(), BasketItemMapper.INSTANCE.requestToDomain(request));
        return BasketMapper.INSTANCE.domainToResponse(basketPort.findByBasketItemId(basketItemCreated.getId()));
    }

    @DeleteMapping("/{basketItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable String basketItemId) throws BasketNotFoundException, BasketItemNotFoundException {
        basketItemPort.delete(basketItemId);
    }

    @PatchMapping("/{basketItemId}")
    @ResponseStatus(HttpStatus.OK)
    public UpsertBasketResponse patchBasketItem(@PathVariable String basketItemId, @Valid @RequestBody UpdateBasketItemRequest request) throws BasketItemNotFoundException, BasketNotFoundException {
        basketItemPort.update(basketItemId, request.getQuantity());
        return BasketMapper.INSTANCE.domainToResponse(basketPort.findByBasketItemId(basketItemId));
    }

}
