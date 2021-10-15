package com.nekromant.finance;

import com.nekromant.finance.commands.MentoringReviewCommand;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

import static com.nekromant.finance.contants.MessageContants.UNKNOWN_COMMAND;


@Component
public class FinanceManagerBot extends TelegramLongPollingCommandBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public FinanceManagerBot(List<MentoringReviewCommand> allCommands) {
        super();
        allCommands.forEach(this::register);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @SneakyThrows
    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
//            if (!(update.getMessage().getChatId().toString().equals(specialChatService.getFamilyChatId()) ||
//                    update.getMessage().getChatId().toString().equals(specialChatService.getReportsChatId()))) {
//                sendMessage(update);
//            }
        } else if (update.hasCallbackQuery()) {

//            String callBackData = update.getCallbackQuery().getData();
//            SendMessage message = new SendMessage();
//            SendMessage messageForMentors = new SendMessage();
//            messageForMentors.setChatId(specialChatService.getMentorsChatId());
//
//            if (callBackData.startsWith(CallBack.APPROVE.getAlias())) {
//                Long reviewId = Long.parseLong(callBackData.split(" ")[1]);
//                int timeSlot = Integer.parseInt(callBackData.split(" ")[2]);
//                ReviewRequest review = reviewRequestRepository.findById(reviewId).orElseThrow(InvalidParameterException::new);
//                review.setBookedDateTime(LocalDateTime.of(review.getDate(), LocalTime.of(timeSlot, 0)));
//                review.setMentorUserName(update.getCallbackQuery().getFrom().getUserName());
//                reviewRequestRepository.save(review);
//                message.setChatId(review.getStudentChatId());
//
//                message.setText(String.format(REVIEW_BOOKED, review.getMentorUserName(),
//                        review.getBookedDateTime().format(defaultDateTimeFormatter()), review.getTitle()));
//
//                messageForMentors.setText(String.format(REVIEW_APPROVED, update.getCallbackQuery().getFrom().getUserName(),
//                        review.getStudentUserName(), review.getBookedDateTime().format(defaultDateTimeFormatter())));
//                deleteMessageMarkUp(review.getPollMessageId(), specialChatService.getMentorsChatId());
//            }
//            if (callBackData.startsWith(CallBack.DENY.getAlias())) {
//                Long reviewId = Long.parseLong(callBackData.split(" ")[1]);
//
//                ReviewRequest review = reviewRequestRepository.findById(reviewId).orElseThrow(InvalidParameterException::new);
//                message.setChatId(review.getStudentChatId());
//                message.setText(String.format(NOBODY_CAN_MAKE_REVIEW, review.getDate().format(defaultDateFormatter())) +
//                        review.getTimeSlots().stream()
//                                .map(String::valueOf)
//                                .collect(Collectors.joining(":00, ")) + ":00" + "\n");
//
//                reviewRequestRepository.deleteById(reviewId);
//
//                messageForMentors.setText(String.format(SOMEBODY_DENIED_REVIEW, update.getCallbackQuery().getFrom().getUserName(),
//                        review.getStudentUserName()));
//                deleteMessageMarkUp(review.getPollMessageId(), specialChatService.getMentorsChatId());
//            }
//            try {
//                execute(message);
//                execute(messageForMentors);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    private void sendMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(UNKNOWN_COMMAND);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        execute(message);
    }
}
