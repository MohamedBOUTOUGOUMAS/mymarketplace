package com.example.mymarketplace.hexa.application.rest.controller;

import com.example.mymarketplace.hexa.application.rest.mapper.BasketMapper;
import com.example.mymarketplace.hexa.application.rest.requests.CreateBasketRequest;
import com.example.mymarketplace.hexa.application.rest.requests.UpdateBasketRequest;
import com.example.mymarketplace.hexa.application.rest.responses.TotalToPayResponse;
import com.example.mymarketplace.hexa.application.rest.responses.UpsertBasketResponse;
import com.example.mymarketplace.hexa.domain.exception.*;
import com.example.mymarketplace.hexa.domain.model.BasketItem;
import com.example.mymarketplace.hexa.domain.port.primary.BasketPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/baskets")
public class BasketController {
    @Autowired
    private BasketPort basketPort;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UpsertBasketResponse createBasket(@Valid @RequestBody CreateBasketRequest request) throws ClientNotFoundException, ProductNotFoundException, BasketAlreadyExistsException {
        List<BasketItem> basketItems = request.getBasketItems()
                .stream().map(BasketMapper.INSTANCE::requestToDomain).toList();
        return BasketMapper.INSTANCE.domainToResponse(basketPort.createBasket(basketItems, request.getClientId()));
    }

    @PutMapping("/{basketId}")
    @ResponseStatus(HttpStatus.OK)
    public UpsertBasketResponse updateBasket(@PathVariable String basketId, @Valid @RequestBody UpdateBasketRequest request) throws AccessDeniedException, BasketNotFoundException, ProductNotFoundException, InvalidArgumentException {
        if (basketId == null) {
            throw new InvalidArgumentException("BasketId must be valid");
        }
        List<BasketItem> basketItemsToAdd = request.getBasketItemsToAdd()
                .stream().map(BasketMapper.INSTANCE::requestToDomain).toList();

        List<BasketItem> basketItemsToRemove= request.getBasketItemsToRemove()
                .stream().map(BasketMapper.INSTANCE::requestToDomain).toList();

        return BasketMapper.INSTANCE.domainToResponse(basketPort.updateBasket(basketId, basketItemsToAdd, basketItemsToRemove, request.getClientId()));
    }

    @DeleteMapping("/{basketId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBasket(@PathVariable String basketId) throws BasketNotFoundException, InvalidArgumentException {
        if (basketId == null) {
            throw new InvalidArgumentException("BasketId must be valid");
        }
        basketPort.deleteBasket(basketId);
    }

    @GetMapping("/total-to-pay")
    @ResponseStatus(HttpStatus.OK)
    public TotalToPayResponse calculateTotalToPay(@RequestParam(name = "clientId", required = true) String clientId) throws BasketNotFoundException, ClientYearTurnoverNotFoundException, InvalidArgumentException {
        if (clientId == null) {
            throw new InvalidArgumentException("ClientId must be valid");
        }
        return new TotalToPayResponse(basketPort.calculateTotal(clientId));
    }
}
