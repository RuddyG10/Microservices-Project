package com.bankapp.cards.service.impl;

import com.bankapp.cards.constants.CardsConstants;
import com.bankapp.cards.dto.CardsDto;
import com.bankapp.cards.entity.Cards;
import com.bankapp.cards.exception.CardAlreadyExistsException;
import com.bankapp.cards.exception.ResourceNotFoundException;
import com.bankapp.cards.mapper.CardsMapper;
import com.bankapp.cards.repository.CardsRepository;
import com.bankapp.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     *
     * @param mobileNumber of the user to create one card
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given Mobile Number "+mobileNumber);
        }
        Cards savedCards = cardsRepository.save(createNewCard(mobileNumber));
    }


    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0L);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param cardNumber
     * @return card details
     */
    @Override
    public CardsDto fetchCard(String cardNumber) {
        Cards cards = cardsRepository.findByCardNumber(cardNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","cardNumber",cardNumber)
        );
        CardsDto cardsDto = CardsMapper.mapToCardsDto(cards,new CardsDto());

        return cardsDto;
    }

    /**
     * @param mobileNumber
     * @return All card from a given user mobile number
     */
    @Override
    public List<CardsDto> fetchAllCardsFromMobileNumber(String mobileNumber) {
        List<Cards> cardsList = cardsRepository.findAllByMobileNumber(mobileNumber);
        if (cardsList.isEmpty()){
            throw new ResourceNotFoundException("Cards","mobileNumber",mobileNumber);
        }
        List<CardsDto> cardsDtoList = new ArrayList<>();
        cardsList.forEach((cards) ->{
            cardsDtoList.add(CardsMapper.mapToCardsDto(cards,new CardsDto()));
        });

        return cardsDtoList;
    }

    /**
     * @param cardsDto
     * @return boolean to indicate if the card was updated or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        boolean isUpdated = false;

        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card","cardNumber", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto,cards);
        cardsRepository.save(cards);
        isUpdated = true;

        return isUpdated;
    }

    /**
     * @param cardNumber
     * @return boolean to indicate if the card was deleted or not
     */
    @Override
    public boolean deleteCard(String cardNumber) {
        Cards cards = cardsRepository.findByCardNumber(cardNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","cardNumber", cardNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
