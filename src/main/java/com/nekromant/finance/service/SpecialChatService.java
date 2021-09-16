package com.nekromant.finance.service;


import com.nekromant.finance.model.SpecialChats;
import com.nekromant.finance.repository.SpecialChatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecialChatService {

    private SpecialChatsRepository specialChatsRepository;


    private String cachedMentorsChatId = "-469295898";

    private String cachedReportsChatId = "-469295898";

    @Autowired
    public SpecialChatService(SpecialChatsRepository specialChatsRepository) {
        this.specialChatsRepository = specialChatsRepository;
        List<SpecialChats> specialChats = specialChatsRepository.findAll();
        if (!specialChats.isEmpty()) {
            this.cachedMentorsChatId = specialChats.get(0).getMentorsChatId();
            this.cachedReportsChatId = specialChats.get(0).getReportChatId();
        }
    }

    public void updateMentorsChatId(String mentorsChatId) {
        List<SpecialChats> specialChatsList = specialChatsRepository.findAll();
        if (specialChatsList.isEmpty()) {
            specialChatsRepository.save(SpecialChats.builder().mentorsChatId(mentorsChatId).build());
        } else {
            SpecialChats specialChats = specialChatsList.get(0);
            specialChats.setMentorsChatId(mentorsChatId);
            specialChatsRepository.save(specialChats);
        }
        cachedMentorsChatId = mentorsChatId;
    }

    public String getMentorsChatId() {
        return this.cachedMentorsChatId;
    }

    public void updateReportsChatId(String reportsChatId) {
        List<SpecialChats> specialChatsList = specialChatsRepository.findAll();
        if (specialChatsList.isEmpty()) {
            specialChatsRepository.save(SpecialChats.builder().reportChatId(reportsChatId).build());
        } else {
            SpecialChats specialChats = specialChatsList.get(0);
            specialChats.setReportChatId(reportsChatId);
            specialChatsRepository.save(specialChats);
        }
        cachedReportsChatId = reportsChatId;
    }

    public String getReportsChatId() {
        return this.cachedReportsChatId;
    }
}
