package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {
    public MyBot() {
        super("");
    }

    public void sendAmount(long chatId, long amount, String text) throws Exception {
        var price = CryptoPrice.spotPrice(text);
        sendMessage(chatId, text + " " + amount/price.getAmount().doubleValue());
    }

    public void sendPicture(long chatId, String name) throws Exception {
        var photo = getClass().getClassLoader().getResourceAsStream(name);
        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, name));
        execute(message);
    }
    public void sendPrice(long chatId, String text) throws Exception {
        var price = CryptoPrice.spotPrice(text);
        sendMessage(chatId, text + " price: " + price.getAmount().doubleValue());
    }

    public void sendMessage(long chatId, String text) throws Exception {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        execute(message);
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chat_id = update.getMessage().getChatId();
        var message_text = update.getMessage().getText();
        try {
            if (message_text.equals("/start")) {
                sendMessage(chat_id, "Hello!");
            } else if (message_text.equals("btc")) {
                sendPicture(chat_id, "bitcoin.png");
                sendPrice(chat_id, message_text);
            } else if (message_text.contains("btc")) {
	        var number = message_text.replaceFirst("btc ", "");
                var amount = Long.parseLong(number);
                sendAmount(chat_id, amount, "BTC");
            } else if (message_text.equals("eth")) {
                sendPicture(chat_id, "eth.jpg");
                sendPrice(chat_id, message_text);
            } else if (message_text.contains("eth")) {
                var number = message_text.replaceFirst("eth ", "");
                var amount = Long.parseLong(number);
                sendAmount(chat_id, amount, "ETH");
            } else if (message_text.equals("doge")) {
                sendPicture(chat_id, "doge.jpg");
                sendPrice(chat_id, "DOGE");
            } else if (message_text.contains("doge")) {
                var number = message_text.replaceFirst("doge ", "");
                var amount = Long.parseLong(number);
                sendAmount(chat_id, amount, "DOGE");
            } else if (message_text.equals("/all")) {
                sendPrice(chat_id, "BTC");
                sendPrice(chat_id, "ETH");
                sendPrice(chat_id, "DOGE");
            } else if (Long.getLong(message_text) > 0) {
                var amount = Long.getLong(message_text);
                sendAmount(chat_id, amount,"BTC");
                sendAmount(chat_id, amount,"ETH");
                sendAmount(chat_id, amount,"DOGE");
            } else {
                sendMessage(chat_id, "Unknown command!");
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    @Override
    public String getBotUsername() {
        return "bot";
    }
}
