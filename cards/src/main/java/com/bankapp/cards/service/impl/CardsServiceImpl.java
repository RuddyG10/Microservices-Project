package com.bankapp.cards.service.impl;

import com.bankapp.cards.dto.CardsDto;
import com.bankapp.cards.entity.Cards;
import com.bankapp.cards.mapper.CardsMapper;
import com.bankapp.cards.repository.CardsRepository;
import com.bankapp.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     * @param cardsDto to create a card
     */
    @Override
    public void createCard(CardsDto cardsDto) {
        Cards cards = CardsMapper.mapToCards(cardsDto,new Cards());
        Optional<Cards> optionalCards = cardsRepository.findByCardNumber(cardsDto.getCardNumber());
        if (optionalCards.isPresent()){
            //TODO Error throwing logic
        }
        Cards savedCards = cardsRepository.save(cards);
    }

    /**
     * @param cardNumber
     * @return card details
     */
    @Override
    public CardsDto fetchCard(String cardNumber) {
        return null;
    }

    /**
     * @param mobileNumber
     * @return All card from a given user mobile number
     */
    @Override
    public List<CardsDto> fetchAllCardsFromMobileNumber(String mobileNumber) {
        return null;
    }

    /**
     * @param cardsDto
     * @return boolean to indicate if the card was updated or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        return false;
    }

    /**
     * @param cardNumber
     * @return boolean to indicate if the card was deleted or not
     */
    @Override
    public boolean deleteCard(String cardNumber) {
        return false;
    }
}
