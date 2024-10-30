package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {
    public MyBot() {
        super("");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);

            if (text.equals("/start")) {
                message.setText("Hello!");
            } else if (text.equals("btc")) {
                CryptoPrice price = CryptoPrice.spotPrice("BTC");
                message.setText("BTC price: " + price.getAmount().doubleValue());
            } else if (text.equals("eth")) {
                CryptoPrice price = CryptoPrice.spotPrice("ETH");
                message.setText("ETH price: " + price.getAmount().doubleValue());
            } else if (text.equals("doge")) {
                CryptoPrice price = CryptoPrice.spotPrice("DOGE");
                message.setText("DOGE price: " + price.getAmount().doubleValue());
            } else {
                message.setText("Unknown command!");
            }

            execute(message);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    @Override
    public String getBotUsername() {
        return "bot";
    }
}
