package com.bankapp.cards.service;

import com.bankapp.cards.dto.CardsDto;

import java.util.List;

public interface ICardsService {
    /**
     *
     * @param cardsDto to create a card
     */
    void createCard(CardsDto cardsDto);

    /**
     *
     * @param cardNumber
     * @return card details
     */
    CardsDto fetchCard(String cardNumber);

    /**
     *
     * @param mobileNumber
     * @return All card from a given user mobile number
     */
    List<CardsDto> fetchAllCardsFromMobileNumber(String mobileNumber);

    /**
     *
     * @param cardsDto
     * @return boolean to indicate if the card was updated or not
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param cardNumber
     * @return boolean to indicate if the card was deleted or not
     */
    boolean deleteCard(String cardNumber);
}
