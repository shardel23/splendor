package main.java.business.entities;

import main.java.business.Utils;
import main.java.business.enums.Color;
import main.java.business.enums.Level;
import main.java.business.exceptions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Player {
    // TODO: implement getters (get wallet, get cards in hand, get reserved cards...) so player can review it's status

    private static final int MAX_GOLDEN = 3;

    private String name;
    private int id;
    private Date dateOfBirth;

    private Wallet wallet;
    private List<Card> boughtCards;
    private List<Card> goldenCards;
    private int victoryPoints;
    private List<Royal> royalsAchieved;

    private void initializePlayerHand(){
        wallet = new Wallet();
        boughtCards = new ArrayList<>();
        goldenCards = new ArrayList<>(MAX_GOLDEN);
        victoryPoints = 0;
        royalsAchieved = new ArrayList<>();
    }

    public Player(String name, int id, Date Bday){
        this.name = name;
        this.id = id;
        dateOfBirth = Bday;
        initializePlayerHand();
    }

    /*
    *   Play turn
    *       "choose action": 1. Buy Card    2. Take chips from bank     3. Reserve card
     */

    public void buyCardFromBoard(Board board, Level level, int index) throws CantPayPriceException{
        Card toBuy = board.getCard(level, index);
        Price cardPrice = toBuy.getPrice();
        // TODO: Add chips back to the bank
        wallet.pay(cardPrice);
        boughtCards.add(toBuy);
        wallet.increaseBonusByOne(toBuy.getColorBonus());
        board.takeCard(level, index);
    }

    public void buyCardFromHand(int index) throws CantPayPriceException {
        Card toBuy = goldenCards.get(index);
        Price cardPrice = toBuy.getPrice();
        // TODO: Add chips back to the bank
        wallet.pay(cardPrice);
        boughtCards.add(toBuy);
        wallet.increaseBonusByOne(toBuy.getColorBonus());

        goldenCards.remove(index);
    }

    public void reserveGoldenCardFromDeck(Board board, Level level) throws EmptyDeckException, MissingChipsInBankException {
        Deck deck = board.getCardDecks().get(level);
        Card cardToGet = deck.draw();
        board.getBank().takeChips(Color.GOLD, 1);
        goldenCards.add(cardToGet);
        wallet.addChips(Color.GOLD, 1);
    }

    public void reserveGoldenCardFromBoard(Board board, Level level, int index) throws MissingChipsInBankException {
        Card toReserve = board.getCard(level, index);
        board.getBank().takeChips(Color.GOLD, 1);
        goldenCards.add(toReserve);
        board.takeCard(level, index);
        wallet.addChips(Color.GOLD, 1);
    }

    public void takeChips(Board board, String chipsToTake)
            throws IllegalTakeChipsException, MissingChipsInBankException, MoreThanTenChipsException {
        Map<Color, Integer> chipsToTakeMap = Utils.createAmountsMap(chipsToTake);
        int numOfChips = chipsToTakeMap.values().stream().reduce(0, Integer::sum);
        ValidateLegalChipsTake(board, chipsToTakeMap, numOfChips);
        for (Map.Entry<Color, Integer> chipAmountPair: chipsToTakeMap.entrySet()) {
            TakeChipsFromBank(board.getBank(), chipAmountPair.getKey(), chipAmountPair.getValue());
        }
    }

    private void TakeChipsFromBank(Bank bank, Color color, Integer amount) throws MissingChipsInBankException {
        bank.takeChips(color, amount);
        wallet.addChips(color, amount);
    }

    private void ValidateLegalChipsTake(Board board, Map<Color, Integer> chipsToTakeMap, int numOfChips)
            throws IllegalTakeChipsException, MissingChipsInBankException, MoreThanTenChipsException {
        // can't take golden chips without reserving a card
        if (chipsToTakeMap.getOrDefault(Color.GOLD, 0) > 0) {
            throw new IllegalTakeChipsException("You can't take golden chips from the b unless you " +
                    "reserve a card to your hand.");
        }
        Bank bank = board.getBank();
        switch (numOfChips) {
            case 3: { ValidateThree(bank, chipsToTakeMap); break; }
            case 2: { ValidateTwo(bank, chipsToTakeMap); break; }
            case 1: { break; }
            default: throw new IllegalTakeChipsException("You must take between 1 to 3 chips.");
        }
        if (wallet.getWalletSize() > Wallet.MAX_CHIPS - numOfChips) { throw new MoreThanTenChipsException(); }
    }

    private void ValidateTwo(Bank bank, Map<Color, Integer> chipsToTake)
            throws IllegalTakeChipsException, MissingChipsInBankException {
        for (Map.Entry<Color, Integer> colorAmountPair: chipsToTake.entrySet()) {
            // 2 chips of the same color
            if (colorAmountPair.getValue() > 1 && bank.getAmountOfColor(colorAmountPair.getKey()) < 4) {
                throw new IllegalTakeChipsException("When choosing 2 chips from same color, " +
                        "there must be at least 4 chips of that color in b.");
            }
            // desired chips not in b
            validateBankHasChips(bank, colorAmountPair);
        }
    }

    private void ValidateThree(Bank bank,  Map<Color, Integer> chipsToTake)
            throws IllegalTakeChipsException, MissingChipsInBankException {
        for (Map.Entry<Color, Integer> colorAmountPair: chipsToTake.entrySet()) {
            // 2 chips of the same color
            if (colorAmountPair.getValue() > 1) {
                throw new IllegalTakeChipsException("When choosing 3 chips, they must be from different colors.");
            }
            // desired chips not in b
            validateBankHasChips(bank, colorAmountPair);
        }
    }

    private void validateBankHasChips(Bank bank, Map.Entry<Color, Integer> colorAmountPair) throws MissingChipsInBankException {
        if (colorAmountPair.getValue() > bank.getAmountOfColor(colorAmountPair.getKey())) {
            throw new MissingChipsInBankException();
        }
    }

    public void chooseRoyal() throws NotImplementedException{
        throw new NotImplementedException();
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String getName() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<Card> getBoughtCards() {
        return boughtCards;
    }

    public List<Card> getGoldenCards() {
        return goldenCards;
    }
}
